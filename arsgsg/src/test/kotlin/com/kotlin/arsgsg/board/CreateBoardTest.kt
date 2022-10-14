package com.kotlin.arsgsg.board

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlin.arsgsg.application.dto.CreateBoardDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

class CreateBoardTest @Autowired constructor(val mockMvc: MockMvc) :
    BaseBoardApiTest() {

    @Test
    fun 보드생성_성공시_201상태코드를_반환한다() {
        var om = ObjectMapper()
        //given
        val dto = CreateBoardDto(title = "title", content = "content")
        //when, then
        mockMvc.perform(
            post("/boards")
                .content(om.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated).andDo(print())
    }
}