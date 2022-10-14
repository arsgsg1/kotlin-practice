package com.kotlin.arsgsg.controller

import com.kotlin.arsgsg.application.BoardService
import com.kotlin.arsgsg.application.dto.CreateBoardDto
import com.kotlin.arsgsg.application.dto.GetBoardDetailDto
import com.kotlin.arsgsg.application.dto.GetBoardListDto
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BoardController(val boardService: BoardService) {
    @PostMapping("/boards", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createBoard(@RequestBody dto: CreateBoardDto): ResponseEntity<Long?> {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.createBoard(dto))
    }

    @GetMapping("/boards/{boardId}")
    fun getBoardById(@PathVariable boardId: Long): ResponseEntity<GetBoardDetailDto> {
        return ResponseEntity.ok(boardService.getBoardById(boardId))
    }

    @GetMapping("/boards")
    fun getAllBoards(
        @RequestParam(required = true, defaultValue = "0") page: Int,
        @RequestParam(required = true, defaultValue = "5") size: Int
    ): ResponseEntity<GetBoardListDto> {
        return ResponseEntity.ok(boardService.getAllBoards(PageRequest.of(page, size)))
    }

    @PutMapping("/boards/{boardId}")
    fun updateBoardById(@PathVariable boardId: Long, @RequestBody dto: CreateBoardDto)
            : ResponseEntity<GetBoardDetailDto> {
        return ResponseEntity.ok(boardService.updateBoardById(boardId = boardId, dto = dto))
    }
}