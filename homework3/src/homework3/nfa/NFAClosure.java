package homework3.nfa;

public class NFAClosure {
    public static void close(NFA nfa) {
        NFAEdge edge = new NFAEdge(
                nfa.getParameters().emptyTerminal,
                nfa.get(nfa.getParameters().startState)
                );

        nfa.get(nfa.getParameters().finalState.get(0)).addEdge(edge);
    }
}
