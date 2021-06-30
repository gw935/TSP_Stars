package application.ui;

import application.ui.menu.FileMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class UIMenuBar extends MenuBar
{

   public UIMenuBar(Stage primaryStage)
   {
      Menu fileMenu = new FileMenu(primaryStage);
      
      this.getMenus().addAll(fileMenu);
   }

}
