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
   
    public List<Symbol> getSymbolsFromPredicate(String predStr, boolean negated) {
        List<Symbol> symbols = new LinkedList<Symbol>();
        for (Rule rule : knowledgeBase) {
            if (rule.predicates.size() == 1) {
                if (rule.predicates.get(0).name.equals(predStr) && rule.predicates.get(0).negated == negated) {
                    symbols.addAll(rule.predicates.get(0).symbols);
                }
            }
        }
        return symbols;
    }

    public Info getInfoForSymbol(String symbolStr) {
        for (Rule rule : knowledgeBase) {
            if (rule.atomic) {
                Predicate predicate = rule.predicates.get(0);
                if (predicate.name.equals("AnswerPerson")
                    || predicate.name.equals("AnswerWeapon")
                    || predicate.name.equals("AnswerRoom")) {
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
      knowledgeBase.add(shownRule);
      resolve();
   }
   
   /*
    * Uses forward chaining to add new predicates to the list
    */
   public void resolve() {
      List<Rule> workspace = create_workspace();
      resolve_inner(workspace);
      while (0 < workspace.size()) {
         resolve_inner(workspace);
      }
   }


   /* ----- Helper functions ----- */

   /*
    * Uses forward chaining to add new predicates to the list
    * workspace: the list of rules that have yet to be examined
    * returns what is left of workspace after cleanup
    */
   private void resolve_inner(List<Rule> workspace) {
      /*
       * Initialize array of all false
       * cleanup[i] means workspace[i] has been used, remove from list
       */
      boolean[] cleanup = new boolean[workspace.size()];
      for (int i = 0; i < workspace.size(); i++) {
         Rule rule = workspace.get(i);

         // TODO - for all things that satisfy all ps, add q

      }

      // cleanup and return workspace
      cleanup_workspace(workspace, cleanup);
   }

   private List<List<Integer>> create_permutations(List<List<Predicate>> all_preds) {
      List<List<Integer>> permutations;
      if (1 == all_preds.size()) {
         permutations = new List<List<Integer>>();
         for (int i = 0; i < all_preds.get(0); i++) {
            LinkedList<Integer> l = new LinkedList<Integer>();
            l.add(i);
            permutations.add(l);
         }
         return permutations;
      }

      List<List<Predicate>> dup_all_preds = new LinkedList<List<Predicate>>(all_preds);

      permutations = create_permutations


   }

   /*
    * Creates a workspace of non-atomic rules
    */
   private List<Rule> create_workspace() {
      LinkedList<Rule> workspace = new LinkedList<Rule>();
      for (Rule rule: knowledgeBase) {
         if (!rule.atomic) {
            workspace.add(rule);
         }
      }
      return workspace;
   }

   /*
    * Cleans up rules that have been used in forward chaining
    * workspace - list of rules that needs to be cleaned
    * cleanup - list of booleans such that cleanup[i] means workspace[i] should
    *           be removed
    */
   private void cleanup_workspace(List<Rule> workspace, boolean[] cleanup) {
      for (int i = workspace.size() - 1; i >= 0; i--) {
         if (cleanup[i]) {
            workspace.remove(i);
         }
      }
   }
}
