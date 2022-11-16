package gov.sp.fatec.laboratorio.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String CURRENCY = "R$";
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("listaCompras.fxml"));

        primaryStage.setTitle("Lista de Compras");
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

