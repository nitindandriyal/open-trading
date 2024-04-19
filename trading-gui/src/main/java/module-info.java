module open.trading.tradinggui {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
                requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
    requires binance.connector.java;
    requires org.agrona.core;
    requires com.google.gson;

    opens open.trading.tradinggui to com.google.gson, javafx.fxml;
    exports open.trading.tradinggui;
    exports open.trading.tradinggui.widget;
    opens open.trading.tradinggui.widget to javafx.fxml;
}
