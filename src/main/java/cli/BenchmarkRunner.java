package cli;

import algorithms.Kadane;
import metrics.PerformanceTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class BenchmarkRunner {
    private static final String RESULTS_FILE = "results.csv";
    private final Random random;
    private final PerformanceTracker tracker;

    public BenchmarkRunner() {
        this.random = new Random();
        this.tracker = new PerformanceTracker();
    }

    private int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(201) - 100; 
        }
        return arr;
    }

    public void runBenchmark(int size) {
        boolean fileExists = new File(RESULTS_FILE).exists();
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESULTS_FILE, true))) { // true for append mode
            if (!fileExists) {
                writer.println(PerformanceTracker.getCsvHeader());
            }

            System.out.printf("Running benchmark for size %d...%n", size);
            int[] arr = generateRandomArray(size);
            
            tracker.reset();
            Kadane kadane = new Kadane(tracker);
            
            tracker.startTimer();
            int maxSum = kadane.findMaxSubarraySum(arr);
            tracker.stopTimer();

            writer.println(tracker.exportToCsv(size));
            System.out.printf("Max sum: %d (from index %d to %d)%n", 
                maxSum, kadane.getStartIndex(), kadane.getEndIndex());
            System.out.printf("Time: %.3f ms, Comparisons: %d, Array Accesses: %d%n",
                tracker.getExecutionTimeMillis(),
                tracker.getComparisons(),
                tracker.getArrayAccesses());
        } catch (IOException e) {
            System.err.println("Error writing to results file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int size = 1000;
        
        if (args.length > 0) {
            try {
                size = Integer.parseInt(args[0]);
                if (size <= 0) {
                    System.err.println("Size must be positive. Using default size 1000.");
                    size = 1000;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid size argument. Using default size 1000.");
            }
        }

        BenchmarkRunner runner = new BenchmarkRunner();
        runner.runBenchmark(size);
    }
}
