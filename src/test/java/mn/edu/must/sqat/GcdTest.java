package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GcdTest {

    @Test
    @DisplayName("GCD: x 13->20, y 13->15 (false)")
    void testGcdWithoutLoop() {
        int result = DataFlowTargets.gcd(7, 0);

        assertEquals(7, result);
    }

    @Test
    @DisplayName("GCD: x 13->16, 17->16/20; y 13->15/16/17, 18->15/16/17; tmp 16->18")
    void testGcdWithTwoIterations() {
        int result = DataFlowTargets.gcd(12, 8);

        assertEquals(4, result);
    }

    @Test
    @DisplayName("GCD: x 13->16, 17->20; y 13->15/16/17; tmp 16->18")
    void testGcdWithZeroFirstInput() {
        int result = DataFlowTargets.gcd(0, 5);

        assertEquals(5, result);
    }
}