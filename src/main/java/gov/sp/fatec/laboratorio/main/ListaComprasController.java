package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ListaComprasController implements Initializable {
//TODO Conexão com o banco de dados.
//TODO quando a chave do produto já existe e a pessoa for adicionar o mesmo item, somar a quantidade na mesma chave.
//TODO Arredondamento dos valores, que estão sendo apresentados com muitas casas decimais.
//TODO O campo de busca somente retornar os produtos procurados
//TODO salvar no banco de dados compras finalizadas.
//TODO quando clicar no carrinho aparecer a lista de todos os produtos adicionados.
//TODO colocar botão para adicionar, excluir ou alterar produtos.
//TODO mostrar para o usuário compras anteriores.
    @FXML
    private VBox produtoEscolhido;

    @FXML
    private TextField precoTotal;

    @FXML
    private TextField valorTotalCompra;

    @FXML
    private TextField textFieldQuantidade;

    private Produto produtoAtual;

//    private List<Produto> produtosAdicionados = new ArrayList<>();

    private Map<Produto, Integer> produtosAdicionados = new HashMap<>();

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
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto;

        produto = new Produto();
        produto.setNomeProduto("açúcar");
        produto.setPreçoProduto(3.89);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/acucar-uniao.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("arroz");
        produto.setPreçoProduto(5.89);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/arroz-prato-fino.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("café");
        produto.setPreçoProduto(19.99);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/cafe-pilao.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("farofa");
        produto.setPreçoProduto(5.52);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/farofa-panco.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("feijão");
        produto.setPreçoProduto(6.99);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/feijao-carioca-kicaldo.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("lámen");
        produto.setPreçoProduto(2.29);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/lamen-nissin.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("macarrão");
        produto.setPreçoProduto(4.19);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/macarrao-pena-reanata.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("manteiga");
        produto.setPreçoProduto(11.29);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/manteiga-tirol.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("óleo de soja");
        produto.setPreçoProduto(7.79);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/oleo-soja-lisa.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("sal");
        produto.setPreçoProduto(2.59);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/sal-cisne.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        produto = new Produto();
        produto.setNomeProduto("farinha de trigo");
        produto.setPreçoProduto(5.29);
        produto.setImagemSource("/gov/sp/fatec/laboratorio/main/imagens/farinha-trigo-primor.png");
        produto.setCor("24736e");
        listaProdutos.add(produto);

        return listaProdutos;

    }

    private void setProdutoEscolhido(Produto produto) {
        labelNomeProduto.setText(produto.getNomeProduto());
        labelPrecoProduto.setText(Main.CURRENCY + produto.getPreçoProduto());
        imagemProduto = new Image(getClass().getResourceAsStream(produto.getImagemSource()));
        produtoImagem.setImage(imagemProduto);
        produtoAtual = produto;
        produtoEscolhido.setStyle("-fx-background-color: #" + produto.getCor() + ";\n" + "     -fx-background-radius" +
                ": 30");
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
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
        produtosAdicionados.put(produtoAtual, quantidade);
        System.out.println("produtosAdicionados = " + produtosAdicionados);
    }

    public String getTextFieldQuantidade() {
        return textFieldQuantidade.getText();
    }

    @FXML
    public Double calcularValorTotal(MouseEvent event) {
        double totalPagar = 0;
        for(Map.Entry<Produto, Integer> entry : produtosAdicionados.entrySet()) {
           totalPagar +=  entry.getKey().getPreçoProduto() * entry.getValue();
        }
        valorTotalCompra.setText(String.valueOf(totalPagar));
        return totalPagar;
    }

    @FXML
    public double valorTotalProduto(KeyEvent key) {
        double valorTotalizado = 0;
        if(getTextFieldQuantidade().isBlank()){
            precoTotal.setText("");
        }
        else {
            String text = getTextFieldQuantidade();
            int quantidade = Integer.parseInt(text);
            System.out.println("Valor total: " + produtoAtual.getPreçoProduto() * quantidade);
            precoTotal.setText(String.valueOf(produtoAtual.getPreçoProduto() * quantidade));
            valorTotalizado = produtoAtual.getPreçoProduto() * quantidade;
        }
        return valorTotalizado;

    }
}

