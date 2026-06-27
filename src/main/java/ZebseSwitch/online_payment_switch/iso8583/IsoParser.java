package ZebseSwitch.online_payment_switch.iso8583;

/*
 * ============================================================
 *  STOP ✋
 *
 *  Never copy and paste code without understanding it.
 *
 *  Ask yourself:
 *  1. Why does this class exist?
 *  2. What problem does it solve?
 *  3. How does it fit into the payment switch?
 *  4. Could I explain this to another developer?
 *
 *  If the answer is NO, don't continue until you understand it.
 * ============================================================
 */
public class IsoParser {

    public IsoMessage parse(String rawMessage) {

        IsoMessage isoMessage = new IsoMessage();

        String[] tokens = rawMessage.split(";");
        //System.out.println("Received Message = " + rawMessage);
        for (String token : tokens) {

            String[] keyValue = token.split("=");

            if (keyValue.length != 2) {
                continue;
            }

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            if ("MTI".equalsIgnoreCase(key)) {

                isoMessage.setMti(value);

            } else {

                int fieldNumber = Integer.parseInt(key);

                isoMessage.setField(fieldNumber, value);
            }

        }

        return isoMessage;

    }
}