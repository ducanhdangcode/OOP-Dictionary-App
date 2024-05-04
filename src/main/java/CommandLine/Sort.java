package CommandLine;

import java.util.Comparator;

public class Sort implements Comparator<WordManagement> {
    @Override
    public int compare(WordManagement o1, WordManagement o2) {
        return o1.getWordTarget().compareTo(o2.getWordTarget());
    }
}
