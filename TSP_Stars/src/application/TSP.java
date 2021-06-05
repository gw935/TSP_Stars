package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TSP
{

   public TSP()
   {
      long start1, result1;
      start1 = System.currentTimeMillis();

      // Einlesen der Datei
      ReadFile readFile = null;
      try
      {
         readFile = new ReadFile(Main.FILENAME3);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      result1 = System.currentTimeMillis() - start1;
      System.out.println("Einlesen der Datei: " + result1 + " ms");

      List<Star> starList = readFile.getListOfStars();
      // printStars(starList);

      // TSP berechnen und zeitmessung
      long start, result;
      start = System.currentTimeMillis();
      List<Star> tsp = nearestNeighbourAlgorithm(starList);
      result = System.currentTimeMillis() - start;
      System.out.println("Dauer der TSP berechnungen in ms:  " + result + " ms");
      System.out.println("Dauer der TSP berechnungen in s:   " + result / 1000 + " s");
      System.out.println("Dauer der TSP berechnungen in min: " + result / 1000 / 60 + " min");

      // Ausgabe des TSP
      // printTSP(tsp);
   }

// This is a code fragment in the C programming language.

   // The coordinates for point i are stored as double (floating-point)
   // numbers
   // in the entries x[i], y[i], z[i].

   // When C converts a double to an int (that is, integer), it rounds the
   // value
   // down. To obtain the TSPLIB rounding to the nearest integer, we add 0.5
   // to
   // the square-root value before casting it to an int.

   public static double euclid3d_edgelen_round(Star star1, Star star2)
   {
      double t1 = star1.getX() - star2.getX();
      double t2 = star1.getY() - star2.getY();
      double t3 = star1.getZ() - star2.getZ();

      return (int) (Math.sqrt(t1 * t1 + t2 * t2 + t3 * t3) + 0.5);
   }

   public static double euclid3d_edgelen(Star star1, Star star2)
   {
      double t1 = star1.getX() - star2.getX();
      double t2 = star1.getY() - star2.getY();
      double t3 = star1.getZ() - star2.getZ();

      return Math.sqrt(t1 * t1 + t2 * t2 + t3 * t3);
   }

   /**
    * 
    * TODO: Offensichtlicher weise fehlerhaft.
    * 
    * Implementierung des nearest Neighbour Algorithm mit worst case
    * performance von O(N^2). (statt O Zeichen fuer worst case)
    *
    * @param stars
    *           Liste aller Sterne.
    * @param starDist
    *           2D Array mit allen Distanzen zwischen den Sternen.
    * @return Der TSP weg wird zurueckgegeben.
    */
   public static List<Star> nearestNeighbourAlgorithm(List<Star> stars)
   {
      Random rand = new Random();

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
      return tsp;
   }

   private void printStars(List<Star> starList)
   {
      for (Star star : starList)
      {
         star.printCoordinates();
      }
   }

   private void printTSP(List<Star> tsp)
   {

      for (Star star : tsp)
      {
         star.printCoordinates();
      }

   }

}
