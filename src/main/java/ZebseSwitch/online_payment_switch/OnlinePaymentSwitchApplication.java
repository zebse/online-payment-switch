package ZebseSwitch.online_payment_switch;

import ZebseSwitch.online_payment_switch.iso8583.BitmapUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlinePaymentSwitchApplication {

	public static void main(String[] args) {

		SpringApplication.run(OnlinePaymentSwitchApplication.class, args);
		String binary =
				BitmapUtil.hexToBinary("F23C800000000000");

		System.out.println(binary);

		System.out.println(
				BitmapUtil.getPresentFields(binary)
		);
	}

}
