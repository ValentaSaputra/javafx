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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class detailDataController {

    @FXML
    private TextField inputId;

    @FXML
    private Button btnCari;

    @FXML
    private TableView<Person> tabel_view;

    @FXML
    private TableColumn<Person, Integer> col_id;

    @FXML
    private TableColumn<Person, String> col_nama;

    @FXML
    private TableColumn<Person, String> col_telpon;

    @FXML
    private Button menu_kembali;

    private ObservableList<Person> dataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
    }

    private void setupTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_teman"));
        col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        col_telpon.setCellValueFactory(new PropertyValueFactory<>("telpon"));
    }

    @FXML
    void handleCari(ActionEvent event) {
        cariData();
    }

    @FXML
    void handleInputKeyPressed(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
            cariData();
        }
    }

    private void cariData() {
        int id;
        try {
            id = Integer.parseInt(inputId.getText());
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.out.println("Input ID tidak valid");
            return;
        }

        String detailData = Koneksi.detaildata(id);
        dataList.clear();

        if (!detailData.equals("Data tidak ditemuan")) {
            String[] lines = detailData.split("\n");
            int idTeman = Integer.parseInt(lines[0].split(":")[1].trim());
            String nama = lines[1].split(":")[1].trim();
            String telpon = lines[2].split(":")[1].trim();

            dataList.add(new Person(idTeman, nama, telpon));
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