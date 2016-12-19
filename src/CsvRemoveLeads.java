import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.stream.*;

public class CsvRemoveLeads {

    public static void main(String[] args) throws IOException{

        String crossCheckFile = "/Users/Chris/Downloads/Datapackage_citrix_valid.csv";
        String originalFile = "/Users/Chris/Downloads/2016-10-27 Advantec-Citrix Processed.csv";
        File newFile = new File("/Users/Chris/Downloads/2016-10-27 Advantec-Citrix Processed - Remove Duplicates.csv");
        BufferedReader brCrossCheckFile;
        BufferedReader brOriginalFile = new BufferedReader(new FileReader(originalFile));
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        String lineOriginalFile;
//        String [] originalKeys = lineOriginalFile.split(",");
//        List<String> normalizedOriginalKeysList = Arrays.stream(originalKeys)
//                .map(String::toLowerCase)
//                .collect(Collectors.toList());
//        String [] normalizedOriginalKeys = normalizedOriginalKeysList.toArray(new String[normalizedOriginalKeysList.size()]);

        CharSequence [] functionalAreaTitles = {
                "EDUCATION", "ENGINEERING", "EXECUTIVE", "FINANCE", "HR", "IT",
                "MARKETING", "MEDICAL", "OPERATIONS", "SALES"
        };
        int count = 0;
        int count2 =0;

        while ((lineOriginalFile = brOriginalFile.readLine()) != null) {
            count++;
            String[] originalValues = lineOriginalFile.split(",");
            int firstNameIndex = 1;
            int lastNameIndex = 2;
            int companyNameIndex = 0;
            for (int i = 0; i < originalValues.length; i++) {
                for (int j = 0; j < functionalAreaTitles.length; j++) {
                    if (originalValues[i].equals(functionalAreaTitles[j])) {
                        companyNameIndex = i + 3;
                        //break;
                    }
                }
            }

            brCrossCheckFile = new BufferedReader(new FileReader(crossCheckFile));
            String lineCrossCheckFile = brCrossCheckFile.readLine();
            String [] crossCheckKeys = lineCrossCheckFile.split(",");
//            List<String> normalizedCrossCheckKeysList = Arrays.stream(crossCheckKeys)
//                    .map(String::toLowerCase)
//                    .collect(Collectors.toList());
//            String [] normalizedCrossCheckKeys = normalizedCrossCheckKeysList.toArray(new String[normalizedCrossCheckKeysList.size()]);
            int count3 = 0;
            while((lineCrossCheckFile = brCrossCheckFile.readLine()) != null) {
                count2++;
                count3++;
                String[] crossCheckValues = lineCrossCheckFile.split(",");
                int crossCheckCompanyNameIndex = 10;
//                for (int i = 0; i < normalizedCrossCheckKeys.length; i++) {
//                    if (normalizedCrossCheckKeys[i].toLowerCase().trim().equals("company_name")) {
//                        crossCheckCompanyNameIndex = i;
//                    }
//                }
                int crossCheckFirstNameIndex = 1;
//                for (int i = 0; i < normalizedCrossCheckKeys.length; i++) {
//                    if (normalizedCrossCheckKeys[i].toLowerCase().trim().equals("first_name")) {
//                        crossCheckFirstNameIndex = i;
//                    }
//                }
                int crossCheckLastNameIndex = 2;
//                for (int i = 0; i < normalizedCrossCheckKeys.length; i++) {
//                    if (normalizedCrossCheckKeys[i].toLowerCase().trim().equals("last_name")) {
//                        crossCheckLastNameIndex = i;
//                    }
//                }
                if (crossCheckValues[crossCheckCompanyNameIndex] != null) {
                    if (crossCheckValues[crossCheckCompanyNameIndex].trim().equals(originalValues[companyNameIndex].trim())) {
                        if (crossCheckValues[crossCheckFirstNameIndex].trim().equals(originalValues[firstNameIndex]) && crossCheckValues[crossCheckLastNameIndex].trim().equals(originalValues[lastNameIndex])) {
                            break;
                        }
                    }
                    else if(count3 == 515){
                        pw.println(lineOriginalFile);
                    }
                }
            }
        }
        System.out.println(count);
        System.out.println(count2);
//        while ((lineOriginalFile = brOriginalFile.readLine()) != null) {
//            count++;
//            String[] originalValues = lineOriginalFile.split(",");
//            int firstNameIndex = 1;
//            int lastNameIndex = 2;
//            int companyNameIndex = 0;
//            for (int i = 0; i < originalValues.length; i++) {
//                for (int j = 0; j < functionalAreaTitles.length; j++) {
//                    if (originalValues[i].equals(functionalAreaTitles[j])) {
//                        companyNameIndex = i + 3;
//                        break;
//                    }
//                }
//            }
//
//            String lineCrossCheckFile = brCrossCheckFile.readLine();
//            String [] crossCheckKeys = lineCrossCheckFile.split(",");
//            List<String> normalizedCrossCheckKeysList = Arrays.stream(crossCheckKeys)
//                    .map(String::toLowerCase)
//                    .collect(Collectors.toList());
//            String [] normalizedCrossCheckKeys = normalizedCrossCheckKeysList.toArray(new String[normalizedCrossCheckKeysList.size()]);
//
//
//            while((lineCrossCheckFile = brCrossCheckFile.readLine()) != null) {
//                count2++;
//                String[] crossCheckValues = lineCrossCheckFile.split(",");
//                int crossCheckCompanyNameIndex = 0;
//                for (int i = 0; i < normalizedCrossCheckKeys.length; i++) {
//                    if (normalizedCrossCheckKeys[i].toLowerCase().trim().equals("company_name")) {
//                        crossCheckCompanyNameIndex = i;
//                    }
//                }
//                int crossCheckFirstNameIndex = 0;
//                for (int i = 0; i < normalizedCrossCheckKeys.length; i++) {
//                    if (normalizedCrossCheckKeys[i].toLowerCase().trim().equals("first_name")) {
//                        crossCheckFirstNameIndex = i;
//                    }
//                }
//                int crossCheckLastNameIndex = 0;
//                for (int i = 0; i < normalizedCrossCheckKeys.length; i++) {
//                    if (normalizedCrossCheckKeys[i].toLowerCase().trim().equals("last_name")) {
//                        crossCheckLastNameIndex = i;
//                    }
//                }
//                System.out.println("CROSSCHECK");
//                System.out.println(crossCheckValues[1]);
//                System.out.println(crossCheckValues[2]);
//                System.out.println(crossCheckValues[10]);
//                System.out.println("ORIGINAL");
//                System.out.println(originalValues[1]);
//                System.out.println(originalValues[2]);
//                System.out.println(originalValues[companyNameIndex]);
//                if (crossCheckValues[crossCheckCompanyNameIndex] != null) {
//                    if (!crossCheckValues[crossCheckCompanyNameIndex].trim().equals(originalValues[companyNameIndex].trim())) {
//                        if (!crossCheckValues[crossCheckFirstNameIndex].trim().equals(originalValues[firstNameIndex]) && !crossCheckValues[crossCheckLastNameIndex].trim().equals(originalValues[lastNameIndex])) {
//                            pw.println(lineOriginalFile);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
    }
}