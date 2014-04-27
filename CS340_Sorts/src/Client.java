
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 Client -

 @author
 @version 1.0 - Apr 30, 2012 at 2:50:25 PM
 */
public class Client {

  //Data Set1
  public static ArrayList<Integer> ds1_random = new ArrayList();
  public static ArrayList<Integer> ds1_reversed = new ArrayList();
  public static ArrayList<Integer> ds1_almost = new ArrayList();
  public static ArrayList<Integer> ds1_sorted = new ArrayList();
  //Data Set2
  public static ArrayList<Integer> ds2_random = new ArrayList();
  public static ArrayList<Integer> ds2_reversed = new ArrayList();
  public static ArrayList<Integer> ds2_almost = new ArrayList();
  public static ArrayList<Integer> ds2_sorted = new ArrayList();
  //Data Set3
  public static ArrayList<Integer> ds3_random = new ArrayList();
  public static ArrayList<Integer> ds3_reversed = new ArrayList();
  public static ArrayList<Integer> ds3_almost = new ArrayList();
  public static ArrayList<Integer> ds3_sorted = new ArrayList();
  //Data Set4
  public static ArrayList<Integer> ds4_random = new ArrayList();
  public static ArrayList<Integer> ds4_reversed = new ArrayList();
  public static ArrayList<Integer> ds4_almost = new ArrayList();
  public static ArrayList<Integer> ds4_sorted = new ArrayList();
  public static double startTime = 0.0;
  public static String[] ds_type = {"  random", "reversed", "  almost", "  sorted"};
  public static final int SETS_TO_LOAD = 3; //3 and 4 a while to load so don't activate until your algorithm is good.

  public static void main(String[] args) {
    loadDataSets();
    Object[][] ds = {{ds1_random, ds1_reversed, ds1_almost, ds1_sorted},
                     {ds2_random, ds2_reversed, ds2_almost, ds2_sorted},
                     {ds3_random, ds3_reversed, ds3_almost, ds3_sorted},
                     {ds4_random, ds4_reversed, ds4_almost, ds4_sorted}};

    //doBubbleSorts(ds);
    doMergeSorts(ds);
    //doInsertionSorts(ds);
    //doQuickSorts(ds);


    //System.out.println(java.util.Arrays.deepToString(ds[1])); //All array data
  }

  public static void BubbleSort(ArrayList<Integer> data) {
    for (int i = 1; i < data.size(); i++) {
      for (int j = 1; j < data.size() - i; j++) {
        if (data.get(j) > data.get(j + 1)) {
          int temp = data.get(j);
          data.set(j, data.get(j + 1));
          data.set(j + 1, temp);
        }
      }
    }
  }

  public static void MergeSort(ArrayList<Integer> data) {
    data = MergeSortHelp(data);
    //System.out.println(data + "," + data.size() + "\n\n");
  }

  private static ArrayList<Integer> MergeSortHelp(ArrayList<Integer> data) {
    int n = data.size();
    if (n >= 2) {
      int m = (int) Math.floor(n / 2);
      ArrayList<Integer> l1 = new ArrayList();
      ArrayList<Integer> l2 = new ArrayList();
      for (int i = 0; i <m; i++) {
        l1.add(data.get(i));
      }
      for (int i = m; i < n; i++) {
        l2.add(data.get(i));
      }
      //System.out.println(m);
      return Merge(MergeSortHelp(l1), MergeSortHelp(l2));
    }
    return data;
  }

  private static ArrayList<Integer> Merge(ArrayList<Integer> l1, ArrayList<Integer> l2) {
    ArrayList<Integer> L = new ArrayList();

    while (!l1.isEmpty() && !l2.isEmpty()) {
      int smallest = Math.min(l1.get(0), l2.get(0));
      
      if (l1.get(0) == smallest) {
        l1.remove(0);
        L.add(smallest);
        if (l1.isEmpty()) {
          L.addAll(l2);
        }
      } else {
        l2.remove(0);
        L.add(smallest);
        if (l2.isEmpty()) {
          L.addAll(l1);
        }
      }
    }
    return L;
  }

  public static void InsertionSort(ArrayList<Integer> data) {
    for (int j = 1; j < data.size(); j++) {
      int i = 0;
      while (data.get(j) > data.get(i)) {
        i++;
      }
      int m = data.get(j);
      for (int k = 0; k <= j - i - 1; k++) {
        data.set(j - k, data.get(j - k - 1));
      }
      data.set(i, m);
    }
    //System.out.println(data + "\n\n");
  }

  public static void QuickSort(ArrayList<Integer> data) {

    data = QuickSortHelper(data);
    //System.out.println(data);
  }

  private static ArrayList<Integer> QuickSortHelper(ArrayList<Integer> data) {

    if (data.size() <= 1) {
      return data;
    }

    int firstElement = data.get(0);
    data.remove(0);
    ArrayList<Integer> lessThan = new ArrayList<>();
    ArrayList<Integer> greaterThan = new ArrayList<>();

    for (int i = 0; i < data.size(); i++) {

      if (data.get(i) <= firstElement) {

        lessThan.add(data.get(i));

      } else {

        greaterThan.add(data.get(i));
      }
    }

    return concatenate(QuickSortHelper(lessThan), firstElement, QuickSortHelper(greaterThan));
  }

  private static ArrayList<Integer> concatenate(ArrayList<Integer> less, int pivot, ArrayList<Integer> greater) {

    ArrayList<Integer> concatenated = new ArrayList<>();

    for (int i : less) {

      concatenated.add(i);
    }

    concatenated.add(pivot);

    for (int i : greater) {

      concatenated.add(i);
    }

    return concatenated;
  }

  /**
   Loads the data sets
   */
  public static void loadDataSets() {
    Object[][] ds = {{ds1_random, ds1_reversed, ds1_almost, ds1_sorted},
                     {ds2_random, ds2_reversed, ds2_almost, ds2_sorted},
                     {ds3_random, ds3_reversed, ds3_almost, ds3_sorted},
                     {ds4_random, ds4_reversed, ds4_almost, ds4_sorted}};

    for (int i = 0; i < SETS_TO_LOAD; i++) {
      resetTimer();
      try (Scanner input = new Scanner(new File("src\\ds" + (i + 1) + ".txt"))) {
        input.nextLine();
        while (input.hasNextLine()) {
          Scanner line = new Scanner(input.nextLine());
          line.useDelimiter(",");
          line.nextInt();
          ArrayList<Integer> random = (ArrayList<Integer>) ds[i][0];
          ArrayList<Integer> reversed = (ArrayList<Integer>) ds[i][1];
          ArrayList<Integer> almost = (ArrayList<Integer>) ds[i][2];
          ArrayList<Integer> sorted = (ArrayList<Integer>) ds[i][3];

          random.add(line.nextInt());
          reversed.add(line.nextInt());
          almost.add(line.nextInt());
          sorted.add(line.nextInt());
        }
      } catch (IOException error) {
        System.out.println("An I/O error occurred: " + error);
      }
      System.out.println("DS " + (i + 1) + " LOADED");
      System.out.print("\tTime: ");
      showTimer();
      System.out.print(" ms\n");
    }
    System.out.println();
  }

  /**
   Resets the timer
   */
  public static void resetTimer() {
    startTime = System.currentTimeMillis();
  }

  /**
   Prints the current timer
   */
  public static void showTimer() {
    System.out.print((System.currentTimeMillis() - startTime));
  }

  /**
   Does all the bubble sorts

   @param ds
   */
  public static void doBubbleSorts(Object[][] ds) {
    String sortType = "Bubble";
    for (int i = 0; i < SETS_TO_LOAD; i++) {
      System.out.println("Sort Type: " + sortType);
      System.out.println("Data Set: " + (i + 1));
      for (int j = 0; j < 4; j++) {
        resetTimer();
        BubbleSort((ArrayList) ds[i][j]);
        System.out.print(ds_type[j] + ": ");
        showTimer();
        System.out.print(" ms\n");
      }
      System.out.println();
    }
  }

  /**
   Does all the merge sorts

   @param ds
   */
  public static void doMergeSorts(Object[][] ds) {
    String sortType = "Merge";
    for (int i = 0; i < SETS_TO_LOAD; i++) {
      System.out.println("Sort Type: " + sortType);
      System.out.println("Data Set: " + (i + 1));
      for (int j = 0; j < 4; j++) {
        resetTimer();
        MergeSort((ArrayList) ds[i][j]);
        System.out.print(ds_type[j] + ": ");
        showTimer();
        System.out.print(" ms\n");
      }
      System.out.println();
    }
  }

  /**
   Does all the insertion sorts

   @param ds
   */
  public static void doInsertionSorts(Object[][] ds) {
    String sortType = "Insertion";
    for (int i = 0; i < SETS_TO_LOAD; i++) {
      System.out.println("Sort Type: " + sortType);
      System.out.println("Data Set: " + (i + 1));
      for (int j = 0; j < 4; j++) {
        resetTimer();
        InsertionSort((ArrayList) ds[i][j]);
        System.out.print(ds_type[j] + ": ");
        showTimer();
        System.out.print(" ms\n");
      }
      System.out.println();
    }
  }

  /**
   Does all the quick sorts

   @param ds
   */
  public static void doQuickSorts(Object[][] ds) {
    String sortType = "Quick";
    for (int i = 0; i < SETS_TO_LOAD; i++) {
      System.out.println("Sort Type: " + sortType);
      System.out.println("Data Set: " + (i + 1));
      for (int j = 0; j < 4; j++) {
        resetTimer();
        QuickSort((ArrayList) ds[i][j]);
        System.out.print(ds_type[j] + ": ");
        showTimer();
        System.out.print(" ms\n");
      }
      System.out.println();
    }
  }
}