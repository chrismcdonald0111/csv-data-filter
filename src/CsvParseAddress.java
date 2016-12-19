import java.io.*;

public class CsvParseAddress {
    public static void main(String [] args) throws FileNotFoundException, IOException {
        String originalFile = "/Users/Chris/Downloads/testAddresses.csv";
        File newFile = new File("/Users/Chris/Downloads/testAddressesNew.csv");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader br = null;
        String address;
        String processedLine = " ";
        int addressStartIndex = 5;
        int addressStopIndex = 0;

        CharSequence[] streetType = {
                "avenue", "ave", "street", "st", "drive", "dr", "court", "ct", "parkway", "pkwy", "way", "road",
                "rd", "lane", "ln", "highway", "hwy", "boulevard", "blvd", "alley", "aly", "clark", "floor"
        };
        CharSequence[] stateList = {
                "ALABAMA", "AL",
                "ALASKA", "AK",
                "ARIZONA", "AZ",
                "ARKANSAS", "AR",
                "CALIFORNIA", "CA",
                "COLORADO", "CO",
                "CONNECTICUT", "CT",
                "DELAWARE", "DE",
                "FLORIDA", "FL",
                "GEORGIA", "GA",
                "HAWAII", "HI",
                "IDAHO", "ID",
                "ILLINOIS", "IL",
                "INDIANA", "IN",
                "IOWA", "IA",
                "KANSAS", "KS",
                "KENTUCKY", "KY",
                "LOUISIANA", "LA",
                "MAINE", "ME",
                "MARYLAND", "MD",
                "MASSACHUSETTS", "MA",
                "MICHIGAN", "MI",
                "MINNESOTA", "MN",
                "MISSISSIPPI", "MS",
                "MISSOURI", "MO",
                "MONTANA", "MT",
                "NEBRASKA",
                "NEVADA", "NV",
                "NEW HAMPSHIRE", "NH",
                "NEW JERSEY", "NJ",
                "NEW MEXICO", "NM",
                "NEW YORK", "NY",
                "NORTH CAROLINA", "NC",
                "NORTH DAKOTA", "ND",
                "OHIO", "OH",
                "OKLAHOMA", "OK",
                "OREGON", "OR",
                "PENNSYLVANIA", "PA",
                "RHODE ISLAND", "RI",
                "SOUTH CAROLINA", "SC",
                "SOUTH DAKOTA", "SD",
                "TENNESSEE", "TN",
                "TEXAS", "TX",
                "UTAH", "UT",
                "VERMONT", "VT",
                "VIRGINIA", "VA",
                "WASHINGTON", "WA",
                "WEST VIRGINIA", "WV",
                "WISCONSIN", "WI",
                "WYOMING", "WY"
        };


        br = new BufferedReader(new FileReader(originalFile));
        address = br.readLine();

        while ((address = br.readLine()) != null) {
            String[] addressComponents = address.split(",");
            int stateIndex = 0;
            int cityIndex = 0;
            String zipCode = "";
            String city = "";
            String addressString = "";
            addressComponents[0] = addressComponents[0].replaceAll(" ", "");
            //outerloop:
//            for (int i = 0; i < addressComponents.length; i++) {
//                for (int j = 0; j < stateList.length; j++) {
//                    if ((addressComponents[i].replaceAll(",", "").replaceAll("\\.", "").replaceAll("\"", "").toUpperCase().trim().equals(stateList[j]))) {
//                        stateIndex = i;
//                        break outerloop;
//                    }
//                }
//            }
//            outerloop:
//            for (int i = 0; i < addressComponents.length; i++) {
//                for (int j = 0; j < streetType.length; j++) {
//                    if (addressComponents[i].replaceAll(",", "").replaceAll("\\.", "").replaceAll("\"", "").toLowerCase().trim().equals(streetType[j])) {
//                        cityIndex = i;
//                        break outerloop;
//                    }
//                }
//            }
//            for(int i = 0; i<cityIndex; i++) {
//                addressString += addressComponents[i];
//            }
//            for(int i = cityIndex + 1; i < stateIndex; i++) {
//                city += " " + addressComponents[i];
//            }
//            if(stateIndex < addressComponents.length - 1) {
//                zipCode = addressComponents[stateIndex + 1];
//            }
//            System.out.println(stateIndex);
//            if (stateIndex == 0) {
//                pw.println(address);
//            }
//            else {
//                pw.println(addressString.replaceAll("\"", "").trim() + "," + city.replaceAll(",", "").trim() + "," + addressComponents[stateIndex] + "," + zipCode.replaceAll("\"", "").trim());
//            }

//            String city = " ";
//            String state = " ";
//            String zipCode = " ";
//            int cityIndex = 0;
//            int stateIndex = 0;
//            String[] addressComponents = address.split(" ");
//            if (address.equals("") || address.equals("not found")) {
//                pw.println(address);
//            }
//            else {
//                outerloop:
//                for (int i = 0; i < addressComponents.length; i++) {
//                    for (int j = 0; j < streetType.length; j++) {
//                        if (addressComponents[i].replaceAll("\\.", "").replaceAll("\"", "").toLowerCase().trim().equals(streetType[j])) {
//                            cityIndex = i;
//                            break outerloop;
//                        }
//                    }
//                }
//
//                outerloop:
//                for (int i = cityIndex; i < addressComponents.length; i++) {
//                    for (int j = 0; j < stateList.length; j++) {
//                        if (addressComponents[i].replaceAll("\"", "").toUpperCase().trim().equals(stateList[j])) {
//                            stateIndex = i;
//                            break outerloop;
//                        }
//                    }
//                }
//                for (int i = cityIndex + 1; i < stateIndex; i++) {
//                    city += " " + addressComponents[i];
//                }
//                state = addressComponents[stateIndex];
//                if(stateIndex < addressComponents.length-1) {
//                    zipCode = addressComponents[stateIndex + 1];
//                }
//                address = " ";
//                for (int i = 0; i < cityIndex + 1; i++) {
//                    address += " " + addressComponents[i];
//                }
//
//                address = address.replaceAll("\"", "").trim();
//                processedLine = address.replaceAll(",", "") + "," + city.replaceAll(",", "").trim() + "," + state.replaceAll(",", "").trim() + "," + zipCode.replaceAll("\"", "").replaceAll(",", "").trim();
//
//                pw.println(processedLine);
//                System.out.print(address + "\n");
//                System.out.print(city.trim() + "\n");
//                System.out.print(state + "\n");
//                System.out.println(zipCode);
        }
        pw.close();
    }
}
