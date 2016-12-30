/**
 * 
 */
package by.pvt.heldyieu.mobile.tools;

import java.util.Random;

import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.LimitedCallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.UnlimitedCallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.LimitedInternetTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.UnlimitedInternetTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;




/**
 * @author HeroDishonest
 *
 */
public final class Initialization {
	public static void main(String[] args) {
		MobileTariff [] tariffs = new MobileTariff[MobileTariff.getTariffs().length];
		try {
			new LimitedInternetTariff("Лимитируемые интернет", 30.5, 4.5);
			new UnlimitedInternetTariff("Нелимитируемый интернет", 41.5, 5.6);
			new LimitedCallsTariff("Лимитируемые звонки", 19.5, 2.5);
			new UnlimitedCallsTariff("Нелимитируемые звонки", 16.5, 5.6);
		} catch (InvalidValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		tariffs = MobileTariff.getTariffs();
		int sum=0;
		Random rand = new Random();
		for (int i = 0; i < tariffs.length; i++) {
			tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Толя", "Толивич");
			tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Коля", "Коливоич");
			tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Валера", "Носовеов");
			tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Катя", "Таня");
			sum+=tariffs[i].getClientsNumbers();
			System.out.println(tariffs[i].getTariffName()+":");
			tariffs[i].getClients();
			System.out.println(tariffs[i].toString());
			System.out.println("=============================================");
		}
		
		System.out.println(sum);
		System.out.println(MobileTariff.getAbonementPriceSort().toString());
		MobileTariff.getDesireTariff(tariffs, 25).forEach(System.out::println);;
		
	}
}