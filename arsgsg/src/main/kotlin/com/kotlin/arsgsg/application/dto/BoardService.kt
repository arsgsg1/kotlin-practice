package com.kotlin.arsgsg.application.dto

import com.kotlin.arsgsg.domain.Board
import com.kotlin.arsgsg.infra.BoardRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(val boardRepository: BoardRepository) {
    @Transactional
    fun createBoard(dto: CreateBoardDto): Long? {
        return boardRepository.save(Board(title = dto.title, content = dto.content)).id
    }

    @Transactional
    fun updateBoardById(boardId: Long, dto: CreateBoardDto): GetBoardDetailDto? {
        var existingBoard = boardRepository.findById(boardId).orElseThrow()
        existingBoard.updateBoard(dto.title, dto.content)
        return fromEntity(boardRepository.save(existingBoard))
    }

    @Transactional(readOnly = true)
    fun getBoardById(boardId: Long): GetBoardDetailDto? {
        return fromEntity(boardRepository.findById(boardId).orElseThrow())
    }

    @Transactional(readOnly = true)
    fun getAllBoards(pageRequest: PageRequest): GetBoardListDto {
        return fromEntity(boardRepository.findAll(pageRequest))
    }
}