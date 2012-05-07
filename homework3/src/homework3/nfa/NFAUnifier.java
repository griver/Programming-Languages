package homework3.nfa;

public class NFAUnifier {
    NFAParameters parameters = null;

    public NFAUnifier(NFAParameters parameters) {
        this.parameters = parameters;
    }

    public NFA unify(NFA first, NFA second) {
        NFA nfa = new NFA();

        first.addPrefix("f_");
        second.addPrefix("s_");
        first.renameEpsilonTo(parameters.emptyTerminal);
        second.renameEpsilonTo(parameters.emptyTerminal);

        NFANode newStartState = new NFANode(parameters.startState);
        newStartState.addEdge(
                new NFAEdge(parameters.emptyTerminal,
                        first.get(first.getParameters().startState)));
        newStartState.addEdge(
                new NFAEdge(parameters.emptyTerminal,
                        second.get(second.getParameters().startState)));

        NFANode newFinalState = new NFANode(parameters.finalState);

        first.get(first.getParameters().finalState).addEdge(
                new NFAEdge(parameters.emptyTerminal, newFinalState)
        );

        second.get(second.getParameters().finalState).addEdge(
                new NFAEdge(parameters.emptyTerminal, newFinalState)
        );

        nfa.putAll(first);
        nfa.putAll(second);
        nfa.put(parameters.startState, newStartState);
        nfa.put(parameters.finalState, newFinalState);
        nfa.setParameters(parameters);
        return nfa;
    }
}
