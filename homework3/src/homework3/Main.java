package homework3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 03.03.12
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        NFAParameters parameters =new NFAParameters("S", "Final", "epsilon", "Typical_NFA");
        Scanner in = new Scanner(System.in);
        try {                                                     
            GrammarsReader reader = new RegularGrammarsReader();
            NFABuilder builder = new NFABuilder(parameters);
            List<GrammarRule> rules = reader.read("input.txt");

            NFA2DOTPrinter printer = new NFA2DOTPrinter("output.dot");

            Map<String, NFANode> automate = builder.build(rules);
            printer.printNFA(builder.build(rules),  parameters);
            printer.close();

            System.out.println("Введите слово: ");
            String word = in.nextLine();

            NFAInterpreter interpreter = new NFAInterpreter(automate, parameters);
            if(interpreter.checkWord(word)) {
                System.out.println("Слово: "+ word + " принадлежит заданому языку");
            } else {
                System.out.println("Слово: "+ word + " не принадлежит заданому языку");
            }


        } catch (FileNotFoundException e) {
            System.err.println("Can't find file");
            
        } catch (IOException e) {
            System.err.println(e.getMessage());

        }
        
    }
}
