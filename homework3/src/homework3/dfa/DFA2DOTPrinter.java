package homework3.dfa;

import java.io.*;
import java.util.Map;
import java.util.Set;


public class DFA2DOTPrinter implements Closeable {
    private PrintWriter printWriter = null;

    public DFA2DOTPrinter(String filename) throws IOException {
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)), true);    
    } 
    
    public void print(Map<Set<String>, DFANode> automate, DFAParameters parameters) {
        printWriter.println("digraph " + parameters.automateName + " {");
        printWriter.println("\trankdir=LR;");

        printWriter.print("\tnode [shape = doublecircle]; ");
        for(DFANode node : parameters.finalState) {
            printWriter.print(setToString(node.getKey(), ", ") + " ");
        }

        printWriter.println(";");
        printWriter.println("\tnode [shape = circle];");
        
        for(Set<String> nodeName : automate.keySet()) {
            DFANode node = automate.get(nodeName);

            for(DFAEdge edge : node.getEdges()) {
                printWriter.print("\t" + setToString(nodeName, ", ") + " -> "
                        + setToString(edge.getDestination().getKey(), ", ") );
                printWriter.println(" [label=\""+ edge.getTerminal() + "\"];");
            }
        }

        printWriter.println("}");
    }

    private static String setToString(Set<String> set, String delimiter) {
        if(set.size() == 0) {
            return "\"{Empty}\"";
        }

        String result = "\"{";
        for (String elem : set) {
            result += elem + delimiter;
        }
        result = result.substring(0, result.length() - delimiter.length());
        result += "}\"";
        return result;
    }

    public void close() {
        if(printWriter != null) {
            printWriter.close();
        }
    }
}
