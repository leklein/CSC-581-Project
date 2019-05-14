import java.util.List;

public class Predicate {
   public String name;
   public List<String> variables;

   public Predicate(String name, List<String> variables) {
      this.name = name;
      this.variables = variables;
   }

   public boolean equals(Object other) {
      if (other == null || !other.getClass().equals(getClass())) {
         return false;
      }
      Predicate o = (Predicate)other;
      return (o.name.equals(name) &
              o.variables.equals(variables));
   }
}
