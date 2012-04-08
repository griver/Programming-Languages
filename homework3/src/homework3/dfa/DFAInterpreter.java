package homework3.dfa;

import java.util.Map;
import java.util.Set;


public class DFAInterpreter {
    private DFAParameters parameters = null;
    private Map<Set<String>, DFANode> automate = null;
    private DFANode activeNode = null;

    public DFAInterpreter(Map<Set<String>, DFANode> automate, DFAParameters parameters) {
        this.automate = automate;
        this.parameters = parameters;
    }


    public boolean checkWord(String word) {
        activeNode = automate.get(parameters.startState.getKey());

        for(int i = 0; i<word.length(); ++i) {
            String terminal = Character.toString(word.charAt(i));
            this.changeState(terminal);
        }

        return parameters.finalState.contains(activeNode);
    }


    void changeState(String terminal) {
        for(DFAEdge edge : activeNode.getEdges()) {
            if(edge.getTerminal().equals(terminal) ) {
                activeNode = edge.getDestination();
                break;
            }
        }
    }
}
