package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="col_moh"
    private TableColumn<TableModel, String> col_moh; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<TableModel> table; // Value injected by FXMLLoader

    @FXML // fx:id="col_eng"
    private TableColumn<TableModel, String> col_eng; // Value injected by FXMLLoader

    @FXML // fx:id="col_oji"
    private TableColumn<TableModel, String> col_oji; // Value injected by FXMLLoader

    @FXML // fx:id="filterField"
    private TextField filterField;

    ObservableList<TableModel> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Connection con = DBConnector.getConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from trtable");
            while (rs.next()) {
                oblist.add(new TableModel(rs.getString("english"), rs.getString("ojibwe"), rs.getString("mohawk")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_eng.setCellValueFactory(new PropertyValueFactory<>("english"));
        col_oji.setCellValueFactory(new PropertyValueFactory<>("ojibwe"));
        col_moh.setCellValueFactory(new PropertyValueFactory<>("mohawk"));

        table.setItems(oblist);

        FilteredList<TableModel> filteredData = new FilteredList<>(oblist, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getEnglish()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<TableModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    public void filter(ActionEvent actionEvent) {
    }
}