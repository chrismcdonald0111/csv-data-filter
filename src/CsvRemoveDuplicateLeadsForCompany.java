import java.io.*;
import java.util.*;

/**
 * Created by Chris on 11/28/16.
 */
public class CsvRemoveDuplicateLeadsForCompany {
    public static void main(String [] args) throws FileNotFoundException, IOException{
        String originalFile = "/Users/Chris/Downloads/iiPay-remove-5+leadspercompany.csv";
        File newFile = new File("/Users/Chris/Downloads/iiPayAtMost5LeadsPerCompany.csv");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader br= null;
        String line;
        String duplicateLine = "";
        String previousLine = "";
        String test = "";
        int prevCompanyIndex = 0;
        int next = 0;
        int count = 0;
        List<String> companyList = new ArrayList<>();
        List<String> duplicateCompany = new ArrayList<>();
        br = new BufferedReader(new FileReader(originalFile));
        while((line = br.readLine()) != null) {
            companyList.add(line);
            count++;
            if(count == 20) {
                break;
            }
            int companyIndex = 0;
            String [] company = line.split(",");
            for (int i = 0; i < company.length; i++) {
                if (company[i].equals("STOP")) {
                    companyIndex = i - 1;
                    break;
                }
            }

//            System.out.println("START");
//            System.out.println(company[companyIndex]);
//            System.out.println("STOP");
//            if(count == 1) {
//                previousLine = line;
//                //prevCompanyIndex = companyIndex;
//            }
            String [] prevCompany = previousLine.split(",");
            for (int i = 0; i < prevCompany.length; i++) {
                if (prevCompany[i].equals("STOP")) {
                    prevCompanyIndex = i - 1;
                    break;
                }
            }
//            if(count > 0) {
//                test = previousLine;
//            }
//            System.out.println(next);
//            System.out.println(company[companyIndex]);
//            System.out.println(prevCompany[prevCompanyIndex]);
//            if(count > 1 && company[companyIndex].trim().equals(prevCompany[prevCompanyIndex].trim())) {
//                //next = 0;
//                duplicateCompany.add(company[companyIndex]);
//                duplicateCompany.add(prevCompany[prevCompanyIndex]);
//                //duplicateCompany.add(test.split(",")[companyIndex]);
//                while((duplicateLine = br.readLine().split(",")[companyIndex].trim()).equals(company[companyIndex].trim())) {
//                    duplicateCompany.add(duplicateLine);
//                    System.out.println("HERE");
//                }
//                duplicateCompany.add(duplicateLine);
//                System.out.println(duplicateCompany);
//                duplicateCompany.clear();
//            }
//            //next++;
//            previousLine = line;
//            if(!company[companyIndex].equals(prevCompany[prevCompanyIndex])) {
//                previousLine = line;
//                //ext = 0;
//            }
        }

        for(int k = 0; k<companyList.size(); k++) {
            int companyIndex = 0;
            String[] company = companyList.get(k).split(",");
            for (int i = 0; i < company.length; i++) {
                if (company[i].equals("STOP")) {
                    companyIndex = i - 1;
                    break;
                }
            }
        }
//            if(k > 0) {
//                prevCompany = companyList.get(k - 1).split(",");
//                for (int i = 0; i < prevCompany.length; i++) {
//                    if (prevCompany[i].equals("STOP")) {
//                        prevCompanyIndex = i - 1;
//                        break;
//                    }
//                }
//            }
//        if(k > 0 && company[companyIndex].trim().equals(prevCompany[prevCompanyIndex].trim())) {
//                duplicateCompany.add(company[companyIndex]);
//                duplicateCompany.add(prevCompany[prevCompanyIndex]);
//            int newCount = 0;
//                while((prevCompany[prevCompanyIndex].trim()).equals(company[companyIndex].trim())) {
//                    duplicateCompany.add(duplicateLine);
//                }
//                System.out.println(duplicateCompany);
//                duplicateCompany.clear();
//            }
//            previousLine = companyList.get(k);
//        }
    }
}
