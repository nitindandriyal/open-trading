package open.trading.tradinggui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import open.trading.tradinggui.widget.BigTileFactory;

import java.io.IOException;

public class TradingGuiApplication extends Application {


    @Override
    public void start(Stage stage) throws InterruptedException {
        System.setProperty("prism.lcdtext", "false");
        VBox fullBlotterContainer = new VBox();
        Scene scene = new Scene(fullBlotterContainer);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@700&display=swap");
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Merriweather:wght@700");
        TilePane tilePane = new TilePane();
        tilePane.setStyle("""
                -fx-background-color: #191038;
                """);

        Label label = new Label("Open Trading Blotter");
        label.setStyle("""
            -fx-font-family: 'Roboto Condensed';
            -fx-font-size: 36px;
            -fx-text-fill: #d6d6d6;
            -fx-font-smoothing-type: gray;
        """);
        label.setPadding(new Insets(5));
        HBox titleBar = new HBox(label);
        titleBar.setStyle("""
                -fx-background-color: #191038;
                """);
        titleBar.setPadding(new Insets(16));

        tilePane.getChildren().add(titleBar);
        tilePane.getChildren().add(BigTileFactory.create("EURUSD").getPane());
        tilePane.getChildren().add(BigTileFactory.create("GBPUSD").getPane());
        tilePane.getChildren().add(BigTileFactory.create("EURJPY").getPane());
        tilePane.getChildren().add(BigTileFactory.create("USDCAD").getPane());
        tilePane.getChildren().add(BigTileFactory.create("EURAUD").getPane());
        tilePane.getChildren().add(BigTileFactory.create("USDJPY").getPane());
        tilePane.getChildren().add(BigTileFactory.create("EURDKK").getPane());
        tilePane.getChildren().add(BigTileFactory.create("EURSEK").getPane());
        tilePane.getChildren().add(BigTileFactory.create("EURNOK").getPane());

        tilePane.setHgap(4);
        tilePane.setVgap(4);
        tilePane.setPadding(new Insets(20));

        fullBlotterContainer.getChildren().add(titleBar);
        fullBlotterContainer.getChildren().add(tilePane);

        stage.setTitle("Open FX Trading");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}