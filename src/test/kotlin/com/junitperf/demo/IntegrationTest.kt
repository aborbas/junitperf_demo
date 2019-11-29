package com.junitperf.demo

import com.github.noconnor.junitperf.JUnitPerfAsyncRule
import com.github.noconnor.junitperf.JUnitPerfTest
import com.github.noconnor.junitperf.JUnitPerfTestRequirement
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
class IntegrationTest(@Autowired val restTemplate: TestRestTemplate) {

    @Rule
    var rule = JUnitPerfAsyncRule()

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
    @JUnitPerfTest(durationMs = 125_000, rampUpPeriodMs = 2_000, warmUpMs = 10_000, maxExecutionsPerSecond = 1000)
    @JUnitPerfTestRequirement(percentiles = "90:7,95:7,98:7,99:8", executionsPerSec = 10_000, allowedErrorPercentage = 0.1F)
    fun `Assert slow answer text`() {
        val entity = restTemplate.getForEntity<String>("/slow")
        entity.statusCode.value() shouldBe 200
        entity.body shouldBe "Slow answer"

    }

    @Test
    @JUnitPerfTest(durationMs = 125_000, rampUpPeriodMs = 2_000, warmUpMs = 10_000, maxExecutionsPerSecond = 1000)
    @JUnitPerfTestRequirement(percentiles = "90:7,95:7,98:7,99:8", executionsPerSec = 10_000, allowedErrorPercentage = 0.1F)
    fun `Assert normal answer text`() {
        val entity = restTemplate.getForEntity<String>("/normal")
        entity.statusCode.value() shouldBe 200
        entity.body shouldBe "Normal answer"

    }

    @Test
    @JUnitPerfTest(durationMs = 125_000, rampUpPeriodMs = 2_000, warmUpMs = 10_000, maxExecutionsPerSecond = 1000)
    @JUnitPerfTestRequirement(percentiles = "90:7,95:7,98:7,99:8", executionsPerSec = 10_000, allowedErrorPercentage = 0.1F)
    fun `Assert quick answer text`() {
        val entity = restTemplate.getForEntity<String>("/quick")
        entity.statusCode.value() shouldBe 200
        entity.body shouldBe "Quick answer"

    }

}
