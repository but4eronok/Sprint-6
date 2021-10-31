package com.kirill.mvc

import com.kirill.mvc.service.Person
import com.kirill.mvc.service.PersonServiceImpl
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class MvcControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var personServiceImpl: PersonServiceImpl

    @BeforeAll
    fun addPerson() {
        personServiceImpl.add(Person("Kirill", "Moscow"))
        personServiceImpl.add(Person("Anna", "Odintsovo"))
    }

    @Test
    fun `add a person`() {
        mockMvc.perform(
            post("/app/add")
                .param("name", "Ivan")
                .param("address", "Ivanovo")
        )
            .andExpect(view().name("redirect:/app/list"))
            .andExpect(content().string(containsString("Ivan")))
    }

    @Test
    fun `should edit a person`() {
        mockMvc.perform(
            post("/app/1/edit")
                .param("name", "Anna")
                .param("address", "Moscow")
        )
            .andExpect(view().name("redirect:/app/list"))
            .andExpect(content().string(containsString("Moscow")))
    }

    @Test
    fun `should get persons list`() {
        mockMvc.perform(get("/app/list"))
            .andExpect(view().name("all_persons"))
            .andExpect(content().string(containsString("Kirill")))
    }

    @Test
    fun `should view a person`() {
        mockMvc.perform(get("/app/1/view"))
            .andExpect(view().name("record"))
            .andExpect(content().string(containsString("Anna")))
    }

    @Test
    fun `should delete a person`() {
        mockMvc.perform(get("/app/1/delete"))
            .andExpect(view().name("redirect:/app/list"))
    }
}