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
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastrarProdutoController implements Initializable {

    @FXML
    private TableView<String> cadastrarProduto;

    @FXML
    private TableColumn<String, String> idNomeProduto;

    @FXML
    private TableColumn<String, Double> idPreco;

    @FXML
    private TableColumn<String, String> localImagem;

    Produto produtoCadastrar = new Produto();
    ObservableList<String> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idNomeProduto.setCellValueFactory(new PropertyValueFactory<>(""));
        idPreco.setCellValueFactory(new PropertyValueFactory<>(""));
        localImagem.setCellValueFactory(new PropertyValueFactory<>(""));


        for (int i = 0; i < 10; i++) {

            observableList.add("");
        }

        cadastrarProduto.setItems(observableList);

        cadastrarProduto.setEditable(true);
        idNomeProduto.setCellFactory(TextFieldTableCell.forTableColumn());
        idPreco.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        localImagem.setCellFactory(TextFieldTableCell.forTableColumn());

    }


    public void alteracaoNomeProduto(TableColumn.CellEditEvent<String, String> stringStringCellEditEvent) {
        produtoCadastrar.setNomeProduto(stringStringCellEditEvent.getNewValue());
        System.out.println(stringStringCellEditEvent.getNewValue());
    }

    public void alteracaoPrecoProduto(TableColumn.CellEditEvent<String, Double> stringDoubleCellEditEvent) {
        produtoCadastrar.setPrecoProduto(stringDoubleCellEditEvent.getNewValue());
        System.out.println("stringDoubleCellEditEvent.getNewValue() = " + stringDoubleCellEditEvent.getNewValue());
    }

    public void alteracaoLocalImagem(TableColumn.CellEditEvent<String, String> stringStringCellEditEvent) {
        produtoCadastrar.setImagemSource(stringStringCellEditEvent.getNewValue());
        System.out.println("stringStringCellEditEvent.getNewValue() = " + stringStringCellEditEvent.getNewValue());
    }

    public void salvar(MouseEvent mouseEvent) {
        String selectedItem = cadastrarProduto.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
        Stage primaryStage = new Stage();

        String url = "jdbc:mysql://172.20.0.3:3306/laboratorio?useTimezone=true&serverTimezone=UTC";
        String username = "root";
        String password = "1";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produtos (nome, preco_unitario, caminho_imagem) VALUES (?,?,?)");


            preparedStatement.setString(1, produtoCadastrar.getNomeProduto());
            preparedStatement.setDouble(2, produtoCadastrar.getPrecoProduto());
            preparedStatement.setString(3, produtoCadastrar.getImagemSource());
            preparedStatement.executeUpdate();
            System.out.println("Executado: " + preparedStatement);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("listaCompras.fxml"));
            primaryStage.setTitle("Lista de Compras");
            primaryStage.setScene(new Scene(fxmlLoader.load()));
            primaryStage.show();
            initialize(null, null);
            salvo();
            ListaComprasController.cadastro.close();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("O produto foi cadastrado com sucesso.");
        alert.setContentText("Talvez seja necessário reiniciar o sistema.");
        alert.showAndWait().ifPresent(rs -> {
        });
    }

}


