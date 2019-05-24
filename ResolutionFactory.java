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
   /*
    * Mark a card as shown in the list of rules and resolve
    */
   public void add_and_resolve(String card, String player) {
      Instance instance = new Instance(card);
      LinkedList<Symbol> instanceList = new LinkedList<Symbol>();
      instanceList.add(instance);
      Predicate shown = new Predicate("Shown", instanceList);
      LinkedList<Predicate> predicateList = new LinkedList<>();
      Rule shownRule = new Rule(predicateList);
      rules.add(shownRule);
      resolve();
   }
   



   /*
    * Uses forward chaining to add new predicates to the list
    */
   public void resolve() {
      List<Rule> workspace = resolve_inner(new LinkedList<Rule>(rules));
      while (0 < workspace.size()) {
         workspace = resolve_inner(workspace);
      }
   }


   /* ----- Helper functions ----- */

   /*
    * Uses forward chaining to add new predicates to the list
    * workspace: the list of rules that have yet to be examined
    * returns what is left of workspace after cleanup
    */
   public List<Rule> resolve_inner(List<Rule> workspace) {
      /*
       * Initialize array of all false
       * cleanup[i] means workspace[i] has been used, remove from list
       */
      boolean[] cleanup = new boolean[workspace.size()];
      for (int i = 0; i < workspace.size(); i++) {
         Rule rule = workspace.get(i);
         // for every thing that makes not q true, add not p
         // for everything that makes p true, add q
         // TODO - wait for Nam

         // if any of the above happened, mark cleanup
         // TODO - wait until build this function
      }

      // cleanup and return workspace
      cleanup_workspace(workspace, cleanup);
      return workspace;
   }


   /*
    * Cleans up rules that have been used in forward chaining
    * workspace - list of rules that needs to be cleaned
    * cleanup - list of booleans such that cleanup[i] means workspace[i] should
    *           be removed
    */
   public void cleanup_workspace(List<Rule> workspace, boolean[] cleanup) {
      for (int i = workspace.size() - 1; i >= 0; i--) {
         if (cleanup[i]) {
            workspace.remove(i);
         }
      }
   }
}
