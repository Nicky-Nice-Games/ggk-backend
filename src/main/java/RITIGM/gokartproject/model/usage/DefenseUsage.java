package RITIGM.gokartproject.model.usage;

/**
 * template for defense items in gameplay
 * @author Diego Velez
 */
public class DefenseUsage {
    public static final String TO_STRING_FORMAT = "Defense Usage:\r\n" + //
                                                    "\t\tDefense 1 = %d, \r\n" + //
                                                    "\t\tDefense 2 = %d, \r\n" + //
                                                    "\t\tDefense 3 = %d, \r\n" + //
                                                    "\t\tDefense 4 = %d, \r\n";
    private int defense1;
    private int defense2;
    private int defense3;
    private int defense4;

   public DefenseUsage(int defense1, int defense2, int defense3, int defense4){
        this.defense1 = defense1;
        this.defense2 = defense2;
        this.defense3 = defense3;
        this.defense4 = defense4;
   }

   /**
    * getter for defense item 1
    * @return defense item 1
    */
   public int getDefense1() {
    return defense1;
   }

   /**
    * setter defense 1
    * @param defense1 defense 1
    */
   public void setDefense1(int defense1) {
    this.defense1 = defense1;
   }

   /**
    * getter for defense item 2
    * @return defense item 2
    */
   public int getDefense2() {
    return defense2;
   }

   /**
    * setter defense 2
    * @param defense2 defense 2
    */
   public void setDefense2(int defense2) {
    this.defense2 = defense2;
   }

   /**
    * getter for defense item 3
    * @return defense item 3
    */
   public int getDefense3() {
    return defense3;
   }

   /**
    * setter defense 3
    * @param defense3 defense 3
    */
   public void setDefense3(int defense3) {
    this.defense3 = defense3;
   }

   /**
    * getter for defense item 4
    * @return defense item 4
    */
   public int getDefense4() {
    return defense4;
   }

   /**
    * setter defense 4
    * @param defense4 defense 4
    */
   public void setDefense4(int defense4) {
    this.defense4 = defense4;
   }

   @Override
   public String toString(){
    return String.format(TO_STRING_FORMAT, defense1, defense2, defense3, defense4);
   }
   
}
