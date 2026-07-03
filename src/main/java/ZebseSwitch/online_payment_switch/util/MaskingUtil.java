package ZebseSwitch.online_payment_switch.util;

public final class MaskingUtil {

    private MaskingUtil() {
    }

    public static String maskPan(String pan) {

        if (pan == null || pan.length() < 10) {
            return "INVALID";
        }

        return pan.substring(0, 6)
                + "******"
                + pan.substring(pan.length() - 4);

    }

}