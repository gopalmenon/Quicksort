import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * QuickSort implementation based on CLRS pseudocode
 *
 */
public class QuickSort {
	
	public static void sort(int[] arrayToSort, int firstElementIndex, int lastElementIndex, boolean singleRandomPivot, int pivotCandidatesParam, Random randomNumberGenerator) {
		
		if (firstElementIndex < lastElementIndex) {
			
			int pivotIndex = 0;
			
			if (singleRandomPivot) {
				pivotIndex = getRandomPivotIndex(arrayToSort, firstElementIndex, lastElementIndex, randomNumberGenerator);
			} else {
				pivotIndex = getPivotIndexFromMedianOfElements(arrayToSort, firstElementIndex, lastElementIndex, pivotCandidatesParam, randomNumberGenerator);
			}
			
			swap(arrayToSort, pivotIndex, lastElementIndex);
			
			int partitionIndex = partitionInputArray(arrayToSort, firstElementIndex, lastElementIndex);
			sort(arrayToSort, firstElementIndex, partitionIndex - 1, singleRandomPivot, pivotCandidatesParam, randomNumberGenerator);
			sort(arrayToSort, partitionIndex + 1, lastElementIndex, singleRandomPivot, pivotCandidatesParam, randomNumberGenerator);
			
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
				swap(arrayToSort, i, j);
			}
			
		}
		
		swap(arrayToSort, i + 1, lastElementIndex);
		
		return i + 1;
		
	}
	
	/**
	 * @param arrayToSort
	 * @param swapIndex1
	 * @param swapIndex2
	 */
	private static void swap(int[] arrayToSort, int swapIndex1, int swapIndex2) {
		
		if (swapIndex1 != swapIndex2) {
			int tempInt = arrayToSort[swapIndex1];
			arrayToSort[swapIndex1] = arrayToSort[swapIndex2];
			arrayToSort[swapIndex2] = tempInt;
		}
		
	}
	
	private static int getPivotIndexFromMedianOfElements(int[] arrayToSort, int firstElementIndex, int lastElementIndex, int pivotCandidatesParam, Random randomNumberGenerator) {
		
		int numberOfPivotCandidate = 2 * pivotCandidatesParam + 1;
		
		if (lastElementIndex - firstElementIndex + 1 <= numberOfPivotCandidate) {
			return lastElementIndex;
		}
		
		Element[] pivotsArray = new Element[numberOfPivotCandidate];
		int randomIndex = 0;
		
		for (int indexCounter = 0; indexCounter < pivotsArray.length; ++indexCounter) {
			randomIndex = getRandomPivotIndex(arrayToSort, firstElementIndex, lastElementIndex, randomNumberGenerator);
			pivotsArray[indexCounter] = new Element(randomIndex, arrayToSort[randomIndex]);
		}
		
		Arrays.sort(pivotsArray, new ElementComparator());
		return pivotsArray[pivotCandidatesParam].getElementIndex();
		
	}
	
	private static int getRandomPivotIndex(int[] arrayToSort, int firstElementIndex, int lastElementIndex, Random randomNumberGenerator) {
		return firstElementIndex + randomNumberGenerator.nextInt(lastElementIndex - firstElementIndex + 1);
	}
	
	private static class Element {
		private int elementIndex, elementValue;
		public Element(int elementIndex, int elementValue) {
			this.elementIndex = elementIndex;
			this.elementValue = elementValue;
		}
		public int getElementIndex() {
			return elementIndex;
		}
		public int getElementValue() {
			return elementValue;
		}
		
	}
	
	private static class ElementComparator implements Comparator<Element> {

		@Override
		public int compare(Element element1, Element element2) {
			
			if (element1.getElementValue() == element2.getElementValue()) {
				return 0;
			} else if (element1.getElementValue() < element2.getElementValue()) {
				return -1;
			} else {
				return 1;
			}

		}
		
	}

}
