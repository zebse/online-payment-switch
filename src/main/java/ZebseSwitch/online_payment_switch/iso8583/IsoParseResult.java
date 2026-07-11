package ZebseSwitch.online_payment_switch.iso8583;

public class IsoParseResult {

    private boolean success;
    private IsoMessage message;
    private String errorMessage;

    public static IsoParseResult ok(IsoMessage msg) {
        IsoParseResult r = new IsoParseResult();
        r.success = true;
        r.message = msg;
        return r;
    }

    public static IsoParseResult fail(String error) {
        IsoParseResult r = new IsoParseResult();
        r.success = false;
        r.errorMessage = error;
        return r;
    }

    public boolean isSuccess() { return success; }
    public IsoMessage getMessage() { return message; }
    public String getErrorMessage() { return errorMessage; }
}