package homework3.nfa;

public class NFAParameters {
    public String finalState = null;
    public String startState = null;
    public String emptyTerminal = null;
    public String automateName = null;
    public NFAParameters(String startState, String finalState, String emptyTerminal, String automateName ) {
        this.emptyTerminal = emptyTerminal;
        this.startState = startState;
        this.finalState = finalState;
        this.automateName = automateName;
    }
}
