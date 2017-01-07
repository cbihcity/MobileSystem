package by.pvt.heldyieu.mobile.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import by.pvt.heldyieu.mobile.beans.interfaces.Constants;
import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.LimitedCallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.UnlimitedCallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.LimitedInternetTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.UnlimitedInternetTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

public class Operations implements Constants {
//private static Scanner input = new Scanner(System.in,"utf-8");
private static List<String> namesClients = new ArrayList<String>();
private static List<String> surnamesClients = new ArrayList<String>();
	
	/**
	 * Closed constructor <b>{@code Operations}</b>
	 */
	private Operations(){
	}
	
	/**
	 * Reads list of tariffs from file
	 * 
	 * @param file - input file
	 * @param list - list of tariffs for returning
	 */
	public static void readFile(List<String> list, File file) {
		try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
			while (sc.hasNext()) {
				list.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Файл не найден..." + file.getName()
					+ " Проверьте наличие этого файла в директории "
					+ INPUT_FOLDER);
			Logger.log(e);
		}
	}

	/**
	 * Splits string to tokens
	 * 
	 * @param str - string
	 * @return list of tokens
	 */
	public static List<String> stringToTokens(String str) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(str, ";");
		while (st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}
		return tokens;
	}

	/**
	 * Print all available tariffs at MobileTariff
	 * 
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void printTariffs(Map<Integer, MobileTariff> mapOfTariffs) {
		Set<Map.Entry<Integer, MobileTariff>> set = mapOfTariffs.entrySet();
		Iterator<Map.Entry<Integer, MobileTariff>> it = set.iterator();
		while (it.hasNext()) {
			MobileTariff temp = it.next().getValue();
			System.out.println(temp.toString());
		}
	}
	
	/**
	 * Read from file values of specific tariff and create its entites
	 * 
	 * @param file - input file with values for specific tariff
	 * @param typeTariff - specific type of tariff
	 */
	public static void createTariffs(File file, String typeTariff) {
		List<String> strings = new ArrayList<>();
		Operations.readFile(strings, file);
		Iterator<String> itStrings = strings.iterator();
		while (itStrings.hasNext()) {
			List<String> tariffTokens = Operations.stringToTokens(itStrings
					.next());
			Iterator<String> itTokens = tariffTokens.iterator();
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
	
	/**
	 * Create new LimitedCallsTariff with specific values
	 * 
	 * @param itTokens - list of parsed values for new tariff
	 */
	private static void createLimitedCallsTariff(Iterator<String> itTokens) {
		try {
			while (itTokens.hasNext()) {
				new LimitedCallsTariff(itTokens.next(),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()),
						Integer.parseInt(itTokens.next()));
			}
		} catch (InvalidValueException | IllegalArgumentException
				| NoSuchElementException e) {
			Logger.log(e);
			System.out
					.println("При создании объекта возникла ошибка. Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new LimitedCallsTariff with specific values
	 * 
	 * @param itTokens - list of parsed values for new tariff
	 */
	private static void createLimitedInternetTariff(Iterator<String> itTokens) {
		try {
			while (itTokens.hasNext()) {
				new LimitedInternetTariff(itTokens.next(),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()));
			}
		} catch (InvalidValueException | IllegalArgumentException
				| NoSuchElementException e) {
			Logger.log(e);
			System.out
					.println("При создании объекта возникла ошибка. Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new LimitedCallsTariff with specific values
	 * 
	 * @param itTokens - list of parsed values for new tariff
	 */
	private static void createUnlimitedCallsTariff(Iterator<String> itTokens) {
		try {
			while (itTokens.hasNext()) {
				new UnlimitedCallsTariff(itTokens.next(),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()),
						Integer.parseInt(itTokens.next()));
			}
		} catch (InvalidValueException | IllegalArgumentException
				| NoSuchElementException e) {
			Logger.log(e);
			System.out
					.println("При создании объекта возникла ошибка. Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new LimitedCallsTariff with specific values
	 * 
	 * @param itTokens - list of parsed values for new tariff
	 */
	private static void createUnlimitedInternetTariff(Iterator<String> itTokens) {
		try {
			while (itTokens.hasNext()) {
				new UnlimitedInternetTariff(itTokens.next(),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()));
			}
		} catch (InvalidValueException | IllegalArgumentException
				| NoSuchElementException e) {
			Logger.log(e);
			System.out
					.println("При создании объекта возникла ошибка. Подробное описание ошибки в файле log.txt");
		}
	}

	public static void createRandomSubscribers(File file, File file2, Map<Integer, MobileTariff> mapOfTariffs) {
		Operations.createListsNamesAndSurnames(file,file2);
		for (MobileTariff tariff : mapOfTariffs.values()) {
			Operations.subscribeForTariff(tariff);
		}
	}

	private static void createListsNamesAndSurnames(File file, File file2) {
		List<String> tempNames = new ArrayList<>();
		List<String> tempClients = new ArrayList<>();
		Operations.readFile(tempNames, file);
		Operations.readFile(tempClients, file2);
		
			Iterator<String> namesTokens = tempNames.iterator();
			Iterator<String> surnameTokens = tempClients.iterator();
			while (namesTokens.hasNext()) {
				namesClients = Operations.stringToTokens(namesTokens.next());
				}
			while (surnameTokens.hasNext()) {
				surnamesClients = Operations.stringToTokens(surnameTokens.next());
			}
	}
	
	private static void subscribeForTariff(MobileTariff tariff) {
		Random randomGenerator = new Random();
		String [] arrayNames = new String[namesClients.size()];
		arrayNames = namesClients.toArray(arrayNames);
		String [] arraySurnames = new String[surnamesClients.size()];
		arraySurnames = surnamesClients.toArray(arraySurnames);
		int randomNumberClients = randomGenerator.nextInt(50);
		try {
			for (int i = 0; i < randomNumberClients; i++) {
				tariff.subscribe(
						"MP" + (randomGenerator.nextInt(PASSPORT_VALUE_FOR_RANDOM) + 1000000),
						arraySurnames[randomGenerator.nextInt(arraySurnames.length)],
						arrayNames[randomGenerator.nextInt(arrayNames.length)]);
			}
		} catch (IllegalArgumentException e) {
			Logger.log(e);
			System.out.println("При создании списка абонентов для "	+ tariff.getTariffName()
							+ " возникла ошибка. Подробное описание ошибки в файле log.txt");
		}
	}
	
	public static void getClientsNumber(Map<Integer, MobileTariff> mapOfTariffs){
		int clientsNumber = 0; 
		for (MobileTariff tariff : mapOfTariffs.values()) {
			clientsNumber += tariff.getClientsNumbers();
		}
		System.out.println("Общая численность клиентов - "+clientsNumber);
	}
	
	/**
	 * Displays lists of tariffs sort by abonement price
	 * @param mapOfTariffs 	- list of available tariffs
	 * @param index 	- sorting index
	 */
	public static void sortServicesBasedOnAbonementPrice(Map<Integer, MobileTariff> mapOfTariffs){
		List<MobileTariff> list = new ArrayList<>(mapOfTariffs.values());
		Collections.sort(list, new ComparatorManager());
		reportAfterSort(list, mapOfTariffs);
	}
	
	/**
	 * Displays list
	 * @param list - list of sorted tariffs
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void reportAfterSort(List<? extends Object> list, Map<Integer, MobileTariff> mapOfTariffs){
		for(Object s : list){
			if(s instanceof MobileTariff){
				Set<Map.Entry<Integer, MobileTariff>> set = mapOfTariffs.entrySet(); 
				Iterator<Map.Entry<Integer, MobileTariff>> it = set.iterator();
				while(it.hasNext()){
					Map.Entry<Integer, MobileTariff> entry = it.next();
					if(entry.getValue().equals((MobileTariff)s)){
						System.out.println(entry.getValue().toString());
						break;
					}
				}
			}
		}
	}
	
}
