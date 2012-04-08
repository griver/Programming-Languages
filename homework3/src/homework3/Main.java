package homework3;

import homework3.dfa.*;
import homework3.grammar.GrammarRule;
import homework3.grammar.GrammarsReader;
import homework3.grammar.RegularGrammarsReader;
import homework3.nfa.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        NFAParameters parameters = new NFAParameters("S", "Final", "eps", "Simple");
        Scanner in = new Scanner(System.in);

        try {                                                     
            GrammarsReader reader = new RegularGrammarsReader();
            NFABuilder builder = new NFABuilder(parameters);
            List<GrammarRule> rules = reader.read("input.txt");

            Map<String, NFANode> automate = builder.build(rules);

            NFA2DFAConverter converter =
                    new NFA2DFAConverter(parameters, automate);
            Map<Set<String>, DFANode> dfa = converter.convert();
            DFAParameters dfaParameters = converter.getParameters();

            NFA2DOTPrinter printer = new NFA2DOTPrinter("outputNfa.dot");
            printer.print(automate, parameters);
            printer.close();

            DFA2DOTPrinter printerDfa = new DFA2DOTPrinter("outputDfa.dot");
            printerDfa.print(dfa, dfaParameters);
            printerDfa.close();

            System.out.println("Input the word: ");
            String word = in.nextLine();

            NFAInterpreter interpreter = new NFAInterpreter(automate, parameters);
            if(interpreter.checkWord(word)) {
                System.out.println("Word: "+ word + " was accepted by NFA.");
            } else {
                System.out.println("Word: "+ word + " was rejected by NFA.");
            }

            DFAInterpreter dfaInterpreter = new DFAInterpreter(dfa, dfaParameters);
            if(dfaInterpreter.checkWord(word)) {
                System.out.println("Word: "+ word + " was accepted by DFA.");
            } else {
                System.out.println("Word: "+ word + " was rejected by DFA.");
            }


        } catch (FileNotFoundException e) {
            System.err.println("Can't find file");
            
        } catch (IOException e) {
            System.err.println(e.getMessage());

        }
    }
}
