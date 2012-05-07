package homework3.grammar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegularGrammarsReader implements GrammarsReader {
    public List<GrammarRule> read(String name) throws FileNotFoundException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(name));
        List<GrammarRule> rules = new ArrayList<GrammarRule>();
        String str;

        try {
            do {
                str = bufferedReader.readLine();

                if(str != null) {
                    String [] tmp = str.split(":");

                    if(tmp.length == 1) {
                        rules.add(new GrammarRule(tmp[0].trim(), null, null));
                    } else if(tmp.length == 2) {
                        String rightPart = tmp[1].trim();
                        String nonterm = null;
                        String term = Character.toString(rightPart.charAt(0));
                        if(rightPart.length() == 2) {
                            nonterm = Character.toString(rightPart.charAt(1));
                        }
                        rules.add(new GrammarRule(tmp[0].trim(), term, nonterm ));
                    } else {
                        throw new IOException("Invalid input format!");
                    }
                }

            } while (str != null);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.err.println("Strange Problem with: bufferedReader.close()");
            }
        }
        return rules;
    }
}
