package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import gov.sp.fatec.laboratorio.model.Sacola;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class ListaComprasController implements Initializable {
//TODO quando a chave do produto já existe e a pessoa for adicionar o mesmo item, somar a quantidade na mesma chave.
//TODO O campo de busca somente retornar os produtos procurados
//TODO salvar no banco de dados compras finalizadas.
//TODO quando clicar no carrinho aparecer a lista de todos os produtos adicionados.
//TODO colocar botão para adicionar, excluir ou alterar produtos.
//TODO mostrar para o usuário compras anteriores.

    ObservableList lista = FXCollections.observableArrayList();

    @FXML
    private ListView<Produto> produtos;

    @FXML
    private VBox produtoEscolhido;

    @FXML
    private TextField precoTotal;

    @FXML
    private TextField valorTotalCompra;

    @FXML
    private TextField textFieldQuantidade;

    private Produto produtoAtual;

    @FXML
    private Button button;

    @FXML
    private Label labelNomeProduto;

    @FXML
    private Label labelPrecoProduto;

    @FXML
    private ImageView produtoImagem;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Produto> listaProdutos = new ArrayList<>();

    private Image imagemProduto;

    private MyListener myListener;

    private List<Produto> getData() {
        String url = "jdbc:mysql://172.20.0.3:3306/laboratorio?useTimezone=true&serverTimezone=UTC";
        String username = "root";
        String password = "1";

        System.out.println("Connecting database...");
        PreparedStatement p = null;
        ResultSet rs = null;

        List<Produto> listaProdutos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "select * from produtos";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next()) {
                Produto produto = new Produto(rs.getString("nome"), rs.getDouble("preco_unitario"),1,"5.20");
                String nome = rs.getString("nome");
                System.out.println("Produto: " + nome + " Preço: " + rs.getDouble("preco_unitario"));
                produto.setNomeProduto(rs.getString("nome"));
                produto.setPrecoProduto(rs.getDouble("preco_unitario"));
                produto.setImagemSource(rs.getString("caminho_imagem"));
                produto.setCor("24736e");
                listaProdutos.add(produto);
            }
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        System.out.println("Tamanho da Lista: " + listaProdutos.size());
        return listaProdutos;
    }

    private void setProdutoEscolhido(Produto produto) {
        labelNomeProduto.setText(produto.getNomeProduto());
        labelPrecoProduto.setText(Main.CURRENCY + String.format("%.2f", produto.getPrecoProduto()));
        File imageFile = new File(produto.getImagemSource());
        imagemProduto = new Image(imageFile.toURI().toString());
        produtoImagem.setImage(imagemProduto);
        produtoAtual = produto;
        produtoEscolhido.setStyle("-fx-background-color: #" + produto.getCor() + ";\n" + "     -fx-background-radius" +
                ": 30");
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        System.out.println("Initialize size: " + listaProdutos.size());
        listaProdutos.addAll(getData());
        if(listaProdutos.size() > 0) {
            setProdutoEscolhido(listaProdutos.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produto produto) {
                    setProdutoEscolhido(produto);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            System.out.println("Tamanho lista: " + listaProdutos.size());
            for (int i = 0; i < listaProdutos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                System.out.println(getClass());
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                System.out.println(listaProdutos.get(i).getNomeProduto());
                itemController.setData(listaProdutos.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void adicionar(MouseEvent key) {
        System.out.println("Olha eu aqui");
        String text = getTextFieldQuantidade();
        int quantidade = Integer.parseInt(text);
        Sacola.getInstance().adicionar(produtoAtual, quantidade);
        System.out.println("produtosAdicionados = " + Sacola.getInstance());
    }

    public String getTextFieldQuantidade() {
        return textFieldQuantidade.getText();
    }

    @FXML
    public Double calcularValorTotal(MouseEvent event) {
        double totalPagar = 0;
        for(Map.Entry<Produto, Integer> entry : Sacola.getInstance().entradas()) {
            System.out.println("Get preço produto: " + entry.getKey().getPrecoProduto() + "Get entry value: " + entry.getValue());
           totalPagar +=  entry.getKey().getPrecoProduto() * entry.getValue();
        }
        valorTotalCompra.setText(String.valueOf(totalPagar));

        return totalPagar;
    }


    public void pressButton(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("carrinho.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public String valorTotalProduto(KeyEvent key) {
        double valorTotalizado = 0;
        String format = null;
        if(getTextFieldQuantidade().isBlank()){
            precoTotal.setText("");
        }
        else {
            String text = getTextFieldQuantidade();
            int quantidade = Integer.parseInt(text);
            System.out.println("Valor total: " + produtoAtual.getPrecoProduto() * quantidade);
            precoTotal.setText(String.format("%.2f", (produtoAtual.getPrecoProduto() * quantidade)));
            valorTotalizado = produtoAtual.getPrecoProduto() * quantidade;
            format = String.format("%.2f", valorTotalizado);
        }
        return format;
    }

}

