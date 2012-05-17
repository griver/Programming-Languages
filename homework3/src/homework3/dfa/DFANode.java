package homework3.dfa;

import java.util.HashSet;
import java.util.Set;

class DFANode {
    private Set<String> key = null;
    private HashSet<DFAEdge> edges = null;
    
    public DFANode(Set<String> key) {
        this.key = key;
        this.edges = new HashSet<DFAEdge>();
    }

    public Set<String> getKey() {
        return key;
    }
    
    public void addEdge(DFAEdge edge) {
        this.edges.add(edge);
    }
    
    public Set<DFAEdge> getEdges() {
        return this.edges;
    }

}
