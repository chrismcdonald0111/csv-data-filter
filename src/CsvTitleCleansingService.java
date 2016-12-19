import edu.emory.mathcs.nlp.component.template.node.NLPNode;
import edu.emory.mathcs.nlp.tokenization.EnglishTokenizer;
import edu.emory.mathcs.nlp.tokenization.Tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvTitleCleansingService {
    private static CsvTitleCleansingService instance;
    private static List<String> stopWordList = new ArrayList<>();
    private static Map<String, String> replaceWordMap = new HashMap<>();

    public static void initialize() {
        instance = new CsvTitleCleansingService();
        loadStopWordList(stopWordList, "/stop_word_list.txt");
        loadReplaceWordList(replaceWordMap, "/replace_word_list.csv.txt");
    }

    public static CsvTitleCleansingService getInstance() {
        return instance;
    }

    private Tokenizer tokenizer = new EnglishTokenizer();

    private CsvTitleCleansingService() {
        System.out.println("Initializing service");
    }

    public String cleanse(String title) {
        String partialCleansedTitle = title
                .replaceAll("[^a-zA-Z0-9]", " ") // Not Alphabetic or Numeric
                .replaceAll("\\s+", " ").trim(); // Remove double+ spaces

        List<NLPNode> tokens = tokenizer.tokenize(partialCleansedTitle);
        List<String>  cleansedTokens = new ArrayList<String>();
        for(NLPNode token : tokens) {
            String partialCleansedToken = removeStopWords(token.getWordFormSimplifiedLowercase());
            String cleansedToken = replaceWords(partialCleansedToken);
            cleansedTokens.add(cleansedToken);
        }

        String cleansedTitle = cleansedTokens.stream().collect(Collectors.joining(" ")).trim().replaceAll("\\s+", " ");

        //System.out.println("Title cleansing request, original title: " + title + " cleansed title: " + cleansedTitle);

        return cleansedTitle;
    }

    private String removeStopWords(String title) {
        String partialCleansedTitle = title;
        for(String stopWord : stopWordList){
            if(title.trim().equals(stopWord.trim())) {
                partialCleansedTitle = " ";
                break;
            }
        }
        return partialCleansedTitle;
    }

    private String replaceWords(String title) {
        String cleansedTitle = title;
        for(String key: replaceWordMap.keySet()) {
            if(title.trim().equals(key.trim())) {
                cleansedTitle = replaceWordMap.get(key).trim();
                break;
            }
        }
        return cleansedTitle;
    }

    private static List<String> loadStopWordList(List<String> stopWordList, String resourceName) {
        InputStream fileIn = CsvTitleCleansingService.class.getResourceAsStream(resourceName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn));

        try
        {
            String line = reader.readLine();
            while(line != null)
            {
                stopWordList.add(line);
                line = reader.readLine();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                fileIn.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return stopWordList;
    }

    private static Map<String, String> loadReplaceWordList(Map<String, String> map, String resourceName) {
        InputStream fileIn = CsvTitleCleansingService.class.getResourceAsStream(resourceName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn));

        try
        {
            String line = reader.readLine();
            while(line != null)
            {
                String[] replaceWords = line.split(",");
                map.put(replaceWords[0], replaceWords[1]);
                line = reader.readLine();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                fileIn.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return map;
    }
}