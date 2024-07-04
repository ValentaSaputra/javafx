import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class LihatSemuaDataController {

    @FXML
    private TableColumn<Person, Integer> col_id;

    @FXML
    private TableColumn<Person, String> col_nama;

    @FXML
    private TableColumn<Person, String> col_telpon;

    @FXML
    private Button menu_kembali;

    @FXML
    private TableView<Person> tabel_view;

    private ObservableList<Person> dataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadData();
    }

    private void setupTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_teman"));
        col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        col_telpon.setCellValueFactory(new PropertyValueFactory<>("telpon"));
    }

    private void loadData() {
        dataList.clear();
        String allData = Koneksi.getAllData();
        String[] rows = allData.split("\n");
        for (String row : rows) {
            if (!row.equals("Data Tidak Ditemukan!!!")) {
                String[] cols = row.split(", ");
                int id = Integer.parseInt(cols[0].split(": ")[1]);
                String nama = cols[1].split(": ")[1];
                String telpon = cols[2].split(": ")[1];
                dataList.add(new Person(id, nama, telpon));
            }
        }
        tabel_view.setItems(dataList);
    }

    @FXML
    void handle_kembali(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) menu_kembali.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}