package open.trading.tradinggui.widget;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.util.Random;

public class BigTile {
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
                 -fx-font-size: 36px;
                 -fx-font-weight: bold;
                 -fx-text-fill: #f2f2f2;
                 -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);
                 -fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );
            """;

    private final String instrument;
    private Label label;

    private Pane pane;

    private final Button bid;

    private final Button ask;

    BigTile(String instrument) {
        this.instrument = instrument;
        this.ask = new Button("1.10");
        this.bid = new Button("1.10");
        this.pane = createTile();
        DecimalFormat df = new DecimalFormat("0.00");
        Random random = new Random(100);

        Platform.runLater(() -> {
            double value = random.nextDouble();
            bid.setText(df.format(value));
        });
    }

    private Pane createTile() {
        HBox hbox = new HBox();
        hbox.setStyle("""
                -fx-background-color: #565656;
                """);
        hbox.setPadding(new Insets(2));

        bid.setPrefSize(160, 110);
        bid.setStyle(BIG_BUTTON_CSS);

        ask.setPrefSize(160, 110);
        ask.setStyle(BIG_BUTTON_CSS);

        label = new Label(this.instrument);
        label.setPadding(new Insets(4, 4, 4, 6));
        label.setStyle("""                 
                -fx-font-family: "Segoe UI";
                -fx-font-weight: bold;
                -fx-text-fill: #03fcdb;
                """);

        hbox.getChildren().add(bid);
        hbox.getChildren().add(ask);

        VBox all = new VBox(label, hbox);
        all.setStyle("""
                -fx-background-color: #565656;
                -fx-background-insets: 0,1,4,5,6;
                -fx-background-radius: 9,8,5,4,3;
                """);
        return all;
    }

    public Pane getPane() {
        return pane;
    }
}
