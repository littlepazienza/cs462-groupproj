import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class executes the LCS algorithm against two files given by arguments to the JVM
 * The run time of this is O(m * n) where m is the number of lines in file1 + 1 and
 * n is the number of lines in file2 + 1
 *
 * @author Jeffrey Umanzor
 * @author Benjamin Pazienza
 * @version 1.0
 */
public class Main{

  public static void main(String[] args) {

    if(args.length < 2) {
      System.err.println("You need to provide the paths to the two files");
      System.exit(1);
    }

    try {
      String file1 = Files.readString(Path.of(args[0]));
      String file2 = Files.readString(Path.of(args[1]));
      
      compareFiles(file1.split("\n"), file2.split("\n"));
      
    } catch (IOException e) {
      System.err.println("Files do not exist");
      System.exit(1);
    }
  }

  /**
   * This function compares two string arrays to get the longest common subsequence between
   * the two and prints it out with pretty formating
   *
   * @param file1 an array of Strings for each line in the first file that was read
   * @param file2 an array of Strings for each line in the second file that was read
   */
  private static void compareFiles(String[] file1, String[] file2) {

    ArrayList<String> lcs = new ArrayList<>();
    int lcs_table[][] = new int[file1.length + 1][file2.length + 1];

    for (int i = 0; i <= file1.length; i++) {
      for (int j = 0; j <= file2.length; j++) {
        if (i == 0 || j == 0) {
          lcs_table[i][j] = 0;
        } else if (file1[i - 1].equals(file2[j - 1])) {
          lcs_table[i][j] = lcs_table[i - 1][j - 1] + 1;
          lcs.add(file1[i-1]);
        } else {
          lcs_table[i][j] = Math.max(lcs_table[i - 1][j], lcs_table[i][j - 1]);
        }
      }
    }

    System.out.printf("# of common lines: %s\n", lcs_table[file1.length][file2.length]);
    System.out.printf("The common lines are\n%s\n\n", lcs);
    System.out.printf("Feedback for 1st file:\n%s is:\n", Arrays.toString(file1));
    Arrays.stream(file1).forEach((s) -> {
      System.out.println(lcs.contains(s) ? s + " is in both files": s + " is only in this file");
    });
    System.out.println();
    System.out.printf("Feedback for 2nd file\n%s is:\n", Arrays.toString(file2));
    Arrays.stream(file2).forEach((s) -> {
      System.out.println(lcs.contains(s) ? s + " is in both files": s + " is only in this file");
    });

  }

}
