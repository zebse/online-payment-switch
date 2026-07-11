package ZebseSwitch.online_payment_switch.exception;

import ZebseSwitch.online_payment_switch.iso8583.IsoMessage;
import ZebseSwitch.online_payment_switch.iso8583.IsoPacker;
import ZebseSwitch.online_payment_switch.iso8583.exception.IsoParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalIsoExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalIsoExceptionHandler.class);

    private final IsoPacker isoPacker;

    public GlobalIsoExceptionHandler(IsoPacker isoPacker) {
        this.isoPacker = isoPacker;
    }

    @ExceptionHandler(IsoParsingException.class)
    public String handleIsoParsingException(
            IsoParsingException ex) {

        logger.error(
                "ISO Parsing Error | MTI={} | DE={} | Position={} | {}",
                ex.getMti(),
                ex.getFieldNumber(),
                ex.getPosition(),
                ex.getMessage());

        IsoMessage response = new IsoMessage();

        response.setMti(buildResponseMti(ex.getMti()));

        response.setField(39, "30");

        return isoPacker.pack(response);
    }

    private String buildResponseMti(String requestMti) {

        if (requestMti == null || requestMti.length() != 4) {
            return "0210";
        }

        char[] chars = requestMti.toCharArray();

        chars[2] = '1';

        return new String(chars);
    }
}