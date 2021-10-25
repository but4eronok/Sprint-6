package ru.sber.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import ru.sber.services.FirstService
import ru.sber.services.FourthService
import ru.sber.services.SecondService

@Configuration
@ComponentScan("ru.sber.services")
class ServicesConfig {
    @Bean
    fun service(): FirstService {
        return FirstService()
    }

    @Bean
    fun secondService() : SecondService {
        return SecondService()
    }
}

@Configuration
@ComponentScan("ru.sber.services")
class AnotherServicesConfig {
    @Bean
    fun anotherServices() : FourthService {
        return FourthService()
    }
}