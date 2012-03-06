package homework3;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 04.03.12
 * Time: 0:56
 * To change this template use File | Settings | File Templates.
 */
public class GrammarRule {
    public String left = null;
    public String term = null;
    public String nonterm = null;

    public GrammarRule(String l, String t, String n) {
        this.left = l;
        this.term = t;
        this.nonterm = n;
    }
}
