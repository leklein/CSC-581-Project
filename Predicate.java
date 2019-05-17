import java.util.List;

public class Predicate {
   public String name;
   public List<Symbol> symbols;

   public Predicate(String name, List<Symbol> symbols) {
      this.name = name;
      this.symbols = symbols;
   }

   public boolean equals(Object other) {
      if (other == null || !(other instanceof Predicate)) {
         return false;
      }
      Predicate o = (Predicate)other;
      return (o.name.equals(name) &
              o.symbols.equals(symbols));
   }
}
