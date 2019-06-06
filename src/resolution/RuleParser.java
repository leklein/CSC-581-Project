package resolution;

import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

public class RuleParser {

    public String factToUserFriendlyString(String fact)
    {
        String[] splitFact = fact.split("_");
        for (int i = 0; i < splitFact.length; i++)
        {
            splitFact[i] = splitFact[i].trim() + " ";
        }

        return Arrays.toString(splitFact);
    }

    public void parse(List<Rule> rules) {

        System.out.println("Number of rules found: " + rules.size());

        for (Rule rule : rules) {
            for (int i = 0; i < rule.predicates.size(); i++) {
                Predicate pred = rule.predicates.get(i);
                System.out.print(pred.name + ":");
                for (int j = 0; j < pred.symbols.size(); j++) {
                    System.out.print(pred.symbols.get(j).name);
                    if (j != pred.symbols.size() - 1) {
                        System.out.print(",");
                    }
                }
                if (i != rule.predicates.size() - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    public List<Rule> generateRules(String fileName) {

        List<Rule> rules = new LinkedList<Rule>();

        try {
            Scanner scanner = new Scanner(new File(fileName));

            String line;
            while (scanner.hasNextLine()) {

                line = scanner.nextLine();

                if (!line.equals("")) {
                    List<Predicate> preds = new LinkedList<Predicate>();

                    String predName = "";
                    String[] predVars = {};

                    int predStart = 0;
                    int argsStart = 0;

                    boolean foundFullPred = false;

                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == ':') {
                            predName = line.substring(predStart, i);
                            argsStart = i + 1;
                        }

                        if (line.charAt(i) == '|' || i == line.length() - 1) {
                            if (i == line.length() - 1) {
                                predVars = line.substring(argsStart).split(",");
                            } else {
                                predVars = line.substring(argsStart, i).split(",");
                            }
                            predStart = i + 2;
                            foundFullPred = true;
                        }

                        List<Symbol> predVarsList = new LinkedList<Symbol>();
                        List<String> predVarsStringList = Arrays.asList(predVars);
                        for (String varString : predVarsStringList) {
                            predVarsList.add(new Variable(varString));
                        }

                        if (foundFullPred) {
                            preds.add(new Predicate(predName, predVarsList));
                            foundFullPred = false;
                        }
                    }

                    rules.add(new Rule(preds));
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return rules;
    }
}