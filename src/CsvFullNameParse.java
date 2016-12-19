import edu.emory.mathcs.nlp.common.util.StringUtils;

import java.io.*;
import org.apache.commons.lang3.StringUtils.*;

/**
 * Created by Chris on 11/17/16.
 */
public class CsvFullNameParse {
    public static void main(String [] args) throws IOException {

        File csvFile = new File("/Users/Chris/Downloads/Untitled spreadsheet - Sheet1-2.txt");
        File newFile = new File("/Users/Chris/Downloads/Copy of Mex Braz 200-500 w contact Fixed Name.csv");
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String line;
        int count = 0;
        line = reader.readLine();
        System.out.println(line);
        while ((line = reader.readLine()) != null) {
            count++;
            String[] name = line.split(" ");
            for(int i = 0; i < name.length; i++) {
                    if(name.length == 2) {
                        pw.println(stripAccents(name[0]) + "," + stripAccents(name[1]));
                        break;
                    }
                    else if(name.length == 3) {
                        pw.println(stripAccents(name[0]) + "," + stripAccents(name[2]));
                        break;
                    }
                    else if(name.length == 4) {
                        pw.println(stripAccents(name[0]) + "," +stripAccents(name[3]));
                        break;
                    }
                    else if(name.length == 5) {
                        pw.println(stripAccents(name[0]) + "," + stripAccents(name[4]));
                        break;
                    }
                    else if(name.length == 6) {
                        pw.println(stripAccents(name[0]) + "," + stripAccents(name[5]));
                        break;
                    }
                    else if(name.length == 7) {
                        pw.println(stripAccents(name[0]) + "," + stripAccents(name[6]));
                        break;
                    }
                    else if(name.length == 8) {
                        pw.println(stripAccents(name[0])  + "," + stripAccents(name[7]));
                        break;
                    }
                    else if(name.length == 9) {
                        pw.println(stripAccents(name[0]) + "," + stripAccents(name[8]));
                        break;
                    }
                    else if(name.length == 10) {
                        pw.println(stripAccents(name[0]) + "," + stripAccents(name[9]));
                        break;
                    }
                    else {
                        System.out.println(name[0]);
                        break;
                    }
            }
        }
        pw.close();
    }
    public static String stripAccents(String str) {
        return org.apache.commons.lang3.StringUtils.stripAccents(str);
    }
}
