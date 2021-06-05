package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application
{
   // 4 Test Sterne
   public final static String FILENAME0 = "res/test.txt";

   // 100 Sterne
   public final static String FILENAME1 = "res/star100.xyz.txt";

   // 1001 Sterne
   public final static String FILENAME2 = "res/sh2_1000.txt";

   // 10000 Sterne
   public final static String FILENAME3 = "res/star10k.xyz.txt";

   // 37860 Sterne
   public final static String FILENAME4 = "res/kj37859.xyz.txt";

   // 119614 Sterne
   public final static String FILENAME5 = "res/hyg119614.xyz.txt";

   @Override
   public void start(Stage primaryStage)
   {
      try
      {
         BorderPane root = new BorderPane();
         Scene scene = new Scene(root, 400, 400);
         scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
         primaryStage.setScene(scene);

         // --------------Read File-------------------
         TSP tsp = new TSP();
         // --------------Read File-------------------

         primaryStage.show();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public static void main(String[] args)
   {
      launch(args);
   }
}
