
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Convert Tab Separate Value (TSV) file to Comma Separated Value (CSV) file
 */
public class TSVToCSVConverter {
    public static void main(String[] args) throws Exception {


        //The input TSV File
        String tsvFilePath = "C:\\Users\\USER\\e_and_m\\recon-master-backend\\src\\main\\resources\\airtime\\rwanda\\mtnrw\\RRN-19-10-2023.txt";

        //The output CSV File
        String csvFilePath = "C:\\Users\\USER\\e_and_m\\recon-master-backend\\src\\main\\resources\\airtime\\rwanda\\mtnrw\\RRN-19-10-2023.csv";

        convertTSVToCSVFile(csvFilePath, tsvFilePath);

    }

    /**
     * Converts a TSV file into CSV file.
     * - Reads one line at a time
     * - Replaces Double Quotes with Single Quotes
     * - Puts Double Quotes Around Every Field
     * - Fileds in the output are separated by comma.
     *
     * @param csvFilePath
     * @param tsvFilePath
     * @throws IOException
     */
    private static void convertTSVToCSVFile(String csvFilePath, String tsvFilePath) throws IOException {

        //TODO: If outfile does not exist, create one.

        StringTokenizer tokenizer;
        try (BufferedReader br = new BufferedReader(new FileReader(tsvFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath));) {

            int i = 0;
            for (String line; (line = br.readLine()) != null; ) {
                i++;
                if (i % 10000 == 0) {
                    System.out.println("Processed: " + i);

                }
                tokenizer = new StringTokenizer(line, "\t");

                String csvLine = "";
                String token;
                while (tokenizer.hasMoreTokens()) {
                    token = tokenizer.nextToken().replaceAll("\"", "'");
                    csvLine += "\"" + token + "\",";
                }

                if (csvLine.endsWith(",")) {
                    csvLine = csvLine.substring(0, csvLine.length() - 1);
                }

                writer.write(csvLine + System.getProperty("line.separator"));

            }

        }


    }

    private static String convertToCSV(String line) {
        String csv = "";
        line = line.replaceAll("\t", ",");
        return line;
    }
}