package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("DU: invalid attendance at line 32")
    void testBonusNullAttendance() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(null, 1)
        );
    }

    @Test
    @DisplayName("DU: negative threshold at line 32")
    void testBonusNegativeThreshold() {
        int[] attendance = {1, 1};

        assertThrows(
            IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(attendance, -1)
        );
    }

    @Test
    @DisplayName("DU: empty attendance, score 49 to 56")
    void testBonusEmptyAttendance() {
        int[] attendance = {};

        int result = DataFlowTargets.bonus(attendance, 1);

        assertEquals(0, result);
    }

    @Test
    @DisplayName("DU: score 51 to 54 to 56")
    void testBonusFullStreak() {
        int[] attendance = {1, 1, 1, 1, 1};

        int result = DataFlowTargets.bonus(attendance, 5);

        assertEquals(8, result);
    }
        
    @Test
    @DisplayName("M2: present and streak have different values")
    void testBonusWrongBestUpdate() {
        int[] attendance = {1, 0, 1, 1, 1, 1};

        int result = DataFlowTargets.bonus(attendance, 9);

        assertEquals(0, result);
    }
}