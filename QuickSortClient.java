import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSortClient {
	
	public static final int ITERATION_COUNT = 5;

	public static void main(String[] args) {
		
		int[] arraySizes = {100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
		
		List<Double> runTimes = new ArrayList<Double>(arraySizes.length);
		Random randomNumberGenrator = new Random(0);
		QuickSortClient quickSortClient = new QuickSortClient();
		double runTime = 0;
		
		for (int size : arraySizes) {
			runTime = quickSortClient.getRunTime(size, true, 0, randomNumberGenrator);
			runTimes.add(Double.valueOf(runTime));
			System.out.println(size + " elements sorted in average of " + runTime + " ms with Big-O constant " + quickSortClient.getBigOConstant(size, runTime) + " for single random pivot");
		}
		
		int[] randomPivotMedian = {1, 2, 3};
		for (int k : randomPivotMedian) {
			for (int size : arraySizes) {
				runTime = quickSortClient.getRunTime(size, false, k, randomNumberGenrator);
				runTimes.add(Double.valueOf(runTime));
				System.out.println(size + " elements sorted in average of " + runTime + " ms with Big-O constant " + quickSortClient.getBigOConstant(size, runTime) + " for median from " + (2 * k + 1) + " pivots");
			}
		}
		
		
	}
	
	private double getRunTime(int arraySize, boolean singleRandomPivot, int pivotCandidatesParam, Random randomNumberGenrator) {
		
		int[] arrayToSort = new int[arraySize];
		for (int arrayIndex = 0; arrayIndex < arraySize; ++arrayIndex) {
			arrayToSort[arrayIndex] = randomNumberGenrator.nextInt();
		}
		
		int runCounter = 0;
		long averageRunTime = 0, startTime = 0, endTime = 0;;
		do {
			
			startTime = System.currentTimeMillis();
			QuickSort.sort(arrayToSort, 0, arrayToSort.length -1, singleRandomPivot, pivotCandidatesParam, randomNumberGenrator);
			endTime = System.currentTimeMillis();
			
			averageRunTime += endTime - startTime;
			runCounter += 1;
			
		} while (runCounter < ITERATION_COUNT);
		
		return averageRunTime / ITERATION_COUNT;
		
	}
	
	private double getBigOConstant(int arraySize, double runTime) {
		return runTime / (arraySize * Math.log(arraySize));
	}

}
