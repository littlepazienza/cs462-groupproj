import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

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

  private static void compareFiles(String[] file1, String[] file2) {
    System.out.printf("Feedback for 1st file\n[%s] is:\n", Arrays.toString(file1));
    System.out.printf("Feedback for 2nd file\n[%s] is:\n", Arrays.toString(file2));
    // TODO: 4/7/21 LMAO FIGURE OUT HOW TO ACTUALLY DO THIS
  }

}
