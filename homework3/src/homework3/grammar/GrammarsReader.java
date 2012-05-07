package homework3.grammar;

import java.io.FileNotFoundException;
import java.util.List;

public interface GrammarsReader {
    List<GrammarRule> read(String name) throws FileNotFoundException;
}
