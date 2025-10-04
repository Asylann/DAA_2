package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long arrayAccesses;
    private long startTime;
    private long endTime;

    public PerformanceTracker() {
        reset();
    }

    public void reset() {
        comparisons = 0;
        arrayAccesses = 0;
        startTime = 0;
        endTime = 0;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public void incrementComparison() {
        comparisons++;
    }

    public void incrementArrayAccess() {
        arrayAccesses++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public long getExecutionTimeNanos() {
        return endTime - startTime;
    }

    public double getExecutionTimeMillis() {
        return getExecutionTimeNanos() / 1000000.0;
    }

    public String exportToCsv(int inputSize) {
        return String.format("%d;%d;%d;%.3f", 
            inputSize,
            comparisons,
            arrayAccesses,
            getExecutionTimeMillis()
        );
    }

    public static String getCsvHeader() {
        return "InputSize;Comparisons;ArrayAccesses;DurationMs";
    }
}
