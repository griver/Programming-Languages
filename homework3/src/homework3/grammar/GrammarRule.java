package homework3.grammar;

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
