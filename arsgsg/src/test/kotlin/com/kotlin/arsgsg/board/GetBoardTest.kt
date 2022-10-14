package com.kotlin.arsgsg.board

import com.kotlin.arsgsg.domain.Board
import com.kotlin.arsgsg.infra.BoardRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class GetBoardTest @Autowired constructor(
    val mockMvc: MockMvc,
    val boardRepository: BoardRepository
) : BaseBoardApiTest() {

    @BeforeEach
    fun beforeEach() {
        for (i in 1..6){
            boardRepository.save(Board(title = "title$i", content = "content$i"))
        }
    }

    @Test
    fun 보드_전체_조회시_리스트와_200을_반환한다() {
        mockMvc.perform(get("/boards"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.contents").isNotEmpty)
            .andExpect(jsonPath("$.hasNext").value(true))
            .andDo(print())
    }

    @Test
    fun 보드_개별_조회시__개별보드와_200을_반환한다(){
        mockMvc.perform(get("/boards/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").isString)
            .andExpect(jsonPath("$.content").isString)
            .andExpect(jsonPath("$.id").isNumber)
            .andDo(print())
    }
}