package com.junitperf.demo

import org.springframework.boot.test.web.client.TestRestTemplate
import com.github.noconnor.junitperf.JUnitPerfTest



class LoadTestDemo {

    val user = 10
    val iter = 5

    val restTemplate = TestRestTemplate()
    val integrationTest = IntegrationTest(restTemplate)
    //val testCase: Unit = integrationTest.`Assert slow answer text`()

}
