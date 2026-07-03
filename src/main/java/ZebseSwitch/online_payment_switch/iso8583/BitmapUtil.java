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

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BitmapUtil {


    public String hexToBinary(String hex) {

        StringBuilder binary = new StringBuilder();

        for (char c : hex.toCharArray()) {

            binary.append(
                    String.format("%4s",
                                    Integer.toBinaryString(
                                            Integer.parseInt(
                                                    String.valueOf(c), 16)))
                            .replace(' ', '0'));
        }

        return binary.toString();
    }

    public List<Integer> extractFields(String bitmapHex) {

        String binary = hexToBinary(bitmapHex);

        List<Integer> fields = new ArrayList<>();

        for (int i = 1; i < binary.length(); i++) {

            if (binary.charAt(i) == '1') {

                fields.add(i + 1);

            }

        }

        return fields;
    }

}