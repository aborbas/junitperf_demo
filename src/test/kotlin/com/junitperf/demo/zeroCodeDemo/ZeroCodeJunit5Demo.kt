import com.junitperf.demo.zeroCodeDemo.IntegrationTest
import org.jsmart.zerocode.core.domain.LoadWith
import org.jsmart.zerocode.core.domain.TestMapping
import org.jsmart.zerocode.core.domain.TestMappings
import org.jsmart.zerocode.jupiter.extension.ParallelLoadExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(ParallelLoadExtension::class)
@LoadWith("load_generation.properties")
class ZeroCodeJunit5Demo {

    @Test
    @DisplayName("testing IntegrationTest scenarios")
    @TestMappings(
            TestMapping(testClass = IntegrationTest::class, testMethod = "assertSlowAnswerText"),
            TestMapping(testClass = IntegrationTest::class, testMethod = "failedTest"),
            TestMapping(testClass = IntegrationTest::class, testMethod = "assertQuickAnswerText"))
    fun testLoad() { /* No code needed here */
    }

}
