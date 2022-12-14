package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import gov.sp.fatec.laboratorio.model.Sacola;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class Carrinho implements Initializable {
    @FXML
    private TableView<Produto> listCarrinho;
    @FXML
    private TableColumn<Produto, String> columnProduto;
    @FXML
    private TableColumn<Produto, Integer> columnQuantidade;
    @FXML
    private TableColumn<Produto, Double> columnUnitario;
    @FXML
    private TableColumn<Produto, Double> columnValorTotal;

    ObservableList<Produto> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnUnitario.setCellValueFactory(new PropertyValueFactory<>("precoProduto"));
        columnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

        Set<Map.Entry<Produto, Integer>> entradas = Sacola.getInstance().entradas();

        for (Map.Entry<Produto, Integer> entrada : entradas) {
            System.out.println("Entrei no for");
            String nomeProduto = entrada.getKey().getNomeProduto();
            Integer quantidade = entrada.getValue();
            double precoProduto = entrada.getKey().getPrecoProduto();
            Double valortotal = quantidade * precoProduto;
            System.out.println(quantidade);
            System.out.println(valortotal);

            Produto produto = new Produto(nomeProduto, precoProduto, quantidade, String.format("%.2f", valortotal));
            observableList.add(produto);
        }

        listCarrinho.setItems(observableList);
        listCarrinho.setEditable(true);
        columnQuantidade.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }
    @FXML
    public void salvarCarrinho(javafx.scene.input.MouseEvent mouseEvent) {
        insertInto();
    }

    public void insertInto() {
        Set<Map.Entry<Produto, Integer>> entradas = Sacola.getInstance().entradas();
        String url = "jdbc:mysql://172.20.0.3:3306/laboratorio?useTimezone=true&serverTimezone=UTC";
        String username = "root";
        String password = "1";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produtosComprados (nome_produto, quantidade, valor_unitario, valor_total, data_compra) VALUES (?,?,?,?,?)");
            System.out.println(preparedStatement);
            for (Map.Entry<Produto, Integer> entrada : entradas) {
                String nomeProduto = entrada.getKey().getNomeProduto();
                Integer quantidade = entrada.getKey().getQuantidade();
                System.out.println("QUANTIDADE: "+ quantidade);
                double precoProduto = entrada.getKey().getPrecoProduto();
                double valor_total = quantidade * precoProduto;
                Date date = Date.valueOf(LocalDate.now());
                System.out.println(date);

                preparedStatement.setString(1, nomeProduto);
                preparedStatement.setInt(2, quantidade);
                preparedStatement.setDouble(3, precoProduto);
                preparedStatement.setDouble(4, valor_total);
                preparedStatement.setDate(5, date);
                preparedStatement.executeUpdate();
            }
            System.out.println(LocalDate.now());
            listCarrinho.getItems().clear();
            ListaComprasController.carrinho.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }


    public void deleteProdutoCarrinho(MouseEvent mouseEvent) {
        Produto selectedItem = listCarrinho.getSelectionModel().getSelectedItem();
        listCarrinho.getItems().removeAll(listCarrinho.getSelectionModel().getSelectedItem());
        Sacola.getInstance().remover(selectedItem);
    }


    public void editarProdutoCarrinho(TableColumn.CellEditEvent<Produto, Integer> produtoIntegerCellEditEvent) {
        Produto selectedItem = listCarrinho.getSelectionModel().getSelectedItem();
        selectedItem.setQuantidade(produtoIntegerCellEditEvent.getNewValue());
        String valorTotal = String.valueOf(selectedItem.getPrecoProduto() * produtoIntegerCellEditEvent.getNewValue());
        System.out.println("EU SOU O VALOR TOTAL: " + valorTotal);
        selectedItem.setValorTotal(valorTotal);
        Sacola.getInstance().update(selectedItem, produtoIntegerCellEditEvent.getNewValue());
    }
}