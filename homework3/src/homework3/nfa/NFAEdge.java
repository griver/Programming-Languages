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
}
