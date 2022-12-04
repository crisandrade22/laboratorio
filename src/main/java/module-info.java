module gov.sp.fatec.laboratorio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens gov.sp.fatec.laboratorio.main to javafx.fxml;
    exports gov.sp.fatec.laboratorio.main;
    opens gov.sp.fatec.laboratorio.model to javafx.fxml;
    exports gov.sp.fatec.laboratorio.model;

}