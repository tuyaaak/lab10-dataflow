package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusTest {

    @Test
    @DisplayName("All-defs: streak reset, best update, score 49 and 54")
    void testBonusWithStreakReset() {
        int[] attendance = {1, 1, 1, 1, 1, 0, 1, 1};

        int result = DataFlowTargets.bonus(attendance, 9);

        assertEquals(3, result);
    }

    @Test
    @DisplayName("All-defs: score 51 to 56")
    void testBonusWithThresholdMet() {
        int[] attendance = {1, 1};

        int result = DataFlowTargets.bonus(attendance, 1);

        assertEquals(5, result);
    }
}