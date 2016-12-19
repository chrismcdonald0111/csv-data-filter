import java.io.*;
import java.util.HashMap;

public class TitleCleanser {
    public static void main(String [] args) throws IOException{
        String rawTitlesFile = "/Users/Chris/Downloads/itRelatedTitles.txt";
        //PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        BufferedReader br = null;
        String line;
        String normalizeTitle;
        int count = 0;
        int index = 0;

        CharSequence [] stopWords = {
                " not ", " not", "not ", " in ", " in", "in ", " and ", " and", "and ", " or ", " or", "or ", " is ", " is", "is ",
                " hlink ", " hlink", "hlink ", " no ", " no", "no ", " longer ", " longer", "longer ", " there ", " there", "there ",
                " the ", " the", "the ", " a ", " a", "a ", " an ", " an", "an "
        };

        HashMap<String, String> misSpellings = new HashMap<>();
        misSpellings.put(" acces ", " access ");
        misSpellings.put(" acess ", " access ");
        misSpellings.put(" acount ", " account ");
        misSpellings.put(" accont ", " account ");
        misSpellings.put(" accoun ", " account ");
        misSpellings.put(" accoutn ", " account ");
        misSpellings.put(" accounts ", " account ");
        misSpellings.put(" accoutning ", " accounting ");
        misSpellings.put(" acoustical ", " acoustic ");
        misSpellings.put(" acqusition ", " acquisition");
        misSpellings.put(" acquisitions ", " acquisition ");
        misSpellings.put(" activations ", " activation ");
        misSpellings.put(" additives ", " additive ");
        misSpellings.put(" adjuct ", " adjunct ");
        misSpellings.put(" aministrator ", " administrator ");
        misSpellings.put(" adminstrator ", " administrator ");
        misSpellings.put(" administartor ", " administrator ");
        misSpellings.put(" administravite ", " adminstrator ");
        misSpellings.put(" adminv ", " admin ");
        misSpellings.put(" admissions ", " admission ");
        misSpellings.put(" advistory ", "advisory ");
        misSpellings.put(" affliate ", " affiliate ");
        misSpellings.put(" analyt ", " analyst ");
        misSpellings.put(" anlst ", " analyst ");
        misSpellings.put(" anayst ", " analyst ");
        misSpellings.put(" ananlyst ", " analyst ");
        misSpellings.put(" aocciate ", " associate ");
        misSpellings.put(" asociate ", " associate ");
        misSpellings.put(" appled ", " apple ");
        misSpellings.put(" applicaton ", " application ");
        misSpellings.put(" applications ", " application ");
        misSpellings.put(" assiatant ", " assistant ");
        misSpellings.put(" assiistant ", " assistant ");
        misSpellings.put(" assisatant ", " assistant ");
        misSpellings.put(" assisitant ", " assistant ");
        misSpellings.put(" assisstant ", " assistant ");
        misSpellings.put(" assistanat ", " assistant ");
        misSpellings.put(" aassistant ", " assistant ");
        misSpellings.put(" assesment ", " assessment ");
        misSpellings.put(" budge ", " budget ");
        misSpellings.put(" businenss ", " business ");
        misSpellings.put(" bulding ", " building ");
        misSpellings.put(" developement ", " development ");


        misSpellings.put(" engneer ", " engineer ");
        misSpellings.put(" ma ", " manager ");
        misSpellings.put(" man ", " manager ");
        misSpellings.put(" mana ", " manager ");
        misSpellings.put(" manf ", " manager ");
        misSpellings.put(" manda ", " manager ");
        misSpellings.put(" manag ", " manager ");
        misSpellings.put(" manage ", " manager ");
        misSpellings.put(" manger ", " manager ");
        misSpellings.put(" mangerr ", " manager ");
        misSpellings.put(" managers ", " manager ");
        misSpellings.put(" manufacuturing ", " manufacturing ");
        misSpellings.put(" masters ", " master ");
        misSpellings.put(" materials ", " material ");
        misSpellings.put(" mathematics ", " math ");
        misSpellings.put(" mathematical ", " math ");
        misSpellings.put(" mathematician ", " math ");
        misSpellings.put(" mathematics ", " math ");
        misSpellings.put(" techn ", " technology ");
        misSpellings.put(" tec ", " technology ");

        misSpellings.put(" traffc ", " traffic ");

        HashMap<String, String> abbreviations = new HashMap<>();
        abbreviations.put(" net ", " network ");
        abbreviations.put(" acad ", " academic ");
        abbreviations.put(" serv ", " service ");
        abbreviations.put(" acct ", " account ");
        abbreviations.put(" acctg ", " accounting ");
        abbreviations.put(" accting ", " accounting ");
        abbreviations.put(" actg ", " acting ");
        abbreviations.put(" adj ", " adjunct ");
        abbreviations.put(" adm ", " admin ");
        abbreviations.put(" admn ", " admin ");
        abbreviations.put(" admnstr ", " administrator ");
        abbreviations.put(" adt ", " adult ");
        abbreviations.put(" adv ", " advanced ");
        abbreviations.put(" advg ", " advertising ");
        abbreviations.put(" afl ", " affiliate ");
        abbreviations.put(" ag ", " agency ");
        abbreviations.put(" appl ", " apple ");
        abbreviations.put(" applic ", " application ");
        abbreviations.put(" asoc ", " associate ");
        abbreviations.put(" assc ", " associate ");
        abbreviations.put(" assis ", " assistant");
        abbreviations.put(" biomdcl ", " biomedical ");
        abbreviations.put(" biomed ", " biomedical ");
        abbreviations.put(" biopharm ", " biopharmacy ");
        abbreviations.put(" manuf ", " manufacturing ");
        abbreviations.put(" mgmt ", " management ");
        abbreviations.put(" serv ", " service ");




        br = new BufferedReader(new FileReader(rawTitlesFile));
        while ((line = br.readLine()) != null) {
            //System.out.println(line);
            normalizeTitle = line.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s+", " ").trim();
            System.out.println(normalizeTitle);
            for(CharSequence stopWord : stopWords) {
                if(normalizeTitle.contains(stopWord)) {
                    normalizeTitle = normalizeTitle.replaceAll(stopWord.toString(), "");
                }
            }
            System.out.println(normalizeTitle);
        }
    }
}
