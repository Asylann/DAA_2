# Kadane's Algorithm Implementation

An optimized implementation of Kadane's algorithm for finding the maximum subarray sum with position tracking and performance metrics.

## Algorithm Overview

Kadane's algorithm solves the maximum subarray sum problem by maintaining two variables:
- `currentSum`: The maximum sum ending at the current position
- `maxSum`: The maximum sum found so far

### Complexity Analysis

#### Time Complexity
- Best Case: Θ(1) - When all numbers are positive (with optimization)
- Average Case: Θ(n) - One pass through the array
- Worst Case: Θ(n) - Must examine all elements

#### Space Complexity
- O(1) - Uses constant extra space regardless of input size
- Only stores a few variables (currentSum, maxSum, indices)

### Optimizations

1. **Early Termination**
   - Detects all-positive arrays in a single pass
   - Returns total sum immediately for all-positive cases
   - Avoids unnecessary comparisons in common scenarios

## Usage Instructions

### Running Benchmarks
Run with default size (1000):
```bash
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner"
```

Run with custom size:
```bash
mvnd exec:java "-Dexec.mainClass=cli.BenchmarkRunner" "-Dexec.args=1000"
```

### Running Tests
```bash
mvn test
```

## Performance Metrics

The implementation tracks three key metrics:
1. **Array Accesses**: Number of times array elements are read
2. **Comparisons**: Number of numerical comparisons performed
3. **Execution Time**: Duration in milliseconds

Results are saved to `results.csv` in the format:
```
InputSize;Comparisons;ArrayAccesses;DurationMs
```

### Sample Performance Data
Based on actual benchmarks:
- Size 100: ~0.011ms, 198 comparisons
- Size 1000: ~0.133ms, 1998 comparisons
- Size 10000: ~0.548ms, 19998 comparisons
- Size 50000: ~2.779ms, 99998 comparisons

## Implementation Details

### Key Features
1. Position tracking (start and end indices of maximum subarray)
2. Performance metrics collection
3. Edge case handling (null/empty arrays)
4. Early termination optimization

### Code Structure
- `Kadane.java`: Core algorithm implementation
- `PerformanceTracker.java`: Metrics collection
- `BenchmarkRunner.java`: CLI interface
- `KadaneTest.java`: Unit tests

## Test Coverage

The implementation includes comprehensive tests for:
- Edge cases (empty arrays, single elements)
- Basic cases (all positive/negative numbers)
- Special cases (zeros, duplicates)
- Complex cases (mixed sequences)
- Performance validation


<img src="https://github.com/Asylann/DAA_2/blob/master/TestPhoto.png" alt="Demo Screenshot" width="800"/>

## Git Commit Storyline

- e668257 (HEAD -> master, origin/master) Merge pull request #5 from Asylann/feat/optimization
- 6b189bb (origin/feat/optimization, feat/optimization) Optimization such as setHeaders to csv and check  for all posistive in algo
- da4de16 Merge pull request #4 from Asylann/feat/cli
- a8eea00 (origin/feat/cli, feat/cli) CLI with unput size was added
- 0ac3553 Merge pull request #3 from Asylann/feat/algorithm
- 5f11f38 (origin/feat/algorithm, feat/algorithm) Tests were added
- 733df83 Merge pull request #2 from Asylann/feat/metrics
- dabc52d (origin/feat/metrics, feat/metrics) proper PerfomanceTracker added
- d581ef7 Merge pull request #1 from Asylann/feat/algorithm
- cd52915 Added TaskTracker for algo
- 75dfc1a First init and already ready algo but can be improvement

## Author
Usen Asylan