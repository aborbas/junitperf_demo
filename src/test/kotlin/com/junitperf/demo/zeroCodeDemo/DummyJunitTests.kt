package com.junitperf.demo.zeroCodeDemo

import org.junit.Assert
import org.junit.jupiter.api.Test

class DummyJunitTests {

    @Test
    fun dummyTest() {
        Thread.sleep(1000)
        Assert.assertTrue(true)
    }
}
