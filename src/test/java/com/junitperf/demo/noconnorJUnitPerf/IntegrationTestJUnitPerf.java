package com.junitperf.demo.noconnorJUnitPerf;

import com.github.noconnor.junitperf.JUnitPerfAsyncRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.data.TestContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTestJUnitPerf {

    @Autowired
    private
    TestRestTemplate restTemplate;

    @Rule
    public JUnitPerfAsyncRule rule = new JUnitPerfAsyncRule();

    private static ExecutorService pool;

    @BeforeClass
    public static void setup() {
        pool = Executors.newFixedThreadPool(100);
    }

    @AfterClass
    public static void teardown() {
        pool.shutdownNow();
    }

    @Test
    @JUnitPerfTest(durationMs = 10_000, warmUpMs = 1_000, maxExecutionsPerSecond = 100)
    public void assertSlowAnswerText() {
        ResponseEntity entity = restTemplate.getForEntity("/slow", String.class);
        Assert.assertEquals(200, entity.getStatusCode().value());
        Assert.assertEquals("Slow answer", entity.getBody().toString());
    }

    @Test
    @JUnitPerfTest(durationMs = 10_000, warmUpMs = 1_000, maxExecutionsPerSecond = 100)
    public void assertErrorAnswerText() {
        ResponseEntity entity = restTemplate.getForEntity("/errorResponse", String.class);
        Assert.assertEquals(400, entity.getStatusCode().value());
    }

    @Test
    @JUnitPerfTest(durationMs = 10_000, warmUpMs = 1_000, maxExecutionsPerSecond = 100)
    public void assertQuickAnswerText() {
        ResponseEntity entity = restTemplate.getForEntity("/quick", String.class);
        Assert.assertEquals(200, entity.getStatusCode().value());
        Assert.assertEquals("Quick answer", entity.getBody().toString());
    }
}
