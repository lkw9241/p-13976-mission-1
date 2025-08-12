package com.ll.domain.wiseSaying.wiseSaying.service

import com.ll.WiseSaying

class WiseSayingService {

    private var lastId = 0
    private val wiseSayings = mutableListOf<WiseSaying>()

    fun write(content: String, author: String): WiseSaying {
        val id = ++lastId

        return WiseSaying(id, content, author).apply {   //apply 사용법 확인해보기.
            wiseSayings.add(this)
        }
    }

    fun isEmpty(): Boolean {
        return wiseSayings.isEmpty()
    }

    fun findAll(): List<WiseSaying> {
        return wiseSayings
    }

    fun findById(id: Int): WiseSaying? {
        return wiseSayings.first { it.id == id }
    }

    fun delete(wiseSaying: WiseSaying) {
        wiseSayings.remove(wiseSaying)
    }

    fun modify(wiseSaying: WiseSaying, author: String, content: String) {
        wiseSaying.modify(content, author)
    }
}