package com.kotlin.arsgsg.domain

import javax.persistence.*

@Table(name = "board")
@Entity
class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "title")
    var title: String,
    @Column(name = "content")
    var content: String
) : BaseEntity() {
    fun updateBoard(title: String, content: String) {
        this.title = title
        this.content = content
    }
}