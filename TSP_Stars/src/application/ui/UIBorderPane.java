package application.ui;

import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIBorderPane extends BorderPane
{
   public static TextArea textArea = new TextArea();

   public UIBorderPane(Stage primaryStage)
   {
      MenuBar menubar = new UIMenuBar(primaryStage);
      
      this.setTop(menubar);
      this.setCenter(textArea);
   }

}
