package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import gov.sp.fatec.laboratorio.model.Sacola;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private TableColumn<Produto, String> columnValorTotal;

    @FXML
    private TableColumn<Produto, String> columnUnitario;

    ObservableList<Produto> insurances = FXCollections.observableArrayList();

    @FXML
    Label myLabel;

    Set<Produto> produtos = new HashSet<>();

    List<String> nomesProdutos = new ArrayList<>();
    List<Double> precosProdutos = new ArrayList<>();

    Map<String, Double> teste = new HashMap<>();

//    TODO acrescentar lógica de quando o produto estiver selecionado, poder excluir, alterar.
    String produtoSelecionado;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Sacola sacola = Sacola.getInstance();

        System.out.println("Entrada:" + sacola.entradas());
        produtos.addAll(sacola.chaves());

        for (Produto produto : produtos) {
            nomesProdutos.add(produto.getNomeProduto());
            precosProdutos.add(produto.getPrecoProduto());
            teste.put(produto.getNomeProduto(), produto.getPrecoProduto());
        }

        String totalPagar;
//        listCarrinho.getItems().addAll("Produto                 Preço Unitário              Preço Total");
        insurances.addAll(, "Cachaça");
        listCarrinho.getColumns().addAll(columnProduto, columnQuantidade, columnUnitario, columnValorTotal);

        for (Map.Entry<Produto, Integer> entry : Sacola.getInstance().entradas()) {
            columnProduto.setCellValueFactory(new PropertyValueFactory(entry.getKey().getNomeProduto()));
//            columnQuantidade.setCellValueFactory(new PropertyValueFactory(entry.getKey().);
//            columnUnitario.setCellValueFactory(new PropertyValueFactory(entry.getKey().getNomeProduto()));
//            columnValorTotal.setCellValueFactory(new PropertyValueFactory(entry.getKey().getNomeProduto()));
            listCarrinho.getColumns().addAll(columnProduto);
//            System.out.println("Get preço produto: " + entry.getKey().getPrecoProduto() + "Get entry value: " + entry.getValue());
//            totalPagar = String.format("%.2f", entry.getKey().getPrecoProduto() * entry.getValue());

//            listCarrinho.getItems().addAll(entry.getKey().getNomeProduto() + "                     " + entry.getKey().getPrecoProduto() + "                             " + totalPagar);
        }
    }

//    public void adicionarCarrinho(javafx.scene.input.MouseEvent mouseEvent) {
//        for (String item : listCarrinho.getItems()) {
//            System.out.println("Item: " + item);
//        }
//    }
}