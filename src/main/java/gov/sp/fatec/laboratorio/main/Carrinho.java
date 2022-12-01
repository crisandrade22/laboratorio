package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import gov.sp.fatec.laboratorio.model.Sacola;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;


public class Carrinho implements Initializable {

    @FXML
    private ListView<String>listCarrinho;

    @FXML
    Label myLabel;

    Set<Produto> produtos = new HashSet<>();

    List<String> nomesProdutos = new ArrayList<>();
    List<Double> precosProdutos = new ArrayList<>();

    Map<String, Double> teste = new HashMap<>();

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
        listCarrinho.getItems().addAll(nomesProdutos + " " + precosProdutos);
    }


//    ObservableList lista = FXCollections.observableArrayList();
//    ListView listView = new ListView();
//
//    private Produto produto;
//
//    @FXML
//    private ListView<Produto> cristiane;
//
//    private List<Produto> listaProdutos = new ArrayList<>();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resourceBundle) {
//        Sacola sacola = Sacola.getInstance();
//
//        listaProdutos.addAll(sacola.chaves());
//        lista.addAll(listaProdutos);
//        listView.getItems().add(lista);
//        System.out.println(listaProdutos);
//
//    }

}

//public class Carrinho extends Application {
//    private List<Produto> listaProdutos = new ArrayList<>();
//    ObservableList lista = FXCollections.observableArrayList();
//
//    @FXML
//    ListView listCarrinho = new ListView();
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        stage.setTitle("ListView Experiment 1");
//
//        Sacola sacola = Sacola.getInstance();
//
//        ListView listCarrinho = new ListView();
//        listaProdutos.addAll(sacola.chaves());
//        lista.addAll(listaProdutos);
//        listCarrinho.getItems().add(lista);
//    }
//
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//}
