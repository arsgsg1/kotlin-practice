package com.kotlin.arsgsg.board

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlin.arsgsg.application.dto.CreateBoardDto
import com.kotlin.arsgsg.domain.Board
import com.kotlin.arsgsg.infra.BoardRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class UpdateBoardTest @Autowired constructor(
    val mockMvc: MockMvc,
    val boardRepository: BoardRepository
) : BaseBoardApiTest() {

    @BeforeEach
    fun beforeEach() {
        boardRepository.save(Board(title = "title", content = "content"))
    }

    @Test
    fun 보드_수정시_수정된_내용을_반환한다() {
        //given
        val om = ObjectMapper()
        val dto = CreateBoardDto(title = "newTitle", content = "newContent")
        //when, then
        mockMvc.perform(
            put("/boards/1").content(om.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value(dto.title))
            .andExpect(jsonPath("$.content").value(dto.content))
            .andDo(print())
    }
}