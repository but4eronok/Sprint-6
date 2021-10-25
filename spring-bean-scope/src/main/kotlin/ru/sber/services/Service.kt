package ru.sber.services

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("singleton")
class SingletonService {
    override fun toString(): String {
        return "singletonService"
    }
}

@Component
@Scope("prototype")
class PrototypeService {
    override fun toString(): String {
        return "prototypeService"
    }
}