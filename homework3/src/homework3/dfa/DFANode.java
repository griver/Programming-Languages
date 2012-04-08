package homework3.dfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DFANode {
    private Set<String> key = null;
    private List<DFAEdge> edges = null;
    
    public DFANode(Set<String> key) {
        this.key = key;
        this.edges = new ArrayList<DFAEdge>();
    }

    public Set<String> getKey() {
        return key;
    }
    
    public void addEdge(DFAEdge edge) {
        this.edges.add(edge);
    }
    
    public List<DFAEdge> getEdges() {
        return this.edges;
    }

}
