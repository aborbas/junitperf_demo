package com.junitperf.demo.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class Controllers {

    @GetMapping("/slow")
    fun slow(): String{
        Thread.sleep(100)
        return "Slow answer"
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bad request for testing errors")
    @GetMapping("/errorResponse")
    fun normal(): String{
        HttpStatus.BAD_REQUEST
        return "Normal answer"
    }

    @GetMapping("/quick")
    fun quick(): String{
        return "Quick answer"
    }
}
