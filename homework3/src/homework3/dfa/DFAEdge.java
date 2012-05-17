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

    @Override
    public int hashCode() {
        int result = terminal != null ? terminal.hashCode() : 0;
        result = 31 * result +
                (destination != null
                ? destination.hashCode()
                : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DFAEdge dfaEdge = (DFAEdge) o;

        if (destination != null
                ? !destination.equals(dfaEdge.destination)
                : dfaEdge.destination != null)
            return false;
        if (terminal != null
                ? !terminal.equals(dfaEdge.terminal)
                : dfaEdge.terminal != null)
            return false;

        return true;
    }
}
