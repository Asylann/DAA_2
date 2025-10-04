package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class KadaneTest {
    private Kadane kadane;
    private PerformanceTracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new PerformanceTracker();
        kadane = new Kadane(tracker);
    }

    // Edge Cases
    @Test
    void testEmptyArray() {
        assertEquals(0, kadane.findMaxSubarraySum(new int[]{}));
        assertEquals(-1, kadane.getStartIndex());
        assertEquals(-1, kadane.getEndIndex());
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        assertEquals(42, kadane.findMaxSubarraySum(arr));
        assertEquals(0, kadane.getStartIndex());
        assertEquals(0, kadane.getEndIndex());
    }

    // Basic Cases
    @Test
    void testBasicPositiveArray() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(15, kadane.findMaxSubarraySum(arr));
        assertEquals(0, kadane.getStartIndex());
        assertEquals(4, kadane.getEndIndex());
    }

    @Test
    void testBasicNegativeArray() {
        int[] arr = {-2, -3, -1, -5};
        assertEquals(-1, kadane.findMaxSubarraySum(arr));
        assertEquals(2, kadane.getStartIndex());
        assertEquals(2, kadane.getEndIndex());
    }

    // Special Cases
    @Test
    void testArrayWithAllZeros() {
        int[] arr = {0, 0, 0, 0};
        assertEquals(0, kadane.findMaxSubarraySum(arr));
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {3, 3, -1, 3, 3};
        assertEquals(11, kadane.findMaxSubarraySum(arr));
    }

    // Complex Cases
    @Test
    void testMixedSequence() {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, kadane.findMaxSubarraySum(arr));
        assertEquals(3, kadane.getStartIndex());
        assertEquals(6, kadane.getEndIndex());
    }

    // Performance Test with Large Input
    @Test
    void testLargeInput() {
        int size = 10000;
        int[] arr = new int[size];
        Random random = new Random(42);
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(201) - 100; 
        }
        
        tracker.startTimer();
        int result = kadane.findMaxSubarraySum(arr);
        tracker.stopTimer();
        
        assertTrue(result >= -100);
        assertTrue(tracker.getExecutionTimeMillis() < 1000);
    }
}
