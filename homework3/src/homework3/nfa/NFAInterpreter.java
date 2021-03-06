package homework3.nfa;

import java.util.HashSet;
import java.util.Set;


public class NFAInterpreter {
    private NFA automate = null;
    private NFAParameters parameters = null;
    private Set<NFANode> activeNodes = null;
    
    public NFAInterpreter(NFA automate, NFAParameters parameters ) {
        this.automate = automate;
        this.parameters = parameters; 
        this.activeNodes = new HashSet<NFANode>();
    }
    
    
    public boolean checkWord(String word) {
        NFANode startNode = automate.get(parameters.startState);
        NFANode finalNode = automate.get(parameters.finalState);

        this.activeNodes.add(startNode);

        for(int i = 0; i<word.length(); ++i) {
            String terminal = Character.toString(word.charAt(i));
            this.changeState(terminal);
        }

        return activeNodes.contains(finalNode);
    }


    void changeState(String terminal) {
        Set<NFANode> nodes = new HashSet<NFANode>();
        NFANode destination;
        
        // для каждой активной вершины
        for(NFANode node : activeNodes) { 
            //для каждого ребра исходящего и этой вершины
            for(NFAEdge edge : node.getEdges()) {
                //проверяем можно ли по нему пройти
                if(edge.getTerminal().equals(terminal) ) {
                    destination = edge.getDestination();
                    //добавляем вершину на другом конце в НОВОЕ МНОЖНСТВО активных вершин
                    nodes.add(destination);
                    //пытаемся пройти по всем эпсилон ребрам вершины
                    for(NFAEdge dEdge : destination.getEdges()) {
                        //если удалось то так же добавляем найденные вершины в активные.
                        if(dEdge.getTerminal().equals(parameters.emptyTerminal)) {
                            nodes.add(dEdge.getDestination());
                        }
                    }
                }
            }
        }
        
        activeNodes = nodes;
    }
}
