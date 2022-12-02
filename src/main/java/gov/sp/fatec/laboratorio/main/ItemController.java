package gov.sp.fatec.laboratorio.main;

import gov.sp.fatec.laboratorio.model.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;

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
        labelPreco.setText(Main.CURRENCY + String.format("%.2f", produto.getPrecoProduto()));
        File imageFile = new File(produto.getImagemSource());
        Image image= new Image(imageFile.toURI().toString());
        imageView.setImage(image);
    }

}
