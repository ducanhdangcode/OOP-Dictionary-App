package com.example.appdictionary.commandline;

import javafx.scene.Parent;

import java.io.*;
import java.util.*;

public class DictionaryManagement {
    protected MainDictionary mainDictionary;
    private List<WordManagement> res = new ArrayList<>();

    public DictionaryManagement(MainDictionary mainDictionary) {
        this.mainDictionary = mainDictionary;
    }

    public List<WordManagement> getRes() {
        return this.res;
    }

    public boolean isWordValid(String word) {
        for (int i = 0; i < word.length(); ++i) {
            if (!Character.isLetter(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void Import(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(bufferedReader.ready()) {
                String lines = bufferedReader.readLine();
                String[] partOfWord = lines.split("\t");
                if (partOfWord.length == 2) {
                    if (!isWordValid(partOfWord[0])) {
                        System.out.println(partOfWord[0] + "is not valid because it's not a English word!" +
                                "Please import word to your dictionary!");
                    } else {
                        mainDictionary.add(new WordManagement(partOfWord[0], partOfWord[1]));
                    }
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with the file: " + e);
        } catch (Exception e) {
            System.out.println("Exception Occur: " + e);
        }
    }

    public void Export(MainDictionary mainDictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (WordManagement word : mainDictionary.getListWord()) {
                bufferedWriter.write(word.getWord() + "\t" + word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Exception Occur: " + e);
        }
    }

    public void addWordToFileFromCMD() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type your number of word you want to add: ");
        int word_sz = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < word_sz; ++i) {
            System.out.println("Word " + (i + 1) + ":");
            System.out.print("\tEnglish word: ");
            String addingWord = sc.nextLine();
            System.out.print("\tYour Vietnamese meaning: ");
            String addingWordExplain = sc.nextLine();
            addingWordExplain = addingWordExplain.toLowerCase();
            WordManagement word = new WordManagement(addingWord, addingWordExplain);
            mainDictionary.add(word);
            Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\WordAndExplain.txt");
        }
    }

    public void AddWordGUI(String addingWord, String addingWordExplain) {
        addingWord = addingWord.toLowerCase();
        addingWordExplain = addingWordExplain.toLowerCase();
        WordManagement word = new WordManagement(addingWord, addingWordExplain);
        mainDictionary.add(word);
        Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\dictionaries.txt");
    }

    public void addWordToFavoriteFile(String addingWord, String addingWordExplain) {
        addingWord = addingWord.toLowerCase();
        addingWordExplain = addingWordExplain.toLowerCase();
        WordManagement word = new WordManagement(addingWord, addingWordExplain);
        mainDictionary.add(word);
        Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\Favorite.txt");
    }

    public void RemoveWordFromCMD(String word) {
        mainDictionary.removeWordFromList(word);
        Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\WordAndExplain.txt");
    }

    public void RemoveWordGUI(String word) {
        mainDictionary.removeWordFromList(word);
        Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\WordAndExplain.txt");
    }

    public void RemoveWordFavorite(String word) {
        mainDictionary.removeWordFromList(word);
        Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\Favorite.txt");
    }

    public void UpdateWordFromCMD(String updateWord, String updateWordExplain) {
        updateWord = updateWord.toLowerCase();
        updateWordExplain = updateWordExplain.toLowerCase();
        mainDictionary.updateWord(updateWord, updateWordExplain);
        Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\WordAndExplain.txt");
    }

    public void UpdateWordGUI(String updateWord, String updateWordExplain) {
        updateWord = updateWord.toLowerCase();
        updateWordExplain = updateWordExplain.toLowerCase();
        mainDictionary.updateWord(updateWord, updateWordExplain);
        Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\dictionaries.txt");
    }

    public void AddWordToHistoryFile(String addingWord, String addingWordExplanation) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\HistoryWord.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(addingWord + "\t" + addingWordExplanation);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Exception Occured: " + e);
        }
    }

    public void RemoveWordFromHistoryFile(String removingWord) {
        mainDictionary.removeWordFromList(removingWord);
        Export(mainDictionary, "src/main/resources/com.example.appdictionary/File/HistoryWord.txt");
    }

    public String LookupWord(String wordToLookup) {
        wordToLookup = wordToLookup.toLowerCase();
        return mainDictionary.lookupWord(wordToLookup);
    }

    public void FullTextSearch(String FullTextWord) {
        for (WordManagement word : mainDictionary.getListWord()) {
            if (word.getWord().startsWith(FullTextWord)) {
                res.add(word);
            }
        }
        if (!res.isEmpty()) {
            for (WordManagement word : res) {
                System.out.println(word.getWord() + " : " + word.getWordExplain());
            }
        } else {
            System.out.println("No full-text word found!");
        }
    }
}
