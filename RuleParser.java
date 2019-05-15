import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

public class RuleParser {

    public static void main(String[] args) {
        RuleParser parser = new RuleParser();
        List<Rule> rules = parser.generateRules("files/rules.txt");
        for (Rule rule : rules) {
            System.out.println("RULE:");
            for (Predicate pred : rule.predicates) {
                System.out.print(pred.name + ":");
                for (String var : pred.variables) {
                    System.out.print(var + ",");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    public List<Rule> generateRules(String fileName) {
        Scanner scanner = new Scanner(fileName);
        List<Rule> rules = new LinkedList<Rule>();

        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            List<Predicate> preds = new LinkedList<Predicate>();

            String predName = "";
            String[] predVars = {};

            int predStart = 0;
            int argsStart = 0;
            
            for (int i = 0; i < line.length(); i++)
            {
                if (line.charAt(i) == ':') {
                    predName = line.substring(predStart, i);
                    argsStart = i + 1;
                }

                if (line.charAt(i) == '|') {
                    predVars = line.substring(argsStart, i).split(",");
                    predStart = i + 2;
                }

                preds.add(new Predicate(predName, Arrays.asList(predVars)));
            }

            rules.add(new Rule(preds));
        }

        scanner.close();

        return rules;
    }
}