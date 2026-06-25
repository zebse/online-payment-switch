package ZebseSwitch.online_payment_switch.iso8583;

import java.util.HashMap;
import java.util.Map;

public class IsoMessage {

    private String mti;

    private final Map<Integer, String> fields = new HashMap<>();

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public void setField(int field, String value) {
        fields.put(field, value);
    }

    public String getField(int field) {
        return fields.get(field);
    }

    public boolean hasField(int field) {
        return fields.containsKey(field);
    }

    public Map<Integer, String> getFields() {
        return fields;
    }
}