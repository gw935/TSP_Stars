package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class TSPController
{
   @FXML // ResourceBundle that was given to the FXMLLoader
   private ResourceBundle resources;

   @FXML // URL location of the FXML file that was given to the FXMLLoader
   private URL location;

   @FXML // fx:id="openFileMenuItem"
   private MenuItem openFileMenuItem; // Value injected by FXMLLoader

   @FXML // fx:id="closeMenuItem"
   private MenuItem closeMenuItem; // Value injected by FXMLLoader

   @FXML // fx:id="nearestNeighbourMenuItem"
   private MenuItem nearestNeighbourMenuItem; // Value injected by FXMLLoader

   @FXML // fx:id="aboutMenuItem"
   private MenuItem aboutMenuItem; // Value injected by FXMLLoader

   @FXML // fx:id="tspTextArea"
   private TextArea tspTextArea; // Value injected by FXMLLoader

   @FXML // fx:id="initListTextArea"
   private TextArea initListTextArea; // Value injected by FXMLLoader

   @FXML // fx:id="statusTextArea"
   private TextArea statusTextArea; // Value injected by FXMLLoader

   private FileChooser fileChooser = new FileChooser();

   private StarFileReader starFileReader;

   private List<Star> starList;

   private List<Star> starListTSP;

   private File file;

   private AboutDialog aboutDialog;

   private TSP tsp;

   @FXML // This method is called by the FXMLLoader when initialization is
   // complete
   void initialize()
   {
      assert openFileMenuItem != null : "fx:id=\"openFileMenuItem\" was not injected: check your FXML file 'TSPBorderPane.fxml'.";
      assert closeMenuItem != null : "fx:id=\"closeMenuItem\" was not injected: check your FXML file 'TSPBorderPane.fxml'.";
      assert nearestNeighbourMenuItem != null : "fx:id=\"nearestNeighbourMenuItem\" was not injected: check your FXML file 'TSPBorderPane.fxml'.";
      assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'TSPBorderPane.fxml'.";
      assert tspTextArea != null : "fx:id=\"tspTextArea\" was not injected: check your FXML file 'TSPBorderPane.fxml'.";
      assert initListTextArea != null : "fx:id=\"initListTextArea\" was not injected: check your FXML file 'TSPBorderPane.fxml'.";
      assert statusTextArea != null : "fx:id=\"statusTextArea\" was not injected: check your FXML file 'TSPBorderPane.fxml'.";

      fileChooser.setInitialDirectory(null);
      starFileReader = new StarFileReader();
      starList = null;
      aboutDialog = new AboutDialog();
      tsp = new TSP();
   }

   @FXML
   void handleOpenFile(ActionEvent event) throws IOException
   {
      Window stage = ((MenuItem) event.getTarget()).getParentPopup().getOwnerWindow();
      fileChooser.setTitle("Open Star File");
      fileChooser.getExtensionFilters()
            .add(new FileChooser.ExtensionFilter("Text Star Coordinate File", "*.txt"));
      try
      {
         statusTextArea.appendText("====================================================================================\n");
         file = fileChooser.showOpenDialog(stage);
         fileChooser.setInitialDirectory(file.getParentFile());
         System.out.println("Open Star File...");
         System.out.println(file.getAbsolutePath());
         starList = starFileReader.readFile(file);

         // initListTextArea
         initListTextArea.clear();
         for (Star star : starList)
         {
            initListTextArea.appendText(star.printCoordinates());
         }

         // tspTextArea
         tspTextArea.clear();

         // statusTextArea
         statusTextArea.appendText("Open Star File...\n");
         statusTextArea.appendText("Path of the opend File: " + file.getAbsolutePath() + "\n");
         statusTextArea.appendText("====================================================================================\n");
      }
      catch (Exception e)
      {
         // TODO: Exception handling
         throw new IOException("An Error occurred trying to load or read the File.");
      }
   }

   @FXML
   void handleClose(ActionEvent event)
   {
      System.out.println("Exit...");
      statusTextArea.appendText("Exit...\n");
      Platform.exit();
   }

   @FXML
   void handleNearestNeighbourAlgorithm(ActionEvent event)
   {
      statusTextArea.appendText("====================================================================================\n");
      // statusTextArea
      statusTextArea.appendText("Calculate TSP of the opend List of Stars.\n");
      
      long startTime, resultTime;
      startTime = System.currentTimeMillis();
      
      starListTSP = tsp.nearestNeighbourAlgorithm(starList);
      
      resultTime = System.currentTimeMillis() - startTime;
      
      statusTextArea.appendText("Calculated Distance of TSP List: " + tsp.getShortestDist() + "\n");
      statusTextArea.appendText("Dauer der TSP berechnungen in ms:  " + resultTime + " ms\n");
      statusTextArea.appendText("Dauer der TSP berechnungen in s:   " + resultTime / 1000 + " s\n");
      statusTextArea.appendText("Dauer der TSP berechnungen in min: " + resultTime / 1000 / 60 + " min\n");

      // tspTextArea
      tspTextArea.clear();
      for (Star star : starListTSP)
      {
         tspTextArea.appendText(star.printCoordinates());
      }

      statusTextArea.appendText("Done calculating TSP of the opend List of Stars.\n");
      statusTextArea.appendText("====================================================================================\n");
   }

   @FXML
   void handleAbout(ActionEvent event)
   {
      aboutDialog.showAboutDialog();
   }
}
