import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        DecimalFormat decForm = new DecimalFormat("#.##");
        double freq;
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();

        FileWriter writer = new FileWriter("output.csv", false);
        Parser parser = new Parser(filename);

        List<Map.Entry<String, Integer>> sortedWrds = new ArrayList<>();
        ListIterator<Map.Entry<String, Integer>> wrdsIt = sortedWrds.listIterator();

        sortedWrds = parser.SortMap();
        for (Map.Entry<String, Integer> item : sortedWrds){
            freq =  (double) Math.round(1000 * parser.Freq(item.getKey())) / 1000;
            writer.write(item.getKey() + ";" + item.getValue() + ";" + freq + ";\n");
            writer.flush();
        }
    }
        }