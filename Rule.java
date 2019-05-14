import java.util.List;

public class Rule {
   String name;
   List<String> variables;

   public Rule(String name, List<String> variables) {
      this.name = name;
      this.variables = variables;
   }

   public boolean equals(Object other) {
      if (other == null || !other.getClass().equals(getClass())) {
         return false;
      }
      Rule o = (Rule)other;
      return (o.name.equals(name) &
              o.variables.equals(variables));
   }
}
