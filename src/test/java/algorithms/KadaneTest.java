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

    // Sorted Data Tests
    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(55, kadane.findMaxSubarraySum(arr));
        assertEquals(0, kadane.getStartIndex());
        assertEquals(9, kadane.getEndIndex());
    }

    @Test
    void testNearlySortedArrayPositive() {
        // Array is mostly sorted but with a few elements out of place
        int[] arr = {1, 2, 4, 3, 5, 7, 6, 8, 10, 9};
        assertEquals(55, kadane.findMaxSubarraySum(arr));
        assertEquals(0, kadane.getStartIndex());
        assertEquals(9, kadane.getEndIndex());
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

    // Performance test with large sorted array
    @Test
    void testLargeSortedArray() {
        int size = 10000;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        
        tracker.startTimer();
        int result = kadane.findMaxSubarraySum(arr);
        tracker.stopTimer();
        
        assertEquals((size - 1) * size / 2, result); // Sum of arithmetic sequence
        assertEquals(0, kadane.getStartIndex());
        assertEquals(size - 1, kadane.getEndIndex());
        assertTrue(tracker.getExecutionTimeMillis() < 1000);
    }

    // Performance test with large nearly-sorted array
    @Test
    void testLargeNearlySortedArray() {
        int size = 10000;
        int[] arr = new int[size];
        // Create sorted array with 5% elements swapped
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        Random random = new Random(42);
        int swaps = size / 20; // 5% of elements
        for (int i = 0; i < swaps; i++) {
            int pos1 = random.nextInt(size);
            int pos2 = random.nextInt(size);
            // Swap
            int temp = arr[pos1];
            arr[pos1] = arr[pos2];
            arr[pos2] = temp;
        }
        
        tracker.startTimer();
        int result = kadane.findMaxSubarraySum(arr);
        tracker.stopTimer();
        
        assertTrue(result > 0);
        assertTrue(tracker.getExecutionTimeMillis() < 1000);
    }
}
