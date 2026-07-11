package ZebseSwitch.online_payment_switch.iso8583;

import ZebseSwitch.online_payment_switch.iso8583.exception.IsoParsingException;
import ZebseSwitch.online_payment_switch.util.MaskingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;

/*
 * ============================================================
 * REAL ISO8583 PARSER (Bitmap Driven)
 * ============================================================
 */
@Component
public class IsoParser {

    private final BitmapUtil bitmapUtil;
    private final IsoSpecification isoSpecification;
    private final FieldReader fieldReader;

    private static final Logger logger = LoggerFactory.getLogger(IsoParser.class);

    public IsoParser(BitmapUtil bitmapUtil, IsoSpecification isoSpecification, FieldReader fieldReader) {
        this.bitmapUtil = bitmapUtil;
        this.isoSpecification = isoSpecification;
        this.fieldReader = fieldReader;
    }

    private String sanitizeField(int field, String value) {
        switch (field) {
            case 2:
                return MaskingUtil.maskPan(value);
            case 35:
                return "<TRACK2 MASKED>";
            case 52:
                return "<PIN BLOCK>";
            case 55:
                return "<EMV DATA>";
            default:
                return value;
        }
    }

    public IsoMessage parse(String rawMessage) {
        IsoMessage message = new IsoMessage();

        //Verify the message length
        if(rawMessage.length() < 20){

            throw new IsoParsingException(
                    "ISO message too short");

        }

        // Step 1: Extract MTI
        String mti = rawMessage.substring(0, 4);
        message.setMti(mti);
        logger.debug("MTI={}", message.getMti());

        // Step 2 & 3: Dynamic Bitmap Parsing (Supports Primary & Secondary)
        BitmapParseResult bitmapResult = bitmapUtil.parse(rawMessage);
        logger.debug("Bitmap={}", bitmapResult.getBitmapHex());
        logger.debug("Fields Present={}", bitmapResult.getFields());

        List<Integer> fields = bitmapResult.getFields();
        int position = bitmapResult.getNextPosition();

        // Step 5: Process active Data Elements sequentially
        for (Integer fieldNumber : fields) {
            FieldDefinition definition = isoSpecification.getDefinition(fieldNumber);
            if (definition == null) {
                throw new IsoParsingException(
                        String.format("Unsupported Data Element DE%d at position %d", fieldNumber, position),
                        message.getMti(),
                        fieldNumber,
                        position);
            }

            // Diagnostic context logging to capture data stream offsets
            logger.debug(
                    "Reading DE{} [{}]",
                    fieldNumber,
                    definition.getFieldName());

            ReadResult result;
            try {
                result = fieldReader.read(rawMessage, position, definition);
                // Keep raw unmasked data inside the message object for routing mapping validation
                message.setField(fieldNumber, result.getValue());
            } catch (Exception ex) {
                throw new IsoParsingException(
                        String.format("Failed to parse DE%d at position %d", fieldNumber, position),
                        message.getMti(),
                        fieldNumber,
                        position,
                        ex);
            }

            // Print beautifully masked values to your switch log dashboard safely
            logger.debug("DE{}={}", fieldNumber, sanitizeField(fieldNumber, result.getValue()));

            // Advance data pointer cleanly based on processed length rules
            position += result.getConsumedCharacters();
        }

        return message;
    }
}