package homework3.dfa;

public class DFAInterpreter {
    private DFAParameters parameters = null;
    private DFA automate = null;
    private DFANode activeNode = null;

    public DFAInterpreter(DFA automate, DFAParameters parameters) {
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
