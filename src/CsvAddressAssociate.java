import java.io.*;

/**
 * Created by Chris on 11/30/16.
 */
public class CsvAddressAssociate {
    public static void main(String [] args) throws FileNotFoundException, IOException {
        File csvFile = new File("/Users/Chris/Downloads/CompanyNamesMNJ.csv");
        File csvFile2 = new File("/Users/Chris/Downloads/mnj-2.csv");
        File newFile = new File("/Users/Chris/Downloads/CompanyAndAddress.csv");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        BufferedReader reader2;
        String line;
        String line2;
        while ((line = reader.readLine()) != null) {
            String name = line;
            String[] name2 = new String [2];
            //System.out.println(line);
            reader2 = new BufferedReader(new FileReader(csvFile2));
            boolean valid = false;
            while((line2 = reader2.readLine()) != null) {
                if(!line.trim().equals(",,,")) {
                    name2 = line2.split(",");
                   //System.out.println(name);
                    //System.out.println(name2[0]);
                    if (name.trim().equals(name2[0].trim())) {
                        //System.out.println("HERE");
                        pw.println(name + "," + name2[2]);
                        valid = true;
                        break;
                    }
                }
            }
            if(!valid) {
                pw.println(name);
            }
        }
    }
}
