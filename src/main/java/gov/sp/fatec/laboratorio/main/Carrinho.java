package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import gov.sp.fatec.laboratorio.model.Sacola;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;


public class Carrinho implements Initializable {

    @FXML
    private ListView<String> listCarrinho;

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
        for (Map.Entry<Produto, Integer> entry : Sacola.getInstance().entradas()) {
            System.out.println("Get preço produto: " + entry.getKey().getPrecoProduto() + "Get entry value: " + entry.getValue());
            totalPagar = String.format("%.2f", entry.getKey().getPrecoProduto() * entry.getValue());

            listCarrinho.getItems().addAll("Produto: " + " " + entry.getKey().getNomeProduto() + " Preço Unitário: " + " " + entry.getKey().getPrecoProduto() + " Preço Total: " + totalPagar);
        }
    }

    public void adicionarCarrinho(javafx.scene.input.MouseEvent mouseEvent) {
        for (String item : listCarrinho.getItems()) {
            System.out.println("Item: " + item);
        }
    }
}