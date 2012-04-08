package homework3.dfa;

class DFAEdge {
    private String terminal = null;
    private DFANode destination = null;

    public DFAEdge(String terminal, DFANode destination) {
        this.destination = destination;
        this.terminal = terminal;
    }

    public String getTerminal() {
        return this.terminal;
    }

    public DFANode getDestination() {
        return destination;
    }

}
