import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class menuController {

    @FXML
    private TextField pilih_menu;

    @FXML
    private Button okButton;

    public void getMenu(ActionEvent event) {
        String menu = pilih_menu.getText();
        switch (menu) {
            case "1":
                switchScene(event, "LihatSemuaData.fxml");
                break;
            case "2":
                switchScene(event, "DetailData.fxml");
                break;
            case "3":
                switchScene(event, "CariData.fxml");
                break;
            case "4":
                switchScene(event, "Main.fxml");
                break;
            case "5":
                switchScene(event, "Main.fxml");
                break;
            case "6":
                switchScene(event, "Main.fxml");
                break;
            case "7":
                switchScene(event, "LaporanData.fxml");
                break;
            case "0":
                switchScene(event, "Exit.fxml");
                break;
            default:
                System.out.println("Menu tidak valid!");
                break;
        }
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) pilih_menu.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println("Gagal berpindah ke " + fxmlFile + "!");
            e.printStackTrace();
        }
    }
    @FXML
    public void onOkButtonClicked(ActionEvent event) {
        getMenu(event);
    }
}