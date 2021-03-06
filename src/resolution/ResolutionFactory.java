package resolution;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;

public class ResolutionFactory {
    List<Rule> knowledgeBase;

    public ResolutionFactory() {
        knowledgeBase = RuleParser.generateRules("files/facts.txt");
        knowledgeBase.addAll(RuleParser.generateRules("files/rules.txt"));
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
      Instance instanceC = new Instance(card);
      Instance instanceP = new Instance(player);
      LinkedList<Symbol> instanceList = new LinkedList<Symbol>();
      instanceList.add(instanceP);
      instanceList.add(instanceC);
      Predicate shown = new Predicate("ShownToMe", instanceList);
      LinkedList<Predicate> predicateList = new LinkedList<Predicate>();
      predicateList.add(shown);
      Rule shownRule = new Rule(predicateList);
      knowledgeBase.add(shownRule);
      resolve();
      resolve();
      RuleParser.parse(knowledgeBase);
   }

   /*
    * add temporary rules
    */
   public void add_temp_and_resolve(String predName, List<String> symbolNames) {
      List<Symbol> symbols = new LinkedList<Symbol>();
      for (String name : symbolNames) {
         symbols.add(new Instance(name));
      }
      Predicate predicate = new Predicate(predName, symbols, false);
      List<Predicate> preds = new LinkedList<Predicate>();
      preds.add(predicate);
      Rule tempRule = new Rule(preds, true);
      knowledgeBase.add(tempRule);
      resolve();
      resolve();
      RuleParser.parse(knowledgeBase);
   }

   /*
    * Removes temporary variables from the knowledgebase
    */
   public void remove_temporary() {
      int i = 0;
      while (i < knowledgeBase.size()) {
         if (knowledgeBase.get(i).temporary) {
            knowledgeBase.remove(i);
         }
         else {
            i += 1;
         }
      }
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

      /* purge duplicates */
      Hashtable<Rule, Boolean> duplicates = new Hashtable<Rule, Boolean>();
      int i = 0;
      while (i < workspace.size()) {
         if (null == duplicates.get(workspace.get(i))) {
            duplicates.put(workspace.get(i), true);
            i += 1;
         }
         else {
            knowledgeBase.remove(i);
         }
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
      LinkedList<Integer> cleanup = new LinkedList<Integer>();
      for (int i = 0; i < workspace.size(); i++) {
         Rule rule = workspace.get(i);

         /* check to see if the rule has no variables (checks for solution) */
         boolean noVars = true;
         for (Predicate predicate : rule.predicates) {
            for (Symbol symbol : predicate.symbols) {
               if (symbol instanceof Variable) {
                  noVars = false;
                  break;
               }
            }
         }

         /* attempt to resolve solution */
         if (noVars && resolve_solution(rule)) {
            cleanup.add(i);
            resolved = true;
         }
         else if (!noVars) {
            List<List<List<Symbol>>> all_preds = collect_satisfying_symbols(rule);
            if (null != all_preds) {
               /* create all permutations of possible matchings */
               List<List<Integer>> tuples = create_permutations(new LinkedList<List<List<Symbol>>>(all_preds));

               resolve_all_possible(rule, all_preds, tuples);
               
               /* Mark rule for cleanup */
               cleanup.add(i);
               resolved = true;
            }
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
      /* Used to detect rules where no substitution needed */
      boolean noVars = true;
      /* Used to check for conflicting definitions of variables */
      Hashtable<String, String> replaceDict = new Hashtable<String, String>();
      /* For each element of the tuple, do: */
      for (int i = 0; i < tuple.size(); i++) {
         /* find the associated symbols from the predicate */
         List<Symbol> pred_symbols = all_preds.get(i).get(tuple.get(i));
         /* Check if the tuple satisfies the rule */
         for (int j = 0; j < pred_symbols.size(); j++) {
            if (rule.predicates.get(i).symbols.get(j) instanceof Variable) {
               noVars = false;
               String res = replaceDict.get(rule.predicates.get(i).symbols.get(j).name);
               if (null == res) {
                  /* haven't seen this symbol defined before; add to dictionary */
                  replaceDict.put(rule.predicates.get(i).symbols.get(j).name, 
                                  pred_symbols.get(j).name);
               }
               else if (!pred_symbols.get(j).name.equals(res)) {
                  /* Contradicts previous information, so this tuple won't work */
                  return;
               }
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
      Predicate predicate = new Predicate(rhs.name, symbols, rhs.negated);
      List<Predicate> wrapper = new LinkedList<Predicate>();
      wrapper.add(predicate);
      Rule newrule = new Rule(wrapper,rule.temporary);
      if (!knowledgeBase.contains(newrule)) {
         knowledgeBase.add(newrule);
      }
   }

   /*
    * attempts to determine a murderer, weapon, or location
    */
   private boolean resolve_solution(Rule rule) {
      List<List<Symbol>> ansSymbols = getSymbolsFromPredicate(rule.predicates.get(0).name, true);

      /* count how many seen */
      /* seen[i] = true if a rule cancels out predicate i */
      boolean[] seen = new boolean[rule.predicates.size()];
      for (int i = 0; i < ansSymbols.size(); i++) {
         Symbol symbol = ansSymbols.get(i).get(0); /* these predicates have only one symbol */
         /* See if matches any predicates in the rule */
         for (int j = 0; j < rule.predicates.size(); j++) {
            if (rule.predicates.get(j).symbols.get(0).name.equals(symbol.name)) {
               /* found a match; mark as seen */
               seen[i] = true;
               break;
            }
         }
      }

      /* count how many seen and find the last one that was not seen */
      int sum = 0;
      int lastFalse = -1;
      for (int i = 0; i < seen.length; i++) {
         if (seen[i]) {
            sum += 1;
         }
         else {
            lastFalse = i;
         }
      }

      /* 
       * if we have seen all but one predicates, we found our answer;
       * add answer to pool
       */

      if (rule.predicates.size() - 1 != sum) {
         return false;
      }

      /* found the answer */
      List<Predicate> answerList = new LinkedList<Predicate>();
      answerList.add(rule.predicates.get(lastFalse));
      Rule ansRule = new Rule(answerList);
      if (!knowledgeBase.contains(ansRule)) {
         knowledgeBase.add(ansRule);
      }
      return true;
   }

   /*
    * Given a resolution.Rule representing a horn clause, colects all symbols that satisfy
    * predicates on the left side of the implication
    * if at least one predicate has no satisfying symbols, returns null
    */
   private List<List<List<Symbol>>> collect_satisfying_symbols(Rule rule) {
      List<List<List<Symbol>>> all_preds = new LinkedList<List<List<Symbol>>>();
      /* For every predicate on the left side of the implication: */
      for (int i = 0; i < rule.predicates.size() - 1; i++) {
         /* Collect all symbols that satisfy each predicate */
         List<List<Symbol>> symbols = getSymbolsFromPredicate(rule.predicates.get(i).name, !rule.predicates.get(i).negated);
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
   private void cleanup_workspace(List<Rule> workspace, List<Integer> cleanup) {
      for (int i = cleanup.size() - 1; i >= 0; i--) {
         workspace.remove(cleanup.get(i).intValue());
      }
   }

}
