import java.util.List;
import java.util.LinkedList;

public class ResolutionFactory {

    List<Rule> knowledgeBase;

    public ResolutionFactory() {
        knowledgeBase = new RuleParser().generateRules("files/facts.txt");
        knowledgeBase.addAll(new RuleParser().generateRules("files/rules.txt"));
    }

    public enum Info {
        YES, NO, UNKNOWN;
    }
   
    public List<Symbol> getSymbolsFromPredicate(String predStr) {
        for (Rule rule : knowledgeBase) {
            if (rule.predicates.size() == 1) {
                if (rule.predicates.get(0).name.equals(predStr)) {
                    return rule.predicates.get(0).symbols;
                }
            }
        }
        System.out.println("No symbols found for predicate: " + predStr);
        return null;
    }

    public Info getInfoForSymbol(String symbolStr) {
        for (Rule rule : knowledgeBase) {
            for (Predicate predicate : rule.predicates) {
                if ((predicate.name.equals("AnswerPerson")
                    || predicate.name.equals("AnswerWeapon")
                    || predicate.name.equals("AnswerRoom")) && !predicate.negated) {
                    if (predicate.symbols.get(0).name.equals(symbolStr) && predicate.negated) {
                        return Info.NO;
                    }
                    else if (predicate.symbols.get(0).name.equals(symbolStr) && !predicate.negated) {
                        return Info.YES;
                    }
                }
            }
        }

        return Info.UNKNOWN;
    }
}
