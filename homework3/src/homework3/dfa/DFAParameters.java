package homework3.dfa;

import java.util.Set;

public class DFAParameters {
    public Set<DFANode> finalState = null;
    public DFANode startState = null;
    public String automateName = null;

    public DFAParameters(DFANode startState, Set<DFANode> finalState, String automateName) {
        this.startState = startState;
        this.finalState = finalState;
        this.automateName = automateName;
    }
}
