package com.junitperf.demo.zeroCodeDemo

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun assertSlowAnswerText() {
        val entity = restTemplate.getForEntity<String>("/slow")
        entity.statusCode.value() shouldBe 200
        entity.body shouldBe "Slow answer"
    }

    @Test
    fun failedTest() {
        val entity = restTemplate.getForEntity<String>("/errorResponse")
        // will always fail, just to put failed tests in log
        entity.statusCode.value() shouldBe 200
    }

    @Test
    fun assertQuickAnswerText() {
        val entity = restTemplate.getForEntity<String>("/quick")
        entity.statusCode.value() shouldBe 200
        entity.body shouldBe "Quick answer"
    }

}
