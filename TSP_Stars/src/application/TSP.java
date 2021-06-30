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
      readFile = new ReadFile();

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

   }

   /**
    * 
    * Diese Methode berechnet die Distanz von 2 Sternen, mit abrundung.
    * Abgeaenderte Methode der Quelle:
    * http://www.math.uwaterloo.ca/tsp/star/index.html
    *
    * @param star1
    *           erster Stern.
    * @param star2
    *           zweiter Stern.
    * @return Distanz von stern1 zu stern2.
    */
   public static double euclid3d_edgelen_round(Star star1, Star star2)
   {
      double t1 = star1.getX() - star2.getX();
      double t2 = star1.getY() - star2.getY();
      double t3 = star1.getZ() - star2.getZ();

      return (int) (Math.sqrt(t1 * t1 + t2 * t2 + t3 * t3) + 0.5);
   }

   /**
    * 
    * Diese Methode berechnet die Distanz von 2 Sternen. Abgeaenderte Methode
    * der Quelle: http://www.math.uwaterloo.ca/tsp/star/index.html
    *
    * @param star1
    *           erster Stern.
    * @param star2
    *           zweiter Stern.
    * @return Distanz von stern1 zu stern2.
    */
   public static double euclid3d_edgelen(Star star1, Star star2)
   {
      double t1 = star1.getX() - star2.getX();
      double t2 = star1.getY() - star2.getY();
      double t3 = star1.getZ() - star2.getZ();

      return Math.sqrt(t1 * t1 + t2 * t2 + t3 * t3);
   }

   /**
    * 
    * Implementierung des nearest Neighbour Algorithm mit worst case
    * performance von O(N^2). Der benutzte Speicherverbrauch betraegt 2n.
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

   /**
    * 
    * Gibt alle Koordinaten der Sterne in der Liste von Sternen aus.
    *
    * @param starList
    *           Liste von Sternen.
    */
   private void printStars(List<Star> starList)
   {
      for (Star star : starList)
      {
         star.printCoordinates();
      }
   }

   /**
    * 
    * Diese Methode gibt alle Koordinaten Sterne in der TSP Reinfolge an.
    *
    * @param tsp
    */
   private void printTSP(List<Star> tsp)
   {

      for (Star star : tsp)
      {
         star.printCoordinates();
      }

   }

   /**
    * 
    * Diese Methode berechnet alle Permutationen von Sternen.
    *
    * @param stars
    *           ist eine Liste von Sternen.
    * @return Liste von Listen von allen Permutationen.
    */
   public List<List<Star>> generatePerm(List<Star> stars)
   {
      if (stars.isEmpty())
      {
         List<List<Star>> result = new ArrayList<>();
         result.add(new ArrayList<>());
         return result;
      }

      Star firstElement = stars.remove(0);
      List<List<Star>> returnValue = new ArrayList<>();
      List<List<Star>> permutations = generatePerm(stars);
      for (List<Star> smallerPermutated : permutations)
      {
         for (int index = 0; index <= smallerPermutated.size(); index++)
         {
            List<Star> temp = new ArrayList<>(smallerPermutated);
            temp.add(index, firstElement);
            returnValue.add(temp);
         }
      }
      return returnValue;
   }

   /**
    * 
    * Berechnet die Distanz von allen Sternen in einer Liste.
    *
    * @param stars
    *           Liste von Sternen.
    * @return Distanz zwischen den Sternen.
    */
   public double calcDistancesOfList(List<Star> stars)
   {
      double dist = 0;
      for (int i = 0; i < stars.size() - 1; i++)
      {
         dist += euclid3d_edgelen(stars.get(i), stars.get(i + 1));
      }

      return dist;
   }

}
