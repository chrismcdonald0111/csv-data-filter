import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class StringContains {

    public static void main(String[] args) throws IOException{

        String str1 = "107c8093-6f1b-4cee-9e60-d0f58532ae45,,Eric,(Not Provided),45429-5111,Dayton,OH,US,,800,(800) 387-2415,contactus@comfortkeepers.com,It Manager,IT,MANAGER,d58e4fab-4754-4fe9-a485-bfd34254f041,\"CK Franchising, Inc. DBA Comfort Keepers\",,,,,,937-264-1933,937,,,,,,,,,,,,,,250 - 1000,,\"Healthcare, Pharmaceuticals, & Biotech\",,$10 - 50M,,Doctors and Health Care Practitioners,,2014-05-28,CALL,VERIFIED,(800) 387-2415,contactus@comfortkeepers.com,2014-05-28,CALL,VERIFIED,(800) 387-2415,contactus@comfortkeepers.com,Copper,\"Name cannot be verified,\"";
        String validationFile = "/Users/Chris/Downloads/itRelatedTitles.txt";
        String originalFile = "/Users/Chris/Documents/Work/BSS/training data DO NOT DELETE/cleansed_titles_1000.csv";
        File trainingFile = new File("/Users/Chris/Documents/Work/BSS/cleansed_titles_1000_DONE_3.csv");
        PrintWriter pwTraining = new PrintWriter(new FileWriter(trainingFile));
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(originalFile));

        String line;
        CharSequence cs1 = " it ";
        CharSequence [] itRelated = {
                " it "," it", "it ", " software", "software", "software ", " developer ", " developer", "developer ", " data ", " data", "data ", " cloud ", " cloud", "cloud ",
                " information ", " information", "information ", " technology ", " technology", "technology ", " server ", " server", "server ", " technician ", " technician", "technician ", " system ",
                " system", "system ", " systems ", " systems", "systems ", " network ", " network", "network ", " app ", " app", "app ", " application ", " application", "application ", " applications ",
                " applications", "applications ", " programmer ", " programmer", "programmer ", " technical ", " technical", "technical ", " computer ", " computer", "computer ", " computers ",
                " computers", "computers ", " computing ", " computing", "computing ", " cyber ", " cyber", "cyber ", " web ", " web", "web ", " database ", " database", "database ", " internet ",
                " internet", "internet ", " quality assurance ", " quality assurance", "quality assurance "
        };
        int count = 0;
        while((line = br.readLine()) != null) {
            count++;
            if(!line.contains(" vice president <END> t supply")) {
                pwTraining.println(line);
                System.out.println(line);
                System.out.println(count);
            }
        }
        pwTraining.close();
        // string contains the specified sequence of char values
//        for(int i = 0; i<itRelated.length; i++) {
//            boolean retval = str1.contains(itRelated[i]);
//            System.out.println("Method returns : " + retval);
//            if(retval) {
//                System.out.println(itRelated[i]);
//            }
//        }
//        br = new BufferedReader(new FileReader(validationFile));
//        while ((line = br.readLine()) != null) {
//            if(str1.contains(line.trim())) {
//                System.out.println(line.trim());
//                break;
//            }
//        }

//        String employeeLine = "6bba6173-da88-4e08-ae38-9f3d2876e14c,,Terri,Andrews,27215-8700,Burlington,NC,US,,336,3365387000,,It Director,IT,DIRECTOR,051a6514-6f34-4d4e-97f3-0c7de918bf09,Alamance Regional Medical Center Inc,,,,,,,,,,,,,,,,,,,,34343,5,000 to less than 25,000,STOP,,,,,,,,,11/7/13,CALL,VERIFIED,3365387000,,11/7/13,CALL,VERIFIED,3365387000,,Copper,\"Missing email information,\"";
//        int employeesIndex = 0;
//        boolean test = false;
//        String [] employee = employeeLine.split(",");
//        for (int i = 0; i < employee.length; i++) {
//            if (employee[i].equals("STOP")) {
//                employeesIndex = i - 1;
//                break;
//            }
//        }
//        String employees = employee[employeesIndex];
//        String[] originalValues = employees.split(",");
//        int ceiling = 0;
//        if(employees.contains("000")) {
//            System.out.println("HERE");
//            if(employee[employeesIndex-2].length() == 1) {
//                employees = employee[employeesIndex - 2].concat(employee[employeesIndex - 1]).concat(employee[employeesIndex]);
//            }
//            else {
//                employees = employee[employeesIndex - 1].concat(employee[employeesIndex]);
//            }
//        }
//        System.out.println(employees);
//        if(employees.contains("5000")) {
//            System.out.println("THIS");
//            System.out.println(employees);
//            test = true;
//        }
//        employees = employees.replaceAll("(K)", "000").replaceAll("(M)", "000000").replaceAll("(B)", "000000000");
//        if(employees.contains("+ ")) {
//            ceiling = Integer.parseInt(employees.replaceAll("[^0-9]", "").concat("0"));
//        }
//        else if(employees.contains(">")) {
//            ceiling = Integer.parseInt(employees.replaceAll("[^0-9]", "").concat("0"));
//        }
//        else if(employees.contains("<")) {
//            ceiling = Integer.parseInt(employees.replaceAll("[^0-9]", ""));
//        }
//        else if(employees.contains("to less than")) {
//            int[] range = Arrays.stream(employees.replaceAll(",", "").replaceAll("[^0-9]"," ").replaceAll("\\s+", ",").split(","))
//                    .map(String::trim).mapToInt(Integer::parseInt).toArray();
//            Arrays.sort(range);
//            ceiling = range[range.length - 1];
//        }
//        else if(employees.contains("less than")) {
//            employees = employees.replaceAll(",", "").replaceAll("[^0-9]"," ").replaceAll("\\s+", ",");
//            ceiling = Integer.parseInt(employees);
//        }
//        else if(employees.contains("-")) {
//            try {
//                int[] range = Arrays.stream(employees.split("-"))
//                        .map(String::trim).mapToInt(Integer::parseInt).toArray();
//                Arrays.sort(range);
//                ceiling = range[range.length - 1];
//            }
//            catch (NumberFormatException n) {
//                System.out.println("CATCH");
//                ceiling = 0;
//            }
//        }
//        else if(employees.toLowerCase().equals("not provided") || employees.toLowerCase().equals("unknown") || employees.equals("")) {
//            ceiling = -1;
//        }
//        else if(!employees.equals("")){
//            System.out.println(employees);
//            try {
//                ceiling = Integer.parseInt(employees.replaceAll("[^0-9]", ""));
//            }
//            catch (NumberFormatException n) {
//                System.out.println("CATCH");
//                ceiling = 0;
//            }
//        }
//        if(test) {
//            System.out.println(ceiling);
//        }
//        System.out.println(ceiling);

    }
}