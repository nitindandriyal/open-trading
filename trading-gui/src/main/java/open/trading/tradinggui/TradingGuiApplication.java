package open.trading.tradinggui;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import open.trading.tradinggui.widget.BigTileFactory;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TradingGuiApplication extends Application {

    private final Gson gson = new Gson();

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

        SpotClient client = new SpotClientImpl(System.getProperty("binance.api.key"),
                System.getProperty("binance.api.secret"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        String result = client.createMargin().getAllIsolatedSymbols(parameters);
        Type listType = new TypeToken<List<Symbol>>() {
        }.getType();
        List<Symbol> symbols = gson.fromJson(result, listType);
        int counter = 0;
        for (Symbol symbol : symbols) {
            if (symbol.getSymbol().length() == 6 && (symbol.getSymbol().contains("BTC") || symbol.getSymbol().contains("ETH"))) {
                tilePane.getChildren().add(BigTileFactory.create(symbol.getSymbol()).getPane());
                if (counter++ == 16) {
                    break;
                }
            }
        }

        tilePane.setHgap(4);
        tilePane.setVgap(4);
        tilePane.setPadding(new Insets(20));

        fullBlotterContainer.getChildren().add(titleBar);
        fullBlotterContainer.getChildren().add(tilePane);

        stage.setTitle("Open FX Trading");
        stage.setScene(scene);
        stage.show();
    }

    private static class Symbol {
        private final String symbol;

        private Symbol(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

    }


    public static void main(String[] args) {
        launch();
    }
}