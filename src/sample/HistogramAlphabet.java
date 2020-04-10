package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class HistogramAlphabet {

    //Variables
    private LinkedHashMap<Character, Integer> frequency;
    private LinkedHashMap<Character, Double> probability;

    //Getters
    public LinkedHashMap<Character, Integer> getFrequency() { return frequency; }
    public LinkedHashMap<Character, Double> getProbability() { return probability; }

    //Setters
    public void setFrequency(String filelocation) throws IOException {
        this.frequency = this.readFile(filelocation);
    }
    public void setProbability() {
        this.probability = this.findProbabilities();
    }

    //Methods
    private LinkedHashMap<Character, Integer> readFile(String fileLocation) throws IOException {
        LinkedHashMap<Character, Integer> temp = new LinkedHashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            temp.put(c,0);
        }
        File f = new File(fileLocation);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        int c = 0;
        while ((c = br.read()) != -1) {
            char readChar = (char) c;
            if (Character.isLetter(readChar)) {
                readChar = Character.toLowerCase(readChar);
                temp.put(readChar, temp .get(readChar) + 1);
            }
        }

        temp = temp
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new)
                );

        return temp;
    }

//    public LinkedHashMap<Character, Integer> sortFrequency() {
//        LinkedHashMap<Character, Integer> sorted = this.frequency
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(
//                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new)
//                );
//        return sorted;
//    }

    private LinkedHashMap<Character, Double> findProbabilities() {
        LinkedHashMap<Character, Double> temp = new LinkedHashMap<>();
        int total = 0;
        for (int value: this.frequency.values())
            total += value;

        for (Map.Entry<Character, Integer> entry: this.frequency.entrySet()) {
            double entryProbability = (double) entry.getValue() / total;
            temp.put(entry.getKey(), entryProbability);
        }

        return temp;
    }
}
