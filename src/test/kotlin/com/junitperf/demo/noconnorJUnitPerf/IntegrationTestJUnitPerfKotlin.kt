package com.junitperf.demo.noconnorJUnitPerf

import com.github.noconnor.junitperf.JUnitPerfAsyncRule
import com.github.noconnor.junitperf.JUnitPerfTest
import com.github.noconnor.junitperf.JUnitPerfTestRequirement
import com.github.noconnor.junitperf.reporting.providers.ConsoleReportGenerator
import io.kotlintest.shouldBe
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTestJUnitPerfKotlin(@Autowired val restTemplate: TestRestTemplate) {

    @Rule
    var rule = JUnitPerfAsyncRule(ConsoleReportGenerator())

    private var pool: ExecutorService? = null

    @BeforeClass
    fun setup() {
        pool = Executors.newFixedThreadPool(100)
    }

    @AfterClass
    fun teardown() {
        pool!!.shutdownNow()
    }

    @Test
    @JUnitPerfTest(durationMs = 10_000, warmUpMs = 1_000, maxExecutionsPerSecond = 100)
    fun `Assert slow answer text`() {
        val entity = restTemplate.getForEntity<String>("/slow")
        entity.statusCode.value() shouldBe 200
        entity.body shouldBe "Slow answer"

    }

    @Test
    @JUnitPerfTest(durationMs = 10_000, warmUpMs = 1_000, maxExecutionsPerSecond = 100)
    fun `Assert normal answer text`() {
        val entity = restTemplate.getForEntity<String>("/errorResponse")
        entity.statusCode.value() shouldBe 400
    }

    @Test
    @JUnitPerfTest(durationMs = 10_000, warmUpMs = 1_000, maxExecutionsPerSecond = 100)
    fun `Assert quick answer text`() {
        val entity = restTemplate.getForEntity<String>("/quick")
        entity.statusCode.value() shouldBe 200
        entity.body shouldBe "Quick answer"

    }

}
