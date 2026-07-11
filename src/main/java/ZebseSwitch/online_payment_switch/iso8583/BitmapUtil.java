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

import ZebseSwitch.online_payment_switch.iso8583.exception.IsoParsingException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BitmapUtil {


    public String hexToBinary(String hex) {

        StringBuilder binary = new StringBuilder();

        for (char c : hex.toCharArray()) {

            try {

                binary.append(
                        String.format("%4s",
                                        Integer.toBinaryString(
                                                Integer.parseInt(
                                                        String.valueOf(c),16)))
                                .replace(' ','0'));

            } catch (NumberFormatException ex) {

                throw new IsoParsingException(
                        String.format(
                        "Invalid bitmap hexadecimal character : " + c,
                        ex));

            }
        }

        return binary.toString();
    }

    public List<Integer> extractFields(String bitmapHex) {

        String binary = hexToBinary(bitmapHex);

        List<Integer> fields = new ArrayList<>();
        if (bitmapHex.length() % 16 != 0) {

            throw new IsoParsingException(
                    "Invalid bitmap length");

        }

        for (int i = 1; i < binary.length(); i++) {

            if (binary.charAt(i) == '1') {

                fields.add(i + 1);

            }

        }

        return fields;
    }

    public BitmapParseResult parse(String rawMessage) {

        // MTI occupies the first 4 characters
        int position = 4;

        // Read Primary Bitmap
        if (rawMessage.length() < 20) {

            throw new IsoParsingException(
                    "ISO message is too short to contain MTI and Primary Bitmap");

        }
        String primaryBitmap =
                rawMessage.substring(position, position + 16);

        position += 16;

        String bitmapHex = primaryBitmap;

        // Check whether a secondary bitmap exists
        if (position + 16 > rawMessage.length()) {

            throw new IsoParsingException(
                    "Secondary bitmap expected but message ended");

        }
        String binaryPrimary =
                hexToBinary(primaryBitmap);

        if (binaryPrimary.charAt(0) == '1') {

            String secondaryBitmap =
                    rawMessage.substring(position, position + 16);

            bitmapHex += secondaryBitmap;

            position += 16;
        }

        List<Integer> fields =
                extractFields(bitmapHex);

        return new BitmapParseResult(
                bitmapHex,
                fields,
                position);
    }

    public String binaryToHex(String binary) {

        if (binary.length() % 4 != 0) {
            throw new IsoParsingException("Binary bitmap length must be a multiple of 4");
        }

        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < binary.length(); i += 4) {
            String nibble = binary.substring(i, i + 4);
            hex.append(Integer.toHexString(Integer.parseInt(nibble, 2)).toUpperCase());
        }

        return hex.toString();
    }

    public String build(java.util.Set<Integer> fields) {

        boolean hasSecondary = fields.stream().anyMatch(f -> f > 64);
        int bitLength = hasSecondary ? 128 : 64;

        char[] binary = new char[bitLength];
        java.util.Arrays.fill(binary, '0');

        if (hasSecondary) {
            binary[0] = '1'; // bit 1 signals a secondary bitmap follows
        }

        for (Integer field : fields) {

            if (field < 2 || field > 128) {
                throw new IsoParsingException("Field number out of range for bitmap: " + field);
            }

            binary[field - 1] = '1';
        }

        return binaryToHex(new String(binary));
    }

}