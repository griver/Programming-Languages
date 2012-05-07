package homework3.nfa;

import java.io.*;

public class NFA2DOTPrinter implements Closeable {
    private PrintWriter printWriter = null;
    
    public NFA2DOTPrinter(String filename) throws IOException {
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)), true);    
    } 
    
    public void print(NFA automate, NFAParameters parameters) {
        String spaces = "    ";
        printWriter.println("digraph " + parameters.automateName + " {");
        printWriter.println("\trankdir=LR;");

        printWriter.print("\tnode [shape = doublecircle]; " + parameters.finalState);

        printWriter.println(";");
        printWriter.println("\tnode [shape = circle];");

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
