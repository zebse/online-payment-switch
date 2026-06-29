package ZebseSwitch.online_payment_switch.iso8583;

/*
 * ============================================================
 * STOP ✋
 *
 * Never copy and paste code without understanding it.
 *
 * Ask yourself:
 * 1. Why does this class exist?
 * 2. Why is bitmap represented in hexadecimal?
 * 3. Why do we need binary before parsing Data Elements?
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.List;

public class BitmapUtil {
    public static String hexToBinary(String hexBitmap) {

        StringBuilder binary = new StringBuilder();

        for (char hexChar : hexBitmap.toCharArray()) {

            int decimal = Integer.parseInt(String.valueOf(hexChar), 16);

            String fourBits = String.format("%4s",
                            Integer.toBinaryString(decimal))
                    .replace(' ', '0');

            binary.append(fourBits);
        }

        return binary.toString();
    }

    public static List<Integer> getPresentFields(String binaryBitmap) {

        List<Integer> fields = new ArrayList<>();

        // Start from index 1 (Bit 2)
        for (int i = 1; i < binaryBitmap.length(); i++) {

            if (binaryBitmap.charAt(i) == '1') {

                fields.add(i + 1);

            }

        }

        return fields;
    }
}