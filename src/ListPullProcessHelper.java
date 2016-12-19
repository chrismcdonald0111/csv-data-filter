import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ListPullProcessHelper {
    public enum GovOrEdu {
        GOV,
        EDU,
        GOV_AND_EDU
    }

    public static void main(String [] args) throws FileNotFoundException, IOException {

        CsvTitleCleansingService.initialize();
        CsvTitleCleansingService csvTitleCleansingService = CsvTitleCleansingService.getInstance();

        File csvFile = new File("/Users/Chris/Downloads/NP Eaks Step 2 Company Size.csv");
        //File csvFile2 = new File("/file/path/dedupe-file-here.csv");
        File newFile = new File("/Users/Chris/Downloads/NP Eaks Step 3 Title.csv");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        //BufferedReader reader2 = new BufferedReader(new FileReader(csvFile2));
        String line;
        //String line2;

        CharSequence[] functionalAreaTitles = {
                "EDUCATION", "ENGINEERING", "EXECUTIVE", "FINANCE", "HR", "IT",
                "MARKETING", "MEDICAL", "OPERATIONS", "SALES", "UNKNOWN", "NURSING", "LEGAL"
        };

        boolean dedupe = false;
        while ((line = reader.readLine()) != null) {
            String[] title = line.split(",");
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
            int employeeCountIndex = 0;
            String [] employeeCount = line.split(",");
            for (int i = 0; i < employeeCount.length; i++) {
                if (employeeCount[i].equals("STOP")) {
                    employeeCountIndex = i - 1;
                    break;
                }
            }
            String cleansedTitle = csvTitleCleansingService.cleanse(title[titleIndex]);
            boolean validTitle = validateTitle(cleansedTitle);
            //int employeeCountCeiling = calculateEmployeeCountCeiling(employeeCount, employeeCountIndex);
            //boolean validNoGovOrEdu = noGovOrEdu(line, GovOrEdu.GOV_AND_EDU);

            if(validTitle) {
                pw.println(line);
            }
//            if(employeeCountCeiling >= 100 && employeeCountCeiling <= 1000) {
//                pw.println(line);
//            }
//            if(validNoGovOrEdu) {
//                pw.println(line);
//            }
        }
    }
    public static boolean validateTitle(String title) {
        CharSequence[] itRelated = {
                " it ", " it", "it ", " software", "software", "software ", " developer ", " developer", "developer ", " data ", " data", "data ", " cloud ", " cloud", "cloud ",
                " information ", " information", "information ", " technology ", " technology", "technology ", " server ", " server", "server ", " technician ", " technician", "technician ", " system ",
                " system", "system ", " systems ", " systems", "systems ", " network ", " network", "network ", " app ", " app", "app ", " application ", " application", "application ", " applications ",
                " applications", "applications ", " programmer ", " programmer", "programmer ", " technical ", " technical", "technical ", " computer ", " computer", "computer ", " computers ",
                " computers", "computers ", " computing ", " computing", "computing ", " cyber ", " cyber", "cyber ", " web ", " web", "web ", " database ", " database", "database ", " internet ",
                " internet", "internet "
        };

        CharSequence[] notItRelatedLoose = {
                " recruit ", " recruit", "recruit ", " recruiting ", " recruiting", "recruiting ", " recruiter ",
                " recruiter", "recruiter ", " audit ", " audit", "audit ", " auditor ", " auditor", "auditor "
        };

        CharSequence[] notItRelatedStrict = {
                " recruit ", " recruit", "recruit ", " recruiting ", " recruiting", "recruiting ", " recruiter ",
                " recruiter", "recruiter ", " hr ", " hr", "hr ", " human resources ", " human resources", "human resources ",
                " marketing ", " marketing", "marketing ", " business ", " business", "business "
        };

        CharSequence[] financeRelated = {
                " finance ", " finance", "finance ", " financial ", " financial", "financial ", " revenue ",
                " revenue", "revenue ", " finances ", " finances", "finances ", " payroll ", " payroll", "payroll ",
                " accounting ", " accounting", "accounting ", " controller ", " controller", "controller ", " loan ",
                " loan", "loan "
        };

        Map<String, String> managementLevelTitles = new HashMap<>();
        managementLevelTitles.put("chief financial officer", "CLEVEL");
        //managementLevelTitles.put("chief executive officer", "CLEVEL");
        //managementLevelTitles.put("chief technology officer", "CLEVEL");
        managementLevelTitles.put("chief information officer", "CLEVEL");
        //managementLevelTitles.put("chief operating officer", "CLEVEL");
        managementLevelTitles.put("president", "EXEC");
        managementLevelTitles.put("vice president", "EXEC");
        managementLevelTitles.put("director", "DIRECTOR");
        managementLevelTitles.put("manager", "MANAGER");

        boolean hasManagementLevel = false;
        String managementLevel = " ";

        for (Map.Entry<String, String> map : managementLevelTitles.entrySet()) {
            if (title.trim().contains(" " + map.getKey().trim() + " ") || title.trim().contains(" " + map.getKey().trim()) || title.trim().contains(map.getKey().trim() + " ") || title.trim().equals(map.getKey().trim())) {
                managementLevel = map.getKey();
                hasManagementLevel = true;
                break;
            } else {
                hasManagementLevel = false;
            }
        }

        boolean validTitle = false;
        if (hasManagementLevel) {
            if(title.toLowerCase().trim().contains("system administrator")) {
                validTitle = true;
            }
            else {
                outerloop:
                for (int i = 0; i < itRelated.length; i++) {
                    if (title.toLowerCase().trim().contains(itRelated[i])) {
                        for (int k = 0; k < notItRelatedStrict.length; k++) {
                            if (!title.toLowerCase().trim().contains(notItRelatedStrict[k])) {
                                validTitle = true;
                                break outerloop;
                            }
                        }
                    }
                }
            }
        }

        return validTitle;
    }

    public static int calculateEmployeeCountCeiling(String [] row, int employeeCountIndex) {
        int ceiling = 0;
        String employees = row[employeeCountIndex];
        if(employees.contains("000") || employees.contains("000\"")) {
            try {
                if(row[employeeCountIndex-2].length() == 1 || row[employeeCountIndex-2].length() == 2) {
                    employees = row[employeeCountIndex - 2].concat(row[employeeCountIndex - 1]).concat(row[employeeCountIndex]).replaceAll("\"", " ");
                }
                else if(row[employeeCountIndex-1].length() == 1 || row[employeeCountIndex-1].length() == 2) {
                    employees = row[employeeCountIndex - 1].concat(row[employeeCountIndex]).replaceAll("\"", " ");
                }
                else {

                }
            } catch (ArrayIndexOutOfBoundsException a) {

            }
        }
        employees = employees.replaceAll("(K)", "000").replaceAll("(M)", "000000").replaceAll("(B)", "000000000");
        if(employees.contains("+")) {
            try {
                ceiling = Integer.parseInt(employees.replaceAll("[^0-9]", "").concat("0"));
            }
            catch (NumberFormatException n) {
                ceiling = 0;
            }
        }
        else if(employees.contains(">")) {
            ceiling = Integer.parseInt(employees.replaceAll("[^0-9]", "").concat("0"));
        }
        else if(employees.contains("<")) {
            ceiling = Integer.parseInt(employees.replaceAll("[^0-9]", ""));
        }
        else if(employees.contains("to less than")) {
            int[] range = Arrays.stream(employees.trim().replaceAll(",", "").replaceAll("[^0-9]"," ").replaceAll("\\s+", ",").split(","))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(range);
            ceiling = range[range.length - 1];
        }
        else if(employees.contains("less than")) {
            employees = employees.replaceAll(",", "").replaceAll("[^0-9]"," ").replaceAll("\\s+", ",");
            try {
                ceiling = Integer.parseInt(employees);
            }
            catch (NumberFormatException n) {

            }
        }
        else if(employees.contains("-")) {
            try {
                int[] range = Arrays.stream(employees.split("-"))
                        .map(String::trim).mapToInt(Integer::parseInt).toArray();
                Arrays.sort(range);
                try {
                    ceiling = range[range.length - 1];
                } catch (ArrayIndexOutOfBoundsException a) {

                }
            }
            catch (NumberFormatException n) {
                ceiling = 0;
            }
        }
        else if(employees.toLowerCase().equals("not provided") || employees.toLowerCase().equals("unknown") || employees.equals("")) {
            ceiling = -1;
        }
        else {
            try {
                ceiling = Integer.parseInt(employees.replaceAll("[^0-9]", ""));
            }
            catch (NumberFormatException n) {
                ceiling = 0;
            }
        }
        return ceiling;
    }


    public static boolean noGovOrEdu(String line, GovOrEdu govOrEdu) {
        boolean valid = true;
        if(govOrEdu.equals(GovOrEdu.GOV)) {
            if (line.contains("gov") || line.contains(".gov")) {
                valid = false;
            }
        } else if (govOrEdu.equals(GovOrEdu.EDU)) {
            if (line.contains("edu") || line.contains(".edu") || line.contains("school") || line.contains("k12")) {
                valid = false;
            }
        } else if (govOrEdu.equals((GovOrEdu.GOV_AND_EDU))) {
            if (line.contains("gov") || line.contains(".gov") || line.contains("edu") || line.contains(".edu") || line.contains("school") || line.contains("k12")) {
                valid = false;
            }
        }
        return valid;
    }
}
