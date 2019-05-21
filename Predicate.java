import java.util.List;

public class Predicate {
   public String name;
   public List<Symbol> symbols;
   public boolean negated;

   public Predicate(String name, List<Symbol> symbols) {
      this.name = name;
      this.symbols = symbols;
      this.negated = false;
   }

   public Predicate(String name, List<Symbol> symbols, boolean negated) {
      this.name = name;
      this.symbols = symbols;
      this.negated = negated;
   }



   public boolean equals(Object other) {
      if (other == null || !(other instanceof Predicate)) {
         return false;
      }
      Predicate o = (Predicate)other;
      return (o.name.equals(name) &&
              o.symbols.equals(symbols) &&
              o.negated == negated);
   }
}
