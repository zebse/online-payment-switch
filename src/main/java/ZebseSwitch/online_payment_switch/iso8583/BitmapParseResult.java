package ZebseSwitch.online_payment_switch.iso8583;

import java.util.List;

/*
 * ============================================================
 * Holds the result of bitmap parsing.
 * ============================================================
 */

public class BitmapParseResult {

    private final String bitmapHex;
    private final List<Integer> fields;
    private final int nextPosition;

    public BitmapParseResult(
            String bitmapHex,
            List<Integer> fields,
            int nextPosition) {

        this.bitmapHex = bitmapHex;
        this.fields = fields;
        this.nextPosition = nextPosition;
    }

    public String getBitmapHex() {
        return bitmapHex;
    }

    public List<Integer> getFields() {
        return fields;
    }

    public int getNextPosition() {
        return nextPosition;
    }
}