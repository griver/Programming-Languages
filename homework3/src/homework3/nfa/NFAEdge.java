package homework3.nfa;

class NFAEdge {
    private String terminal = null;
    private NFANode destination = null;

    public NFAEdge(String term, NFANode dest) {
        this.destination = dest;
        this.terminal = term;
    }

    public String getTerminal() {
        return this.terminal;
    }

    //Ну можно пройти по ребру и активировать его а можно получить вершину
    // с другого конца и активировать вручную.
    public NFANode getDestination() {
        return destination;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NFAEdge nfaEdge = (NFAEdge) o;

        if (destination != null
                ? !destination.equals(nfaEdge.destination)
                : nfaEdge.destination != null)
            return false;
        if (terminal != null
                ? !terminal.equals(nfaEdge.terminal)
                : nfaEdge.terminal != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = terminal != null ? terminal.hashCode() : 0;
        result = 31 * result +
                (destination != null ? destination.hashCode() : 0);
        return result;
    }
}
