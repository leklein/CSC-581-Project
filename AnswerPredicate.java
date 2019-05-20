import java.util.List;

public class AnswerPredicate extends Predicate {
   public AnswerPredicate(List<Symbol> symbols) {
      super(null, symbols);
   }

   public boolean equals(Object other) {
      if (other == null || !(other instanceof AnswerPredicate)) {
         return false;
      }
      AnswerPredicate o = (AnswerPredicate)other;
      return o.symbols.equals(symbols);
   }
}
