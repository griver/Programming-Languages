package homework3.dfa;

import java.util.HashMap;
import java.util.Set;

public class DFA extends HashMap<Set<String>, DFANode> {
    private DFAParameters parameters = null;

    public void setParameters(DFAParameters parameters) {
        this.parameters = parameters;
    }

    public DFAParameters getParameters() {
        return parameters;
    }
}
