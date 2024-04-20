package CommandLine;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> wordList = new ArrayList<>();

    public Dictionary() {
        this.wordList = new ArrayList<>();
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
        int length = wordList.size();
        int index = searchIndexInsert(0, length - 1, word.getWordTarget());
        if (index <= length && index >= 0) {
            wordList.add(index, word);
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
        int index = binarySearchWord(wordTarget);
        if (index >= 0) {
            wordList.remove(index);
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
        for (Word word : wordList) {
            if (word.getWordTarget().equals(wordTarget)) {
                word.setWordExplain(updateWordExplain);
                System.out.println("Update successfully!");
                return;
            }
        }
        System.out.println("Word not found, update word failed!");
    }

    /**
     * Search word index.
     *
     * @param start      start index
     * @param end        end index
     * @param wordTarget word in English
     * @return index
     */
    private int searchIndexInsert(int start, int end, String wordTarget) {
        if (end < start) return start;
        int mid = start + (end - start) / 2;
        if (mid == wordList.size()) return mid;
        Word word = wordList.get(mid);
        int compare = word.getWordTarget().compareTo(wordTarget);
        if (compare == 0) return -1;
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
        int index = binarySearchWord(wordTarget);
        if (index >= 0) {
            return "Work Explain: " + wordList.get(index).getWordExplain();
        }
        return "Work not found, lookup word failed!";
    }

    /**
     * Get word at index i.
     *
     * @param index index
     */
    public Word getWord(int index) {
        return wordList.get(index);
    }
}
