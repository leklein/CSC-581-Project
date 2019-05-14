import java.util.List;

public class Rule {
   public List<Predicate> predicates;
   
   public Rule(List<Predicate> predicates) {
      this.predicates = predicates;
   }

   public boolean equals(Object other) {
      if (null == other || !other.getClass().equals(getClass())) {
         return false;
      }
      return ((Rule)other).predicates.equals(predicates);
   }
}
