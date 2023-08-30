module open.trading.tradinggui {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
                requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
        
    opens open.trading.tradinggui to javafx.fxml;
    exports open.trading.tradinggui;
    exports open.trading.tradinggui.widget;
    opens open.trading.tradinggui.widget to javafx.fxml;
}