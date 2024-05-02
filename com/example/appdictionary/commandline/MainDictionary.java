package com.example.appdictionary.commandline;

import com.example.appdictionary.Main;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainDictionary {
    private ArrayList<WordManagement> listWord;
    private Tree storeWord;

    public MainDictionary() {
        this.listWord = new ArrayList<>();
        this.storeWord = new Tree();
    }

    public void DisplayWord() {
        System.out.println("No   |   English         |   Vietnamese");
        System.out.println("---x-------------------x---------------x----");
        for (int i = 0; i < listWord.size(); ++i) {
            System.out.printf("%-4d |   %-15s |   %-15s%n", (i + 1), listWord.get(i).getWord()
                    , listWord.get(i).getWordExplain());
        }
    }

    public ArrayList<WordManagement> getListWord() {
        return listWord;
    }

    public void setListWord(ArrayList<WordManagement> listWord) {
        this.listWord = listWord;
    }

    public void add(WordManagement addingWord) {
        int flag =  storeWord.search(addingWord.getWord());
        if (flag == -1) {
            String _addingWord = addingWord.getWord();
            int insertIndex = searchInsertIndex(0, listWord.size() - 1, _addingWord);
            listWord.add(insertIndex, addingWord);
            storeWord.insert(_addingWord, insertIndex);
        }
    }

    public void removeWordFromList(String removeWord) {
        int idx = storeWord.remove(removeWord);
        if (idx == -1 ) {
            System.out.println("Having errors when removing your word!");
            System.exit(1);
        } else {
            listWord.remove(idx);
            System.out.println("You have removed the word successfully!");
        }
    }

    public void updateWord(String wordToUpdate, String newWordUpdate) {
        int idx = searchWordFromList(wordToUpdate);
        if (idx == -1) {
            System.out.println("World doesn't exist in file or something went wrong!");
            System.exit(1);
        } else {
            listWord.get(idx).setWordExplain(newWordUpdate);
            storeWord.insert(wordToUpdate, idx);
            System.out.println("Updating word successfully!");
        }
    }

    public String lookupWord(String wordToLookup) {
        int idx = searchWordFromList(wordToLookup);
        int flag = storeWord.search(wordToLookup);
        if (flag == -1) {
            System.out.println("Something went wrong when looking up the word!");
            return null;
        } else {
            return listWord.get(idx).getWordExplain();
        }
    }

    private int searchInsertIndex(int start, int end, String insertWord) {
        if (end < start) {
            return start;
        }
        int mid = start + (end - start)/2;
        if (mid == listWord.size()) {
            return mid;
        }
        WordManagement word = listWord.get(mid);
        int cmp = word.getWord().compareTo(insertWord);
        if (cmp == 0) {
            return mid;
        } else if (cmp > 0) {
            searchInsertIndex(start, mid - 1, insertWord);
        }
        return searchInsertIndex(mid + 1, end, insertWord);
    }

    public int searchWordFromList(String searchWord) {
        listWord.sort(new Comparator<WordManagement>() {
            @Override
            public int compare(WordManagement o1, WordManagement o2) {
                return o1.getWord().compareTo(o2.getWord());
            }
        });
        int l = 0;
        int r = listWord.size() - 1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            int check = listWord.get(mid).getWord().compareTo(searchWord);
            if (check == 0) {
                return mid;
            } else if (check < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public WordManagement getWord(int idx) {
        return listWord.get(idx);
    }

    public ArrayList<String> searching(String str, boolean permitted) {
        ArrayList<String> suggest = storeWord.suggestion(str, permitted);
        int SelfFlag = storeWord.search(str);
        if (SelfFlag != -1) {
            suggest.add(str);
        }
        return suggest;
    }
}
