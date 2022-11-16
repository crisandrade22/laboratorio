package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ItemController {
    @FXML
    private Label labelItem;

    @FXML
    private Label labelPreco;

    @FXML
    private ImageView imageView;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(produto);
    }

    private Produto produto;
    private MyListener myListener;

    public void setData(Produto produto, MyListener myListener) {
        this.produto = produto;
        this.myListener = myListener;
        labelItem.setText(produto.getNomeProduto());
        labelPreco.setText(Main.CURRENCY + produto.getPreçoProduto());
        Image image = new Image(getClass().getResourceAsStream(produto.getImagemSource()));
        imageView.setImage(image);
    }

}
