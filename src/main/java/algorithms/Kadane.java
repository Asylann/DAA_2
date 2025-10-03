package algorithms;

import metrics.PerformanceTracker;

/**
 * Implementation of Kadane's Algorithm for finding the maximum subarray sum
 * with position tracking and performance metrics.
 *
 * Time Complexity: Θ(n) where n is the length of the input array
 * Space Complexity: O(1) constant extra space
 */
public class Kadane {
    private int startIndex;
    private int endIndex;

    public Kadane() {
        this.startIndex = -1;
        this.endIndex = -1;
    }

    public int findMaxSubarraySum(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        if (arr.length == 0) {
            this.startIndex = -1;
            this.endIndex = -1;
            return 0;
        }

        int maxSum = arr[0];    
        int currentSum = arr[0];  
        int tempStart = 0;  
        this.startIndex = 0;
        this.endIndex = 0;


        for (int i = 1; i < arr.length; i++) {
            if (currentSum < 0) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum += arr[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                this.startIndex = tempStart;
                this.endIndex = i;
            }
        }

        return maxSum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
