package resolution;

import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

public class RuleParser {

    public static String factToUserFriendlyString(String fact)
    {
        String[] splitFact = fact.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < splitFact.length; i++)
        {
            if (i != 1) stringBuilder.append(" ");
            stringBuilder.append(splitFact[i].trim());
        }

        return stringBuilder.toString();
    }

    public static String userFriendlyStringToFact(String userFriendlyString)
    {
        String[] splitUserFriendlyString = userFriendlyString.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < splitUserFriendlyString.length; i++)
        {
            stringBuilder.append("_" + splitUserFriendlyString[i].trim());
        }

        return stringBuilder.toString();
    }

    public static void parse(List<Rule> rules) {

        System.out.println("Number of rules found: " + rules.size());

        for (Rule rule : rules) {
            for (int i = 0; i < rule.predicates.size(); i++) {
                Predicate pred = rule.predicates.get(i);
                if (!pred.negated) {
                   System.out.print(pred.name + ":");
                }
                else {
                  System.out.print("!" + pred.name + ":");
                }
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

    public static List<Rule> generateRules(String fileName) {

        List<Rule> rules = new LinkedList<Rule>();

        try {
            Scanner scanner = new Scanner(new File(fileName));

            String line;
            while (scanner.hasNextLine()) {

                line = scanner.nextLine();

                if (!line.equals("")) {
                    List<Predicate> preds = new LinkedList<Predicate>();

                    boolean negated = false;
                    String predName = "";
                    String[] predVars = {};

                    int predStart = 0;
                    int argsStart = 0;

                    boolean foundFullPred = false;

                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == ':') {
                            if (line.charAt(predStart) == '!') {
                               predStart += 1;
                               negated = true;
                            }
                            predName = line.substring(predStart, i);
                            argsStart = i + 1;
                        }

                        if (line.charAt(i) == '|' || i == line.length() - 1) {
                            if (i == line.length() - 1) {
                                predVars = line.substring(argsStart).split(",");
                            } else {
                                predVars = line.substring(argsStart, i-1).split(",");
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
                            preds.add(new Predicate(predName, predVarsList, negated));
                            foundFullPred = false;
                            negated = false;
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
