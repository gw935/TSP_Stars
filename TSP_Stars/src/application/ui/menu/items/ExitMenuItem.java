package application.ui.menu.items;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class ExitMenuItem extends MenuItem
{

   public ExitMenuItem()
   {
      super("_Exit");

      KeyCodeCombination beendenKeyCombination = new KeyCodeCombination(KeyCode.Q,
            KeyCombination.SHORTCUT_DOWN);
      this.setAccelerator(beendenKeyCombination);

      this.setMnemonicParsing(true);

      this.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent e)
         {
            System.out.println("Exit...");
            Platform.exit();
         }
      });
   }

}
