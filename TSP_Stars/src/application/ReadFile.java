package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Woerter uebergeben und in eine TreeMap schreiben.
 * 
 * @author Gin-Wah Chau
 *
 */
public class ReadFile
{
   public final String REGEX = "\s";

   private File file;

   private List<Star> stars;

   /**
    * Oeffnet eine Datei mit dem uebergebenen Path und ruft die readFile()
    * Methode auf.
    * 
    * @param path
    *           Pfad einer Datei.
    * @throws IOException
    *            kommt von readFile().
    */
   public ReadFile(String path) throws IOException
   {
      if (path == null)
      {
         throw new IllegalArgumentException("Uebergebene Pfad ist null");
      }
      if (path.isEmpty())
      {
         throw new IllegalArgumentException("Uebergebene Pfad ist leer");
      }

      File file = new File(path);

      if (!file.exists())
      {
         throw new IllegalArgumentException("Pfad existiert nicht.");
      }
      if (!file.isFile())
      {
         throw new IllegalArgumentException("Ungueltiger Datei.");
      }
      if (!file.canRead())
      {
         throw new IllegalArgumentException("Datei nicht lesbar.");
      }

      this.file = file;
      this.stars = readFile();
   }

   /**
    * Liest alle Zeilen einer Datei und fuellt eine Liste mit Star Objecten.
    * 
    * @return Unmodifizierbare Liste mit Star Objekten.
    * @throws IOException
    */
   private List<Star> readFile() throws IOException
   {
      stars = new ArrayList<Star>();

      String line = null;

      BufferedReader reader = null;
      try
      {
         reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
         while ((line = reader.readLine()) != null)
         {
            // cut coordinates in 3 coordinates and create new Star
            if (!line.isEmpty())
            {
               String[] coordinates = line.split(REGEX);
               if (coordinates.length == 3)
               {
                  try
                  {
                     stars.add(new Star(Double.parseDouble(coordinates[0]),
                           Double.parseDouble(coordinates[1]), Double.parseDouble(coordinates[2])));
                  }
                  catch (NumberFormatException e)
                  {
                     // Do nothing!
                  }
               }
            }
         }
      }
      finally
      {
         if (reader != null)
         {
            reader.close();
         }
      }

      return Collections.unmodifiableList(stars);
   }

   /**
    * Gibt eine unmodifizierbares Liste mit allen Star Objekten zurueck.
    * 
    * @return Eine unmodifizierbare Liste mit allen Star Objekten.
    */
   public List<Star> getListOfStars()
   {
      return Collections.unmodifiableList(stars);
   }

}
