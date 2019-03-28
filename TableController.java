package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable {

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="col_moh"
        private TableColumn<TableModel,String> col_moh; // Value injected by FXMLLoader

        @FXML // fx:id="table"
        private TableView<TableModel> table; // Value injected by FXMLLoader

        @FXML // fx:id="col_eng"
        private TableColumn<TableModel,String> col_eng; // Value injected by FXMLLoader

        @FXML // fx:id="col_oji"
        private TableColumn<TableModel,String> col_oji; // Value injected by FXMLLoader

ObservableList<TableModel> oblist= FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Connection con = DBConnector.getConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from trtable");
            while (rs.next()) {
                oblist.add(new TableModel(rs.getString("english"),rs.getString("ojibwe"),rs.getString("mohawk")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_eng.setCellValueFactory(new PropertyValueFactory<>("english"));
        col_oji.setCellValueFactory(new PropertyValueFactory<>("ojibwe"));
        col_moh.setCellValueFactory(new PropertyValueFactory<>("mohawk"));

        table.setItems(oblist);
    }
}
