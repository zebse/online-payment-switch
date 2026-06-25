package ZebseSwitch.online_payment_switch.router;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

import ZebseSwitch.online_payment_switch.iso8583.IsoMessage;

public interface IsoMessageHandler {

    IsoMessage handle(IsoMessage message);

}