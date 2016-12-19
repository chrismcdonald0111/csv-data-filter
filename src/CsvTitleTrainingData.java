import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.emory.mathcs.nlp.component.template.node.NLPNode;
import edu.emory.mathcs.nlp.tokenization.EnglishTokenizer;
import edu.emory.mathcs.nlp.tokenization.Tokenizer;

public class CsvTitleTrainingData {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        CsvTitleCleansingService.initialize();
        CsvTitleCleansingService csvTitleCleansingService = CsvTitleCleansingService.getInstance();

        String originalFile = "/Users/Chris/Downloads/cleansedTitles.csv";
        String inFile = "/Users/Chris/Documents/Developer/CSV Filter/src/replace_word_list.csv.txt";
        BufferedReader reader = null;
        BufferedReader br = null;
        BufferedReader brRawFile = null;
        String lineRawFile;
        String lineValidationFile;
        Scanner cin = new Scanner(System.in);

        File newFile = new File("/Users/Chris/Downloads/.csv");
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        File trainingFile = new File("/Users/Chris/Downloads/cleansed_titles.csv");
        PrintWriter pwTraining = new PrintWriter(new FileWriter(trainingFile));


        CharSequence [] functionalAreaTitles = {
                "EDUCATION", "ENGINEERING", "EXECUTIVE", "FINANCE", "HR", "IT",
                "MARKETING", "MEDICAL", "OPERATIONS", "SALES"
        };

        Map<String, String> managementLevelTitles = new HashMap<>();
        managementLevelTitles.put("chief financial officer", "CLEVEL");
        managementLevelTitles.put("chief executive officer", "CLEVEL");
        managementLevelTitles.put("chief technology officer", "CLEVEL");
        managementLevelTitles.put("chief information officer", "CLEVEL");
        managementLevelTitles.put("chief operating officer", "CLEVEL");
        //managementLevelTitles.put("chief executive", "CLEVEL");
        managementLevelTitles.put("president", "EXEC");
        managementLevelTitles.put("vice president", "EXEC");
        managementLevelTitles.put("director", "DIRECTOR");
        managementLevelTitles.put("manager", "MANAGER");
        int count = 0;
        brRawFile = new BufferedReader(new FileReader(originalFile));
        while ((lineRawFile = brRawFile.readLine()) != null) {
            count++;
            int titleIndex = 0;
            String[] title = lineRawFile.split(",");
//            String cleansedTitle = csvTitleCleansingService.cleanse(title[0]);
//            pw.println(cleansedTitle);
//            for (int i = 0; i < title.length; i++) {
//                for (int j = 0; j < functionalAreaTitles.length; j++) {
//                    if (title[i].equals(functionalAreaTitles[j])) {
//                        titleIndex = i - 1;
//                        break;
//                    }
//                }
//            }
            String managementLevelClass = "STAFF";
            String functionalAreaClass = "UNKNOWN";
            String managementLevel = "";
            String cleansedTitle = csvTitleCleansingService.cleanse(title[0]);
            boolean hasManagementLevel = false;
//            //Tokenizer tokenizer = new EnglishTokenizer();
//            //reader = new BufferedReader(new FileReader(inFile));
//            //List<NLPNode> tokens = tokenizer.tokenize(cleansedTitle);
//            //List<String> trainingData = new ArrayList<>();
//            //for (NLPNode token : tokens) {
//                if(titleIndex != 0) {
                    for (Map.Entry<String, String> map : managementLevelTitles.entrySet()) {
                        if (cleansedTitle.trim().contains(" " + map.getKey().trim() + " ") || cleansedTitle.trim().contains(" " + map.getKey().trim()) || cleansedTitle.trim().contains(map.getKey().trim() + " ") || cleansedTitle.trim().equals(map.getKey().trim())) {
                            managementLevelClass = map.getValue();
                            managementLevel = map.getKey();
                            hasManagementLevel = true;
                            break;
                        }
                        else {
                            hasManagementLevel = false;
                        }
                    }
//                }
//            //}
            //System.out.println(cleansedTitle);
            //System.out.println(managementLevel);
            Tokenizer tokenizer = new EnglishTokenizer();
            List<NLPNode> tokens = tokenizer.tokenize(cleansedTitle);
            List<String> trainingData = new ArrayList<>();
            String newCleansedTitle = "";
            //System.out.println(cleansedTitle);
            boolean invalid = false;
            boolean valid = false;
            int wordCount = 0;
            int mngLevelPosition = 0;
            List<String> wordList = new ArrayList();
            String [] newTitle;
            String tempTitle;
            if(hasManagementLevel) {
                if(cleansedTitle.contains(" vice president ") || cleansedTitle.contains(" vice president") || cleansedTitle.contains("vice president ")) {
                    newTitle = cleansedTitle.split("(vice presiden)");
                    if(newTitle.length < 2) {
                        tempTitle = "";
                    }
                    else {
                        tempTitle = newTitle[1];
                    }
                    wordList.add(0, newTitle[0] + "<START:" + managementLevelClass + "> " + managementLevel + " <END> " + tempTitle);
                }
                else if(cleansedTitle.contains(" chief operating officer ") || cleansedTitle.contains(" chief operating officer") || cleansedTitle.contains("chief operating officer ")) {
                    newTitle = cleansedTitle.split("(chief operating officer)");
                    if(newTitle.length < 2) {
                        tempTitle = "";
                    }
                    else {
                        tempTitle = newTitle[1];
                    }
                    wordList.add(0, newTitle[0] + "<START:" + managementLevelClass + "> " + managementLevel + " <END> " + tempTitle);
                }
                else if(cleansedTitle.contains(" chief information officer ") || cleansedTitle.contains(" chief information officer") || cleansedTitle.contains("chief information officer ")) {
                    newTitle = cleansedTitle.split("(chief information officer)");
                    if(newTitle.length < 2) {
                        tempTitle = "";
                    }
                    else {
                        tempTitle = newTitle[1];
                    }
                    wordList.add(0, newTitle[0] + "<START:" + managementLevelClass + "> " + managementLevel + " <END> " + tempTitle);
                }
                else if(cleansedTitle.contains(" chief technology officer ") || cleansedTitle.contains(" chief technology officer") || cleansedTitle.contains("chief technology officer ")) {
                    newTitle = cleansedTitle.split("(chief technology office)");
                    if(newTitle.length < 2) {
                        tempTitle = "";
                    }
                    else {
                        tempTitle = newTitle[1];
                    }
                    wordList.add(0, newTitle[0] + "<START:" + managementLevelClass + "> " + managementLevel + " <END> " + tempTitle);
                }
                else if(cleansedTitle.contains(" chief executive officer ") || cleansedTitle.contains(" chief executive officer") || cleansedTitle.contains("chief executive officer ")) {
                    newTitle = cleansedTitle.split("(chief executive office)");
                    if(newTitle.length < 2) {
                        tempTitle = "";
                    }
                    else {
                        tempTitle = newTitle[1];
                    }
                    wordList.add(0, newTitle[0] + "<START:" + managementLevelClass + "> " + managementLevel + " <END> " + tempTitle);
                }
                else if(cleansedTitle.contains(" chief financial officer ") || cleansedTitle.contains(" chief financial officer") || cleansedTitle.contains("chief financial officer ")) {
                    newTitle = cleansedTitle.split("(chief financial officer )");
                    if(newTitle.length < 2) {
                        tempTitle = "";
                    }
                    else {
                        tempTitle = newTitle[1];
                    }
                    wordList.add(0, newTitle[0] + "<START:" + managementLevelClass + "> " + managementLevel + " <END> " + tempTitle);

                }
                else {
                    for (NLPNode token : tokens) {
                        if (!managementLevel.equals(token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim())) {
                            newCleansedTitle = newCleansedTitle + " " + token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim();
                            wordCount++;
                            wordList.add(wordCount - 1, token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim());
                        }
                        if (managementLevel.equals(token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim())) {
                            valid = true;
                            wordCount++;
                            mngLevelPosition = wordCount;
                            wordList.add(wordCount - 1, "<START:" + managementLevelClass + "> " + managementLevel + " <END> ");
                        }
                    }
                }
                //System.out.println(wordList.toString());
            }
            else {
                //System.out.println("Moshi moshi?");
                if(count > 348 && count < 1000) {
                    System.out.println(count);
                    System.out.println(cleansedTitle);
                    System.out.println("Enter the managementLevel (Staff, consultant):");
                    managementLevel = cin.nextLine();
                    if (managementLevel.equals("no")) {
                        invalid = true;
                    }
                }
                int thisCount = 0;
                for (NLPNode token : tokens) {
                    if(thisCount == 0) {
                        wordList.add(wordCount, "<START:" + managementLevelClass + "> " + token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim() + " <END> ");
                    }

                    if (!managementLevel.equals(token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim())) {
                        newCleansedTitle = newCleansedTitle + " " + token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim();
                        wordCount++;
                        wordList.add(wordCount, token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim());
                    }
                    if (managementLevel.equals(token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim())) {
                        //alid = true;
                        wordCount++;
                        mngLevelPosition = wordCount;
                        wordList.add(wordCount, "<START:" + managementLevelClass + "> " + token.toString().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim() + " <END> ");
                    }
                    thisCount++;
                }

                //System.out.println(wordList.toString());
            }
            //System.out.println(count);
//            System.out.println(wordCount);
//            System.out.println(mngLevelPosition);
            //System.out.println(newCleansedTitle.replaceAll("[^a-zA-Z]", " ") // Not Alphabetic or Numeric
               //     .replaceAll("\\s+", " ").trim()); // Remove double+ spaces);
            //if(titleIndex != 0) {
            //System.out.println("<START:" + managementLevelClass + "> " + managementLevel + " <END> " + newCleansedTitle);
//            if(count == 348) {
//                break;
//            }
            //System.out.println(wordList.toString().replaceAll(",", "").replaceAll("\\[", "").replaceAll("]", ""));
            if(!invalid) {
                //System.out.println(wordList.toString().replaceAll(",", "").replaceAll("\\[", "").replaceAll("]", ""));
                pwTraining.println(wordList.toString().replaceAll(",", "").replaceAll("\\[", "").replaceAll("]", ""));
            }
//            else if(count < 1000){
//                System.out.println("INVALID");
//            }
            //}

//            if(count == 3) {
//                break;
//            }
        }
        pwTraining.close();
        //pwTraining.flush();
    }
}
