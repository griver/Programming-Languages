package homework3;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;    
import java.util.List;
import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 05.03.12
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
public class NFAInterpreter {
    private Map<String, NFANode> automate = null;
    private NFAParameters parameters = null;
    private Set<NFANode> activeNodes = null;
    
    public NFAInterpreter(Map<String, NFANode> automate, NFAParameters parameters ) {
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
            this.changeStatement(terminal);
        }

        return activeNodes.contains(finalNode);
    }


    public void changeStatement( String terminal ) {
        Set<NFANode> nodes = new HashSet<NFANode>();
        NFANode destination = null;
        
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


    public Set<NFANode> getActiveNodes() {
        return this.activeNodes;
    }
}
