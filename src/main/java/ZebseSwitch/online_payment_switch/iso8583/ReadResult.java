package ZebseSwitch.online_payment_switch.iso8583;

/*
 * ============================================================
 * Never copy and paste code without understanding it.
 *
 * Why don't we return only the field value?
 * What else does the parser need to continue reading?
 * ============================================================
 */

public class ReadResult {

    private final String value;

    private final int consumedCharacters;

    public ReadResult(String value, int consumedCharacters) {
        this.value = value;
        this.consumedCharacters = consumedCharacters;
    }

    public String getValue() {
        return value;
    }

    public int getConsumedCharacters() {
        return consumedCharacters;
    }

}