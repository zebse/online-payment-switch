package ZebseSwitch.online_payment_switch.iso8583;

import org.springframework.stereotype.Component;

import java.beans.FeatureDescriptor;
import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================
 * Never copy and paste code without understanding it.
 *
 * This class represents our supported ISO8583 specification.
 *
 * The parser asks this class how each Data Element should
 * be interpreted.
 * ============================================================
 */

@Component
public class IsoSpecification {


    private final Map<Integer, FieldDefinition> definitions =
            new HashMap<>();

    public boolean contains(int field) {

        return definitions.containsKey(field);

    }

    public IsoSpecification() {

        register(2,
                "Primary Account Number",
                FieldType.LLVAR,
                19);

        register(3,
                "Processing Code",
                FieldType.FIXED,
                6);

        register(4,
                "Transaction Amount",
                FieldType.FIXED,
                12);

        register(7,
                "Transmission Date & Time",
                FieldType.FIXED,
                10);

        register(11,
                "System Trace Audit Number",
                FieldType.FIXED,
                6);

        register(12,
                "Local Transaction Time",
                FieldType.FIXED,
                6);

        register(13,
                "Local Transaction Date",
                FieldType.FIXED,
                4);

        register(14,
                "Expiry Date",
                FieldType.FIXED,
                4);

        register(22,
                "POS Entry Mode",
                FieldType.FIXED,
                3);

        register(25,
                "POS Condition Code",
                FieldType.FIXED,
                2);

        register(32,
                "Acquiring Institution ID",
                FieldType.LLVAR,
                11);

        register(35,
                "Track 2 Data",
                FieldType.LLVAR,
                37);

        register(37,
                "Retrieval Reference Number",
                FieldType.FIXED,
                12);
        register(38,
                "Approval code",
                FieldType.FIXED,
                6);
        register(39,
                "Response code",
                FieldType.FIXED,
                2);

        register(41,
                "Terminal ID",
                FieldType.FIXED,
                8);

        register(42,
                "Merchant ID",
                FieldType.FIXED,
                15);

        register(48,
                "Additional Data - Private",
                FieldType.LLLVAR,
                999);
    }

    public FieldDefinition getDefinition(int field) {

        return definitions.get(field);

    }
    private void register(
            int number,
            String name,
            FieldType type,
            int length) {

        if (definitions.containsKey(number)) {

            throw new IllegalStateException(
                    "Duplicate definition for DE"
                            + number);

        }

        definitions.put(
                number,
                new FieldDefinition(
                        number,
                        name,
                        type,
                        length));
    }

}