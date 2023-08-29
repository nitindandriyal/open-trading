package open.trading.tradinggui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TradingGuiApplication extends Application {
    public static final String BIG_BUTTON_CSS = """
                -fx-background-color:
                     linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),
                     linear-gradient(#020b02, #3a3a3a),
                     linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),
                     linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),
                     linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);
                 -fx-border-width: 0;
                 -fx-background-insets: 0,1,4,5,6;
                 -fx-background-radius: 9,8,5,4,3;
                 -fx-padding: 15 30 15 30;
                 -fx-font-family: "Segoe UI";
                 -fx-font-size: 48px;
                 -fx-font-weight: bold;
                 -fx-text-fill: #f2f2f2;
                 -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);
                 -fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );
            """;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TradingGuiApplication.class.getResource("hello-view.fxml"));

        TilePane tilePane = new TilePane();
        tilePane.setStyle("""
                -fx-background-color: #202020;
                """);

        HBox hbox1 = createTile("EURUSD");

        HBox hbox2 = createTile("GBPUSD");

        tilePane.getChildren().add(hbox1);
        tilePane.getChildren().add(hbox2);

        tilePane.setHgap(3);
        tilePane.setVgap(3);
        tilePane.setPadding(new Insets(20));

        Scene scene = new Scene(tilePane);
        stage.setTitle("Open FX Trading");
        stage.setScene(scene);
        stage.show();
    }

    private static HBox createTile(String ccyPair) {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #565656");
        hbox.setPadding(new Insets(2));

        VBox sell = new VBox();
        Button bid = new Button("1.10");
        bid.setPrefSize(160, 120);
        bid.setStyle(BIG_BUTTON_CSS);

        VBox buy = new VBox();
        Button ask = new Button("1.10");
        ask.setPrefSize(160, 120);
        ask.setStyle(BIG_BUTTON_CSS);

        Label label = new Label(ccyPair);
        label.setPadding(new Insets(2, 2, 2, 5));
        label.setStyle("""                 
                -fx-font-family: "Segoe UI";
                -fx-font-weight: bold;
                -fx-text-fill: #03fcdb;
                """);

        sell.getChildren().add(label);
        sell.getChildren().add(bid);

        buy.getChildren().add(label);
        buy.getChildren().add(ask);

        hbox.getChildren().add(sell);
        hbox.getChildren().add(buy);
        return hbox;
    }

    public static void main(String[] args) {
        launch();
    }
}