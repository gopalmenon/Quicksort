
/**
 * QuickSort implementation based on CLRS pseudocode
 *
 */
public class QuickSort {
	
	/**
	 * @param arrayToSort
	 * @param firstElementIndex
	 * @param lastElementIndex
	 */
	public static void sort(int[] arrayToSort, int firstElementIndex, int lastElementIndex) {
		
		if (firstElementIndex < lastElementIndex) {
			
			int partitionIndex = partitionInputArray(arrayToSort, firstElementIndex, lastElementIndex);
			sort(arrayToSort, firstElementIndex, partitionIndex - 1);
			sort(arrayToSort, partitionIndex + 1, lastElementIndex);
			
		}
		
	}
	
	/**
	 * @param arrayToSort
	 * @param firstElementIndex
	 * @param lastElementIndex
	 * @return index to partition array on
	 */
	private static int partitionInputArray(int[] arrayToSort, int firstElementIndex, int lastElementIndex) {
		
		int pivot = arrayToSort[lastElementIndex];
		int i = firstElementIndex - 1;
		
		for (int j = firstElementIndex; j < lastElementIndex; ++j) {
			
			if (arrayToSort[j] <= pivot) {
				i += 1;
				int tempInt = arrayToSort[i];
				arrayToSort[i] = arrayToSort[j];
				arrayToSort[j] = tempInt;
			}
			
		}
		
		int tempInt = arrayToSort[i + 1];
		arrayToSort[i + 1] = arrayToSort[lastElementIndex];
		arrayToSort[lastElementIndex] = tempInt;
		
		return i + 1;
		
	}

}
