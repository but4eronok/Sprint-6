package com.kirill.mvc.controllers

import com.kirill.mvc.service.Person
import com.kirill.mvc.service.PersonServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping("/api")
class RestController {
    @Autowired
    private lateinit var personServiceImpl: PersonServiceImpl

    @PostMapping("/add")
    fun addPerson(@RequestBody person: Person) {
        personServiceImpl.add(person)
    }

    @GetMapping("/list")
    fun getAllRecords(): ConcurrentHashMap<Int, Person> {
        return personServiceImpl.getAll()
    }

    @GetMapping("/{id}/view")
    fun getPerson(@PathVariable id: Int): Person? {
        return personServiceImpl.get(id)
    }

    @PostMapping("/{id}/edit")
    fun editPerson(@PathVariable id: Int, @RequestBody person: Person) {
        personServiceImpl.update(id, person)
    }

    @DeleteMapping("/{id}/delete")
    fun deletePerson(@PathVariable id: Int) {
        personServiceImpl.remove(id)
    }
}