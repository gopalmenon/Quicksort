
public class QuickSortClient {

	public static void main(String[] args) {
		
		int[] inputArray = {2, 8, 7, 1, 3, 5, 6, 4};
		QuickSort.sort(inputArray, 0, inputArray.length - 1);
		System.out.println("Done");
	}

}
