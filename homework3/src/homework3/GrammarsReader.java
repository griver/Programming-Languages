package homework3;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 04.03.12
 * Time: 0:53
 * To change this template use File | Settings | File Templates.
 */
import java.io.FileNotFoundException;
import java.util.List;

public interface GrammarsReader {
    List<GrammarRule> read(String name) throws FileNotFoundException;
}
