package library;

public class LinearSearch {

	public static int search(String[] array, String key) {
		for (int i = 0; i < array.length; i++)
			if (array[i].equalsIgnoreCase(key))
				return i;
		return -1;
	}
	
	
	public static int search(int[] array, int key) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == key)
				return i;
		return -1;
	}
	
}
