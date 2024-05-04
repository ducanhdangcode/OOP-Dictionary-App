package CommandLine;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> wordList = new ArrayList<>();
    private Trie storeTargetWord;

    public Dictionary() {
        this.wordList = new ArrayList<>();
        this.storeTargetWord = new Trie();
    }

    /**
     * Display wordList.
     */
    public void displayWords() {
        System.out.println("No   |   English         |   Vietnamese");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < wordList.size(); ++i) {
            System.out.printf("%-4d |   %-15s |   %-15s%n", (i + 1), wordList.get(i).getWordTarget(), wordList.get(i).getWordExplain());
        }
    }

    /**
     * Get words.
     *
     * @return wordList
     */
    public ArrayList<Word> getWords() {
        return wordList;
    }

    /**
     * Set words.
     *
     * @param words wordList
     */
    public void setWords(ArrayList<Word> words) {
        this.wordList = words;
    }

    /**
     * add new word.
     *
     * @param word new word
     */
    public void addWord(Word word) {
        int check = storeTargetWord.search(word.getWordTarget());
        if (check == -1) {
            String wordTarget = word.getWordTarget();
            int insertIndex = searchIndexInsert(0, wordList.size() - 1, wordTarget);
            wordList.add(insertIndex, word);
            storeTargetWord.insert(wordTarget, insertIndex);
        } else {
            System.out.println("The word already exists");
        }
    }

    /**
     * remove E word.
     *
     * @param wordTarget E word
     */
    public void removeWord(String wordTarget) {
        int check = storeTargetWord.remove(wordTarget);
        if (check != -1) {
            wordList.remove(check);
            System.out.println("Remove successfully!");
        } else {
            System.out.println("Word not found, no word removed.");
        }
    }

    /**
     * update word meaning.
     *
     * @param wordTarget        word in English
     * @param updateWordExplain word meaning
     */
    public void updateWord(String wordTarget, String updateWordExplain) {
        int index = binarySearchWord(wordTarget);
        if (index != -1) {
            wordList.get(index).setWordExplain(updateWordExplain);
            storeTargetWord.insert(wordTarget, index); // Reinsert at the same position in the Trie
            System.out.println("Word updated successfully!");
        } else {
            System.out.println("Word not found, could not update.");
        }

    }

    /**
     * Find the index for inserting a word in sorted order.
     *
     * @param start      start index
     * @param end        end index
     * @param wordTarget word in English
     * @return index for insertion
     */
    private int searchIndexInsert(int start, int end, String wordTarget) {
        if (end < start) return start;
        int mid = start + (end - start) / 2;
        if (mid == wordList.size()) return mid;
        Word word = wordList.get(mid);
        int compare = word.getWordTarget().compareTo(wordTarget);
        if (compare == 0) return mid;
        if (compare > 0) return searchIndexInsert(start, mid - 1, wordTarget);
        return searchIndexInsert(mid + 1, end, wordTarget);
    }

    /**
     * Find index of word.
     *
     * @param wordTarget word in English
     * @return index or -1 if not found
     */
    private int binarySearchWord(String wordTarget) {
        wordList.sort(new Sort());
        int left = 0;
        int right = wordList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = wordList.get(mid).getWordTarget().compareTo(wordTarget);
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Look up.
     *
     * @param wordTarget word in English
     * @return mean in Vietnamese
     */
    public String lookupWord(String wordTarget) {
        int check = storeTargetWord.search(wordTarget);
        if (check == -1) {
            return null;
        } else {
            return wordList.get(check).getWordExplain();
        }

    }

    /**
     * Get word at index i.
     *
     * @param index index
     */
    public Word getWord(int index) {
        return wordList.get(index);
    }

    public ArrayList<String> searcher(String s, boolean permission) {
        ArrayList<String> suggestions = storeTargetWord.suggestion(s, permission);
        int selfCheck = storeTargetWord.search(s);
        if (selfCheck != -1) {
            suggestions.add(s);
        }
        return suggestions;
    }
}
