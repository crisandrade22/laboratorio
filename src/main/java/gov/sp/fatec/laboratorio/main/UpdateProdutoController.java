package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UpdateProdutoController implements Initializable {

    @FXML
    private TableView<Produto> updateProduto;

    @FXML
    private TableColumn<Produto, String> idNomeProduto;

    @FXML
    private TableColumn<Produto, Double> idPrecoProduto;

    ObservableList<Produto> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        idPrecoProduto.setCellValueFactory(new PropertyValueFactory<>("precoProduto"));

        Produto produtoAtual = ListaComprasController.produtoAtual;
        System.out.println("EU SOU O PRODUTO ALTERAR: " + produtoAtual);
        Produto produtoAlterar = new Produto();
        produtoAlterar.setNomeProduto(produtoAtual.getNomeProduto());
        produtoAlterar.setPrecoProduto(produtoAtual.getPrecoProduto());

        observableList.add(produtoAlterar);
        updateProduto.setItems(observableList);
        updateProduto.setEditable(true);
        idPrecoProduto.setCellFactory(TextFieldTableCell.forTableColumn((new DoubleStringConverter())));
    }


    public void salvarUpdate(MouseEvent mouseEvent) {
        System.out.println("GET OWNER: " + ListaComprasController.update.getTitle());
        System.out.println("ListaComprasController.update.getScene() = " + ListaComprasController.update.getScene().getRoot());
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("listaCompras.fxml"));
        primaryStage.setTitle("Lista de Compras");
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.show();
        initialize(null, null);
        updateMensagem();
        ListaComprasController.update.close();
    }

    public void alteracaoProduto(TableColumn.CellEditEvent<Produto, Double> produtoDoubleCellEditEvent) {
        String url = "jdbc:mysql://172.20.0.3:3306/laboratorio?useTimezone=true&serverTimezone=UTC";
        String username = "root";
        String password = "1";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produtos SET preco_unitario = (?) WHERE nome = (?);");
            Double newValue = produtoDoubleCellEditEvent.getNewValue();

            Produto selectedItem = updateProduto.getSelectionModel().getSelectedItem();

            preparedStatement.setDouble(1, newValue);
            preparedStatement.setString(2, selectedItem.getNomeProduto());
            preparedStatement.executeUpdate();
            System.out.println("Executado: " + preparedStatement);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateMensagem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("O produto foi alterado com sucesso.");
        alert.setContentText("Talvez seja necessário reiniciar o sistema.");
        alert.showAndWait().ifPresent(rs -> {
        });
    }

}

