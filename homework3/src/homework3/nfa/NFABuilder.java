package homework3.nfa;

import homework3.grammar.GrammarRule;

import java.util.List;

public class NFABuilder {
    private NFAParameters parameters = null;

    public NFABuilder(NFAParameters parameters) {
        this.parameters = parameters;
    }

    public NFA build(List<GrammarRule> rules) {
        //НКА хранится в виде списка вершин, каждая вершина
        // хранит ребра ведущие из неё
        NFA automate = new NFA();
        automate.setParameters(parameters);

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
