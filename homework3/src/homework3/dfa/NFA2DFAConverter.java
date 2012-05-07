package homework3.dfa;

import homework3.nfa.NFA;
import homework3.nfa.NFANode;
import homework3.nfa.NFAParameters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFA2DFAConverter {
    private final NFAParameters nfaParameters;
    private final NFA nfa;
    private DFA dfa;

    public NFA2DFAConverter(NFA nfa) {
        this.nfaParameters = nfa.getParameters();
        this.nfa = nfa;
    }

    public DFA getDFA() {
        DFA dfa = new DFA();
        String eps = nfaParameters.emptyTerminal;
        Set<String> alphabet = nfa.getAlphabet();
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
        dfa.setParameters(generateParameters());
        return dfa;
    }

    private DFAParameters generateParameters() {
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

        return new DFAParameters(startState, finalState,
                nfaParameters.automateName + "DFA");

    }

    // Helper method
    private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
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
}
