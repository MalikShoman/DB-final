
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.EqualizerBand;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

public class Scenes {

	public static Pane RnetalCompaniesScene(Stage primaryStage) {

		TableView<RentalCompany> table = new TableView<RentalCompany>();
		table.setLayoutX(54);
		table.setLayoutY(100);
		table.setPrefHeight(409);
		table.setPrefWidth(670);
		table.setEditable(true);

		TableColumn<RentalCompany, String> RentalCompanyidColumn = new TableColumn<RentalCompany, String>("co_address");
		RentalCompanyidColumn.setCellValueFactory(new PropertyValueFactory<>("co_address"));
		RentalCompanyidColumn.setMinWidth(100);
		TableColumn<RentalCompany, String> RentalCompanynameColumn = new TableColumn<RentalCompany, String>("co_name");
		RentalCompanynameColumn.setCellValueFactory(new PropertyValueFactory<>("co_name"));
		RentalCompanynameColumn.setMinWidth(100);
		RentalCompanynameColumn.setCellFactory(TextFieldTableCell.<RentalCompany>forTableColumn());
		RentalCompanynameColumn.setOnEditCommit((CellEditEvent<RentalCompany, String> e) -> {
			((RentalCompany) e.getTableView().getItems().get(e.getTablePosition().getRow()))
					.setCo_name(e.getNewValue()); // display only
			Queries.updateNameRent(e.getRowValue().getCo_address(), e.getNewValue());

		});
		TableColumn<RentalCompany, String> RentalCompanyNumberColumn = new TableColumn<RentalCompany, String>(
				"phone_num");
		RentalCompanyNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
		RentalCompanyNumberColumn.setMinWidth(100);
		RentalCompanyNumberColumn.setCellFactory(
				TextFieldTableCell.<RentalCompany>forTableColumn());
		RentalCompanyNumberColumn.setOnEditCommit((CellEditEvent<RentalCompany, String> t) -> {
			((RentalCompany) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setPhone_num(t.getNewValue());
			Queries.updatePhoneRent(t.getRowValue().getCo_address(), t.getNewValue());
		});

		TableColumn<RentalCompany, String> RentalCompanyemailColumn = new TableColumn<RentalCompany, String>("email");
		RentalCompanyemailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		RentalCompanyemailColumn.setMinWidth(100);
		RentalCompanyemailColumn.setCellFactory(TextFieldTableCell.<RentalCompany>forTableColumn());
		RentalCompanyemailColumn.setOnEditCommit((CellEditEvent<RentalCompany, String> e) -> {
			((RentalCompany) e.getTableView().getItems().get(e.getTablePosition().getRow())).setEmail(e.getNewValue()); // display
																														// only
			Queries.updateEmailRent(e.getRowValue().getCo_address(), e.getNewValue());

		});

		ObservableList<RentalCompany> questionsOb;
		try {
			questionsOb = Queries.getRentalCompany();
			table.setItems(questionsOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading ");
			e.printStackTrace();
		}

		table.getColumns().addAll(RentalCompanyidColumn, RentalCompanynameColumn, RentalCompanyNumberColumn,
				RentalCompanyemailColumn);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(500);
		refreshButton.setLayoutY(510);

		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getRentalCompany());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		final TextField addCName = new TextField();
		addCName.setMaxWidth(RentalCompanynameColumn.getPrefWidth());
		addCName.setPromptText("Name");
		addCName.setLayoutX(70);
		addCName.setLayoutY(510);
		final TextField addID = new TextField();
		addID.setMaxWidth(RentalCompanyidColumn.getPrefWidth());
		addID.setPromptText("Address");
		addID.setLayoutX(160);
		addID.setLayoutY(510);

		final TextField addEmail = new TextField();
		addEmail.setMaxWidth(RentalCompanyemailColumn.getPrefWidth());
		addEmail.setPromptText("E-mail");
		addEmail.setLayoutX(250);
		addEmail.setLayoutY(510);
		final TextField addPhoneN = new TextField();
		addPhoneN.setMaxWidth(RentalCompanyNumberColumn.getPrefWidth());
		addPhoneN.setPromptText("phoneN");
		addPhoneN.setLayoutX(350);
		addPhoneN.setLayoutY(510);
		final Button addButton = new Button("Add");
		addButton.setLayoutX(450);
		addButton.setLayoutY(510);
		addButton.setOnAction((ActionEvent e) -> {
			RentalCompany rc;
			rc = new RentalCompany(addID.getText(), addCName.getText(),addPhoneN.getText(),
					addEmail.getText());
			try {
				Queries.insertDataRentComp(rc);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			addCName.clear();
			addID.clear();
			addEmail.clear();
			addPhoneN.clear();

		});
	
		Button back = new Button("Back");


		back.setOnAction(ev ->{
			Scene sc3;
			try {
				sc3 = Scenes.getManager(primaryStage);
				sc3.getStylesheets().add("Plugins.css");
				primaryStage.setScene(sc3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		});
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color :  #0c1f46");
		pane.getChildren().addAll(back,addCName, addID, addEmail, addPhoneN, addButton,refreshButton,table);

		return pane;

	}

	public static Pane SellCompaniesScene(Stage primaryStage) {

		TableView<SellCompany> table = new TableView<SellCompany>();
		table.setLayoutX(54);
		table.setLayoutY(100);
		table.setPrefHeight(409);
		table.setPrefWidth(670);
		table.setEditable(true);

		TableColumn<SellCompany, String> SellCompanyidColumn = new TableColumn<SellCompany, String>("co_address");
		SellCompanyidColumn.setCellValueFactory(new PropertyValueFactory<>("co_address"));
		SellCompanyidColumn.setMinWidth(100);
		TableColumn<SellCompany, String> RentalCompanynameColumn = new TableColumn<SellCompany, String>("co_name");
		RentalCompanynameColumn.setCellValueFactory(new PropertyValueFactory<>("co_name"));
		RentalCompanynameColumn.setMinWidth(100);
		RentalCompanynameColumn.setCellFactory(TextFieldTableCell.<SellCompany>forTableColumn());
		RentalCompanynameColumn.setOnEditCommit((CellEditEvent<SellCompany, String> e) -> {
			((SellCompany) e.getTableView().getItems().get(e.getTablePosition().getRow())).setCo_name(e.getNewValue()); // display
																														// only
			Queries.updateNameRent(e.getRowValue().getCo_address(), e.getNewValue());

		});
		TableColumn<SellCompany, String> RentalCompanyNumberColumn = new TableColumn<SellCompany, String>(
				"phone_num");
		RentalCompanyNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
		RentalCompanyNumberColumn.setMinWidth(100);
		RentalCompanyNumberColumn
				.setCellFactory(TextFieldTableCell.<SellCompany>forTableColumn());
		RentalCompanyNumberColumn.setOnEditCommit((CellEditEvent<SellCompany, String> t) -> {
			((SellCompany) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setPhone_num(t.getNewValue());
			Queries.updatePhoneRent(t.getRowValue().getCo_address(), t.getNewValue());
		});

		TableColumn<SellCompany, String> RentalCompanyemailColumn = new TableColumn<SellCompany, String>("email");
		RentalCompanyemailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		RentalCompanyemailColumn.setMinWidth(100);
		RentalCompanyemailColumn.setCellFactory(TextFieldTableCell.<SellCompany>forTableColumn());
		RentalCompanyemailColumn.setOnEditCommit((CellEditEvent<SellCompany, String> e) -> {
			((SellCompany) e.getTableView().getItems().get(e.getTablePosition().getRow())).setEmail(e.getNewValue()); // display
																														// only
			Queries.updateEmailRent(e.getRowValue().getCo_address(), e.getNewValue());

		});

		ObservableList<SellCompany> questionsOb;
		try {
			questionsOb = Queries.getSellCompany();
			table.setItems(questionsOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading ");
			e.printStackTrace();
		}

		table.getColumns().addAll(SellCompanyidColumn, RentalCompanynameColumn, RentalCompanyNumberColumn,
				RentalCompanyemailColumn);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(500);
		refreshButton.setLayoutY(510);

		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getSellCompany());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		final TextField addCName = new TextField();
		addCName.setMaxWidth(RentalCompanynameColumn.getPrefWidth());
		addCName.setPromptText("Name");
		addCName.setLayoutX(70);
		addCName.setLayoutY(510);
		final TextField addID = new TextField();
		addID.setMaxWidth(SellCompanyidColumn.getPrefWidth());
		addID.setPromptText("Address");
		addID.setLayoutX(160);
		addID.setLayoutY(510);

		final TextField addEmail = new TextField();
		addEmail.setMaxWidth(RentalCompanyemailColumn.getPrefWidth());
		addEmail.setPromptText("E-mail");
		addEmail.setLayoutX(250);
		addEmail.setLayoutY(510);
		final TextField addPhoneN = new TextField();
		addPhoneN.setMaxWidth(RentalCompanyNumberColumn.getPrefWidth());
		addPhoneN.setPromptText("phoneN");
		addPhoneN.setLayoutX(350);
		addPhoneN.setLayoutY(510);
		final Button addButton = new Button("Add");
		addButton.setLayoutX(450);
		addButton.setLayoutY(510);
		
		addButton.setOnAction((ActionEvent e) -> {
			SellCompany rc;
			rc = new SellCompany(addID.getText(), addCName.getText(),addPhoneN.getText(),addEmail.getText());
			try {
				Queries.insertDataSellComp(rc);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			addCName.clear();
			addID.clear();
			addEmail.clear();
			addPhoneN.clear();

		});

		Button back = new Button("Back");


		back.setOnAction(ev ->{
			Scene sc3;
			try {
				sc3 = Scenes.getManager(primaryStage);
				sc3.getStylesheets().add("Plugins.css");
				primaryStage.setScene(sc3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		});
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color :  #0c1f46");
		pane.getChildren().addAll(back,addCName, addID, addEmail, addPhoneN, addButton, refreshButton, table);

		Scene scene = new Scene(pane, 1000, 600);
		return pane;

	}

//	public static Scene MangerShowCompany(Stage primaryStage) {
//		Button CompanyRentalButton = new Button("Rental Companies");
//		CompanyRentalButton.setPrefHeight(100);
//		CompanyRentalButton.setPrefWidth(300);
//		CompanyRentalButton.setLayoutX(100);
//		CompanyRentalButton.setLayoutY(50);
//		Button CompanySellButton = new Button("Sell Companies");
//		CompanySellButton.setPrefHeight(100);
//		CompanySellButton.setPrefWidth(300);
//		CompanySellButton.setLayoutX(100);
//		CompanySellButton.setLayoutY(200);
//		Button backFromChosenCompany = new Button("Back");
//		backFromChosenCompany.setLayoutX(470);
//		backFromChosenCompany.setLayoutY(482);
//		Pane p = new Pane();
//		p.getChildren().addAll(CompanyRentalButton, CompanySellButton, backFromChosenCompany);
//		Scene scene = new Scene(p, 500, 500);
//		primaryStage.setTitle("CompaniesType");
//		RentalCompany s = null;
//		CompanyRentalButton.setOnAction(e -> {
//			primaryStage.setScene(Scenes.RnetalCompaniesScene(primaryStage, s));
//		});
//		SellCompany a = null;
//		CompanySellButton.setOnAction(e -> {
//			primaryStage.setScene(Scenes.SellCompaniesScene(primaryStage, a));
//		});
//		return scene;
//
//	}

	
	
	public static Scene Login(Stage primaryStage)
	{
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
		
		
		
		Button backTomain = new Button("Back");
		backTomain.setPrefSize(150,50);
		backTomain.setOnAction(event ->{
			Scene sc = Scenes.First(primaryStage);
			primaryStage.setScene(sc);
			sc.getStylesheets().add("Plugins.css");
			
		});
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
		
		root.getChildren().addAll(backTomain,image,nameLabel,PasswordLabel,NameTF,PasswordTF,signIn,newAccount);
		

		
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
			
			
			root.getChildren().removeAll(backTomain,image,nameLabel,PasswordLabel,NameTF,PasswordTF,signIn,newAccount);
			
			root.getChildren().addAll(main,LUN,LEmail,LPhone,LPss,TUN,Temail,TPass,TPhone,back,done);
			
			
			back.setOnAction(e->{
				
				TUN.clear();
				Temail.clear();
				TPhone.clear();
				TPass.clear();
				root.getChildren().removeAll(main,LUN,LEmail,LPhone,LPss,TUN,Temail,TPass,TPhone,back,done);

				root.getChildren().addAll(backTomain,image,nameLabel,PasswordLabel,NameTF,PasswordTF,signIn,newAccount);

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

							root.getChildren().addAll(backTomain,image,nameLabel,PasswordLabel,NameTF,PasswordTF,signIn,newAccount);
							
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
		return scene;

	}
	
	
	
	
	
	public static Scene UserSelectCompany(Stage primaryStage) {
		ImageView image = new ImageView("file:main2.jpg");
		image.setFitHeight(600);
		image.setFitWidth(1000);
		Label l = new Label("please choose Rental or Sell to show cars");
		l.setTextFill(Color.WHITE);
		l.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Button c1 = new Button("RentalCompanies");
		c1.setPrefSize(200, 80);
		Button c2 = new Button("SellCompanies");
		c2.setPrefSize(200, 80);

		Button b3 = new Button("logOut");
		b3.setPrefSize(200, 80);

		HBox se = new HBox(5);
		l.setLayoutX(250);
		l.setLayoutY(150);		
		b3.setLayoutX(390);
		b3.setLayoutY(500);
		c1.setLayoutX(570);
		c1.setLayoutY(226);
		c2.setLayoutX(220);
		c2.setLayoutY(226);
		Pane p = new Pane();
		ObservableList<RentalCars> Ob = FXCollections.observableArrayList();
		ObservableList<SellCars> Ob1 = FXCollections.observableArrayList();
		try {
			Ob = Queries.getRentalCars();
			
		
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading Rental");
			e.printStackTrace();
		}
		try {
			Ob1 = Queries.getSellCars();
		
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading Rental");
			e.printStackTrace();
		}
		
		c1.setOnAction(e -> {
			Scene sc = Scenes.UserShow_RentalCarss(primaryStage);
			sc.getStylesheets().add("Plugins.css");

			primaryStage.setScene(sc);
			
		});
		
		c2.setOnAction(e -> {
			Scene sc = Scenes.UserShow_SellCarss(primaryStage);
			sc.getStylesheets().add("Plugins.css");
			primaryStage.setScene(sc);
		});
		
		b3.setOnAction(e ->{
			
			Scene sc = Scenes.Login(primaryStage);
			sc.getStylesheets().add("Plugins.css");
			
			primaryStage.setScene(sc);
			
			
			
			
			
			
			
			
		});
		

	
	
		// for c2 dont forget ;

		p.getChildren().addAll(image,l , c1, c2,b3);
		Scene scene = new Scene(p, 1000, 600);
		p.setStyle("-fx-background: grey");

		scene.getStylesheets().add("Plugins.css");
		return scene;

	}
	public static Scene UserShow_RentalCarss(Stage primaryStage) {
		ImageView imageMain = new ImageView("file:rentCars.jpg");
		imageMain.setFitHeight(600);
		imageMain.setFitWidth(1000);
//		ComboBox<RentalCompany> cb  = new ComboBox<>();
//		cb.setPrefSize(236, 31);
//		ObservableList<RentalCompany> Ob = FXCollections.observableArrayList();
//		cb.setLayoutX(30);
//		cb.setLayoutY(25);
		Button back = new Button("Back") ;
		back.setPrefSize(151, 51);
		back.setLayoutX(792);
		back.setLayoutY(497);
		
//		Button submint = new Button("Submint") ;
//		submint.setPrefSize(151, 51);
//		submint.setLayoutX(295);
//		submint.setLayoutY(14);
		
		
		FadeTransition ft = new FadeTransition(Duration.seconds(1.5),imageMain);
		ft.setFromValue(1);
		ft.setToValue(0.2);
		ft.play();
		
		
		back.setOnAction(e -> {
			primaryStage.setScene(Scenes.UserSelectCompany(primaryStage));
		});
//		try {
//			Ob = Queries.get_Specific_RentalCompany();
//			cb.setItems(Ob);
//		
//		} catch (ClassNotFoundException | SQLException e) {
//			AlertBox.display("Error in dataBase while reading Rental");
//			e.printStackTrace();
//		}
		ScrollPane sp = new ScrollPane();
		
		sp.setPrefSize(600, 427);
		sp.setLayoutX(400);
		sp.setLayoutY(71);
		
	
		
		
		
		
		
		
		
		
		
		
		ArrayList<RentalCars> rent = null;
		try {
			rent = Queries.getRentalCars1();
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageView[] image = new ImageView[rent.size()];
		Button[] butt = new Button[rent.size()];
		VBox[] vbox = new VBox[rent.size()];
		
		VBox vbox1  = new VBox();
		vbox1.setSpacing(100);
		vbox1.setAlignment(Pos.CENTER);
			for(int i = 0 ; i < rent.size();i++)
			{
				image[i] = new ImageView("file:RentCars\\"+rent.get(i).car_name+".jpg");
				image[i].setFitHeight(100);
				image[i].setFitWidth(200);
				butt[i] = new Button(""+rent.get(i).car_name+"   "+rent.get(i).car_id+"");
				butt[i].setPrefSize(200,50);
				
				vbox[i] = new VBox();
				vbox[i].setAlignment(Pos.CENTER);
				vbox[i].getChildren().addAll(image[i],butt[i]);
				
				
				vbox1.getChildren().add(vbox[i]);
				
				
			}
			
			System.out.println();
		
		BorderPane bp = new BorderPane();
		bp.setCenter(vbox1);
		
		sp.setCenterShape(true);
		sp.setContent(bp);
		
		for(int i = 0 ; i < rent.size();i++)
		{
			ArrayList<RentalCars> tmp = rent;
			int index = i;
			butt[i].setOnAction(e ->{
				
				
				Scene sc = Scenes.UserShow_PropertiesOf_SpecificCarRental(primaryStage, tmp.get(index));
				sc.getStylesheets().add("Plugins.css");
				
				primaryStage.setScene(sc);
				});
		}
		
		
		
		
		
		 Pane p = new Pane();
		 p.getChildren().addAll(imageMain,sp,back);
		p.setStyle("-fx-background: grey");
		 Scene scene = new Scene(p,1000,600);
		
		return scene ;
		
	}
	public static Scene UserShow_SellCarss(Stage primaryStage) {
		ImageView imageMain = new ImageView("file:sellCars.jpg");
		imageMain.setFitHeight(600);
		imageMain.setFitWidth(1000);
//		ComboBox<SellCompany> cb  = new ComboBox<>();
//		ObservableList<SellCompany> Ob = FXCollections.observableArrayList();
		Button back = new Button("Back") ;
		back.setPrefSize(151, 51);
		back.setLayoutX(792);
		back.setLayoutY(497);
		back.setOnAction(e -> {
			primaryStage.setScene(Scenes.UserSelectCompany(primaryStage));
		});
//		cb.setLayoutX(30);
//		cb.setLayoutY(25);
//		try {
//			Ob = Queries.get_Specific_SellCompany();
//			cb.setItems(Ob);
//		
//		} catch (ClassNotFoundException | SQLException e) {
//			AlertBox.display("Error in dataBase while reading Rental");
//			e.printStackTrace();
//		}
//		
		FadeTransition ft = new FadeTransition(Duration.seconds(1.5),imageMain);
		ft.setFromValue(1);
		ft.setToValue(0.2);
		ft.play();
		
		
//		Button submint = new Button("Submint") ;
//		submint.setPrefSize(151, 51);
//		submint.setLayoutX(260);
//		submint.setLayoutY(14);
		
//		submint.setOnAction(e->{
//			
//			System.out.println(submint.getText());
//		});
//		
		
	ScrollPane sp = new ScrollPane();
		
		sp.setPrefSize(600, 427);
		sp.setLayoutX(400);
		sp.setLayoutY(71);
		
		
		ArrayList<SellCars> sell = null;
		try {
			sell = Queries.getSellCars1();
			
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		ImageView[] image = new ImageView[sell.size()];
		Button[] butt = new Button[sell.size()];
		VBox[] vbox = new VBox[sell.size()];
		
		VBox vbox1  = new VBox();
		vbox1.setSpacing(100);
		vbox1.setAlignment(Pos.CENTER);
		
		
		
			for(int i = 0 ; i < sell.size();i++)
			{
				image[i] = new ImageView("file:SellCars\\"+sell.get(i).CarName+".jpg");
				image[i].setFitHeight(100);
				image[i].setFitWidth(200);
				butt[i] = new Button(""+sell.get(i).CarName+"   "+sell.get(i).CarId+"");
				butt[i].setPrefSize(200,50);
				
				vbox[i] = new VBox();
				vbox[i].setAlignment(Pos.CENTER);
				vbox[i].getChildren().addAll(image[i],butt[i]);
				
				
				vbox1.getChildren().add(vbox[i]);
				
				
			}
			
			System.out.println();
		
		
		
		BorderPane bp = new BorderPane();
		bp.setCenter(vbox1);
		
		sp.setCenterShape(true);
		sp.setContent(bp);
		
		for(int i = 0 ; i < sell.size();i++)
		{
			ArrayList<SellCars> tmp = sell;
			int index = i;
			butt[i].setOnAction(e ->{
				System.out.println(tmp.get(index).toString());
				Scene sc = Scenes.UserShow_PropertiesOf_SpecificCarSell(primaryStage, tmp.get(index));
				sc.getStylesheets().add("Plugins.css");
				
				primaryStage.setScene(sc);
			});
		}
		
		
		 Pane p = new Pane();
		 p.getChildren().addAll(imageMain,sp,back);
		
		 Scene scene = new Scene(p,1000,600);
		p.setStyle("-fx-background: grey");

		return scene ;
		
	}
//	public static Scene UserShow_FiltersRentalCarss(Stage primaryStage) {
//		
//		return null;
//		
//	}
	public static Scene UserShow_PropertiesOf_SpecificCarRental(Stage primaryStage, RentalCars node) {

		BorderPane root = new BorderPane();
		Label l = new Label("Properties of the car that you choose");
		Button ResrvButton = new Button("Reserve");
		Button Back = new Button("Back");
		Back.setLayoutX(950);
		Back.setLayoutY(560);
		l.setLayoutX(40);
		l.setLayoutY(40);
		l.setFont(Font.font(30));
		Back.setOnAction(e -> {
			Scene sc = Scenes.UserShow_RentalCarss(primaryStage);
			sc.getStylesheets().add("Plugins.css");
			primaryStage.setScene(sc);
		});
		
		
		ImageView image = new ImageView("file:RentCars\\"+node.getCar_name()+".jpg");
		image.setFitWidth(510);
		image.setFitHeight(316);
		image.setLayoutX(470);
		image.setLayoutY(140);
		
		
		
		Label l1 = new Label("Car_id :");
		Label l2 = new Label(" " + node.getCar_id());
		l1.setFont(Font.font(20));
		l2.setFont(Font.font(20));
		l1.setTextFill(Color.RED);
		l2.setTextFill(Color.GOLD);
		l1.setLayoutX(20);
		l1.setLayoutY(150);
		l2.setLayoutX(100);
		l2.setLayoutY(150);
		Label l3 = new Label("CarName :");
		Label l4 = new Label(" "+node.getCar_name());
		l3.setFont(Font.font(20));
		l4.setFont(Font.font(20));
		l3.setTextFill(Color.RED);
		l4.setTextFill(Color.GOLD);
		l3.setLayoutX(20);
		l3.setLayoutY(180);
		l4.setLayoutX(120);
		l4.setLayoutY(180);
		Label l5 = new Label("Insurance :");
		Label l6 = new Label(" "+node.getInsurance());
		l5.setFont(Font.font(20));
		l6.setFont(Font.font(20));
		l5.setTextFill(Color.RED);
		l6.setTextFill(Color.GOLD);
		l5.setLayoutX(20);
		l5.setLayoutY(220);
		l6.setLayoutX(120);
		l6.setLayoutY(220);
		Label l7 = new Label("Delivery_availavility :");
		Label l8 = new Label(" " + node.Delivery_availability);
		l7.setFont(Font.font(20));
		l8.setFont(Font.font(20));
		l7.setTextFill(Color.RED);
		l8.setTextFill(Color.GOLD);
		l7.setLayoutX(20);
		l7.setLayoutY(250);
		l8.setLayoutX(200);
		l8.setLayoutY(250);
		Label l9 = new Label("Millage :");
		Label l10 = new Label(" " + node.getMillage());
		l9.setTextFill(Color.RED);
		l10.setTextFill(Color.GOLD);
		l9.setFont(Font.font(20));
		l10.setFont(Font.font(20));
		l9.setLayoutX(20);
		l9.setLayoutY(280);
		l10.setLayoutX(100);
		l10.setLayoutY(280);
		Label l11 = new Label("CarYear :");
		Label l12 = new Label(" " + node.getCar_year());
		l11.setFont(Font.font(20));
		l12.setFont(Font.font(20));
		l11.setTextFill(Color.RED);
		l12.setTextFill(Color.GOLD);
		l11.setLayoutX(20);
		l11.setLayoutY(310);
		l12.setLayoutX(100);
		l12.setLayoutY(310);
		Label l13 = new Label("Commision :");
		Label l14 = new Label(" " + node.getCommision());
		l13.setTextFill(Color.RED);
		l14.setTextFill(Color.GOLD);
		l13.setFont(Font.font(20));
		l14.setFont(Font.font(20));
		l13.setLayoutX(20);
		l13.setLayoutY(340);
		l14.setLayoutX(140);
		l14.setLayoutY(340);
		Label l15 = new Label("Price  :");
		Label l16 = new Label(" " + node.getPrice_day());
		l15.setTextFill(Color.RED);
		l16.setTextFill(Color.GOLD);
		l15.setFont(Font.font(20));
		l16.setFont(Font.font(20));

		l15.setLayoutX(20);
		l15.setLayoutY(370);
		l16.setLayoutX(90);
		l16.setLayoutY(370);
		Label l17 = new Label("CarType : ");
		Label l18 = new Label(" "+node.getCar_type());
		l17.setTextFill(Color.RED);
		l18.setTextFill(Color.GOLD);
		l17.setFont(Font.font(20));
		l18.setFont(Font.font(20));
		l17.setLayoutX(20);
		l17.setLayoutY(400);
		l18.setLayoutX(100);
		l18.setLayoutY(400);
		ResrvButton.setLayoutX(20);
		ResrvButton.setLayoutY(450);
		Label lf = new Label("your reserved sucssfully");
		lf.setVisible(false);
		lf.setLayoutX(100);
		lf.setFont(Font.font(20));
		lf.setLayoutY(445);
		ResrvButton.setOnAction(e -> {
			try {
				Reservation tmp = Queries.CheckReservRent(node);
				
				if(tmp!=null)
				{
					Alert(Alert.AlertType.ERROR, root.getScene().getWindow(), "ERROR!",
							"The Car is Reserved!");
				}
				else
				{
					Queries.insertDataRent(node);
					Alert(Alert.AlertType.CONFIRMATION, root.getScene().getWindow(), "Successfully!",
							"Operation is successfully completed!");
				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Pane p = new Pane();
		root.setStyle("-fx-background: grey");

		p.setStyle("-fx-background: grey");
		p.getChildren().addAll(ResrvButton, lf, l, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15,
				l16, l17, l18, image,Back);
		root.getChildren().add(p);
		Scene scene = new Scene(root, 1000, 600);

		return scene;

	}

	public static Scene UserShow_PropertiesOf_SpecificCarSell(Stage primaryStage, SellCars node)
			 {

		BorderPane root = new BorderPane();
		Label l = new Label("Properties of the car that you choose");
		Button ResrvButton = new Button("Reserve");
		Button Back = new Button("Back");
		Back.setLayoutX(950);
		Back.setLayoutY(560);
		l.setLayoutX(40);
		l.setLayoutY(40);
		l.setFont(Font.font(30));
		Back.setOnAction(e -> {
			Scene sc = Scenes.UserShow_SellCarss(primaryStage);
			sc.getStylesheets().add("Plugins.css");
			primaryStage.setScene(sc);
		});
		ImageView image = new ImageView("file:SellCars\\"+node.getCarName()+".jpg");
		image.setFitWidth(510);
		image.setFitHeight(316);
		image.setLayoutX(470);
		image.setLayoutY(140);

		
		
		
		Label l1 = new Label("Car_id :");
		Label l2 = new Label(" " + node.getCarId());
		l1.setFont(Font.font(20));
		l2.setFont(Font.font(20));
		l1.setTextFill(Color.RED);
		l2.setTextFill(Color.GOLD);
		l1.setLayoutX(20);
		l1.setLayoutY(150);
		l2.setLayoutX(100);
		l2.setLayoutY(150);
		Label l3 = new Label("CarName :");
		Label l4 = new Label(node.getCarName());
		l3.setFont(Font.font(20));
		l4.setFont(Font.font(20));
		l3.setTextFill(Color.RED);
		l4.setTextFill(Color.GOLD);
		l3.setLayoutX(20);
		l3.setLayoutY(180);
		l4.setLayoutX(120);
		l4.setLayoutY(180);
		Label l5 = new Label("Insurance :");
		Label l6 = new Label(" "+node.getInsurance());
		l5.setFont(Font.font(20));
		l6.setFont(Font.font(20));
		l5.setTextFill(Color.RED);
		l6.setTextFill(Color.GOLD);
		l5.setLayoutX(20);
		l5.setLayoutY(220);
		l6.setLayoutX(120);
		l6.setLayoutY(220);
		Label l7 = new Label("Delivery_availavility : ");
		Label l8 = new Label(" " + node.Delivery_availavility);
		l7.setFont(Font.font(20));
		l8.setFont(Font.font(20));
		l7.setTextFill(Color.RED);
		l8.setTextFill(Color.GOLD);
		l7.setLayoutX(20);
		l7.setLayoutY(250);
		l8.setLayoutX(200);
		l8.setLayoutY(250);
		Label l9 = new Label("Millage : ");
		Label l10 = new Label(" " + node.getMillage());
		l9.setTextFill(Color.RED);
		l10.setTextFill(Color.GOLD);
		l9.setFont(Font.font(20));
		l10.setFont(Font.font(20));
		l9.setLayoutX(20);
		l9.setLayoutY(280);
		l10.setLayoutX(100);
		l10.setLayoutY(280);
		Label l11 = new Label("CarYear :");
		Label l12 = new Label(" " + node.getCarYear());
		l11.setFont(Font.font(20));
		l12.setFont(Font.font(20));
		l11.setTextFill(Color.RED);
		l12.setTextFill(Color.GOLD);
		l11.setLayoutX(20);
		l11.setLayoutY(310);
		l12.setLayoutX(100);
		l12.setLayoutY(310);
		Label l13 = new Label("Commision : ");
		Label l14 = new Label(" " + node.getCommision());
		l13.setTextFill(Color.RED);
		l14.setTextFill(Color.GOLD);
		l13.setFont(Font.font(20));
		l14.setFont(Font.font(20));
		l13.setLayoutX(20);
		l13.setLayoutY(340);
		l14.setLayoutX(140);
		l14.setLayoutY(340);
		Label l15 = new Label("Price  :");
		Label l16 = new Label(" " + node.getPrice());
		l15.setTextFill(Color.RED);
		l16.setTextFill(Color.GOLD);
		l15.setFont(Font.font(20));
		l16.setFont(Font.font(20));

		l15.setLayoutX(20);
		l15.setLayoutY(370);
		l16.setLayoutX(90);
		l16.setLayoutY(370);
		Label l17 = new Label("CarType :");
		Label l18 = new Label(" "+node.getCarType());
		l17.setTextFill(Color.RED);
		l18.setTextFill(Color.GOLD);
		l17.setFont(Font.font(20));
		l18.setFont(Font.font(20));
		l17.setLayoutX(20);
		l17.setLayoutY(400);
		l18.setLayoutX(100);
		l18.setLayoutY(400);
		ResrvButton.setLayoutX(20);
		ResrvButton.setLayoutY(450);
		Label lf = new Label("your reserved sucssfully");
		lf.setVisible(false);
		lf.setLayoutX(100);
		lf.setFont(Font.font(20));
		lf.setLayoutY(445);
		ResrvButton.setOnAction(e -> {
			try {
				Reservation tmp = Queries.CheckReservSell(node);
				
				if(tmp!=null)
				{
					Alert(Alert.AlertType.ERROR, root.getScene().getWindow(), "ERROR!",
							"The Car is Reserved!");
				}
				else
				{
					Queries.insertDataSell(node);
					Alert(Alert.AlertType.CONFIRMATION, root.getScene().getWindow(), "Successfully!",
							"Operation is successfully completed!");
				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		root.setStyle("-fx-background: grey");
		Pane p = new Pane();
		p.setStyle("-fx-background: grey");
		p.getChildren().addAll(ResrvButton, lf, l, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15,
				l16, l17, l18, image,Back);
		root.getChildren().add(p);
		Scene scene = new Scene(root, 1000, 600);

		return scene;

	}

public static Scene getUsers(Stage primaryStage) throws Exception {
		
		AnchorPane pane = new AnchorPane();

		Button back = new Button("Back");


		back.setOnAction(ev ->{
			Scene sc3;
			try {
				sc3 = Scenes.getAdvisor(primaryStage);
				sc3.getStylesheets().add("Plugins.css");
				primaryStage.setScene(sc3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		TableView<Users> table = new TableView<Users>();
		table.setPrefWidth(800);
		table.setPrefHeight(500);
		table.setLayoutX(105);
		table.setLayoutY(100);
		table.setEditable(true);

		// Adding columns to the table

		TableColumn<Users, String> Name = new TableColumn<Users, String>("Name");
		TableColumn<Users, String> Phone_Number = new TableColumn<Users, String>("Phone_Number");
		TableColumn<Users, String> Email = new TableColumn<Users, String>("Email");
		TableColumn<Users, String> pass = new TableColumn<Users, String>("pass");
		


		Name.setCellValueFactory(new PropertyValueFactory<>("user_name"));
		pass.setCellValueFactory(new PropertyValueFactory<>("pass"));
		Email.setCellValueFactory(new PropertyValueFactory<>("email"));
		Phone_Number.setCellValueFactory(new PropertyValueFactory<>("phone"));

	

		// adding columns to the table
		table.getColumns().addAll( Name, Email, Phone_Number,pass);
		ObservableList<Users> list = FXCollections.observableArrayList();
		list = Queries.getUsers();
		table.setItems(list);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		Label l = new Label("Users Page");
		l.setPrefWidth(260);
		l.setPrefHeight(32);
		l.setLayoutX(400);
		l.setLayoutY(30);
		l.setFont(new Font("Baskerville Old Face", 30));
		l.setStyle("-fx-text-fill : #c39c48;");
		
		
		pane.getChildren().addAll(back,table, l);
		pane.setStyle("-fx-background-color :  #0c1f46");

		Scene s = new Scene(pane, 1000, 600);
		s.getStylesheets().add("Plugins.css");

		return s;
		
		
		
	}




public static Scene empLogin(Stage primarystage){
	Button bmanager = new Button("Manager");
	Button badvisor = new Button("Advisor");
	Button baccountant = new Button("Accountant");
	
	Button back = new Button("Back");
	HBox Hbox = new HBox();

	ImageView imageView = new ImageView("file:Pictures\\aa.jpg");
	Button backTomain = new Button("Back");
	backTomain.setPrefSize(150,50);
	backTomain.setOnAction(event ->{
		Scene sc = Scenes.First(primarystage);
		primarystage.setScene(sc);
		sc.getStylesheets().add("Plugins.css");
		
	});

	imageView.setX(0);
	imageView.setY(0);
	imageView.setFitWidth(1100);
	imageView.setFitHeight(800);
	imageView.setPreserveRatio(true);

	ImageView imageView1 = new ImageView("file:Pictures\\manager.jpg");


	imageView1.setX(0);
	imageView1.setY(0);
	imageView1.setFitWidth(1000);
	imageView1.setFitHeight(800);
	imageView1.setPreserveRatio(true);

	ImageView imageView2 = new ImageView("file:Pictures\\advisor1.jpg");


	imageView2.setX(0);
	imageView2.setY(0);
	imageView2.setFitWidth(1200);
	imageView2.setFitHeight(800);
	imageView2.setPreserveRatio(true);
	imageView1.setPreserveRatio(true);

	ImageView imageView3 = new ImageView("file:Pictures\\acc1.jpg");


	imageView3.setX(0);
	imageView3.setY(0);
	imageView3.setFitWidth(1100);
	imageView3.setFitHeight(800);
	imageView3.setPreserveRatio(true);
	Hbox.setPrefWidth(200);
	baccountant.setMinHeight(Hbox.getPrefWidth());
	badvisor.setMinHeight(Hbox.getPrefWidth());
	bmanager.setMinHeight(Hbox.getPrefWidth());
	bmanager.setMinWidth(Hbox.getPrefWidth());
	badvisor.setMinWidth(Hbox.getPrefWidth());
	back.setStyle("-fx-background-color: #F08080");
	baccountant.setMinWidth(Hbox.getPrefWidth());
	Hbox.getChildren().addAll(bmanager, badvisor, baccountant);
	Hbox.setLayoutX(200);
	Hbox.setLayoutY(330);

	bmanager.setOnAction(e -> {
		try {
			BorderPane root1 = new BorderPane();
			TextField log = new TextField();
			Button blogin= new Button("Log in");
			
			
			
		
			PasswordField pass = new PasswordField();
			VBox vbox = new VBox();
			Text user = new Text();
			user.setText("ID ");
			user.setFont(Font.font("Arial", FontWeight.BOLD, 18));

			Text passT = new Text();
			passT.setText("Password ");
			passT.setFont(Font.font("Arial", FontWeight.BOLD, 18));

			blogin.setOnAction(v -> { 	
				if(!log.getText().isEmpty() && !pass.getText().isEmpty()) {
					try {
						Manager m = Queries.managerlogin(log.getText(), pass.getText());
						if(m!=null) {
							try {
								Scene sc1 = Scenes.getManager(primarystage);
								sc1.getStylesheets().add("Plugins.css");	
								primarystage.setScene(sc1);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						else Alert(Alert.AlertType.ERROR, root1.getScene().getWindow(), "ERROR!",
								"Wrong ID Or Password!");
					} catch (ClassNotFoundException | SQLException e1) {
						
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else 
					Alert(Alert.AlertType.ERROR, root1.getScene().getWindow(), "ERROR!",
						"Please Fill ID or Password!");
				
			});
			vbox.setPrefWidth(120);
			vbox.setLayoutX(100);
			vbox.setLayoutY(100);
			log.setMinWidth(60);
			log.setMinHeight(10);
			pass.setMinWidth(60);
			pass.setMinHeight(10);
		

			vbox.getChildren().addAll(user, log, passT, pass,blogin);
			Group root = new Group(imageView1,vbox,back);
			root1.getChildren().add(root);
			
			Scene scene = new Scene(root1, 1000, 600);

			
			scene.getStylesheets().add("Plugins.css");
			

			
			primarystage.setScene(scene);
			primarystage.show();
		} catch (Exception l) {
			l.printStackTrace();
		}

	});
	badvisor.setOnAction(e -> {
		try {
			BorderPane root1 = new BorderPane();

			TextField log = new TextField();
			Button blogin= new Button("Log in");
			
			
			
		
			PasswordField pass = new PasswordField();
			VBox vbox = new VBox();
			Text user = new Text();
			user.setText("ID ");
			user.setFont(Font.font("Arial", FontWeight.BOLD, 18));

			Text passT = new Text();
			passT.setText("Password ");
			passT.setFont(Font.font("Arial", FontWeight.BOLD, 18));

			blogin.setOnAction(v -> { 	
				if(!log.getText().isEmpty() && !pass.getText().isEmpty()) {
					try {
						Advisor m = Queries.Advisorlogin(log.getText(), pass.getText());
						if(m!=null) {
							try {
								Scene sc1 = Scenes.getAdvisor(primarystage);
								sc1.getStylesheets().add("Plugins.css");	
								primarystage.setScene(sc1);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}else Alert(Alert.AlertType.ERROR, root1.getScene().getWindow(), "ERROR!",
								"Wrong ID Or Password!");
					} catch (ClassNotFoundException | SQLException e1) {
						
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else Alert(Alert.AlertType.ERROR, root1.getScene().getWindow(), "ERROR!",
						"Please Fill ID or Password!");
				
			});
			vbox.setPrefWidth(120);
			vbox.setLayoutX(100);
			vbox.setLayoutY(100);
			log.setMinWidth(60);
			log.setMinHeight(10);
			pass.setMinWidth(60);
			pass.setMinHeight(10);
		

			vbox.getChildren().addAll(user, log, passT, pass,blogin);
			Group root = new Group(imageView2,vbox,back);
			root1.getChildren().add(root);
			Scene scene = new Scene(root1,  1000, 600);

			scene.getStylesheets().add("Plugins.css");
			

			
			primarystage.setScene(scene);
			primarystage.show();
		} catch (Exception l) {
			l.printStackTrace();
		}

	});

	baccountant.setOnAction(e -> {
		try {
			BorderPane root1 = new BorderPane();
			TextField log = new TextField();
			Button blogin= new Button("Log in");
			
			
			
		
			PasswordField pass = new PasswordField();
			VBox vbox = new VBox();
			Text user = new Text();
			user.setText("ID ");
			user.setFont(Font.font("Arial", FontWeight.BOLD, 18));
			Text passT = new Text();
			passT.setText("Password ");
			passT.setFont(Font.font("Arial", FontWeight.BOLD, 18));

			blogin.setOnAction(v -> { 	
				if(!log.getText().isEmpty()&& !pass.getText().isEmpty()) {
					try {
						Accountant m = Queries.Accountantlogin(log.getText(), pass.getText());
						if(m!=null) {
							
							try {
								Scene sc1 = Scenes.getAccountant(primarystage);
								sc1.getStylesheets().add("Plugins.css");	
								primarystage.setScene(sc1);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}else Alert(Alert.AlertType.ERROR, root1.getScene().getWindow(), "ERROR!",
								"Wrong ID Or Password!");
					} catch (ClassNotFoundException | SQLException e1) {
						
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else Alert(Alert.AlertType.ERROR, root1.getScene().getWindow(), "ERROR!",
						"Please Fill ID or Password!");
				
			});
			vbox.setPrefWidth(120);
			vbox.setLayoutX(100);
			vbox.setLayoutY(100);
			log.setMinWidth(60);
			log.setMinHeight(10);
			pass.setMinWidth(60);
			pass.setMinHeight(10);
		

			vbox.getChildren().addAll(user, log, passT, pass,blogin);
			Group root = new Group(imageView3,vbox,back);
			root1.getChildren().add(root);
			Scene scene = new Scene(root1, 1000, 600);

			scene.getStylesheets().add("Plugins.css");
			

			
			primarystage.setScene(scene);
			primarystage.show();
			
		} catch (Exception l) {
			l.printStackTrace();
		}

	});

	Group root = new Group(imageView, Hbox,backTomain);
	Scene scene = new Scene(root, 1000, 600);
	back.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			 primarystage.setScene(scene);

		}
	});
	return scene;
	
		

}
public static Scene First(Stage primaryStage){
	AnchorPane root = new AnchorPane();
	ImageView img = new ImageView("file:main.jpg");
	img.setFitHeight(640);
	img.setFitWidth(1020);
	root.getChildren().add(img);
	
	Label welcome = new Label("Welcome To Rent And Sell Application");
	welcome.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	welcome.setLayoutX(280);
	welcome.setLayoutY(100);
	
	
	
	
	Label secondLabel = new Label("If you Want Buy Press Continue");
	secondLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	secondLabel.setLayoutX(320);
	secondLabel.setLayoutY(160);
	
	Button enter = new Button("Continue");
	enter.setFont(Font.font("Arial", FontWeight.BOLD, 36));

	enter.setPrefSize(371, 74);
	enter.setLayoutX(315);
	enter.setLayoutY(234);
	
	
	

	Button emp = new Button("Only For Employess");
	emp.setPrefSize(155, 58);
	emp.setLayoutX(845);
	emp.setLayoutY(0);


	

	root.getChildren().addAll(welcome,secondLabel,enter,emp);



	enter.setOnAction(event ->{
		
		
		Scene sc = Scenes.Login(primaryStage);
		primaryStage.setScene(sc);
		sc.getStylesheets().add("Plugins.css");
	});
	
	
	
	
	
	emp.setOnAction(event ->{
		Scene sc = Scenes.empLogin(primaryStage);
		primaryStage.setScene(sc);
		sc.getStylesheets().add("Plugins.css");
	});
	
	
	
	
	
	

	Scene scene = new Scene(root,1000,600);

	scene.getStylesheets().add("Plugins.css");


	FadeTransition ft = new FadeTransition(Duration.seconds(3),img);
	ft.setFromValue(0.5);
	ft.setToValue(1);
	ft.play();


	return scene;

}



public static Scene getSellCompanyCarID(Stage primaryStage) throws Exception {
	
	AnchorPane pane = new AnchorPane();
	Button back = new Button("Back");


	Label rentCom = new Label("Commision Rent : "+Queries.getCommisionRent()+" $");
	rentCom.setFont(new Font("Baskerville Old Face", 30));
	rentCom.setStyle("-fx-text-fill : #c39c48;");
	rentCom.setPrefWidth(300);
	rentCom.setPrefHeight(32);
	rentCom.setLayoutX(130);
	rentCom.setLayoutY(60);
	
	
	
	
	
	Label sellCom = new Label("Commision Sell : "+Queries.getCommisionSell()+" $");
	sellCom.setFont(new Font("Baskerville Old Face", 30));
	sellCom.setStyle("-fx-text-fill : #c39c48;");
	sellCom.setPrefWidth(300);
	sellCom.setPrefHeight(32);
	sellCom.setLayoutX(610);
	sellCom.setLayoutY(60);
	
	back.setOnAction(ev ->{
		Scene sc3;
		try {
			sc3 = Scenes.getAccountant(primaryStage);
			sc3.getStylesheets().add("Plugins.css");
			primaryStage.setScene(sc3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});

	VBox v = new VBox(40);
	v.setLayoutX(10);
	v.setLayoutY(216);
	//v.getChildren().addAll(b1);
	TableView<SellCompanyCarID> table = new TableView<SellCompanyCarID>();
	table.setPrefWidth(400);
	table.setPrefHeight(500);
	table.setLayoutX(50);
	table.setLayoutY(100);
	table.setEditable(true);
	
	TableView<RentalCompanyCarID> table1 = new TableView<RentalCompanyCarID>();
	table1.setPrefWidth(400);
	table1.setPrefHeight(500);
	table1.setLayoutX(550);
	table1.setLayoutY(100);
	table1.setEditable(true);

	// Adding columns to the table

	TableColumn<SellCompanyCarID, String> co_address = new TableColumn<SellCompanyCarID, String>("Company Address");
	TableColumn<SellCompanyCarID, Integer> car_idS = new TableColumn<SellCompanyCarID, Integer>("ID S");
	TableColumn<SellCompanyCarID, String> carName = new TableColumn<SellCompanyCarID, String>("Car Name");
	
	TableColumn<RentalCompanyCarID, String> co_address1 = new TableColumn<RentalCompanyCarID, String>("Company Address");
	TableColumn<RentalCompanyCarID, Integer> car_idR = new TableColumn<RentalCompanyCarID, Integer>("ID R");
	TableColumn<RentalCompanyCarID, String> carName1 = new TableColumn<RentalCompanyCarID, String>("Car Name");

	
	co_address1.setCellValueFactory(new PropertyValueFactory<>("co_address"));
	car_idR.setCellValueFactory(new PropertyValueFactory<>("car_idR"));
	carName.setCellValueFactory(new PropertyValueFactory<>("carName"));
	
	co_address.setCellValueFactory(new PropertyValueFactory<>("co_address"));
	car_idS.setCellValueFactory(new PropertyValueFactory<>("id"));
	carName1.setCellValueFactory(new PropertyValueFactory<>("carName"));



	// adding columns to the table
	
	table1.getColumns().addAll(co_address1,car_idR,carName1);
	table.getColumns().addAll(co_address,car_idS,carName);
	
	ObservableList<SellCompanyCarID> list = FXCollections.observableArrayList();
	list = Queries.get_ReservationSell();
	System.out.println(list.toString());
	table.setItems(list);
	table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	
	ObservableList<RentalCompanyCarID> list1 = FXCollections.observableArrayList();
	list1 = Queries.get_ReservationRental();
	table1.setItems(list1);
	table1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


Label l = new Label("Sell Page") ;
l.setPrefWidth(260);
l.setPrefHeight(32);
l.setLayoutX(200);
l.setLayoutY(30);
l.setFont(new Font("Baskerville Old Face", 30));

Label l1 = new Label("Rental Page") ;
l1.setPrefWidth(260);
l1.setPrefHeight(32);
l1.setLayoutX(660);
l1.setLayoutY(30);
l1.setFont(new Font("Baskerville Old Face", 30));

l.setStyle("-fx-text-fill : #c39c48;");
l1.setStyle("-fx-text-fill : #c39c48;");
pane.getChildren().addAll(back,table,v,l,l1,sellCom,rentCom,table1);
pane.setStyle("-fx-background-color :  #0c1f46");

Scene s = new Scene(pane,1000,600);	
s.getStylesheets().add("Plugins.css");

return s;
	
}



public static Scene getAdvisor(Stage primaryStage) throws Exception {
	
	AnchorPane pane = new AnchorPane();
	Button b1 = new Button("Show Users");
	b1.setPrefWidth(92);
	b1.setPrefHeight(32);
Button logOut = new Button("Log Out");
	
	
	logOut.setOnAction(ev ->{
		Scene sc3 = Scenes.empLogin(primaryStage);
		sc3.getStylesheets().add("Plugins.css");
		primaryStage.setScene(sc3);
		
		
		
	});

	VBox v = new VBox(40);
	v.setLayoutX(10);
	v.setLayoutY(216);
	v.getChildren().addAll(b1);
	TableView<Advisor> table = new TableView<Advisor>();
	table.setPrefWidth(800);
	table.setPrefHeight(500);
	table.setLayoutX(130);
	table.setLayoutY(100);
	table.setEditable(true);

	// Adding columns to the table

	TableColumn<Advisor, String> Name = new TableColumn<Advisor, String>("Name");
	TableColumn<Advisor, Integer> ID = new TableColumn<Advisor, Integer>("ID");
	TableColumn<Advisor, String> Email = new TableColumn<Advisor, String>("Email");
	TableColumn<Advisor, String> Phone_Number = new TableColumn<Advisor, String>("Phone_Number");
	TableColumn<Advisor, String> university_degree = new TableColumn<Advisor, String>("university_degree");
	TableColumn<Advisor, Double> Salary = new TableColumn<Advisor, Double>("Salary");

	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
	ID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
	Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
	Phone_Number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
	university_degree.setCellValueFactory(new PropertyValueFactory<>("university_degree"));
	Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));

	// adding columns to the table
	table.getColumns().addAll(ID, Name, Email, Phone_Number, university_degree, Salary);
	ObservableList<Advisor> list = FXCollections.observableArrayList();
	list = Queries.getAdvisor();
	table.setItems(list);
	table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	b1.setOnAction(e -> {
		
		try {
			Scene sc= Scenes.getUsers(primaryStage);
			primaryStage.setScene(sc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	});



Label l = new Label("Advisor Page") ;
l.setPrefWidth(260);
l.setPrefHeight(32);
l.setLayoutX(400);
l.setLayoutY(30);
l.setFont(new Font("Baskerville Old Face", 30));

l.setStyle("-fx-text-fill : #c39c48;");
pane.getChildren().addAll(logOut,table,v,l);
pane.setStyle("-fx-background-color :  #0c1f46");

Scene s = new Scene(pane,1000,600);	
s.getStylesheets().add("Plugins.css");

return s;
	
}

public static Scene getAccountant(Stage primaryStage) throws Exception {
	
	AnchorPane pane = new AnchorPane();
	Button b1 = new Button("Reservation");
	b1.setPrefWidth(92);
	b1.setPrefHeight(32);
Button logOut = new Button("Log Out");
	
	
	logOut.setOnAction(ev ->{
		Scene sc3 = Scenes.empLogin(primaryStage);
		sc3.getStylesheets().add("Plugins.css");
		primaryStage.setScene(sc3);
		
		
		
	});



	VBox v = new VBox(40);
	v.setLayoutX(10);
	v.setLayoutY(216);
	v.getChildren().addAll(b1);
	TableView<Accountant> table = new TableView<Accountant>();
	table.setPrefWidth(800);
	table.setPrefHeight(500);
	table.setLayoutX(130);
	table.setLayoutY(100);
	table.setEditable(true);

	// Adding columns to the table

	TableColumn<Accountant, String> Name = new TableColumn<Accountant, String>("Name");
	TableColumn<Accountant, Integer> ID = new TableColumn<Accountant, Integer>("ID");
	TableColumn<Accountant, String> Email = new TableColumn<Accountant, String>("Email");
	TableColumn<Accountant, String> Phone_Number = new TableColumn<Accountant, String>("Phone_Number");
	TableColumn<Accountant, String> university_degree = new TableColumn<Accountant, String>("university_degree");
	TableColumn<Accountant, Double> Salary = new TableColumn<Accountant, Double>("Salary");

	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
	ID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
	Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
	Phone_Number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));

	university_degree.setCellValueFactory(new PropertyValueFactory<>("university_degree"));
	Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));

	// adding columns to the table
	table.getColumns().addAll(ID, Name, Email, Phone_Number, university_degree, Salary);
	ObservableList<Accountant> list = FXCollections.observableArrayList();
	list = Queries.getAccountant();
	table.setItems(list);
	table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



	
	b1.setOnAction(e -> {
		
		try {
			Scene sc= Scenes.getSellCompanyCarID(primaryStage);
			primaryStage.setScene(sc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	});

Label l = new Label("Accountant Page") ;
l.setPrefWidth(260);
l.setPrefHeight(32);
l.setLayoutX(400);
l.setLayoutY(30);
l.setFont(new Font("Baskerville Old Face", 30));

l.setStyle("-fx-text-fill : #c39c48;");
pane.getChildren().addAll(logOut,table,v,l);
pane.setStyle("-fx-background-color :  #0c1f46");

Scene s = new Scene(pane,1000,600);	
s.getStylesheets().add("Plugins.css");

return s;

	
}
public static Scene MangerShowCompany(Stage primaryStage) throws Exception {
	
	TabPane tapm=new TabPane();
	
	Tab Accountant = new Tab("Accountant");
	Tab Advisor = new Tab("Advisor");
	
	Tab CompanyRent = new Tab("Company Rent");
	
	Tab CompanySell = new Tab("Company Sell");

	
	Accountant.setClosable(false);
	Advisor.setClosable(false);
	CompanySell.setClosable(false);
	CompanyRent.setClosable(false);
	
	tapm.getTabs().addAll(Accountant, Advisor,CompanySell,CompanyRent);
	
	Accountant.setContent(Scenes.getAccountantTable(primaryStage));
	
	Advisor.setContent(Scenes.getAdvisorTable(primaryStage));
	
	
	
	
	CompanyRent.setContent(Scenes.RnetalCompaniesScene(primaryStage));
	CompanySell.setContent(Scenes.SellCompaniesScene(primaryStage));
	Scene scene = new Scene(tapm, 1000, 600);
	primaryStage.setScene(scene);
	
	
	
	return scene;

}

public static Pane getAdvisorTable(Stage primaryStage) throws Exception {
	
	AnchorPane pane = new AnchorPane();


	Button b2 = new Button("Insert");
	b2.setPrefWidth(92);
	b2.setPrefHeight(32);



	VBox v = new VBox(40);
	v.setLayoutX(10);
	v.setLayoutY(216);
	v.getChildren().addAll(b2);
	TableView<Advisor> table = new TableView<Advisor>();
	table.setPrefWidth(544);
	table.setPrefHeight(341);
	table.setLayoutX(110);
	table.setLayoutY(150);
	table.setEditable(true);

	// Adding columns to the table

	TableColumn<Advisor, String> Name = new TableColumn<Advisor, String>("Name");
	TableColumn<Advisor, Integer> ID = new TableColumn<Advisor, Integer>("ID");
	TableColumn<Advisor, String> Email = new TableColumn<Advisor, String>("Email");
	TableColumn<Advisor, String> Phone_Number = new TableColumn<Advisor, String>("Phone_Number");
	TableColumn<Advisor, String> university_degree = new TableColumn<Advisor, String>("university_degree");
	TableColumn<Advisor, Double> Salary = new TableColumn<Advisor, Double>("Salary");

	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
	ID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
	Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
	Phone_Number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));

	university_degree.setCellValueFactory(new PropertyValueFactory<>("university_degree"));
	Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));

	
	
	
	
	
	
Name.setCellFactory(TextFieldTableCell.<Advisor>forTableColumn());
	
	
	
	Name.setOnEditCommit((CellEditEvent<Advisor, String> e) -> {
		((Advisor) e.getTableView().getItems().get(e.getTablePosition().getRow())).setName(e.getNewValue()); // display only
		Queries.updateNameAdvisor(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Email.setCellFactory(TextFieldTableCell.<Advisor>forTableColumn());

	Email.setOnEditCommit((CellEditEvent<Advisor, String> e) -> {
		((Advisor) e.getTableView().getItems().get(e.getTablePosition().getRow())).setEmail(e.getNewValue()); // display only
		Queries.updateEmailAdvisor(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Phone_Number.setCellFactory(TextFieldTableCell.<Advisor>forTableColumn());

	Phone_Number.setOnEditCommit((CellEditEvent<Advisor, String> e) -> {
		((Advisor) e.getTableView().getItems().get(e.getTablePosition().getRow())).setPhone_number(e.getNewValue()); // display only
		Queries.updatePhoneAdvisor(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	university_degree.setCellFactory(TextFieldTableCell.<Advisor>forTableColumn());

	university_degree.setOnEditCommit((CellEditEvent<Advisor, String> e) -> {
		((Advisor) e.getTableView().getItems().get(e.getTablePosition().getRow())).setUniversity_degree(e.getNewValue()); // display only
		Queries.updateUDAdvisor(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	Salary.setOnEditCommit((CellEditEvent<Advisor, Double> e) -> {
		((Advisor) e.getTableView().getItems().get(e.getTablePosition().getRow())).setSalary(e.getNewValue()); // display only
		Queries.updateSalaryAdvisor(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	
	
	
	
	
	
	
	
	// adding columns to the table
	table.getColumns().addAll(ID, Name, Email, Phone_Number, university_degree, Salary);
	ObservableList<Advisor> list = FXCollections.observableArrayList();
	list = Queries.getAdvisor();
	table.setItems(list);
	table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
b2.setOnAction(event ->{
	
	Label idl = new Label("ID :");
	idl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	
	
	Label namel = new Label("Name :");
	namel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

	Label passl = new Label("Password :");
	passl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	
	
	
	Label salaryl = new Label("Salary :");
	salaryl.setFont(Font.font("Arial", FontWeight.BOLD, 24));

	Label emaill = new Label("Email :");
	emaill.setFont(Font.font("Arial", FontWeight.BOLD, 24));

	Label phonel = new Label("Phone :");
	phonel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

	Label UDl = new Label("University Degree :");
	UDl.setFont(Font.font("Arial", FontWeight.BOLD, 24));

	
	
	
	TextField idt =new TextField();
	TextField namet =new TextField();
	TextField passt =new TextField();
	TextField salaryt =new TextField();
	TextField emailt =new TextField();
	TextField phonet =new TextField();
	TextField UDt =new TextField();
	
	Stage st =new Stage();
	
	
	GridPane grid = new GridPane();
	grid.setVgap(10);
	grid.setHgap(10);
	grid.add(idl, 0, 0);
	grid.add(namel, 0, 1);
	grid.add(salaryl, 0, 2);
	grid.add(emaill, 0, 3);
	grid.add(phonel, 0, 4);
	grid.add(UDl, 0, 5);
	grid.add(idt, 1, 0);
	grid.add(namet, 1, 1);
	grid.add(salaryt, 1, 2);
	grid.add(emailt, 1, 3);
	grid.add(phonet, 1, 4);
	grid.add(UDt, 1, 5);
	grid.add(passl, 0, 6);
	grid.add(passt, 1, 6);

	
	

	Button save = new Button("Save");
	grid.add(save, 0, 7);
	
	save.setOnAction(ev ->{
		
		try {
			Queries.insertDataAdviser(new Advisor(Integer.parseInt(idt.getText().trim()),passt.getText(),namet.getText(), Double.parseDouble(salaryt.getText()), emailt.getText(), phonet.getText(), UDt.getText()));
		} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ObservableList<Advisor> list1 = FXCollections.observableArrayList();
		try {
			list1 = Queries.getAdvisor();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table.setItems(list1);
		
		
		
			
		});
	
	
	
	grid.setStyle("-fx-background:grey");
	
	
	Scene sc2 = new Scene(grid,500,500);
	sc2.getStylesheets().add("Plugins.css");

	st.setScene(sc2);
	st.show();
	
	
	
	
	
	
	
	
	
	
	
	
});



Label l = new Label("Advisor Page") ;
l.setPrefWidth(260);
l.setPrefHeight(32);
l.setLayoutX(255);
l.setLayoutY(30);
l.setFont(new Font("Baskerville Old Face", 30));
Button back = new Button("Back");


back.setOnAction(ev ->{
	Scene sc3;
	try {
		sc3 = Scenes.getManager(primaryStage);
		sc3.getStylesheets().add("Plugins.css");
		primaryStage.setScene(sc3);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
});
l.setStyle("-fx-text-fill : #c39c48;");
pane.getChildren().addAll(back,table,v,l);
pane.setStyle("-fx-background-color :  #0c1f46");

//Scene s = new Scene(pane,1000,600);	
//s.getStylesheets().add("Plugins.css");

return pane;
	
}
public static AnchorPane getAccountantTable(Stage primaryStage) throws Exception {
	
	AnchorPane pane = new AnchorPane();
	

	
	
	
	
Button back = new Button("Back");
	
	
	back.setOnAction(ev ->{
		Scene sc3;
		try {
			sc3 = Scenes.getManager(primaryStage);
			sc3.getStylesheets().add("Plugins.css");
			primaryStage.setScene(sc3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	});
	
	
	Button b2 = new Button("Insert");
	b2.setPrefWidth(92);
	b2.setPrefHeight(32);

	
	VBox v = new VBox(40);
	v.setLayoutX(10);
	v.setLayoutY(216);
	v.getChildren().addAll(back, b2);
	TableView<Accountant> table = new TableView<Accountant>();
	table.setPrefWidth(544);
	table.setPrefHeight(341);
	table.setLayoutX(110);
	table.setLayoutY(150);
	table.setEditable(true);

	// Adding columns to the table

	TableColumn<Accountant, String> Name = new TableColumn<Accountant, String>("Name");
	TableColumn<Accountant, Integer> ID = new TableColumn<Accountant, Integer>("ID");
	TableColumn<Accountant, String> Email = new TableColumn<Accountant, String>("Email");
	TableColumn<Accountant, String> Phone_Number = new TableColumn<Accountant, String>("Phone_Number");
	TableColumn<Accountant, String> university_degree = new TableColumn<Accountant, String>("university_degree");
	TableColumn<Accountant, Double> Salary = new TableColumn<Accountant, Double>("Salary");

	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
	ID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
	Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
	Phone_Number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
	
	
	
	
	
	
Name.setCellFactory(TextFieldTableCell.<Accountant>forTableColumn());
	
	
	
	Name.setOnEditCommit((CellEditEvent<Accountant, String> e) -> {
		((Accountant) e.getTableView().getItems().get(e.getTablePosition().getRow())).setName(e.getNewValue()); // display only
		Queries.updateNameAccountant(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Email.setCellFactory(TextFieldTableCell.<Accountant>forTableColumn());

	Email.setOnEditCommit((CellEditEvent<Accountant, String> e) -> {
		((Accountant) e.getTableView().getItems().get(e.getTablePosition().getRow())).setEmail(e.getNewValue()); // display only
		Queries.updateEmailAccountant(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Phone_Number.setCellFactory(TextFieldTableCell.<Accountant>forTableColumn());

	Phone_Number.setOnEditCommit((CellEditEvent<Accountant, String> e) -> {
		((Accountant) e.getTableView().getItems().get(e.getTablePosition().getRow())).setPhone_number(e.getNewValue()); // display only
		Queries.updatePhoneAccountant(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	university_degree.setCellFactory(TextFieldTableCell.<Accountant>forTableColumn());

	university_degree.setOnEditCommit((CellEditEvent<Accountant, String> e) -> {
		((Accountant) e.getTableView().getItems().get(e.getTablePosition().getRow())).setUniversity_degree(e.getNewValue()); // display only
		Queries.updateUDAccountant(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Salary.setOnEditCommit((CellEditEvent<Accountant, Double> e) -> {
		((Accountant) e.getTableView().getItems().get(e.getTablePosition().getRow())).setSalary(e.getNewValue()); // display only
		Queries.updateSalaryAccountant(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	
	
	
	
	
	
	
	
	
	

	university_degree.setCellValueFactory(new PropertyValueFactory<>("university_degree"));
	Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));

	// adding columns to the table
	table.getColumns().addAll(ID, Name, Email, Phone_Number, university_degree, Salary);
	ObservableList<Accountant> list = FXCollections.observableArrayList();
	list = Queries.getAccountant();
	table.setItems(list);
	table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	b2.setOnAction(event ->{
		
		Label idl = new Label("ID :");
		idl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		
		Label namel = new Label("Name :");
		namel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Label passl = new Label("Password :");
		passl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		
		
		Label salaryl = new Label("Salary :");
		salaryl.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Label emaill = new Label("Email :");
		emaill.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Label phonel = new Label("Phone :");
		phonel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Label UDl = new Label("University Degree :");
		UDl.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		
		
		
		TextField idt =new TextField();
		TextField namet =new TextField();
		TextField passt =new TextField();
		TextField salaryt =new TextField();
		TextField emailt =new TextField();
		TextField phonet =new TextField();
		TextField UDt =new TextField();
		
		Stage st =new Stage();
		
		
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		grid.add(idl, 0, 0);
		grid.add(namel, 0, 1);
		grid.add(salaryl, 0, 2);
		grid.add(emaill, 0, 3);
		grid.add(phonel, 0, 4);
		grid.add(UDl, 0, 5);
		grid.add(idt, 1, 0);
		grid.add(namet, 1, 1);
		grid.add(salaryt, 1, 2);
		grid.add(emailt, 1, 3);
		grid.add(phonet, 1, 4);
		grid.add(UDt, 1, 5);
		grid.add(passl, 0, 6);
		grid.add(passt, 1, 6);

		
		

		Button save = new Button("Save");
		grid.add(save, 0, 7);
		
		save.setOnAction(ev ->{
			
			try {
				Queries.insertDataAccountant(new Accountant(Integer.parseInt(idt.getText().trim()),passt.getText(),namet.getText(), Double.parseDouble(salaryt.getText()), emailt.getText(), phonet.getText(), UDt.getText()));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			ObservableList<Accountant> list1 = FXCollections.observableArrayList();
			try {
				list1 = Queries.getAccountant();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			table.setItems(list1);
			
			
			
				
			});
		
		
		
		grid.setStyle("-fx-background:grey");
		
		
		Scene sc2 = new Scene(grid,500,500);
		sc2.getStylesheets().add("Plugins.css");

		st.setScene(sc2);
		st.show();
		
		
		
		
		
		
		
		
		
		
		
		
	});


Label l = new Label("Accountant Page") ;
l.setPrefWidth(260);
l.setPrefHeight(32);
l.setLayoutX(255);
l.setLayoutY(30);
l.setFont(new Font("Baskerville Old Face", 30));

l.setStyle("-fx-text-fill : #c39c48;");
pane.getChildren().addAll(back,table,v,l);
pane.setStyle("-fx-background-color :  #0c1f46");

//Scene s = new Scene(pane,684,525);	
//s.getStylesheets().add("Plugins.css");

return pane;

	
}
public static Scene getManager(Stage primaryStage) throws Exception {

	
	AnchorPane pane = new AnchorPane();
	
Button logOut = new Button("Log Out");
	
	
	logOut.setOnAction(ev ->{
		Scene sc3 = Scenes.empLogin(primaryStage);
		sc3.getStylesheets().add("Plugins.css");
		primaryStage.setScene(sc3);
		
		
		
	});
		
	Button b2 = new Button("Insert");
	b2.setPrefWidth(92);
	b2.setPrefHeight(32);
	
	


	
	Button showemp=new Button ("Show Employee");
	showemp.setPrefWidth(150);
	showemp.setPrefHeight(32);
	showemp.setOnAction(e -> {
		
		try {
			Scene sc1 = Scenes.MangerShowCompany(primaryStage);
			sc1.getStylesheets().add("Plugins.css");
			
			primaryStage.setScene(sc1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	});
	VBox v = new VBox(40);
	v.setLayoutX(10);
	v.setLayoutY(216);
	v.getChildren().addAll(b2,showemp);


	TableView<Manager> table = new TableView<Manager>();
	table.setPrefWidth(544);
	table.setPrefHeight(341);
	table.setLayoutX(210);
	table.setLayoutY(150);
	table.setEditable(true);



	// Adding columns to the table

	TableColumn<Manager, String> Name = new TableColumn<Manager, String>("Name");
	TableColumn<Manager, Integer> ID = new TableColumn<Manager, Integer>("ID");
	TableColumn<Manager, String> Email = new TableColumn<Manager, String>("Email");
	TableColumn<Manager, String> Phone_Number = new TableColumn<Manager, String>("Phone_Number");
	TableColumn<Manager, String> Unversty_Degree = new TableColumn<Manager, String>("Unversty_Degree");
	TableColumn<Manager, Double> Salary = new TableColumn<Manager, Double>("Salary");

	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
	ID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
	Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
	Phone_Number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));

	Unversty_Degree.setCellValueFactory(new PropertyValueFactory<>("unversty_degree"));
	Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
	
	
	
	Name.setCellFactory(TextFieldTableCell.<Manager>forTableColumn());
	
	
	
	Name.setOnEditCommit((CellEditEvent<Manager, String> e) -> {
		((Manager) e.getTableView().getItems().get(e.getTablePosition().getRow())).setName(e.getNewValue()); // display only
		Queries.updateNameManager(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Email.setCellFactory(TextFieldTableCell.<Manager>forTableColumn());

	Email.setOnEditCommit((CellEditEvent<Manager, String> e) -> {
		((Manager) e.getTableView().getItems().get(e.getTablePosition().getRow())).setEmail(e.getNewValue()); // display only
		Queries.updateEmailManager(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Phone_Number.setCellFactory(TextFieldTableCell.<Manager>forTableColumn());

	Phone_Number.setOnEditCommit((CellEditEvent<Manager, String> e) -> {
		((Manager) e.getTableView().getItems().get(e.getTablePosition().getRow())).setPhone_number(e.getNewValue()); // display only
		Queries.updatePhoneManager(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Unversty_Degree.setCellFactory(TextFieldTableCell.<Manager>forTableColumn());

	Unversty_Degree.setOnEditCommit((CellEditEvent<Manager, String> e) -> {
		((Manager) e.getTableView().getItems().get(e.getTablePosition().getRow())).setUniversity_degree(e.getNewValue()); // display only
		Queries.updateUDManager(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	Salary.setOnEditCommit((CellEditEvent<Manager, Double> e) -> {
		((Manager) e.getTableView().getItems().get(e.getTablePosition().getRow())).setSalary(e.getNewValue()); // display only
		Queries.updateSalaryManager(e.getRowValue().getEmployee_id(), e.getNewValue());

	});
	
	
	
	
	
	
	
	
	
	
	

	// adding columns to the table
	table.getColumns().addAll(ID, Name, Email, Phone_Number, Unversty_Degree, Salary);
	ObservableList<Manager> list = FXCollections.observableArrayList();
	list = Queries.getManger();
	table.setItems(list);
	table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	
	
	b2.setOnAction(event ->{
		
		Label idl = new Label("ID :");
		idl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		
		Label namel = new Label("Name :");
		namel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Label passl = new Label("Password :");
		passl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		
		
		Label salaryl = new Label("Salary :");
		salaryl.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Label emaill = new Label("Email :");
		emaill.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Label phonel = new Label("Phone :");
		phonel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Label UDl = new Label("University Degree :");
		UDl.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		
		
		
		TextField idt =new TextField();
		TextField namet =new TextField();
		TextField passt =new TextField();
		TextField salaryt =new TextField();
		TextField emailt =new TextField();
		TextField phonet =new TextField();
		TextField UDt =new TextField();
		
		Stage st =new Stage();
		
		
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		grid.add(idl, 0, 0);
		grid.add(namel, 0, 1);
		grid.add(salaryl, 0, 2);
		grid.add(emaill, 0, 3);
		grid.add(phonel, 0, 4);
		grid.add(UDl, 0, 5);
		grid.add(idt, 1, 0);
		grid.add(namet, 1, 1);
		grid.add(salaryt, 1, 2);
		grid.add(emailt, 1, 3);
		grid.add(phonet, 1, 4);
		grid.add(UDt, 1, 5);
		grid.add(passl, 0, 6);
		grid.add(passt, 1, 6);
	
		
		

		Button save = new Button("Save");
		grid.add(save, 0, 7);
		
		save.setOnAction(ev ->{
			
			try {
				Queries.insertDataManager(new Manager(Integer.parseInt(idt.getText().trim()),passt.getText(),namet.getText(), Double.parseDouble(salaryt.getText()), emailt.getText(), phonet.getText(), UDt.getText()));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			ObservableList<Manager> list1 = FXCollections.observableArrayList();
			try {
				list1 = Queries.getManger();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			table.setItems(list1);
			
			
			
			
		});
		
		
		
		
		
		
		grid.setStyle("-fx-background:grey");
		
		
		Scene sc2 = new Scene(grid,500,500);
		sc2.getStylesheets().add("Plugins.css");

		st.setScene(sc2);
		st.show();
		
		
		
		
		
		
		
		
		
		
		
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	Label l = new Label("Manger Page");
	l.setPrefWidth(220);
	l.setPrefHeight(32);
	l.setLayoutX(400);
	l.setLayoutY(30);
	l.setFont(new Font("Baskerville Old Face", 30));
	l.setStyle("-fx-text-fill : #c39c48;");
	
	
	pane.getChildren().addAll(logOut,table, v, l);
	pane.setStyle("-fx-background-color :  #0c1f46");

	Scene s = new Scene(pane, 1000, 600);
	s.getStylesheets().add("Plugins.css");

	return s;
	
	
	
}

	private static void Alert(Alert.AlertType alert1, Window root, String title, String message) { // method that give new
		// window with alert
		Alert alert = new Alert(alert1);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(root);
		alert.show();
	}
	
	
}