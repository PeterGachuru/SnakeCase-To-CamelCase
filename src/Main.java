import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    Main() {
        try {
            String path = "C:\\Users\\USER\\e_and_m\\EMT_Leasing_System\\emt_leasing_server\\src\\main\\java\\co\\ke\\emtechhouse\\leasing\\products\\CustomerProducts\\AssetsClassification\\Assetsclassification.java";
            boolean replace = true;
            Scanner scanner = new Scanner(new File(path));
            String result = "", camel, next;
            while (scanner.hasNextLine()) {
                System.out.println("------------------");
                next = scanner.nextLine();
                if (!next.trim().isEmpty()) {
                    System.out.println(next);
                    camel = toCamelCase(" "+next);
                    System.out.println(camel);
                }else {
                    camel = "";
                }
                result += "\n"+camel;
            }
            writeUsingFileWriter(result, path, replace);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String toCamelCase(String str) {

        // Capitalize first letter of string
        str = str.substring(0, 1).toUpperCase()
                + str.substring(1);

        // Run a loop till string
        // string contains underscore
        while (str.contains("_")) {

            // Replace the first occurrence
            // of letter that present after
            // the underscore, to capitalize
            // form of next letter of underscore
            str = str
                    .replaceFirst(
                            "_(?i)[a-z]",
                            String.valueOf(
                                    Character.toUpperCase(
                                            str.charAt(
                                                    str.indexOf("_") + 1))));
        }

        // Return string
        return str;
    }


    private void writeUsingFileWriter(String result, String path, boolean replace) {
        System.out.println("In right writeUsingFileWriter");
        File file = new File(replace? path :"camelcase.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new Main();
    }
}