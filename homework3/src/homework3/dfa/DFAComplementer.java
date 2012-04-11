package homework3.dfa;

import java.util.HashSet;
import java.util.Set;

public class DFAComplementer {
    public static void complement(DFA dfa) {
        DFAParameters p = dfa.getParameters();
        Set<DFANode> allStates = new HashSet<DFANode>(dfa.values());
        allStates.removeAll(p.finalState);
        dfa.getParameters().finalState = allStates;
    }
}
