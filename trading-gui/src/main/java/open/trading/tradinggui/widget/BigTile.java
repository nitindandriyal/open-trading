package open.trading.tradinggui.widget;

import com.binance.connector.client.WebSocketStreamClient;
import com.binance.connector.client.impl.WebSocketStreamClientImpl;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

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
                 -fx-padding: 20 30 20 30;
                 -fx-font-family: 'Roboto Condensed';
                 -fx-font-size: 32px;
                 -fx-font-weight: bold;
                 -fx-text-fill: #f2f2f2;
                 -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);
                 -fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );
            """;

    private final String instrument;
    private final Pane pane;
    private final Button bid;
    private final Button ask;
    private final UnsafeBuffer unsafeBuffer = new UnsafeBuffer(ByteBuffer.allocateDirect(128));

    BigTile(String instrument) {
        this.instrument = instrument;
        this.ask = new Button();
        this.bid = new Button();
        this.pane = createTile();

        WebSocketStreamClient wsStreamClient = new WebSocketStreamClientImpl();
        wsStreamClient.bookTicker(instrument, ((event) -> {
            unsafeBuffer.wrap(event.getBytes());
            int i = 5;
            StringBuffer bidBuffer = new StringBuffer();
            StringBuffer askBuffer = new StringBuffer();
            while (i < event.length()) {
                if (unsafeBuffer.getByte(i) == '"'
                        && unsafeBuffer.getByte(i + 1) == 'b'
                        && unsafeBuffer.getByte(i + 2) == '"') {
                    i+=5;
                    while (i < event.length()) {
                        if (unsafeBuffer.getByte(i) == '"'
                                && unsafeBuffer.getByte(i + 1) == ','
                                && unsafeBuffer.getByte(i + 2) == '"') {
                            i+=5;
                            break;
                        }
                        bidBuffer.append((char)unsafeBuffer.getByte(i));
                        i++;
                    }
                    break;
                }

                i++;
            }

            while (i < event.length()) {
                if (unsafeBuffer.getByte(i) == '"'
                        && unsafeBuffer.getByte(i + 1) == 'a'
                        && unsafeBuffer.getByte(i + 2) == '"') {
                    i+=5;
                    while (i < event.length()) {
                        if (unsafeBuffer.getByte(i) == '"'
                                && unsafeBuffer.getByte(i + 1) == ','
                                && unsafeBuffer.getByte(i + 2) == '"') {
                            break;
                        }
                        askBuffer.append((char)unsafeBuffer.getByte(i));
                        i++;
                    }
                    break;
                }
                i++;
            }

            Platform.runLater(() -> {
                bid.setText(bidBuffer.toString());
                ask.setText(askBuffer.toString());
            });
        }));


    }

    private Pane createTile() {
        HBox hbox = new HBox();
        hbox.setStyle("""
                -fx-background-color: #292929;
                """);
        hbox.setPadding(new Insets(2));

        bid.setPrefSize(300, 110);
        bid.setStyle(BIG_BUTTON_CSS);

        ask.setPrefSize(300, 110);
        ask.setStyle(BIG_BUTTON_CSS);

        Label label = new Label(this.instrument);
        label.setPadding(new Insets(4, 8, 4, 6));
        label.setStyle("""                 
                -fx-font-family: 'Roboto Condensed';
                -fx-font-weight: bold;
                -fx-text-fill: #94fc49;
                -fx-font-size: 16px;
                """);

        hbox.getChildren().add(bid);
        hbox.getChildren().add(ask);

        VBox all = new VBox(label, hbox);
        all.setStyle("""
                -fx-background-color: #292929;
                -fx-background-insets: 0,1,4,5,6;
                -fx-background-radius: 9,8,5,4,3;
                -fx-effect: dropshadow(gaussian, gray, 8, 0, 0, 1);
                """);
        return all;
    }

    public Pane getPane() {
        return pane;
    }
}
