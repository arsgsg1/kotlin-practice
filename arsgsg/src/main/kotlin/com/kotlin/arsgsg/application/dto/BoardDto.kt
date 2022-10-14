package com.kotlin.arsgsg.application.dto

import com.kotlin.arsgsg.domain.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Slice
import java.time.LocalDateTime
import java.util.stream.Collectors

data class CreateBoardDto(val title: String, val content: String)
data class GetBoardDetailDto(
    val id: Long,
    val title: String,
    val content: String,
    val createAt: LocalDateTime
)

data class GetBoardDto(val id: Long, val title: String, val createAt: LocalDateTime)
data class GetBoardListDto(val contents: List<GetBoardDto?>, val hasNext: Boolean)

fun fromEntity(board: Board): GetBoardDetailDto? {
    return board.id?.let {
        board.createAt?.let { it1 ->
            GetBoardDetailDto(
                id = it,
                title = board.title,
                content = board.content,
                createAt = it1
            )
        }
    }
}

fun fromEntity(slice: Page<Board>): GetBoardListDto {
    return GetBoardListDto(
        slice.stream().map {
            it.id?.let { it1 ->
                it.createAt?.let { it2 ->
                    GetBoardDto(
                        id = it1, title = it.title,
                        it2
                    )
                }
            }
        }
            .collect(Collectors.toList()), slice.hasNext())
}