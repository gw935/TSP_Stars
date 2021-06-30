package application;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class AboutDialog extends Dialog<ButtonType>
{
   String text = "The Program demonstrates the Travelling salesman problem using the Nearest neighbour algorithm.";

   public AboutDialog()
   {
      setTitle("About!");

      ButtonType buttonOk = ButtonType.OK;
      getDialogPane().getButtonTypes().add(buttonOk);

      setContentText(text);
   }

   public void showAboutDialog()
   {
      Optional<ButtonType> result = showAndWait();
   }

}
