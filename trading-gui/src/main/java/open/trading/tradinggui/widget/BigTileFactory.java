package open.trading.tradinggui.widget;

public class BigTileFactory {
    public static BigTile create(String instrument) {
        return new BigTile(instrument);
    }
}
