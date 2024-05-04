package CommandLine;

import java.util.Scanner;

public class DictionaryCommandLine {
    private Dictionary dictionary;
    public DictionaryCommandLine(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void showAllWords() {
        dictionary.displayWords();
    }

    public void dictionaryBasic() {
        DictionaryManagement management = new DictionaryManagement(dictionary);
        management.insertFromCommandLine();
        showAllWords();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void displayAdvance() {
        DictionaryManagement management = new DictionaryManagement(dictionary);
        Scanner sc = new Scanner(System.in);
        management.insertFromFile("src/main/resources/data/dictionaries.txt");
        System.out.println("Welcome to My Application!");
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
            switch (choice) {
                case 0:
                    System.out.println("Goodbye!");
                    return;
                case 1:
                    management.insertFromCommandLine();
                    break;
                case 2:
                    System.out.print("Enter a word to remove: ");
                    String wordToRemove = sc.nextLine();
                    wordToRemove = wordToRemove.trim().toLowerCase();
                    management.removeWord(wordToRemove);
                    break;
                case 3:
                    System.out.print("Enter a word to update: ");
                    sc.nextLine();
                    System.out.print("English: ");
                    String wordTarget = sc.nextLine();
                    System.out.print("Vietnamese: ");
                    String updatedWordExplain = sc.nextLine();
                    management.updateWord(wordTarget, updatedWordExplain);
                    break;
                case 4:
                    showAllWords();
                    break;
                case 5:
                    System.out.print("Enter a word to lookup: ");
                    String wordSearch = sc.nextLine();
                    System.out.println(management.dictionaryLookup(wordSearch));
                    break;
                case 6:
                    System.out.print("Enter a prefix to search: ");
                    String searchPrefix = sc.nextLine();
                    management.searchByPrefix(searchPrefix);
                    break;
                case 7:

                    System.out.println("Type C to Play or ESC-Enter to Exit");
                    Scanner choose = new Scanner(System.in);
                    System.out.print("Your choice: ");
                    String option;
                    option = choose.nextLine();
                    while(!option.equals("ESC")) {
                        System.out.println("Type H: Hang Man or G: Giving Word  -> Enter");
                        option = choose.nextLine();
                        if(option.equals("H")){
                            Hangman hm = new Hangman(dictionary);
                            hm.play();
                        }
                        else if (option.equals("G")){
                            GivingWord gv = new GivingWord(dictionary);
                            gv.play();
                        }
                        else{
                            System.out.println("Incorrect input. Exit");
                            break;
                        }
                        System.out.println("Type C to Play or ESC-Enter to Exit");
                        option = choose.nextLine();
                    }

                    break;
                case 8:
                    management.exportToFile(dictionary);
                    System.out.println("Import to file succesfully!");
                    break;
                case 9:
                    management.exportToFile(dictionary);
                    System.out.println("Export to file succesfully!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Dictionary dic = new Dictionary();
        DictionaryCommandLine test = new DictionaryCommandLine(dic);
        test.displayAdvance();
    }
}
