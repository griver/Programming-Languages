package homework3;

import homework3.dfa.*;
import homework3.grammar.GrammarRule;
import homework3.grammar.GrammarsReader;
import homework3.grammar.RegularGrammarsReader;
import homework3.nfa.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NFAParameters parameters = new NFAParameters("S", "Final", "eps", "Simple");
        Scanner in = new Scanner(System.in);

        try {                                                     
            GrammarsReader reader = new RegularGrammarsReader();
            NFABuilder builder = new NFABuilder(parameters);
            // Troubles with paths, hardcode for now
            List<GrammarRule> rules = reader.read("../../../input.txt");

            NFA nfa = builder.build(rules);

            NFA nfaU1 = builder.build(rules);
            NFA nfaU2 = builder.build(rules);
            nfaU2.removeEpsilon();
            NFAUnifier unifier = new NFAUnifier(parameters);
            NFA nfaUnion = unifier.unify(nfaU1, nfaU2);

            NFA nfaCl = builder.build(rules);
            NFAClosure.close(nfaCl);

            // If we have NFA rather than DFA, we must
            // first convert it to DFA before swapping
            // states to get its complement.
            NFA2DFAConverter converterComp = new NFA2DFAConverter(nfa);
            DFA dfaComp = converterComp.getDFA();
            DFAComplementer.complement(dfaComp);

            NFA2DFAConverter converter = new NFA2DFAConverter(nfa);
            DFA dfa = converter.getDFA();

            NFA2DOTPrinter printer = new NFA2DOTPrinter("../../../outputNfa.dot");
            printer.print(nfa, nfa.getParameters());
            printer.close();

            printer = new NFA2DOTPrinter("../../../outputNfaNoEps.dot");
            nfa.removeEpsilon();
            printer.print(nfa, nfa.getParameters());
            printer.close();

            printer = new NFA2DOTPrinter("../../../outputNfaUnion.dot");
            printer.print(nfaUnion, nfaUnion.getParameters());
            printer.close();

            printer = new NFA2DOTPrinter("../../../outputNfaClosure.dot");
            printer.print(nfaCl, nfaCl.getParameters());
            printer.close();

            DFA2DOTPrinter printerDfa = new DFA2DOTPrinter("../../../outputDfa.dot");
            printerDfa.print(dfa, dfa.getParameters());
            printerDfa.close();

            printerDfa = new DFA2DOTPrinter("../../../outputDfaComplement.dot");
            printerDfa.print(dfaComp, dfaComp.getParameters());
            printerDfa.close();

            System.out.println("Input the word: ");
            String word = in.nextLine();

            NFAInterpreter interpreter = new NFAInterpreter(nfa, nfa.getParameters());
            if(interpreter.checkWord(word)) {
                System.out.println("Word: "+ word + " was accepted by NFA.");
            } else {
                System.out.println("Word: "+ word + " was rejected by NFA.");
            }

            DFAInterpreter complementary = new DFAInterpreter(dfaComp, dfaComp.getParameters());
            if(complementary.checkWord(word)) {
                System.out.println("Word: "+ word + " was accepted by complemented DFA.");
            } else {
                System.out.println("Word: "+ word + " was rejected by complemented DFA.");
            }

            DFAInterpreter dfaInterpreter = new DFAInterpreter(dfa, dfa.getParameters());
            if(dfaInterpreter.checkWord(word)) {
                System.out.println("Word: "+ word + " was accepted by DFA.");
            } else {
                System.out.println("Word: "+ word + " was rejected by DFA.");
            }


        } catch (FileNotFoundException e) {
            System.err.println("Can't find file: " + e.getLocalizedMessage());
            
        } catch (IOException e) {
            System.err.println(e.getMessage());

        }
    }
}
