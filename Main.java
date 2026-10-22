// Lab 4 - Sorting Algorithms
// Name: Adrian Horhots

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
      System.out.println("Unsorted Array ---------------------------------------------------");
      ArrayList<Integer> integerList = Lab4.getList();
      Lab4.outputList(integerList);

      System.out.println("\n\nBubble sort results ----------------------------------------------");
      ArrayList<Integer> bubbleSortedList = Lab4.bubbleSort(integerList);
      Lab4.outputList(bubbleSortedList);

      System.out.println("\n\nInsertion sort results -------------------------------------------");
      ArrayList<Integer> insertionSortedList = Lab4.insertionSort(integerList);
      Lab4.outputList(insertionSortedList);
    }
}

class Lab4 {
  public static ArrayList<Integer> insertionSort(ArrayList<Integer> integerList) {
    // Step 1 - Implement insertion sort algorithm here

    // Work on a copy so the original unsorted list is left untouched.
    // Without this, whichever sort runs second receives an already-sorted
    // list and its timing is not comparable.
    ArrayList<Integer> list = new ArrayList<Integer>(integerList);

    long startTime = System.nanoTime();

    for (int i = 1; i < list.size(); i++) {
      // Insert list.get(i) into the sorted sublist list[0..i-1]
      int currentElement = list.get(i);
      int k;
      for (k = i - 1; k >= 0 && list.get(k) > currentElement; k--) {
        list.set(k + 1, list.get(k));
      }
      // Insert the current element into list[k + 1]
      list.set(k + 1, currentElement);
    }

    long endTime = System.nanoTime();
    long elapsedTime = endTime - startTime;
    System.out.println("Insertion sort elapsed time: " + elapsedTime + " nanoseconds");

    return list;
  }

  public static ArrayList<Integer> bubbleSort(ArrayList<Integer> integerList) {
    // Step 2 - Implement the bubble sort algorithm here

    // Work on a copy for the same reason as above.
    ArrayList<Integer> list = new ArrayList<Integer>(integerList);

    long startTime = System.nanoTime();

    boolean needNextPass = true;
    for (int k = 1; k < list.size() && needNextPass; k++) {
      // Array may already be sorted and next pass not needed
      needNextPass = false;
      for (int i = 0; i < list.size() - k; i++) {
        if (list.get(i) > list.get(i + 1)) {
          // Swap list[i] with list[i + 1]
          int temp = list.get(i);
          list.set(i, list.get(i + 1));
          list.set(i + 1, temp);

          needNextPass = true; // Next pass still needed
        }
      }
    }

    long endTime = System.nanoTime();
    long elapsedTime = endTime - startTime;
    System.out.println("Bubble sort elapsed time: " + elapsedTime + " nanoseconds");

    return list;
  }

  public static ArrayList<Integer> getList() {
    ArrayList<Integer> integerList = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("integers.txt"))) {
        while ((line = br.readLine()) != null) {
            integerList.add(Integer.parseInt(line));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return integerList;
  }

  public static void outputList(ArrayList<Integer> integerList) {
    for (int i = 0; i < integerList.size(); i++) {
        System.out.print(integerList.get(i) + " ");
    }
  }
}
