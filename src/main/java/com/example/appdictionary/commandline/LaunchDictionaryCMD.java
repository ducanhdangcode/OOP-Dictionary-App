package com.example.appdictionary.commandline;

import com.example.appdictionary.Main;

import java.util.*;

public class LaunchDictionaryCMD {
    private MainDictionary mainDictionary;

    public LaunchDictionaryCMD(MainDictionary mainDictionary) {
        this.mainDictionary = mainDictionary;
    }

    public void displayAllWords() {
        mainDictionary.DisplayWord();
    }

    public MainDictionary getMainDictionary() {
        return mainDictionary;
    }

    public void setMainDictionary(MainDictionary mainDictionary) {
        this.mainDictionary = mainDictionary;
    }

    public void DisplayConsole() {
        DictionaryManagement management = new DictionaryManagement(mainDictionary);
        Scanner sc = new Scanner(System.in);
        management.Import("D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\WordAndExplain.txt");
        System.out.println("Welcome to my SUPER DICTIONARY!");
        while (true) {
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 0:
                    System.out.println("Goodbye and hope you will come back soon!");
                    return;
                case 1:
                    management.addWordToFileFromCMD();
                    break;
                case 2:
                    System.out.println("Enter the word you want to remove: ");
                    String removingWord = sc.nextLine();
                    removingWord = removingWord.trim().toLowerCase();
                    management.RemoveWordFromCMD(removingWord);
                    break;
                case 3:
                    System.out.print("Enter the word that you want to update: ");
                    sc.nextLine();
                    System.out.print("English word: ");
                    String updatingWord = sc.nextLine();
                    System.out.print("Vietnamese meaning: ");
                    String updatingWordExplain = sc.nextLine();
                    management.UpdateWordFromCMD(updatingWord, updatingWordExplain);
                    break;
                case 4:
                    displayAllWords();
                    break;
                case 5:
                    System.out.print("Enter the word you want to look up: ");
                    String lookupWord = sc.nextLine();
                    System.out.print("Vietnamese meaning: ");
                    System.out.println(management.LookupWord(lookupWord));
                    break;
                case 6:
                    System.out.print("Enter a full-text: ");
                    String fullText = sc.nextLine();
                    management.FullTextSearch(fullText);
                    break;
                case 7:
                    // add
                    break;
                case 8:
                    management.Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\Plain.txt");
                    System.out.println("Imported your file! Please open Plain.txt to see the content!");
                    break;
                case 9:
                    management.Export(mainDictionary, "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\Plain.txt");
                    System.out.println("Exported your file! Please open Plain.txt to see the content!");
                    break;
                default:
                    System.out.println("Our app doesn't support your choice!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        MainDictionary mainDictionary = new MainDictionary();
        LaunchDictionaryCMD cmd = new LaunchDictionaryCMD(mainDictionary);
        cmd.DisplayConsole();
    }
}
