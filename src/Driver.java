import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Driver extends Application {
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		RentalCompany s = null;
		RentalCars see = null ;
		ComboBox<RentalCompany> a = new ComboBox<>();
		Scene sc = Scenes.getAccountant(primaryStage);
		primaryStage.setScene(sc);
		sc.getStylesheets().add("Plugins.css");
		
		Queries.getRentalCompany();
		primaryStage.setResizable(false);

		primaryStage.show();

	}
}