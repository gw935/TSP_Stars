package application.test;

import application.Star;

public class TestMain
{

   public TestMain()
   {
      Star star1 = new Star(2, 2, 2);
      Star star2 = new Star(1, 5, 0);
      
      System.out.println(euclid3d_edgelen(star1, star2));
   }

   public static void main(String[] args)
   {
      new TestMain();
   }

   public static double euclid3d_edgelen(Star star1, Star star2)
   {
      double t1 = star1.getX() - star2.getX();
      double t2 = star1.getY() - star2.getY();
      double t3 = star1.getZ() - star2.getZ();

      return Math.sqrt(t1 * t1 + t2 * t2 + t3 * t3);
   }

}
