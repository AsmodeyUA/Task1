package com.company;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try{
            Path fileName = Paths.get(args[0]);
            Integer N=Integer.valueOf(args[1]);
            Map<String, Integer> countWords = new HashMap<>();
            Stream<String> lines = Files.lines(fileName, StandardCharsets.UTF_8);
            //Read all lines from file
            lines.forEach((line)->{
                String[] words = line.split("\\s+");
            //Split every line to words
                for ( String word : words) {
                    String wordCleaned=word.replaceAll("[^\\w]", "");
                    //check if previously word was exist
                    if (countWords.containsKey(wordCleaned)) {
                        countWords.put(wordCleaned, countWords.get(wordCleaned) + 1);
                    } else {
                        if (wordCleaned.length()>0)
                            countWords.put(wordCleaned,1);
                    }
                }
            });

            //Convert HashMap to sorted list with input limitation
            final List<String> sortedStats = countWords.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .map(Map.Entry::getKey).limit(N)
                    .collect(Collectors.toList());

            //countWords.forEach((k,v)->System.out.println(k + " : " + v));
            //Printout Sorted result List with n most used words from the file
            sortedStats.forEach(System.out::println);

        }catch (Exception e){
            //Simple error handling
            System.out.println("Check input data. Somethings goes wrong. Error:"+e);
        }
    }
}
