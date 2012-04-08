package homework3.dfa;

import homework3.nfa.NFAEdge;
import homework3.nfa.NFANode;
import homework3.nfa.NFAParameters;

import java.util.*;

public class NFA2DFAConverter {
    private NFAParameters nfaParameters = null;
    private final Map<String, NFANode> nfa;
    private DFAParameters dfaParameters = null;
    private Map<Set<String>, DFANode> dfa;

    // This method should be in other place, in class NFA, but we haven't one
    public static Set<String> getAlphabet(Map<String, NFANode> nfa) {
        Set<String> alphabet = new TreeSet<String>();

        for(NFANode node : nfa.values()) {
            for(NFAEdge edge : node.getEdges()) {
                alphabet.add(edge.getTerminal());
            }
        }

        return alphabet;
    }

    public NFA2DFAConverter(NFAParameters parameters, Map<String, NFANode> nfa) {
        this.nfaParameters = parameters;
        this.nfa = nfa;
    }

    public Map<Set<String>, DFANode> convert() {
        Map<Set<String>, DFANode> dfa = new HashMap<Set<String>,DFANode>();
        String eps = nfaParameters.emptyTerminal;
        Set<String> alphabet = getAlphabet(nfa);
        alphabet.remove(eps);

        Map<Set<NFANode>, Boolean> DStates =
                new HashMap<Set<NFANode>, Boolean>();
        DStates.put(
                nfa.get(nfaParameters.startState).getTerminalClosure(eps, eps),
                Boolean.FALSE);

        while(DStates.containsValue(Boolean.FALSE)) {
            Set<NFANode> T = getKeyByValue(DStates, Boolean.FALSE);
            DStates.put(T, Boolean.TRUE);

            for (String a : alphabet) {
                Set<NFANode> U = new HashSet<NFANode>();
                // Adding all reachable states from state T to state U
                for (NFANode node : T) {
                    U.addAll(node.getTerminalClosure(a, eps));
                }
                U.addAll(getTerminalClosure(U, eps));
                if (!DStates.containsKey(U)) {
                    DStates.put(U, Boolean.FALSE);
                }

                // Generating edge from T to U and nodes if they were not exist
                Set<String> key = generateSet(T);
                Set<String> targetKey = generateSet(U);
                DFANode target = dfa.containsKey(targetKey)
                        ? dfa.get(targetKey)
                        : new DFANode(targetKey);

                // Adding node that equals to U state
                if(!dfa.containsKey(targetKey))
                    dfa.put(targetKey, target);

                // Adding edge from T to U with terminal a
                DFAEdge edge = new DFAEdge(a, target);
                if(dfa.containsKey(key)) {
                    dfa.get(key).addEdge(edge);
                } else {
                    DFANode node = new DFANode(key);
                    node.addEdge(edge);
                    dfa.put(key, node);
                }
            }
        }

        this.dfa = dfa;
        // Generating parameters for our new DFA
        generateParameters();
        return dfa;
    }

    private void generateParameters() {
        Set<DFANode> finalState = new HashSet<DFANode>();
        DFANode startState = null;

        for(DFANode node : dfa.values()) {
            if(node.getKey().contains(nfaParameters.startState)) {
                // Should be assigned only once
                startState = node;
            }
            if(node.getKey().contains(nfaParameters.finalState)) {
                finalState.add(node);
            }
        }

        dfaParameters = new DFAParameters(startState, finalState,
                nfaParameters.automateName + "DFA");
    }

    // Helper method
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Set<NFANode> getTerminalClosure(Set<NFANode> T, String terminal) {
        Set<NFANode> result = new HashSet<NFANode>();
        for (NFANode node : T) {
            result.addAll(node.getTerminalClosure(terminal,
                    nfaParameters.emptyTerminal));
        }
        return result;
    }

    // Converting set of NFA nodes to set of strings.
    private static Set<String> generateSet(Set<NFANode> nodes) {
        Set<String> result = new HashSet<String>();
        for (NFANode node : nodes) {
            result.add(node.getNonterminal());
        }
        return result;
    }

    public DFAParameters getParameters() {
        return dfaParameters;
    }
}
