
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Raed extends Application {

	@Override
	public void start(Stage primarystage) throws Exception {
		/*
		 * ArrayList<Accountant> dataAccountant; ObservableList<Accountant>
		 * dataListAccountant; ArrayList<Manger> datamanger; ObservableList<Manger>
		 * dataListmanger; ArrayList<employee> dataEmp; ObservableList<employee>
		 * dataListEmp; ArrayList<Advisor> dataAdvisor; ObservableList<Advisor>
		 * dataListAdvisor;
		 */

		AnchorPane pane = new AnchorPane();
		Button b1 = new Button("Remove");
		b1.setPrefWidth(92);
		b1.setPrefHeight(32);

		Button b2 = new Button("Insert");
		b2.setPrefWidth(92);
		b2.setPrefHeight(32);

		Button b3 = new Button("Update");
		b3.setPrefWidth(92);
		b3.setPrefHeight(32);

		VBox v = new VBox(40);
		v.setLayoutX(10);
		v.setLayoutY(216);
		v.getChildren().addAll(b1, b2, b3);

		// TableView<Manger> table = new TableView<Manger>();
		// table.setPrefWidth(547);
		// table.setPrefHeight(341);
		// table.setLayoutX(120);
		// table.setLayoutY(165);
		// table.setEditable(true);

		TableView<Manager> table = new TableView<Manager>();
		table.setPrefWidth(544);
		table.setPrefHeight(341);
		table.setLayoutX(110);
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

		// adding columns to the table
		table.getColumns().addAll(ID, Name, Email, Phone_Number, Unversty_Degree, Salary);
		ObservableList<Manager> list = FXCollections.observableArrayList();
		list = Queries.getManger();
		table.setItems(list);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		Label l = new Label("Manger Page");
		l.setPrefWidth(220);
		l.setPrefHeight(32);
		l.setLayoutX(255);
		l.setLayoutY(30);
		l.setFont(new Font("Baskerville Old Face", 30));
		l.setStyle("-fx-text-fill : #c39c48;");
		
		
		pane.getChildren().addAll(table, v, l);
		pane.setStyle("-fx-background-color :  #0c1f46");

		Scene s = new Scene(pane, 684, 525);
		s.getStylesheets().add(this.getClass().getResource("Plugins.css").toExternalForm());

		primarystage.setScene(s);
		primarystage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
