package resolution;

import java.util.List;

public class Rule {
   public List<Predicate> predicates;
   public boolean atomic;
   
   public Rule(List<Predicate> predicates) {
      this.predicates = predicates;
      if (1 >= predicates.size()) {
         this.atomic = true;
      }
      else {
         this.atomic = false;
      }
   }

   public boolean equals(Object other) {
      if (null == other || !other.getClass().equals(getClass())) {
         return false;
      }
      return (((Rule)other).predicates.equals(predicates) && 
              ((Rule)other).atomic == atomic);
   }
}
