import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvValidationAndFilter {

    public static void main(String [] args) throws IOException {

        CsvTitleCleansingService.initialize();
        CsvTitleCleansingService csvTitleCleansingService = CsvTitleCleansingService.getInstance();

        //String validationFile = "/Users/Chris/Downloads/Netparneting - Proper job titles.csv";
        String originalFile = "/Users/Chris/Downloads/2016-11-01 XenTegra Second #2.csv";
        File newFile = new File("/Users/Chris/Downloads/2016-11-01 XenTegra Second #2 Processed.csv");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader brRawFile = null;
        BufferedReader brValidationFile = null;
        String lineRawFile;
        String lineValidationFile;

//        brRawFile = new BufferedReader(new FileReader(originalFile));
//        lineRawFile = brRawFile.readLine();
//        String [] originalKeys = lineRawFile.split(",");
//        List<String> normalizedOriginalKeysList = Arrays.stream(originalKeys)
//                .map(String::toLowerCase)
//                .collect(Collectors.toList());
//        String [] normalizedOriginalKeys = normalizedOriginalKeysList.toArray(new String[normalizedOriginalKeysList.size()]);

        CharSequence [] itRelated = {
                " it "," it", "it ", " software", "software", "software ", " developer ", " developer", "developer ", " data ", " data", "data ", " cloud ", " cloud", "cloud ",
                " information ", " information", "information ", " technology ", " technology", "technology ", " server ", " server", "server ", " technician ", " technician", "technician ", " system ",
                " system", "system ", " systems ", " systems", "systems ", " network ", " network", "network ", " app ", " app", "app ", " application ", " application", "application ", " applications ",
                " applications", "applications ", " programmer ", " programmer", "programmer ", " technical ", " technical", "technical ", " computer ", " computer", "computer ", " computers ",
                " computers", "computers ", " computing ", " computing", "computing ", " cyber ", " cyber", "cyber ", " web ", " web", "web ", " database ", " database", "database ", " internet ",
                " internet", "internet ", " quality assurance ", " quality assurance", "quality assurance "
        };
        CharSequence [] notItRelated = {
                " recruit ", " recruit", "recruit ", " recruiting ", " recruiting", "recruiting ", " recruiter ",
                " recruiter", "recruiter ", " audit ", " audit", "audit ", " auditor ", " auditor", "auditor "
        };

        CharSequence [] requiredTitles = {
                " manager ", " manager", "manager ", " managers ", " managers", "managers ", " director ", " director",
                "director ", " directors ", " directors", "directors ", " vp ", " vp", "vp ", " vice president ",
                " vice president", "vice president ", " cio ", " cio", "cio ", " ceo ", " ceo", "ceo ",
                " coo ", " coo", "coo ", " cfo ", " cfo", "cfo ", " cto ", " cto", "cto ", " chief information officer ",
                " chief information officer", "chief information officer ", " chief executive officer ",
                " chief executive officer", "chief executive officer ", " chief operating officer ",
                " chief operating officer", "chief operating officer ", " chief financial officer ",
                " chief financial officer", "chief financial officer ", " chief technical officer ",
                " chief technical officer", "chief technical officer "
        };
        CharSequence [] functionalAreaTitles = {
                "EDUCATION", "ENGINEERING", "EXECUTIVE", "FINANCE", "HR", "IT",
                "MARKETING", "MEDICAL", "OPERATIONS", "SALES"
        };

        try {


            brRawFile = new BufferedReader(new FileReader(originalFile));
            int count = 0;
            int count2 = 0;
            while ((lineRawFile = brRawFile.readLine()) != null) {
                int titleIndex = 0;
                String [] title = lineRawFile.split(",");
                for (int i = 0; i < title.length; i++) {
                    for (int j = 0; j < functionalAreaTitles.length; j++) {
                        if (title[i].equals(functionalAreaTitles[j])) {
                            titleIndex = i - 1;
                            break;
                        }
                    }
                }
                int companyIndex = 0;
                String [] company = lineRawFile.split(",");
                for (int i = 0; i < company.length; i++) {
                    for (int j = 0; j < functionalAreaTitles.length; j++) {
                        if (company[i].equals(functionalAreaTitles[j])) {
                            companyIndex = i + 3;
                            break;
                        }
                    }
                }

                int employeesIndex = 0;
                String [] employees = lineRawFile.split(",");
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals("STOP")) {
                        employeesIndex = i - 1;
                        break;
                    }
                }
                String[] originalValues = lineRawFile.split(",");
                String cleansedTitle;
                boolean valid = false;
                for (int i = 0; i < originalValues.length; i++) {
//                    if(titleIndex != 0) {
//                        cleansedTitle = csvTitleCleansingService.cleanse(originalValues[titleIndex]);
//                        if (!valid && validTitle(cleansedTitle)) {
//                            valid = true;
//                        }
//                    }
                    if(titleIndex != 0 && title[titleIndex] != null) {
                        //if (company[companyIndex].trim().toLowerCase().contains("university") || company[companyIndex].trim().toLowerCase().contains("college")|| company[companyIndex].trim().toLowerCase().contains("school")) {
                        cleansedTitle = csvTitleCleansingService.cleanse(title[titleIndex]);
                        if(!valid && validTitle(cleansedTitle)) {
                            valid = true;
                        }
                    }
                    if(employeesIndex != 0 && employees[employeesIndex] != null) {
                        //cleansedTitle = csvTitleCleansingService.cleanse(title[titleIndex]);
                        if(valid && calculateEmployeeCountCeiling(employees, employeesIndex) >= 50) {
                            pw.println(lineRawFile);
                            break;
                        }
                    }
                }
            }
//
//                index = 0;
//                String[] title = lineRawFile.split(",");
//                for (int i = 0; i < title.length; i++) {
//                    for (int j = 0; j < functionalAreaTitles.length; j++) {
//                        if (title[i].equals(functionalAreaTitles[j])) {
//                            index = i;
//                            break;
//                        }
//                    }
//                }
//
//                boolean validationFileCheck = false;
//                brValidationFile = new BufferedReader((new FileReader(validationFile)));
//                while ((lineValidationFile = brValidationFile.readLine()) != null) {
//                    String[] validatedTitle = lineValidationFile.split(",");
//                    if(index != 0) {
//                        if (title[index - 1].toLowerCase().trim().equals(validatedTitle[0].toLowerCase().trim()) && validatedTitle[1].equals("Y")) {
//                            if(!title[index - 1].contains("gov")) {
//                                pw.println(lineRawFile);
//                                count++;
//                                validationFileCheck = true;
//                                break;
//                            }
//                            else {
//                                validationFileCheck = true;
//                                break;
//                            }
//                        }
//                        else if(title[index - 1].toLowerCase().trim().equals(validatedTitle[0].toLowerCase().trim()) && validatedTitle[1].equals("N"))
//                        {
//                            validationFileCheck = true;
//                            break;
//                        }
//                    }
//                }
//                if (!validationFileCheck) {
//                    if (title[index].equals("IT")) {
//                        for (int k = 0; k < requiredTitles.length; k++) {
//                            if (title[index - 1].toLowerCase().trim().equals((requiredTitles[k].toString().trim()))) {
//                                if(title[index - 1].contains("gov")) {
//                                    pw.println(lineRawFile);
//                                    count++;
//                                    System.out.println(count);
//                                    break;
//                                }
//                                else {
//                                    break;
//                                }
//                            }
//                        }
//                    } else {
//
//                        String validTitle = "";
//                        if (index != 0) {
//                            outerloop:
//                            for (int i = 0; i < itRelated.length; i++) {
//                                for (int j = 0; j < notItRelated.length; j++) {
//                                    if (title[index - 1].toLowerCase().contains(itRelated[i]) && !title[index - 1].toLowerCase().contains(notItRelated[j])) {
//                                        validTitle = title[index - 1];
//                                        break outerloop;
//                                    }
//                                }
//                            }
//                        }
//                        for (int k = 0; k < requiredTitles.length; k++) {
//                            if (validTitle.toLowerCase().contains(requiredTitles[k])) {
//                                if(validTitle.contains("gov")) {
//                                    pw.println(lineRawFile);
//                                    count++;
//                                    System.out.println(count);
//                                    break;
//                                }
//                                else {
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (brRawFile != null) {
                try {
                    brRawFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int calculateEmployeeCountCeiling(String [] employee, int employeesIndex) {
        int ceiling = 0;
        boolean test = false;
        String employees = employee[employeesIndex];
        if(employees.contains("000") || employees.contains("000\"")) {
            //System.out.println(employees);
            try {
                if(employee[employeesIndex-2].length() == 1 || employee[employeesIndex-2].length() == 2) {
                    employees = employee[employeesIndex - 2].concat(employee[employeesIndex - 1]).concat(employee[employeesIndex]).replaceAll("\"", " ");
                }
                else if(employee[employeesIndex-1].length() == 1 || employee[employeesIndex-1].length() == 2) {
                    employees = employee[employeesIndex - 1].concat(employee[employeesIndex]).replaceAll("\"", " ");
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
                System.out.println("CATCH");
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
                System.out.println("CATCH");
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
                System.out.println("CATCH");
                ceiling = 0;
            }
        }
        if(test) {
            System.out.println(ceiling);
        }
        return ceiling;
    }

    public static boolean validTitle(String title) {
        CharSequence [] itRelated = {" it "," it", "it ", " software", "software", "software ", " developer ", " developer", "developer ", " data ", " data", "data ", " cloud ", " cloud", "cloud ",
                " information ", " information", "information ", " technology ", " technology", "technology ", " server ", " server", "server ", " technician ", " technician", "technician ", " system ",
                " system", "system ", " systems ", " systems", "systems ", " network ", " network", "network ", " app ", " app", "app ", " application ", " application", "application ", " applications ",
                " applications", "applications ", " programmer ", " programmer", "programmer ", " technical ", " technical", "technical ", " computer ", " computer", "computer ", " computers ",
                " computers", "computers ", " computing ", " computing", "computing ", " cyber ", " cyber", "cyber ", " web ", " web", "web ", " database ", " database", "database ", " internet ",
                " internet", "internet ", " quality assurance ", " quality assurance", "quality assurance "
        };
        boolean validTitle = false;
            if ((title.toLowerCase().trim().contains("engineer"))) {
//                if (title.toLowerCase().trim().contains("") || title.toLowerCase().trim().contains("information technology")) {
//                    validTitle = true;
//                }
                for(int i = 0; i<itRelated.length; i++) {
                    if(title.toLowerCase().trim().contains(itRelated[i])) {
                        validTitle = true;
                        break;
                    }
                }
//            } else if ((title.toLowerCase().trim().contains("vp") || title.toLowerCase().trim().contains("vice president"))) {
//                if (title.toLowerCase().trim().contains("it") || title.toLowerCase().trim().contains("information technology")) {
//                    validTitle = true;
//                } else
//                if(title.toLowerCase().trim().contains("purchasing")) {
//                    validTitle = true;
//                }
//                else {
//                    for(int i = 0; i<itRelated.length; i++) {
//                        if(title.toLowerCase().trim().contains(itRelated[i])) {
//                            validTitle = true;
//                            break;
//                        }
//                    }
//                }
            } else if ((title.toLowerCase().trim().contains("manager"))) {
                for(int i = 0; i<itRelated.length; i++) {
                    if(title.toLowerCase().trim().contains(itRelated[i])) {
                        validTitle = true;
                        break;
                    }
                }
            } else if (title.toLowerCase().trim().contains(" CIO ") || title.toLowerCase().trim().contains(" CIO") || title.toLowerCase().trim().contains("CIO ") ||
                    title.toLowerCase().trim().contains(" chief information officer ") || title.toLowerCase().trim().contains(" chief information officer") || title.toLowerCase().trim().contains("chief information officer ")) {
                    validTitle = true;
            } else if (title.toLowerCase().trim().contains("citrix")) {
                    validTitle = true;
            } else {
                validTitle = false;
            }
        return validTitle;
    }
}