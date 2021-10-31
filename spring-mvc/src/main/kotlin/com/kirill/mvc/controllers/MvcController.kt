package com.kirill.mvc.controllers

import com.kirill.mvc.service.Person
import com.kirill.mvc.service.PersonServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/app")
class MvcController {
    @Autowired
    private lateinit var personServiceImpl: PersonServiceImpl

    @GetMapping("/add")
    fun addRecordGet(): String {
        return "add_person"
    }

    @PostMapping("/add")
    fun addRecordPost(@ModelAttribute("record") person: Person, model: Model): String {
        personServiceImpl.add(person)
        return "redirect:/app/list"
    }

    @GetMapping("/list")
    fun getAllRecords(
        @RequestParam(required = false) id: Int?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) address: String?,
        model: Model
    ): String {
        model.addAttribute("records", personServiceImpl.getAll())
        return "all_persons"
    }

    @GetMapping("/{id}/view")
    fun getPerson(@PathVariable id: Int, model: Model): String {
        model.addAttribute("record", personServiceImpl.get(id))
        return "view_person"
    }

    @GetMapping("/{id}/edit")
    fun editPerson(@PathVariable id: Int, model: Model): String {
        model.addAttribute("record", personServiceImpl.get(id))
        return "edit_person"
    }

    @PostMapping("/{id}/edit")
    fun editForm(@PathVariable id: Int, @ModelAttribute("record") person: Person): String {
        personServiceImpl.update(id, person)
        return "redirect:/app/list"
    }

    @GetMapping("/{id}/delete")
    fun deletePerson(@PathVariable id: Int): String {
        personServiceImpl.remove(id)
        return "redirect:/app/list"
    }
}