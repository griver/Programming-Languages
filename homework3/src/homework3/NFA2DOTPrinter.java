package homework3;


import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import java.io.IOException;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 05.03.12
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class NFA2DOTPrinter {
    private PrintWriter printWriter = null;
    
    public NFA2DOTPrinter(String filename) throws IOException {
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)), true);    
    } 
    
    public void printNFA(Map<String, NFANode> automate, NFAParameters param) {
        String spaces = "    ";
        printWriter.println("digraph " + param.automateName + " {");
        
        //можно сделать и одним зиклом но буду использовать более педантичную запись 
        //сначала перечесление вершин
        for(String nodeName : automate.keySet()) {
            printWriter.print(spaces);
            printWriter.println(nodeName + ";");
        }
        
        //теперь ребер
        for(String nodeName : automate.keySet()) {
            NFANode node = automate.get(nodeName);

            for(NFAEdge edge : node.getEdges()) {
                printWriter.print(spaces);
                printWriter.print( nodeName +" -> " + edge.getDestination().getNonterminal() );
                printWriter.println(" [label=\""+ edge.getTerminal() + "\"];");
            }
        }

        printWriter.println("}");
    }

    public void close() {
        if(printWriter != null) {
            printWriter.close();
        }
    }
}
