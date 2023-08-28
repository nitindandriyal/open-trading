package open.trading.tradinggui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;

public class TradingGuiApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TradingGuiApplication.class.getResource("hello-view.fxml"));
        TilePane tilePane = new TilePane();
        Button bid = new Button("1.1");
        bid.setPrefSize(100, 80);
        Button ask = new Button("1.1");
        ask.setPrefSize(100, 80);
        tilePane.getChildren().add(bid);
        tilePane.getChildren().add(ask);
        tilePane.setHgap(3);
        tilePane.setVgap(3);
        tilePane.setPadding(new Insets(6));
        Scene scene = new Scene(tilePane);
        stage.setTitle("Open FX Trading");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}