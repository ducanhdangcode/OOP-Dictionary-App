package CommandLine;

import java.util.Comparator;

public class Sort implements Comparator<Word> {
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWordTarget().compareTo(o2.getWordTarget());
    }
}
