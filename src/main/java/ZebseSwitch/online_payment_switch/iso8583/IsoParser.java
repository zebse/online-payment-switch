package ZebseSwitch.online_payment_switch.iso8583;

import ZebseSwitch.online_payment_switch.service.AuthorizationService;
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

    public IsoParser(BitmapUtil bitmapUtil,
                     IsoSpecification isoSpecification,
                     FieldReader fieldReader) {

        this.bitmapUtil = bitmapUtil;
        this.isoSpecification = isoSpecification;
        this.fieldReader = fieldReader;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(
                    IsoParser.class);

    public IsoMessage parse(String rawMessage) {

        IsoMessage message = new IsoMessage();

        // Step 1
        String mti = rawMessage.substring(0, 4);
        message.setMti(mti);
        logger.debug(
                "MTI={}",
                message.getMti());

        // Step 2
        String bitmapHex = rawMessage.substring(4, 20);
        logger.debug(
                "Bitmap={}",
                bitmapHex);

        // Step 3
        List<Integer> fields =
                bitmapUtil.extractFields(bitmapHex);
        logger.debug(
                "Fields Present={}",
                fields);
        // Step 4
        int position = 20;

        // Step 5
        for (Integer fieldNumber : fields) {
            FieldDefinition definition =
                    isoSpecification.getDefinition(fieldNumber);
            ReadResult result =
                    fieldReader.read(
                            rawMessage,
                            position,
                            definition);

            message.setField(fieldNumber,
                    result.getValue());
            String value = result.getValue();
            if(fieldNumber == 2){
                value = MaskingUtil.maskPan(value);
            }
            logger.debug("DE{}={}", fieldNumber, value);

            position += result.getConsumedCharacters();
        }

        return message;
    }
}