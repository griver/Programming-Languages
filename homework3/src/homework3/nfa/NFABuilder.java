package homework3.nfa;

import homework3.grammar.GrammarRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 04.03.12
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */

public class NFABuilder {
    private NFAParameters parameters = null;

    public NFABuilder( NFAParameters parameters) {
        this.parameters = parameters;
    }

    public Map<String, NFANode> build(List<GrammarRule> rules) {
        //НКА хранится в виде списка вершин, каждая вершина
        // хранит ребра ведущие из неё
        Map<String, NFANode> automate = new HashMap<String,NFANode>();

        for(GrammarRule rule : rules) {
            if(!automate.containsKey(rule.left)) {
                automate.put(rule.left, new NFANode(rule.left) );
            }
        }
        automate.put(parameters.finalState, new NFANode(parameters.finalState));

        for(GrammarRule rule : rules) {
            NFANode node = automate.get(rule.left);

            String nonterm = rule.nonterm;
            String term = rule.term;
            if(nonterm == null) {
                nonterm = parameters.finalState;
            }
            if(term == null) {
                term = parameters.emptyTerminal;
            }

            node.addEdge(new NFAEdge(term, automate.get(nonterm)));
        }
        
        return automate;
    }
}
