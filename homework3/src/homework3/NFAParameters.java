package homework3;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 05.03.12
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
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
