package ZebseSwitch.online_payment_switch.iso8583;

import java.util.Map;
import java.util.TreeMap;

public class IsoPacker {

    public String pack(IsoMessage message) {

        StringBuilder builder = new StringBuilder();

        builder.append("MTI=")
                .append(message.getMti());

        Map<Integer, String> sortedFields =
                new TreeMap<>(message.getFields());

        for (Map.Entry<Integer, String> entry : sortedFields.entrySet()) {

            builder.append(";")
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());

        }

        return builder.toString();

    }

}