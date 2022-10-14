package com.kotlin.arsgsg.application.dto

import com.kotlin.arsgsg.domain.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Slice
import java.util.stream.Collectors

data class CreateBoardDto(val title: String, val content: String)
data class GetBoardDetailDto(val id: Long, val title: String, val content: String)
data class GetBoardDto(val id: Long, val title: String)
data class GetBoardListDto(val contents: List<GetBoardDto>, val hasNext: Boolean)

fun fromEntity(board: Board): GetBoardDetailDto? {
    return board.id?.let {
        GetBoardDetailDto(
            id = it,
            title = board.title,
            content = board.content
        )
    }
}

fun fromEntity(slice: Page<Board>): GetBoardListDto {
    return GetBoardListDto(
        slice.stream().map { it.id?.let { it1 -> GetBoardDto(id = it1, title = it.title) } }
            .collect(Collectors.toList()), slice.hasNext())
}