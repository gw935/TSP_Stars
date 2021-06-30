package application.ui.menu;

import application.ui.menu.items.ExitMenuItem;
import application.ui.menu.items.OpenFileMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class FileMenu extends Menu
{

   public FileMenu(Stage primaryStage)
   {
      super("_File");
      
      MenuItem openMenuItem = new OpenFileMenuItem(primaryStage);
      MenuItem exitMenuItem = new ExitMenuItem();

      this.getItems().addAll(openMenuItem, exitMenuItem);
   }

}
