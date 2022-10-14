package com.kotlin.arsgsg.infra

import com.kotlin.arsgsg.domain.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long>