package homework3.nfa;

import java.util.HashSet;
import java.util.Set;

public class NFANode {
    private String nonterminal = null;
    private boolean active = false;
    private HashSet<NFAEdge> edges = null;
    
    public NFANode(String nonterm) {
        this.nonterminal = nonterm;
        this.edges = new HashSet<NFAEdge>();
    }
    
    public String getNonterminal() {
        return nonterminal;
    }

    public void setNonterminal(String nonterminal) {
        this.nonterminal = nonterminal;
    }

    public void addEdge(NFAEdge edge) {
        this.edges.add(edge);
    }
    
    public Set<NFAEdge> getEdges() {
        return this.edges;
    }

    public Set<NFANode> getTerminalClosure(String terminal,
                                           String emptyTerminal) {
        Set<NFANode> result = new HashSet<NFANode>();
        if(terminal.equals(emptyTerminal)) {
            result.add(this);
        }
        for (NFAEdge edge : edges) {
            if(edge.getTerminal().equals(terminal)) {
                result.add(edge.getDestination());
            }
        }
        return result;
    }
}
