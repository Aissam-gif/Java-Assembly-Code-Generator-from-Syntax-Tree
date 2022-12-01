module com.tree.compilationproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.tree.compilationproject to javafx.fxml;
    exports com.tree.compilationproject;
}