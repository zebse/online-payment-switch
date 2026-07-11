package ZebseSwitch.online_payment_switch.iso8583.exception;

public class IsoParsingException extends RuntimeException {

    private final String mti;
    private final int fieldNumber;
    private final int position;


    // Simple error
    public IsoParsingException(String message) {

        super(message);

        this.mti = null;
        this.fieldNumber = -1;
        this.position = -1;
    }


    // Detailed ISO parsing error
    public IsoParsingException(
            String message,
            String mti,
            int fieldNumber,
            int position) {

        super(message);

        this.mti = mti;
        this.fieldNumber = fieldNumber;
        this.position = position;
    }


    // Detailed error with original exception
    public IsoParsingException(
            String message,
            String mti,
            int fieldNumber,
            int position,
            Throwable cause) {

        super(message, cause);

        this.mti = mti;
        this.fieldNumber = fieldNumber;
        this.position = position;
    }


    public String getMti() {
        return mti;
    }


    public int getFieldNumber() {
        return fieldNumber;
    }


    public int getPosition() {
        return position;
    }
}