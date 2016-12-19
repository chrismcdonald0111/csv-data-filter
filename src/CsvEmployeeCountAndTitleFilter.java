import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CsvEmployeeCountAndTitleFilter {
    public static void main(String [] args) throws FileNotFoundException, IOException{

        CsvTitleCleansingService.initialize();
        CsvTitleCleansingService csvTitleCleansingService = CsvTitleCleansingService.getInstance();

        File csvFile = new File("/Users/Chris/Downloads/NP Netpros step 2 done.csv");
        //File csvFile2 = new File("/Users/Chris/Downloads/Les Olson Email.csv");
        File newFile = new File("/Users/Chris/Downloads/NP Netpros step 3-2-2 done.csv");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        File newFile2 = new File("/Users/Chris/Downloads/random.csv");
        PrintWriter pw2 = new PrintWriter(new FileWriter(newFile2));
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        //BufferedReader reader2 = new BufferedReader(new FileReader(csvFile2));
        String line;
        String line2;

        CharSequence [] itRelated = {
                " it "," it", "it ", " software", "software", "software ", " developer ", " developer", "developer ", " data ", " data", "data ", " cloud ", " cloud", "cloud ",
                " information ", " information", "information ", " technology ", " technology", "technology ", " server ", " server", "server ", " technician ", " technician", "technician ", " system ",
                " system", "system ", " systems ", " systems", "systems ", " network ", " network", "network ", " app ", " app", "app ", " application ", " application", "application ", " applications ",
                " applications", "applications ", " programmer ", " programmer", "programmer ", " technical ", " technical", "technical ", " computer ", " computer", "computer ", " computers ",
                " computers", "computers ", " computing ", " computing", "computing ", " cyber ", " cyber", "cyber ", " web ", " web", "web ", " database ", " database", "database ", " internet ",
                " internet", "internet "
        };

        CharSequence [] notItRelated = {
                " recruit ", " recruit", "recruit ", " recruiting ", " recruiting", "recruiting ", " recruiter ",
                " recruiter", "recruiter ", " audit ", " audit", "audit ", " auditor ", " auditor", "auditor "
        };

        CharSequence [] hrRelated = {
                " recruit ", " recruit", "recruit ", " recruiting ", " recruiting", "recruiting ", " recruiter ",
                " recruiter", "recruiter ", " hr ", " hr", "hr ", " human resources ", " human resources", "human resources ",
                " marketing ", " marketing", "marketing ", " business ", " business", "business "
        };

        CharSequence [] financeRelated = {
                " finance ", " finance", "finance ", " financial ", " financial", "financial ", " revenue ",
                " revenue", "revenue ", " finances ", " finances", "finances ", " payroll ", " payroll", "payroll ",
                " accounting ", " accounting", "accounting ", " controller ", " controller", "controller ", " loan ",
                " loan", "loan "
        };

        CharSequence [] functionalAreaTitles = {
                "EDUCATION", "ENGINEERING", "EXECUTIVE", "FINANCE", "HR", "IT",
                "MARKETING", "MEDICAL", "OPERATIONS", "SALES", "UNKNOWN", "NURSING", "LEGAL"
        };

        Map<String, String> managementLevelTitles = new HashMap<>();
        managementLevelTitles.put("chief financial officer", "CLEVEL");
        managementLevelTitles.put("chief executive officer", "CLEVEL");
        //managementLevelTitles.put("chief technology officer", "CLEVEL");
        managementLevelTitles.put("chief information officer", "CLEVEL");
        //managementLevelTitles.put("chief operating officer", "CLEVEL");
        managementLevelTitles.put("chief executive", "CLEVEL");
        managementLevelTitles.put("president", "EXEC");
        managementLevelTitles.put("vice president", "EXEC");
        managementLevelTitles.put("director", "DIRECTOR");
        managementLevelTitles.put("manager", "MANAGER");
//        managementLevelTitles.put("owner", "MANAGER");
//        managementLevelTitles.put("founder", "MANAGER");
//        managementLevelTitles.put("superintendent", "MANAGER");
        //line = reader.readLine();
        //System.out.println(line); 865517 1048576 267824
        boolean dedupe = false;
        while ((line = reader.readLine()) != null) {
              String [] title = line.split(",");
            int titleIndex = 0;
            outloop:
            for (int i = 0; i < title.length; i++) {
                for (int j = 0; j < functionalAreaTitles.length; j++) {
                    if (title[i].equals(functionalAreaTitles[j])) {
                        titleIndex = i - 1;
                        break outloop;
                    }
                }
            }
//            int employeesIndex = 0;
//            String [] employees = line.split(",");
//            for (int i = 0; i < employees.length; i++) {
//                if (employees[i].equals("STOP")) {
//                    employeesIndex = i - 1;
//                    break;
//                }
//            }
            String managementLevel;
            String cleansedTitle = csvTitleCleansingService.cleanse(title[titleIndex]);
            boolean hasManagementLevel = false;
//            //Tokenizer tokenizer = new EnglishTokenizer();
//            //reader = new BufferedReader(new FileReader(inFile));
//            //List<NLPNode> tokens = tokenizer.tokenize(cleansedTitle);
//            //List<String> trainingData = new ArrayList<>();
//            //for (NLPNode token : tokens) {
//                if(titleIndex != 0) {
            for (Map.Entry<String, String> map : managementLevelTitles.entrySet()) {
                if (cleansedTitle.trim().contains(" " + map.getKey().trim() + " ") || cleansedTitle.trim().contains(" " + map.getKey().trim()) || cleansedTitle.trim().contains(map.getKey().trim() + " ") || cleansedTitle.trim().equals(map.getKey().trim())) {
                    managementLevel = map.getKey();
                    hasManagementLevel = true;
                    break;
                }
                else {
                    hasManagementLevel = false;
                }
            }
            boolean validTitle = false;
//            if(cleansedTitle.contains("system administrator")) {
//                validTitle = true;
//            }
//            else {
                    if (hasManagementLevel) {
//                    if (cleansedTitle.toLowerCase().trim().contains("system administrator")) {
//                        validTitle = true;
//                    } else {
                        outerloop:
                        for (int i = 0; i < itRelated.length; i++) {
                            if (cleansedTitle.toLowerCase().trim().contains(itRelated[i])) {
                                for (int k = 0; k < hrRelated.length; k++) {
                                    if (!cleansedTitle.toLowerCase().trim().contains(hrRelated[k])) {
                                        validTitle = true;
                                        break outerloop;
                                    }
                                }
                            }
                        }
                    //}
                }
//
            //}
//                outerloop:
//                for (int i = 0; i < financeRelated.length; i++) {
//                    if (cleansedTitle.toLowerCase().trim().contains(financeRelated[i])) {
//                        for (int k = 0; k < notItRelated.length; k++) {
//                            if (!cleansedTitle.toLowerCase().trim().contains(notItRelated[k])) {
//                                validTitle = true;
//                                break outerloop;
//                            }
//                        }
//                    }
//                }
//            }
           // System.out.println(line);
//            if(CsvValidationAndFilter.calculateEmployeeCountCeiling(title, employeesIndex) >= 50 && CsvValidationAndFilter.calculateEmployeeCountCeiling(title, employeesIndex) <= 1000) {
//                pw.println(line);
//            }
            if(validTitle) {
                pw.println(line);
            }
////            else {
//                pw2.println(line);
//            }
//            if(line.contains("gov") || line.contains(".gov") || line.contains("edu") || line.contains(".edu") || line.contains("school") || line.contains("k12")) {
//
//////            if(!validTitle) {
//
//            }
//            else {
//                pw.println(line);
//            }
            //CsvValidationAndFilter.calculateEmployeeCountCeiling(title, employeesIndex);
            //System.out.print(reader2.readLine());
//            while ((line2 = reader2.readLine()) != null) {
//                String [] email = line.split(",");
//                String [] email2 = line2.split(",");
//                if(email[employeesIndex].trim().equals(email2[0].trim())) {
//                    dedupe = false;
//                    break;
//                }
//                else {
//                    dedupe = true;
//                }
//            }
//            if(dedupe) {
//                pw.println(line);
//            }
//            int titleIndex = 0;


////            if(title[titleIndex].trim().equals(" ") || title[titleIndex].trim().equals("")) {
////                pw.println(line);
////            }
////            else {
//                String cleansedTitle = csvTitleCleansingService.cleanse(title[titleIndex]);
//                outerloop:
//                for (int i=0; i<itRelated.length; i++) {
//                    if(cleansedTitle.toLowerCase().trim().contains(itRelated[i])) {
//                        for(int k = 0; k<notItRelated.length; k++) {
//                            if(!cleansedTitle.toLowerCase().trim().contains(notItRelated[k])) {
//                                pw.println(line);
//                                break outerloop;
//                            }
//                        }
//                    }
//                }
//            //}
//            if(employeesIndex != 0 && CsvValidationAndFilter.calculateEmployeeCountCeiling(employees, employeesIndex) >= 250 && CsvValidationAndFilter.calculateEmployeeCountCeiling(employees, employeesIndex) <= 1000) {
//                pw.println(line);
//            }
        }
    }
}
