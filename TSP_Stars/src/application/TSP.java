package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TSP
{
   private List<Star> starList;

   private List<Star> result;

   private Random rand;

   private double shortestDist;

   public TSP()
   {
      rand = new Random();
   }

   public List<Star> nearestNeighbourAlgorithm(List<Star> stars)
   {
      long startTime, resultTime;
      startTime = System.currentTimeMillis();

      Star currentStar = null;
      int indexCurrentStar;

      double completeDist = 0;
      List<Star> tsp = new ArrayList<Star>(stars.size());
      // 1. Initialize all vertices as unvisited.
      // 2. Select an arbitrary vertex, set it as the current vertex u. Mark u
      // as visited.
      int arbitraryNum = rand.nextInt(stars.size());
      // int arbitraryNum = 3;
      System.out.println("Index des ersten Sternes ist: " + arbitraryNum);
      currentStar = stars.get(arbitraryNum);
      currentStar.setVisited(true);
      tsp.add(currentStar);
      indexCurrentStar = arbitraryNum;

      double starDist;
      // 3. Find out the shortest edge connecting the current vertex u and an
      // unvisited vertex v.
      for (int i = 0; i < stars.size(); i++)
      {
         int indexShortestEdge = 0;
         double shortestEdge = Double.MAX_VALUE;
         for (int j = 0; j < stars.size(); j++)
         {
            starDist = euclid3d_edgelen(stars.get(indexCurrentStar), stars.get(j));
            if (shortestEdge > starDist && !stars.get(j).isVisited())
            {
               indexShortestEdge = j;
               shortestEdge = starDist;
            }
         }
         // wenn alle sterne besucht sind muss shortestEdge auf 0 gesetzt
         // werden.
         if (shortestEdge == Double.MAX_VALUE)
         {
            shortestEdge = 0;
         }
         // 4. Set v as the current vertex u. Mark v as visited.
         currentStar = stars.get(indexShortestEdge);
         tsp.add(currentStar);
         currentStar.setVisited(true);
         indexCurrentStar = indexShortestEdge;
         // System.out.println(shortestEdge);
         completeDist += shortestEdge;
         // 5. If all the vertices in the domain are visited, then terminate.
         // Else, go to step 3.
      }
      System.out.println("Completer Weg: " + completeDist + " Laengeneinheiten");

      resultTime = System.currentTimeMillis() - startTime;
      System.out.println("Dauer der TSP berechnungen in ms:  " + resultTime + " ms");
      System.out.println("Dauer der TSP berechnungen in s:   " + resultTime / 1000 + " s");
      System.out.println("Dauer der TSP berechnungen in min: " + resultTime / 1000 / 60 + " min");

      result = tsp;
      shortestDist = completeDist;

      return result;
   }

   public static double euclid3d_edgelen(Star star1, Star star2)
   {
      double t1 = star1.getX() - star2.getX();
      double t2 = star1.getY() - star2.getY();
      double t3 = star1.getZ() - star2.getZ();

      return Math.sqrt(t1 * t1 + t2 * t2 + t3 * t3);
   }

   public double getShortestDist()
   {
      return shortestDist;
   }

   public List getResultList()
   {
      return result;
   }

}
