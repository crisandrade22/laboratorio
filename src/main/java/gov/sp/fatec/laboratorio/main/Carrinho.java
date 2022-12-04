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

import java.net.URL;
import java.util.*;


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

    }
}