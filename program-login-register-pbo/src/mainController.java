import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class mainController {
    @FXML
    private Button clear;
    @FXML
    private TableColumn<Person, Integer> col_id;
    @FXML
    private TableColumn<Person, String> col_nama;
    @FXML
    private TableColumn<Person, String> col_telpon;
    @FXML
    private Button delete;
    @FXML
    private Button insert;
    @FXML
    private TextField nama;
    @FXML
    private TableView<Person> tabel_view;
    @FXML
    private TextField telpon;
    @FXML
    private Button update;

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_teman"));
        col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        col_telpon.setCellValueFactory(new PropertyValueFactory<>("telpon"));
        tabel_view.setItems(personList);
        loadDataFromDatabase();

        // Tambahkan listener untuk selection di tabel_view
        tabel_view.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nama.setText(newSelection.getNama());
                telpon.setText(newSelection.getTelpon());
            }
        });
    }

    private void loadDataFromDatabase() {
        personList.clear();
        String allData = Koneksi.getAllData();
        String[] dataLines = allData.split("\n");
        for (String line : dataLines) {
            if (!line.equals("Data Tidak Ditemukan!!!")) {
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0].split(": ")[1]);
                String nama = parts[1].split(": ")[1];
                String telpon = parts[2].split(": ")[1];
                personList.add(new Person(id, nama, telpon));
            }
        }
    }

    @FXML
    private void handleInsert() {
        String namaPerson = nama.getText().trim();
        String telponPerson = telpon.getText().trim();

        if (namaPerson.isEmpty() || telponPerson.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Nama dan Telpon harus diisi!");
            return;
        }

        if (Koneksi.tambahData(namaPerson, telponPerson)) {
            loadDataFromDatabase();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil ditambahkan ke database!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal menambahkan data ke database!");
        }
    }

    @FXML
    private void handleUpdate() {
        Person selectedPerson = tabel_view.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Pilih data yang akan diupdate!");
            return;
        }

        String namaPerson = nama.getText().trim();
        String telponPerson = telpon.getText().trim();

        if (Koneksi.updateData(selectedPerson.getId_teman(), namaPerson, telponPerson)) {
            loadDataFromDatabase();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil diupdate!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal mengupdate data!");
        }
    }

    @FXML
    private void handleDelete() {
        Person selectedPerson = tabel_view.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Pilih data yang akan dihapus!");
            return;
        }

        if (Koneksi.deleteData(selectedPerson.getId_teman())) {
            loadDataFromDatabase();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil dihapus!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal menghapus data!");
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
    }

    private void clearFields() {
        nama.clear();
        telpon.clear();
        tabel_view.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}