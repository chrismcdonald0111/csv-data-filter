import java.io.*;

public class CsvValidation {

    public static void main(String [] args) throws IOException {

        String csvFile = "/Users/Chris/Downloads/Title.csv";
        File newFile = new File("/Users/Chris/Downloads/itRelatedTitlesTEST.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        CharSequence [] itRelated = {" it "," it", "it ", " software", "software", "software ", " developer ", " developer", "developer ", " data ", " data", "data ", " cloud ", " cloud", "cloud ",
                " information ", " information", "information ", " technology ", " technology", "technology ", " server ", " server", "server ", " technician ", " technician", "technician ", " system ",
                " system", "system ", " systems ", " systems", "systems ", " network ", " network", "network ", " app ", " app", "app ", " application ", " application", "application ", " applications ",
                " applications", "applications ", " programmer ", " programmer", "programmer ", " technical ", " technical", "technical ", " computer ", " computer", "computer ", " computers ",
                " computers", "computers ", " computing ", " computing", "computing ", " cyber ", " cyber", "cyber ", " web ", " web", "web ", " database ", " database", "database ", " internet ",
                " internet", "internet ", " quality assurance ", " quality assurance", "quality assurance "
        };
        CharSequence [] notItRelated = { " recruit ", " recruit", "recruit ", " recruiting ", " recruiting", "recruiting ", " recruiter ", " recruiter", "recruiter ", " audit ", " audit", "audit ",
                " auditor ", " auditor", "auditor "
        };
        int count = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] title = line.split(cvsSplitBy);
                for(int i = 0; i < itRelated.length; i++) {
                    if (title[0].toLowerCase().contains(itRelated[i])) {
                        System.out.println(title[0]);
                        count++;
                        pw.println(title[0]);
                        break;
                    }
                }
//                for(int j = 0; j < notItRelated.length; j++) {
//                    if(!title[0].toLowerCase().contains(notItRelated[j])) {
//                        pw.println(title[0]);
//                        break;
//                    }
//                }
                //System.out.println("Title [code= " + title[0] + " , name=" + "THIS" + "]");

            }
            System.out.println(count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}