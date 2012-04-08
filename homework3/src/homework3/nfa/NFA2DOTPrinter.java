package homework3.nfa;


import java.io.*;
import java.util.Map;


public class NFA2DOTPrinter implements Closeable {
    private PrintWriter printWriter = null;
    
    public NFA2DOTPrinter(String filename) throws IOException {
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)), true);    
    } 
    
    public void print(Map<String, NFANode> automate, NFAParameters param) {
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
