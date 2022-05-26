
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RentAndSellDriver extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = Scenes.First(primaryStage);
		primaryStage.setScene(sc);
		sc.getStylesheets().add("Plugins.css");
		primaryStage.setTitle("Rent And Sell Application");
		primaryStage.getIcons().add(new Image("file:logo.png"));
		primaryStage.setResizable(false);

		primaryStage.show();

	}
}
