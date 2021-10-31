package com.kirill.mvc.service

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class PersonServiceImpl {

    fun getAll(): ConcurrentHashMap<Int, Person> {
        return bookOfPersons
    }

    fun add(person: Person) {
        bookOfPersons[id] = person
        id++
    }

    fun get(id: Int): Person? {
        return bookOfPersons[id]
    }

    fun remove(id: Int): Person? {
        return bookOfPersons.remove(id)
    }

    fun update(id: Int, person: Person) {
        bookOfPersons[id] = person
    }


    companion object {
        private val bookOfPersons = ConcurrentHashMap<Int, Person>()
        private var id = 0
    }
}