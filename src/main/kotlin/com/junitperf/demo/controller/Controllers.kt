package com.junitperf.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controllers {

    @GetMapping("/slow")
    fun slow(): String{
        Thread.sleep(3000)
        return "Slow answer"
    }

    @GetMapping("/normal")
    fun normal(): String{
        Thread.sleep(1500)
        return "Normal answer"
    }

    @GetMapping("/quick")
    fun quick(): String{
        Thread.sleep(100)
        return "Quick answer"
    }
}
