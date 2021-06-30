package application.ui.menu.items;

import java.io.File;
import java.io.IOException;

import application.ReadFile;
import application.ui.UIBorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OpenFileMenuItem extends MenuItem
{

   public OpenFileMenuItem(Stage primaryStage)
   {
      super("_Open File");

      ReadFile readFile = new ReadFile();

      FileChooser fileChooser = new FileChooser();

      this.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent e)
         {
            System.out.println("Open new File...");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            System.out.println(selectedFile.getAbsolutePath());
            readFile.loadfile(selectedFile);
            
            UIBorderPane.textArea.clear();
            UIBorderPane.textArea.appendText("The Selected File is: " + selectedFile.getAbsolutePath() + "\n");
         }
      });
   }

}
