import java.io.*;

public class CsvFilter {

    public static void main(String [] args) throws IOException {

        String validationFile = "/Users/Chris/Downloads/itRelatedTitles.txt";
        String originalFile = "/Users/Chris/Downloads/it-related-fields-IT-FA.csv";
        File newFile = new File("/Users/Chris/Downloads/IT-Related-Fields-Done.csv");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        String lineBr1;
        String lineBr2;
        int count = 0;
        int index = 0;

        try {

            br1 = new BufferedReader(new FileReader(originalFile));
            while ((lineBr1 = br1.readLine()) != null) {
                index = 0;
                String [] title = lineBr1.split(",");
                for(int i = 0; i<title.length; i++) {
                    if(title[i].equals("IT")) {
                        //System.out.println(index);
                        index = i;
                        break;
                    }
                }
                System.out.println(title[index]);
                if(index != 0) {
                    System.out.println(title[index - 1]);
                }
                //System.out.println(index);
                //System.out.println();
                br2 = new BufferedReader((new FileReader(validationFile)));
                while ((lineBr2 = br2.readLine()) != null) {
                    //System.out.println(lineBr2.trim());
                    if(index != 0) {
                        //System.out.println(index);
                        if (title[index-1].contains(lineBr2.trim())) {
                            pw.println(lineBr1);
                            count++;
                            System.out.println(count);
                            break;
                        }
                    }
                }
            }
            System.out.println(count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br1 != null) {
                try {
                    br1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br2 != null) {
                try {
                    br2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}