import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

public class RuleParser {

    public RuleParser() { }
    
    public List<Predicate> readRules(String fileName) {
        Scanner scanner = new Scanner(fileName);
        Scanner tokenizer;
        List<Predicate> preds = new LinkedList<Predicate>();

        String line, token;
        String[] lineData;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            tokenizer = new Scanner(line);

            String pred = "";
            String[] predArgs = {};

            int predStart = 0;
            int argsStart = 0;

            boolean andConnected = false;
            
            for (int i = 0; i < line.length(); i++)
            {
                if (line.charAt(i) == ':') {
                    pred = line.substring(predStart, i);
                    argsStart = i + 1;
                }

                if (line.charAt(i) == '|' || line.charAt(i) == '&') {
                    predArgs = line.substring(argsStart, i).split(",");
                    predStart = i + 2;
                    if (line.charAt(i) == '&') {
                        andConnected = true;
                    }
                }

                if (andConnected) {
                    // TODO '&' vs '|' distinction

                    andConnected = false;
                }

                Predicate newPred = new Predicate(pred, Arrays.asList(predArgs));

                // TODO add all predicates to list? or is '|' end of a rule?
            }
        }

        return null;
    }
}