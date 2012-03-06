package homework3;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 04.03.12
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public class NFANode {
    private String nonterminal = null;
    private boolean active = false;
    private List<NFAEdge> edges = null;
    
    public NFANode(String nonterm) {
        this.nonterminal = nonterm;
        this.edges = new ArrayList<NFAEdge>();
    }
    public void setActivity(boolean value) {
        this.active = value; 
    }
    
    public boolean  getActivity() {
        return this.active;
    }
    
    public String getNonterminal() {
        return nonterminal;
    }
    
    public void addEdge(NFAEdge edge) {
        this.edges.add(edge);
    }
    
    public List<NFAEdge> getEdges() {
        return this.edges;
    }

}
