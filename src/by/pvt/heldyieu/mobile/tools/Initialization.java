/**
 * 
 */
package by.pvt.heldyieu.mobile.tools;

import java.util.HashMap;
import java.util.Map;
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
		Map<Integer, MobileTariff> tariffs = new HashMap<Integer, MobileTariff>();
		try {
			new LimitedInternetTariff("Лимитируемые интернет", 10.5, 4.5);
			new UnlimitedInternetTariff("Нелимитируемый интернет", 11.5, 5.6);
			new LimitedCallsTariff("Лимитируемые звонки", 9.5, 2.5);
			new UnlimitedCallsTariff("Нелимитируемые звонки", 13.5, 5.6);
		} catch (InvalidValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		tariffs = MobileTariff.getTariffs();
		int sum=0;
		Random rand = new Random();
		for (int i = 0; i < tariffs.size(); i++) {
			tariffs.get(i).subscribe("MP"+(rand.nextInt(8999999)+1000000), "Толя", "Толивич");
			tariffs.get(i).subscribe("MP"+(rand.nextInt(8999999)+1000000), "Коля", "Коливоич");
			tariffs.get(i).subscribe("MP"+(rand.nextInt(8999999)+1000000), "Валера", "Носовеов");
			tariffs.get(i).subscribe("MP"+(rand.nextInt(8999999)+1000000), "Катя", "Таня");
			sum+=tariffs.get(i).getClientsNumbers();
			System.out.println(tariffs.get(i).getClients().toString());
		}
		System.out.println(sum);
		System.out.println(MobileTariff.getAbonementPriceSort().toString());
	}
}