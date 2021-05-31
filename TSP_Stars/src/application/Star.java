package application;

public class Star
{
   private double x;

   private double y;

   private double z;

   private boolean isVisited;

   public Star(double x, double y, double z)
   {
      this.x = x;
      this.y = y;
      this.z = z;
      isVisited = false;
   }

   /**
    * Getter for property x.
    * 
    * @return Returns the x.
    */
   public double getX()
   {
      return x;
   }

   /**
    * Setter for property x.
    *
    * @param x
    *           The x to set.
    */
   public void setX(double x)
   {
      this.x = x;
   }

   /**
    * Getter for property y.
    * 
    * @return Returns the y.
    */
   public double getY()
   {
      return y;
   }

   /**
    * Setter for property y.
    *
    * @param y
    *           The y to set.
    */
   public void setY(double y)
   {
      this.y = y;
   }

   /**
    * Getter for property z.
    * 
    * @return Returns the z.
    */
   public double getZ()
   {
      return z;
   }

   /**
    * Setter for property z.
    *
    * @param z
    *           The z to set.
    */
   public void setZ(double z)
   {
      this.z = z;
   }

   /**
    * Getter for property isVisited.
    * 
    * @return Returns the isVisited.
    */
   public boolean isVisited()
   {
      return isVisited;
   }

   /**
    * Setter for property isVisited.
    *
    * @param isVisited
    *           The isVisited to set.
    */
   public void setVisited(boolean isVisited)
   {
      this.isVisited = isVisited;
   }

   public void printCoordinates()
   {
      System.out.printf("|X: %16f|Y: %16f|Z: %16f|\n", x, y, z);
   }

}
