import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;

public class ResolutionFactory {
    List<Rule> knowledgeBase;

    public ResolutionFactory() {
        knowledgeBase = new RuleParser().generateRules("files/facts.txt");
        knowledgeBase.addAll(new RuleParser().generateRules("files/rules.txt"));
    }

    public enum Info {
        YES, NO, UNKNOWN;
    }
   
    public List<List<Symbol>> getSymbolsFromPredicate(String predStr, boolean negated) {
        List<List<Symbol>> symbols = new LinkedList<List<Symbol>>();
        for (Rule rule : knowledgeBase) {
            if (rule.predicates.size() == 1) {
                if (rule.predicates.get(0).name.equals(predStr) && rule.predicates.get(0).negated == negated) {
                    symbols.add(rule.predicates.get(0).symbols);
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
      /* 
       * resolved is true if a rule was resolved in the last call to
       * resolve_inner and false otherwise
       * if resolved is false, means that no new atomic predicates can be added
       * with the existing information
       */
      boolean resolved = resolve_inner(workspace);
      /* Keep resolving rules until no more rules can be resolved */
      while (0 < workspace.size() && resolved) {
         resolved = resolve_inner(workspace);
      }
   }


   /* ----- Helper functions ----- */

   /*
    * Uses forward chaining to add new predicates to the list
    * workspace: the list of rules that have yet to be examined
    * returns true if a rule was removed and false otherwise
    */
   private boolean resolve_inner(List<Rule> workspace) {
      /*
       * Initialize array of all false
       * cleanup[i] means workspace[i] has been used, remove from list
       */
      boolean resolved = false;
      boolean[] cleanup = new boolean[workspace.size()];
      for (int i = 0; i < workspace.size(); i++) {
         Rule rule = workspace.get(i);

         List<List<List<Symbol>>> all_preds = collect_satisfying_symbols(rule);
         if (null != all_preds) {
            /* create all permutations of possible matchings */
            List<List<Integer>> tuples = create_permutations(new LinkedList<List<List<Symbol>>>(all_preds));

            resolve_all_possible(rule, all_preds, tuples);
            
            /* Mark rule for cleanup */
            cleanup[i] = true;
            resolved = true;
         }
      }

      // cleanup and return workspace
      cleanup_workspace(workspace, cleanup);
      return resolved;
   }

   /*
    * Given a rule, a list of symbols that satisfy the predicates on the left
    * side of the implication for that rule, and a list of all possible tuples,
    * see if any tuples produce a valid substitution for that rule and add
    * to the workspace if so
    */
   private void resolve_all_possible(Rule rule,
                                     List<List<List<Symbol>>> all_preds,
                                     List<List<Integer>> tuples) {
      for (List<Integer> tuple: tuples) {
         resolve_one_possible(rule, all_preds, tuple);
      }
   }

   /*
    * Given a rule, a list of symbols that satisfy the predicates on the left
    * side of the implication for that rule, and a tuples,
    * see if that tuple produces a valid substitution for that rule and add
    * to the workspace if so
    */
   private void resolve_one_possible(Rule rule,
                                     List<List<List<Symbol>>> all_preds,
                                     List<Integer> tuple) {
      /* Used to check for conflicting definitions of variables */
      Hashtable<String, String> replaceDict = new Hashtable<String, String>();
      /* For each element of the tuple, do: */
      for (int i = 0; i < tuple.size(); i++) {
         /* find the associated symbols from the predicate */
         List<Symbol> pred_symbols = all_preds.get(i).get(tuple.get(i));
         /* Check if the tuple satisfies the rule */
         for (int j = 0; j < pred_symbols.size(); j++) {
            String res = replaceDict.get(rule.predicates.get(i).symbols.get(i).name);
            if (null == res) {
               /* haven't seen this symbol defined before; add to dictionary */
               replaceDict.put(res, pred_symbols.get(j).name);
            }
            else if (!pred_symbols.get(j).equals(res)) {
               /* Contradicts previous information, so this tuple won't work */
               return;
            }
         }
      }

      /* 
       * No contradiction has been found, so tuple produces valid interpretation
       * Add right side of implication to rules
       */
      List<Symbol> symbols = new LinkedList<Symbol>();
      Predicate rhs = rule.predicates.get(rule.predicates.size() - 1);
      for (Symbol symbol: rhs.symbols) {
         /* Add the names of the instance to the new predicate */
         symbols.add(new Instance(replaceDict.get(symbol.name)));
      }
      /* Make an atomic rule and add to the knowledgebase */
      Predicate predicate = new Predicate(rhs.name, symbols);
      List<Predicate> wrapper = new LinkedList<Predicate>();
      wrapper.add(predicate);
      Rule newrule = new Rule(wrapper);
      knowledgeBase.add(newrule);
   }

   /*
    * Given a Rule representing a horn clause, colects all symbols that satisfy
    * predicates on the left side of the implication
    * if at least one predicate has no satisfying symbols, returns null
    */
   private List<List<List<Symbol>>> collect_satisfying_symbols(Rule rule) {
      List<List<List<Symbol>>> all_preds = new LinkedList<List<List<Symbol>>>();
      /* For every predicate on the left side of the implication: */
      for (int i = 0; i < rule.predicates.size() - 1; i++) {
         /* Collect all symbols that satisfy each predicate */
         List<List<Symbol>> symbols = getSymbolsFromPredicate(rule.predicates.get(i).name, true);
         /* If size of returned list is 0, return null */
         if (0 == symbols.size()) {
            return null;
         }
         all_preds.add(symbols);
      }
      return all_preds;
   }

   /*
    * Creates all possible ordered tuples to index into each list of preds inside
    * of a list of preds
    * all_preds: a list of list of predicates with size at least 1
    */
   private List<List<Integer>> create_permutations(List<List<List<Symbol>>> all_preds) {
      /* The list to be returned */
      List<List<Integer>> permutations = new LinkedList<List<Integer>>();

      if (1 == all_preds.size()) {
         /* Create 1-tuples of all numbers from 0 to size - 1 */
         for (int i = 0; i < all_preds.get(0).size(); i++) {
            LinkedList<Integer> l = new LinkedList<Integer>();
            l.add(i);
            permutations.add(l);
         }
         return permutations;
      }

      /* Recurse */
      List<List<Symbol>> removed = all_preds.get(0); /* Saving for later */
      all_preds.remove(0); /* Reduce problem size */
      List<List<Integer>> prev_permutations = create_permutations(all_preds); /* Get previous permutations */

      for (int i = 0; i < removed.size(); i++) {
         /* 
          * For every tuple in the previous permutations, add i as a new element
          * at the front of the list
          */
         for (List<Integer> tuple : prev_permutations) {
            /* avoid contamination of original tuple */
            List<Integer> update_tuple = new LinkedList<Integer>(tuple);
            /* add new index */
            update_tuple.add(0, i);
            /* add to permutation */
            permutations.add(update_tuple);
         }
      }
      return permutations;
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
