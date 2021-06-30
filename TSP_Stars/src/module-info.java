module TSP_Stars {
	requires javafx.controls;
   requires javafx.graphics;
   requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
