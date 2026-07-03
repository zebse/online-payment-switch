package ZebseSwitch.online_payment_switch.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public final class TransactionIdGenerator {

    private static final AtomicLong SEQUENCE = new AtomicLong(1);

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private TransactionIdGenerator() {
    }

    public static String generate() {

        String timestamp =
                LocalDateTime.now().format(FORMATTER);

        long sequence = SEQUENCE.getAndIncrement();

        return timestamp + String.format("%06d", sequence);
    }

}