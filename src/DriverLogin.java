


import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class DriverLogin extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	Scene scene;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ImageView image = new ImageView("file:logo.png");
		image.setFitHeight(330);
		image.setFitWidth(600);
		image.setLayoutX(177);
		image.setLayoutY(0);

		AnchorPane root = new AnchorPane();
		
	 
		root.setStyle("-fx-background: grey;");
		
		
//		image.setOnMouseClicked(event ->{
//			FadeTransition ft2 = new FadeTransition(Duration.seconds(1),image);
//			ft2.setFromValue(1.0);
//			ft2.setToValue(0);
//			ft2.setCycleCount(1);
//			ft2.setAutoReverse(true);
//			ft2.play();
//			
//			image.setDisable(true);
//		});
		
		
		
		
		
		Label nameLabel = new Label("User Name :");
		nameLabel.setPrefHeight(44);
		nameLabel.setPrefWidth(113);
		nameLabel.setLayoutX(319);
		nameLabel.setLayoutY(330);
		nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));


		Label PasswordLabel = new Label("Password :");
		PasswordLabel.setPrefHeight(44);
		PasswordLabel.setPrefWidth(113);
		PasswordLabel.setLayoutX(319);
		PasswordLabel.setLayoutY(374);
		PasswordLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));


		TextField NameTF = new TextField();
		NameTF.setLayoutX(432);
		NameTF.setLayoutY(340);
		
		
		PasswordField PasswordTF = new PasswordField();
		PasswordTF.setLayoutX(432);
		PasswordTF.setLayoutY(384);
		
		Button signIn = new Button("Log In");
		signIn.setPrefHeight(35);
		signIn.setPrefWidth(100);
		signIn.setLayoutX(637);
		signIn.setLayoutY(357);
		
		

		Button newAccount = new Button("Create new Account");
		newAccount.setPrefHeight(35);
		newAccount.setPrefWidth(150);
		newAccount.setLayoutX(425);
		newAccount.setLayoutY(438);
		
		root.getChildren().addAll(image,nameLabel,PasswordLabel,NameTF,PasswordTF,signIn,newAccount);
		

		
		signIn.setOnAction(event ->{
			if (NameTF.getText().isEmpty() || PasswordTF.getText().isEmpty()) {
				Alert(Alert.AlertType.WARNING, root.getScene().getWindow(), "WARNING!",
						"Please Fill All text Text Field!");
			}
			else {
			
			try {
				Users u = Queries.getUserForSignIn(PasswordTF.getText(),NameTF.getText());
				if (u == null) {
					Alert(Alert.AlertType.WARNING, root.getScene().getWindow(), "WARNING!",
							"Wrong user name or password!");
				} else {
					Scene sc = Scenes.UserSelectCompany(primaryStage);
					sc.getStylesheets().add("Plugins.css");
					primaryStage.setScene(sc);
					

				}

			} catch (ClassNotFoundException | SQLException e) {
				Alert(Alert.AlertType.ERROR, root.getScene().getWindow(), "ERROR!",
						"ERROR In DATABASE!");
			}
			
			}
		});
		
		
		newAccount.setOnAction(event ->{
			NameTF.clear();
			PasswordTF.clear();
			
			
			Label main = new Label("Make new Account");
			main.setFont(Font.font("Arial", FontWeight.BOLD, 30));
			main.setLayoutX(364);
			main.setLayoutY(44);
			
			
			
			Label LUN = new Label("User Name:");
			LUN.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			LUN.setLayoutX(364);
			LUN.setLayoutY(115);
			
			
			Label LPss = new Label("Password:");
			LPss.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			LPss.setLayoutX(364);
			LPss.setLayoutY(198);
			
			
			Label LEmail = new Label("Email:");
			LEmail.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			LEmail.setLayoutX(364);
			LEmail.setLayoutY(283);
			
			
			Label LPhone = new Label("Phone:");
			LPhone.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			LPhone.setLayoutX(364);
			LPhone.setLayoutY(369);
			
			
			
			TextField TUN = new TextField();
			TUN.setLayoutX(480);
			TUN.setLayoutY(115);
			
			PasswordField TPass = new PasswordField();
			TPass.setLayoutX(480);
			TPass.setLayoutY(198);
			
			TextField Temail = new TextField();
			Temail.setLayoutX(480);
			Temail.setLayoutY(283);
			
			
			TextField TPhone = new TextField();
			TPhone.setLayoutX(480);
			TPhone.setLayoutY(369);
			
			Button back = new Button("Back");
			back.setPrefSize(137, 37);
			back.setLayoutX(364);
			back.setLayoutY(440);
			
			Button done = new Button("Done");
			done.setPrefSize(137, 37);
			done.setLayoutX(530);
			done.setLayoutY(440);
			
			
			root.getChildren().removeAll(image,nameLabel,PasswordLabel,NameTF,PasswordTF,signIn,newAccount);
			
			root.getChildren().addAll(main,LUN,LEmail,LPhone,LPss,TUN,Temail,TPass,TPhone,back,done);
			
			
			back.setOnAction(e->{
				
				TUN.clear();
				Temail.clear();
				TPhone.clear();
				TPass.clear();
				root.getChildren().removeAll(main,LUN,LEmail,LPhone,LPss,TUN,Temail,TPass,TPhone,back,done);

				root.getChildren().addAll(image,nameLabel,PasswordLabel,NameTF,PasswordTF,signIn,newAccount);

			});
			
			done.setOnAction(e->{
				
				if (TPass.getText().isEmpty() || TUN.getText().isEmpty()
						|| Temail.getText().isEmpty() || TPhone.getText().isEmpty()) {
					Alert(Alert.AlertType.WARNING, root.getScene().getWindow(), "WARNING!",
							"Please Fill All text Text Field!");
				} else {
					try {
						Users u = Queries.getUserForCheck(TUN.getText());
						if(u==null)
						{
							String password = TPass.getText();
							String user_name = TUN.getText();
							String email = Temail.getText();
							String phone = TPhone.getText();
							
							Users user = new Users(user_name, phone,password,email );
							Queries.insertUser(user);
							
							Alert(Alert.AlertType.CONFIRMATION, root.getScene().getWindow(), "CONFIRMATION!",
									"Welcome "+TUN.getText()+"!");
							
							
							TUN.clear();
							Temail.clear();
							TPhone.clear();
							TPass.clear();
							root.getChildren().removeAll(main,LUN,LEmail,LPhone,LPss,TUN,Temail,TPass,TPhone,back,done);

							root.getChildren().addAll(image,nameLabel,PasswordLabel,NameTF,PasswordTF,signIn,newAccount);
							
						}
						else
						{
							Alert(Alert.AlertType.WARNING, root.getScene().getWindow(), "WARNING!",
									"User name Exist please Change user name!");
						}
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
					

					
				}
			});
				
			
			
			
		});
		
		
		
		
		
		
		
		
		
			
			
//		});
		System.out.println();
		Scene scene= new Scene(root, 1000, 600);
		
		scene.getStylesheets().add("Plugins.css");
		primaryStage.setScene(scene);
//		Queries.getManager();

		primaryStage.show();

	}
	private void Alert(Alert.AlertType alert1, Window root, String title, String message) { // method that give new
		// window with alert
		Alert alert = new Alert(alert1);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(root);
		alert.show();
	}
	
	
	
	
	
	
	
	
	
	
	
}