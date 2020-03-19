import java.io.*;
import java.lang.Character;
import java.util.*;

import static java.lang.Character.isLetterOrDigit;

public class Parser {

    private java.io.Reader reader = null;
    private HashMap<String, Integer> Words = new HashMap<>();
    private int numOfWords;


    Parser(String fin){
        try{
            reader = new InputStreamReader(new FileInputStream(fin));
            int intSymbol = reader.read();
            int lenWord = 0;
            char symbol = (char) intSymbol;
            StringBuilder sb = new StringBuilder();
            numOfWords = 0;
            while (intSymbol != -1){
                if (isLetterOrDigit(symbol)){
                    sb.insert(lenWord, symbol); //make a word
                    lenWord = sb.length();
                }
                else if (lenWord != 0) {
                    if (Words.containsKey(sb.toString())){
                        Integer val = Words.get(sb.toString()) + 1;
                        Words.put(sb.toString(), val);
                    }
                    else {
                        Words.put(sb.toString().toLowerCase(), 1);
                    }
                    sb.delete(0, lenWord);
                    lenWord = 0;
                    numOfWords++;
                }
                intSymbol = reader.read();
                symbol = (char) intSymbol;
            }
        }
        catch (IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (null != reader){
                try{
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    List<Map.Entry<String, Integer>> SortMap(){
        List<Map.Entry<String, Integer>> toSort = new ArrayList<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : Words.entrySet()) {
            toSort.add(stringIntegerEntry);
        }
        toSort.sort(HashMap.Entry.<String, Integer>comparingByValue().reversed());
        return toSort;
    }

    double Freq(String word){
        return ((double)Words.get(word)) / numOfWords * 100;
    }
}
