package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StarFileReader
{
   public final String REGEX = "\s";

   public List<Star> readFile(File file) throws IOException
   {
      if (file == null)
      {
         throw new IllegalArgumentException("Uebergebene Pfad ist null");
      }
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

      List<Star> stars = new ArrayList<Star>();
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

}
