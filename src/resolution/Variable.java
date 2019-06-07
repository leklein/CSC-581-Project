package resolution;

public class Variable extends Symbol {
   public Variable(String name) {
      this.name = name;
   }

   public boolean equals(Object other) {
      if (null == other || !(other instanceof Variable)) {
         return false;
      }
      return name.equals(((Variable)other).name);
   }
}
