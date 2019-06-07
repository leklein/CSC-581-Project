package resolution;

public class Instance extends Symbol {
   public Instance(String name) {
      this.name = name;
   }

   public boolean equals(Object other) {
      if (null == other || !(other instanceof Instance)) {
         return false;
      }
      return name.equals(((Instance)other).name);
   }
}
