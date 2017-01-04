/**
 * 
 */
package by.pvt.heldyieu.mobile.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import by.pvt.heldyieu.mobile.beans.interfaces.Constants;
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
public final class Initialization implements Constants {
	private static File file;
	private static Map<Integer, MobileTariff> mapOfTariffs = new HashMap<Integer, MobileTariff>();

	// public static void main(String[] args) {
	// try {
	// new LimitedInternetTariff("Лимитируемые интернет", 30.5, 4.5);
	// new UnlimitedInternetTariff("Нелимитируемый интернет", 41.5, 5.6);
	// new LimitedCallsTariff("Лимитируемые звонки", 19.5, 2.5);
	// new UnlimitedCallsTariff("Нелимитируемые звонки", 16.5, 5.6);
	// } catch (InvalidValueException e) {
	// // TODO Auto-generated catch block
	// System.out.println(e.getMessage());
	// }
	// MobileTariff[] tariffs = MobileTariff.getTariffs();
	// int sum=0;
	// Random rand = new Random();
	// for (int i = 0; i < tariffs.length; i++) {
	// tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Толя",
	// "Толивич");
	// tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Коля",
	// "Коливоич");
	// tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Валера",
	// "Носовеов");
	// tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Катя",
	// "Таня");
	// sum+=tariffs[i].getClientsNumbers();
	// System.out.println(tariffs[i].getTariffName()+":");
	// tariffs[i].printClients();
	// System.out.println(tariffs[i].toString());
	// System.out.println("=============================================");
	// }
	//
	// System.out.println(sum);
	// System.out.println(MobileTariff.getAbonementPriceSort().toString());
	// MobileTariff.getDesireTariff(tariffs, 25).forEach(System.out::println);;
	//
	// Manager manager = new Manager("Karl", "Karlovich",new
	// GregorianCalendar(1989,11,23),Category.TOPLEVEL);
	// System.out.println(manager.toString());
	// GregorianCalendar date = new GregorianCalendar(1970, 5, 12);
	// System.out.println(date.before(manager.getBirthday()));
	// }

	public static void initialize() {

		// Create new entities for LimitedCallsTariff tariffs from input files
		file = new File(INPUT_FOLDER + LIMITED_CALLS_TARIFF);
		createTariffs(file, LIMITED_CALLS_TARIFF);
		
		// Create new entities for LimitedCallsTariff tariffs from input files
		file = new File(INPUT_FOLDER + LIMITED_INERNET_TARIFF);
		createTariffs(file, LIMITED_INERNET_TARIFF);
		
		// Create new entities for LimitedCallsTariff tariffs from input files
		file = new File(INPUT_FOLDER + UNLIMITED_CALLS_TARIFF);
		createTariffs(file, UNLIMITED_CALLS_TARIFF);
		
		// Create new entities for LimitedCallsTariff tariffs from input files
		file = new File(INPUT_FOLDER + UNLIMITED_INERNET_TARIFF);
		createTariffs(file, UNLIMITED_INERNET_TARIFF);
		
		mapOfTariffs = MobileTariff.getTariffs();
		//print all tariffs
		Operations.printTariffs(mapOfTariffs);

	}

	

	private static void createTariffs(File file, String typeTariff) {
		List<String> strings = new ArrayList<>();
		strings = Operations.readFile(strings, file);
		Iterator<String> itStrings = strings.iterator();
		while (itStrings.hasNext()) {
			List<String> tariffTokens = Operations.stringToTokens(itStrings
					.next());
			Iterator<String> itTokens = tariffTokens.iterator();
			while (itTokens.hasNext()) {
				switch (typeTariff) {
				case LIMITED_CALLS_TARIFF:
					createLimitedCallsTariff(itTokens);
					break;
				case LIMITED_INERNET_TARIFF:
					createLimitedInternetTariff(itTokens);
					break;
				case UNLIMITED_CALLS_TARIFF:
					createUnlimitedCallsTariff(itTokens);
					break;
				case UNLIMITED_INERNET_TARIFF:
					createUnlimitedInternetTariff(itTokens);
					break;
				default:
					break;
				}
			}
		}
	}

	private static void createLimitedCallsTariff(Iterator<String> itTokens) {
		try {
			new LimitedCallsTariff(itTokens.next(),
					Double.parseDouble(itTokens.next()),
					Double.parseDouble(itTokens.next()));
		} catch (NumberFormatException | InvalidValueException e) {
			Logger.log(e);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			Logger.log(e);
		}
	}
	
	private static void createLimitedInternetTariff(Iterator<String> itTokens) {
		try {
			new LimitedInternetTariff(itTokens.next(),
					Double.parseDouble(itTokens.next()),
					Double.parseDouble(itTokens.next()));
		} catch (NumberFormatException | InvalidValueException e) {
			Logger.log(e);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			Logger.log(e);
		}
	}
	
	private static void createUnlimitedCallsTariff(Iterator<String> itTokens) {
		try {
			new UnlimitedCallsTariff(itTokens.next(),
					Double.parseDouble(itTokens.next()),
					Double.parseDouble(itTokens.next()));
		} catch (NumberFormatException | InvalidValueException e) {
			Logger.log(e);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			Logger.log(e);
		}
	}
	
	private static void createUnlimitedInternetTariff(Iterator<String> itTokens) {
		try {
			new UnlimitedInternetTariff(itTokens.next(),
					Double.parseDouble(itTokens.next()),
					Double.parseDouble(itTokens.next()));
		} catch (NumberFormatException | InvalidValueException e) {
			Logger.log(e);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			Logger.log(e);
		}
	}
}