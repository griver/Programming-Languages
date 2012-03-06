package homework3;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 04.03.12
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class NFAEdge {
    private String terminal = null;
    private NFANode destination = null;
    
    public NFAEdge(String term, NFANode dest) {
        this.destination = dest;
        this.terminal = term;
    }
    
    public String getTerminal() {
        return this.terminal;
    }

    //Ну можно пройти по ребру и активировать его а можно получить вершину с другого конца и активировать вручную.
    public NFANode getDestination() {
        return destination;
    }

}
