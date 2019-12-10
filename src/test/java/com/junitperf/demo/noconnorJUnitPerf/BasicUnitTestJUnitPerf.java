package com.junitperf.demo.noconnorJUnitPerf;

import com.github.noconnor.junitperf.JUnitPerfAsyncRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import org.junit.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicUnitTestJUnitPerf {

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
    public void assertTrue() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(true);
    }
}
