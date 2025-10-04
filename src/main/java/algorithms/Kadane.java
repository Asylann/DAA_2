package algorithms;

import metrics.PerformanceTracker;


public class Kadane {
    private final PerformanceTracker tracker;
    private int startIndex;
    private int endIndex;

    public Kadane(PerformanceTracker tracker) {
        this.tracker = tracker;
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
        boolean allPositive = true;
        int totalSum = arr[0];
        tracker.incrementArrayAccess();
        
        for (int i = 1; i < arr.length && allPositive; i++) {
            tracker.incrementArrayAccess();
            if (arr[i] < 0) {
                allPositive = false;
            }
            totalSum += arr[i];
        }
        
        // If all numbers are positive, the entire array is the maximum subarray
        if (allPositive) {
            this.startIndex = 0;
            this.endIndex = arr.length - 1;
            return totalSum;
        }
        
        int maxSum = arr[0];
        int currentSum = arr[0];
        int tempStart = 0;
        this.startIndex = 0;
        this.endIndex = 0;
        
        tracker.incrementArrayAccess();
        
        for (int i = 1; i < arr.length; i++) {
            tracker.incrementArrayAccess(); 
            tracker.incrementComparison();  

            if (currentSum < 0) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum += arr[i];
            }

            tracker.incrementComparison();
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
