//Vanessa Saunders 0570255 and Anecia Johnson 0598121

package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
//TableController implements interface Initializable that allows for interaction with FXML
public class TableController implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableColumn<TableModel, String> col_moh;
    @FXML
    private TableView<TableModel> table;
    @FXML
    private TableColumn<TableModel, String> col_eng;
    @FXML
    private TableColumn<TableModel, String> col_oji;
    @FXML
    private TextField filterField;
    private ObservableList<TableModel> oblist = FXCollections.observableArrayList();

    public TableController() {
    }
//initializes database connection
    public void initialize(URL location, ResourceBundle resources) {
        Connection con = DBConnector.getConnection();
//attempts to execute query from database & catches sql exceptions
        try {
            assert con != null;
            ResultSet rs = con.createStatement().executeQuery("select * from trtable");
//iterates through the columns in the database
            while(rs.next()) {
                this.oblist.add(new TableModel(rs.getString("english"), rs.getString("ojibwe"), rs.getString("mohawk")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//connect the columns in the database with that of the TableView column properties
        this.col_eng.setCellValueFactory(new PropertyValueFactory<>("english"));
        this.col_oji.setCellValueFactory(new PropertyValueFactory<>("ojibwe"));
        this.col_moh.setCellValueFactory(new PropertyValueFactory<>("mohawk"));
        this.table.setItems(this.oblist);
        FilteredList<TableModel> filteredData = new FilteredList(this.oblist, (p) -> {
            return true;
        });
        //uses typed word in filterField as a filter for the table view and returns the filtered table list if the word typed matches words in the translator
        this.filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((myObject) -> {
                if (newValue != null && !newValue.isEmpty()) {
                    String lowerCaseFilter = newValue.toLowerCase();
                    return String.valueOf(myObject.getEnglish()).toLowerCase().contains(lowerCaseFilter);
                } else {
                    return true;
                }
            });
        });
        SortedList<TableModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(this.table.comparatorProperty());
        this.table.setItems(sortedData);
    }

}
