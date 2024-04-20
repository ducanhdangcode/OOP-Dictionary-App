package CommandLine;

public class Word {
    private String wordTarget;
    private String wordExplain;

    public Word() {

    }

    /**
     * Init word.
     *
     * @param wordTarget       word in English
     * @param wordExplain Explain - translate into Vietnamese
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget.trim().toLowerCase();
        this.wordExplain = wordExplain.trim().toLowerCase();
    }

    /**
     * Get word.
     *
     * @return English word - meaning in Vietnamese
     */
    public String getWordTarget() {
        return this.wordTarget;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    /**
     * Set word.
     *
     * @param wordTarget       English
     */
    public void setWord(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    /**
     * Set word.
     * @param wordExplain meaning.
     */

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }
}
