package homework3.nfa;

import java.util.*;

public class NFA extends HashMap<String, NFANode> {
    private NFAParameters parameters;

    public void setParameters(NFAParameters parameters) {
        this.parameters = parameters;
    }

    public NFAParameters getParameters() {

        return parameters;
    }

    public Set<String> getAlphabet() {
        Set<String> alphabet = new HashSet<String>();

        for(NFANode node : values()) {
            for(NFAEdge edge : node.getEdges()) {
                alphabet.add(edge.getTerminal());
            }
        }

        return alphabet;
    }

    public void removeEpsilon() {
        // It's not the fastest algorithm, but sufficient for learning and
        // easy to implement.
        for(NFANode node : values()) {
            List<NFAEdge> edgesToRemove = new LinkedList<NFAEdge>();
            for (NFAEdge edge : node.getEdges()) {
                if(edge.getTerminal().equals(parameters.emptyTerminal)) {
                    copyIncomingEdges(node, edge.getDestination());
                    copyOutcomingEdges(edge.getDestination(), node);
                    edgesToRemove.add(edge);
                }
                // Looks like we don't need to mark S1 as final state
                // if S2 was in final state, and there was eps transition
                // between S1 and S2, because we just copying all incoming
                // edges from S1 to S2
            }
            node.getEdges().removeAll(edgesToRemove);
        }
    }

    private void copyIncomingEdges(NFANode first, NFANode second) {
        for(NFANode node : values()) {
            LinkedList<NFAEdge> copy = new LinkedList<NFAEdge>(node.getEdges());
            while(!copy.isEmpty()) {
                NFAEdge edge = copy.pollFirst();
                if(edge.getDestination().equals(first)) {
                    NFAEdge newEdge = new NFAEdge(edge.getTerminal(), second);
                    node.getEdges().add(newEdge);
                    copy.addLast(newEdge);
                }
            }
        }
    }

    private void copyOutcomingEdges(NFANode first, NFANode second) {
        second.getEdges().addAll(first.getEdges());
    }

    public void addPrefix(String prefix) {
        NFA newNfa = new NFA();
        for(Map.Entry<String, NFANode> entry : this.entrySet()) {
            entry.getValue().setNonterminal(prefix + entry.getValue().getNonterminal());
            newNfa.put(prefix + entry.getKey(), entry.getValue());
        }

        List<String> newFinalState = new LinkedList<String>();
        for(String finalState : parameters.finalState) {
            newFinalState.add(prefix + finalState);
        }

        NFAParameters newParameters = new NFAParameters(
                prefix + parameters.startState,
                newFinalState,
                parameters.emptyTerminal,
                parameters.automateName
        );
        setParameters(newParameters);
        this.clear();
        this.putAll(newNfa);
    }

    public void renameEpsilonTo(String emptyTerminal) {
        for(NFANode node : values()) {
            List<NFAEdge> edgesToRemove = new LinkedList<NFAEdge>();
            for (NFAEdge edge : node.getEdges()) {
                if(edge.getTerminal().equals(parameters.emptyTerminal)) {
                    edge.setTerminal(emptyTerminal);
                }
            }
            node.getEdges().removeAll(edgesToRemove);
        }
    }
}
