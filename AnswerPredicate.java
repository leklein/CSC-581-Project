import java.util.List;

public class AnswerPredicate extends Predicate {
   public AnswerPredicate(List<String> variables) {
      super(null, variables);
   }

   public boolean equals(Object other) {
      if (other == null || !(other instanceof AnswerPredicate)) {
         return false;
      }
      AnswerPredicate o = (AnswerPredicate)other;
      return o.variables.equals(variables);
   }
}
