package by.pvt.heldyieu.mobile.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import by.pvt.heldyieu.mobile.beans.interfaces.Constants;
import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.CallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.LimitedCallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.UnlimitedCallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.InternetTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.LimitedInternetTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.UnlimitedInternetTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

public class Operations implements Constants {
	private static Scanner input = new Scanner(System.in, "utf-8");
	private static List<String> namesClients = new ArrayList<String>();
	private static List<String> surnamesClients = new ArrayList<String>();
	private static File filePathNames = new File(INPUT_FOLDER + CLIENTS_FILE_NAMES);
	private static File filePathSurnames = new File(INPUT_FOLDER + CLIENTS_FILE_SURNAMES);
	
	/**
	 * Closed constructor <b>{@code Operations}</b>
	 */
	private Operations(){
	}
	
	/**
	 * Reads list of tariffs from file
	 * @param file - input file
	 * @param list - list of tariffs for returning
	 * @throws FileNotFoundException 
	 */
	public static void readFile(List<String> list, File file) throws FileNotFoundException {
		BufferedReader br = null;
		String temp = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			while ((temp = br.readLine()) != null) {
				list.add(temp);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Файл не найден..." + file.getName()
					+ " Проверьте наличие этого файла в директории "
					+ INPUT_FOLDER);
			Logger.log(e);
			throw e;
		} catch (IOException e) {
			Logger.log(e);
			System.out.println("Произошла ошибка ввода-вывода" + file.getName()
					+ " Подробную информацию смотрите в файле log.txt");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				System.err.println("Ошибка закрыти потока ввода : " + e);
				Logger.log(e);
			}
		}
	}

	/**
	 * Splits string to tokens
	 * 
	 * @param str - string
	 * @return list of tokens
	 */
	public static List<String> stringParser(String str) {
		List<String> tokens = new ArrayList<>();
		String [] temp = str.split(";");
		for (String string : temp) {
			tokens.add(string);
		}
		return tokens;
	}
	
	/**
	 * Read input number from console
	 * @return number 
	 */
	public static int inputNumber() {
		int choice = 0;
		while (true) {
			try {
				input = new Scanner(System.in, "utf-8");
				choice = input.nextInt();
				if (choice >= 0) {
					break;
				} else {
					System.out.println(INCORRECT_INPUT_VALUE_FOR_SCANNER);
				}
			} catch (InputMismatchException e) {
				System.out.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
				Logger.log(e);
			} 
		}
		return choice;
	}
	
	/**
	 * Read string from console
	 * @return string
	 */
	public static String inputString() {
		input = new Scanner(System.in, "utf-8");	
		return input.next();
	}

	/**
	 * Print all available tariffs at MobileTariff
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void printTariffs(Map<Integer, MobileTariff> mapOfTariffs) {
		Set<Map.Entry<Integer, MobileTariff>> set = mapOfTariffs.entrySet();
		Iterator<Map.Entry<Integer, MobileTariff>> it = set.iterator();
		while (it.hasNext()) {
			int temp = it.next().getKey();
			System.out.println("id="+temp + " : " + mapOfTariffs.get(temp).toString());
		}
		System.out.println(DELIMITER);
	}
	
	/**
	 * Read from file values of specific tariff and create its entites
	 * @param file - input file with values for specific tariff
	 * @param typeTariff - specific type of tariff
	 */
	public static void createTariffs(File file, String typeTariff) {
		List<String> strings = new ArrayList<>();
		try {
			Operations.readFile(strings, file);
		} catch (FileNotFoundException e) {
			System.out.println("При создании объекта "+typeTariff.replaceAll(".txt","")+" возникла ошибка."
					+ " Подробное описание ошибки в файле log.txt");
		}
		Iterator<String> itStrings = strings.iterator();
		while (itStrings.hasNext()) {
			List<String> tariffTokens = Operations.stringParser(itStrings
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
					System.out.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
				}
		}
	}
	
	/**
	 * Create new LimitedCallsTariff with specific values
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
					.println("При создании объекта LimitedCallsTariff возникла ошибка."
							+ " Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new LimitedInternetTariff with specific values
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
					.println("При создании объекта LimitedInternetTariff возникла ошибка."
							+ " Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new UnlimitedCallsTariff with specific values
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
					.println("При создании объекта UnlimitedCallsTariff возникла ошибка."
							+ " Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new UnlimitedInternetTariff with specific values
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
					.println("При создании объекта UnlimitedInternetTariff возникла ошибка."
							+ " Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create random clients and subscribe them on various mobiletariff
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void createRandomSubscribers(Map<Integer, MobileTariff> mapOfTariffs) {
		Operations.createListsNamesAndSurnames();
		for (MobileTariff tariff : mapOfTariffs.values()) {
			Operations.subscribeForTariff(tariff);
		}
	}
	
	/**
	 * Create list of random clients from input files (names and surnames)
	 */
	private static void createListsNamesAndSurnames() {
		List<String> tempNames = new ArrayList<>();
		List<String> tempSurnames = new ArrayList<>();
		try {
			Operations.readFile(tempNames, filePathNames);
			Operations.readFile(tempSurnames, filePathSurnames);
		} catch (FileNotFoundException e) {
			System.out
					.println("При создании списка абонентов возникла ошибка. Подробное описание ошибки в файле log.txt");
		}
		Iterator<String> namesTokens = tempNames.iterator();
		Iterator<String> surnameTokens = tempSurnames.iterator();
		while (namesTokens.hasNext()) {
			namesClients = Operations.stringParser(namesTokens.next());
		}
		while (surnameTokens.hasNext()) {
			surnamesClients = Operations.stringParser(surnameTokens.next());
			}
	}
	
	/**
	 * subscribe random number of random clients on a mobile tariff
	 * @param tariff - certain tariff on which we subscribe clients
	 */
	private static void subscribeForTariff(MobileTariff tariff) {
		Random randomGenerator = new Random();
		StringBuilder clientInformation;
		String[] arrayNames = new String[namesClients.size()];
		String[] arraySurnames = new String[surnamesClients.size()];
		arrayNames = namesClients.toArray(arrayNames);
		arraySurnames = surnamesClients.toArray(arraySurnames);
		int randomNumberClients = randomGenerator.nextInt(50);
		try {
			for (int i = 0; i < randomNumberClients; i++) {
				clientInformation = new StringBuilder();
				String date = Operations.getRandomDate();
				tariff.subscribe(
						"MP" + (randomGenerator.nextInt(PASSPORT_VALUE_FOR_RANDOM) + PASSPORT_VALUE_MINIMUM_FOR_RANDOM),
						clientInformation.append(arraySurnames[randomGenerator.nextInt(arraySurnames.length)])
							.append(" ")
							.append(arrayNames[randomGenerator.nextInt(arrayNames.length)])
							.append(" ")
							.append(date));
			}
		} catch (IllegalArgumentException e) {
			Logger.log(e);
		}
	}
	
	/**
	 * create random date of subscribe 
	 * @return string of date
	 */
	public static String getRandomDate() {
		Random rand = new Random();
		int year = rand.nextInt(YEAR_VALUE_FOR_RANDOM)
				+ YEAR_VALUE_MINIMUM_FOR_RANDOM;
		int month = rand.nextInt(MONTH_VALUE_FOR_RANDOM);
		int day = rand.nextInt(DAY_VALUE_FOR_RANDOM);
		GregorianCalendar calendar = new GregorianCalendar(year, month, day);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
	
	/**
	 * get current date for new real client 
	 * @return string of date
	 */
	public static String getDate() {
		Date date = Calendar.getInstance().getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(date);
	}

	/**
	 * print total number of clients
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void getClientsNumber(Map<Integer, MobileTariff> mapOfTariffs){
		int clientsNumber = 0; 
		for (MobileTariff tariff : mapOfTariffs.values()) {
			clientsNumber += tariff.getClientsNumbers();
		}
		System.out.println("Общая численность клиентов : "+clientsNumber);
	}
	
	/**
	 * Displays lists of tariffs sort by abonement price
	 * @param mapOfTariffs 	- list of available tariffs
	 */
	public static void sortServicesBasedOnAbonementPrice(Map<Integer, MobileTariff> mapOfTariffs){
		List<MobileTariff> list = new ArrayList<>(mapOfTariffs.values());
		Collections.sort(list, new MobileTariffComparator());
		Operations.reportAfterSort(list, mapOfTariffs);
	}
	
	/**
	 * Displays list
	 * @param list - list of sorted tariffs
	 * @param mapOfTariffs - list of available tariffs
	 */
	private static void reportAfterSort(List<? extends Object> list, Map<Integer, MobileTariff> mapOfTariffs){
		for(Object s : list){
			if(s instanceof MobileTariff){
				Set<Map.Entry<Integer, MobileTariff>> set = mapOfTariffs.entrySet(); 
				Iterator<Map.Entry<Integer, MobileTariff>> it = set.iterator();
				while (it.hasNext()) {
					Map.Entry<Integer, MobileTariff> entry = it.next();
					if (entry.getValue().equals((MobileTariff)s)){
						System.out.println("id=" + entry.getKey() + " - " + entry.getValue().toString());
						break;
					}
				}
			}
		}
	}

	/**
	 * Find and display list of suitable tariff for different searching parameters
	 * @param mapOfTariffs - list of available tariffs
	 * @param parameter1 - first parameter for searching
	 * @param parameter2 - second parameter for searching
	 * @param typeOfSearch - type of searching
	 */
	public static void findSuitableTariff(
			Map<Integer, MobileTariff> mapOfTariffs, double parameter1, double parameter2, int typeOfSearch) {
		switch (typeOfSearch) {
		case SEARCH_BY_ABONPRICE_AND_OVERALL_COST:
			mapOfTariffs.forEach((key, value) -> {
				if (value.getAbonementPrice() <= parameter1
						&& value.getCost() <= parameter2) {
					System.out.println("id="+key + " - " + value.toString().substring(INDEX_FOR_SUBSTRING_TARIFFNAME));
				}
			});
			System.out.println(DELIMITER);
			break;
			
		case SEARCH_BY_CALL_COST_AND_FREE_MINUTES:
			mapOfTariffs.forEach((key, value) -> {
				if (value instanceof CallsTariff) {
					if (((CallsTariff)value).getcallsPrice()<=parameter1 && 
							((CallsTariff)value).getFreeMinutes()>=parameter2){
						System.out.println("id="+key + " - " + value.toString().substring(INDEX_FOR_SUBSTRING_TARIFFNAME));
					}
				}
			});
			System.out.println(DELIMITER);
			break;
			
		case SEARCH_BY_INTERNET_COST_AND_FREE_GB:
			mapOfTariffs.forEach((key, value) -> {
				if (value instanceof InternetTariff) {
					if (((InternetTariff)value).getInternetPrice()<=parameter1 && 
							((InternetTariff)value).getFreeGb()>=parameter2){
						System.out.println("id="+key + " - " + value.toString().substring(INDEX_FOR_SUBSTRING_TARIFFNAME));
					}
				}
			});
			System.out.println(DELIMITER);
			break;

		default:
			System.out.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
		}
	}
	
	/**
	 * serialize tariffs and their state into the fileOuptut
	 * @param mapOfTariffs - list of available tariffs
	 * @param fileOutput - output file for storing serializable objects
	 */
	public static void serializeObjects (Map<Integer, MobileTariff> mapOfTariffs, File fileOutput){
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileOutput));
			out.writeObject(mapOfTariffs);
			System.out.println("Запись успешно произведена в файл \"" + fileOutput.getName() + "\"");
			System.out.println(mapOfTariffs.toString());
		} 
		catch (IOException e) {
			System.out.println("Ошибка записи. Невозможно создать файл \"" + fileOutput.getName() + "\"");
			Logger.log(e);
		} finally {
			try {
				if (out!=null) {
					out.close();
				}
			} catch (Exception e){
				System.err.println("Ошибка закрыти потока вывода : "+e);
				Logger.log(e);
			}
		}
	}
	
	/**
	 * deserialize tariffs and their state from the fileOuptut
	 * @param fileOutput - output file for storing serializable objects
	 * @return deserializableObjects - hashmap deserializable tariffs objects
	 */
	@SuppressWarnings("unchecked")
	public static Map<Integer, MobileTariff> deserializeObjects(File fileOutput){
		Map<Integer, MobileTariff> deserializableObjects = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileOutput));
			deserializableObjects = (Map<Integer, MobileTariff>) ois.readObject();
			System.out.println("Объект успешно восстановлен.");
		} 
		catch (FileNotFoundException e) {
			System.out.println("Файл не найден..." + fileOutput.getName());
			Logger.log(e);
		} 
		catch (IOException e) {
			Logger.log(e);
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Класс не найден...");
			Logger.log(e);
		} 
		finally {
			try {
				if (ois!=null) {
					ois.close();
				}
			} catch (Exception e){
				System.err.println("Ошибка закрыти потока ввода : "+e);
				Logger.log(e);
			}
		}
		return deserializableObjects;
	}

	/**
	 * subscribe new real client on a tariff
	 * @param mobileTariff - certain tariff for client
	 * @param passport - unique passport of client
	 * @param clientInformation - another information of client (name, surname, date of subscribe)
	 */
	public static void subscribe(MobileTariff mobileTariff, String passport, StringBuilder clientInformation) {
		mobileTariff.subscribe(passport, clientInformation);
	}

	/**
	 * unsubscribe real client from a tariff
	 * @param passport - unique passport of client
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void unsubscribe(String passport, Map<Integer, MobileTariff> mapOfTariffs) {
		for (MobileTariff tariff : mapOfTariffs.values()) {
			if (tariff.getClients().containsKey(passport)) {
				tariff.unsubscribe(passport);
				System.out.println("Вы были успешно отключены от тарифного плана " + tariff.getTariffName());
			}
		}
		System.out.println(DELIMITER);
	}
	
	/**
	 * print all clients from all tariffs
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void printAllClients(Map<Integer, MobileTariff> mapOfTariffs) {
		for (MobileTariff tariff : mapOfTariffs.values()) {
			System.out.println(tariff.getTariffName()+":");
			tariff.printClients();
			System.out.println(DELIMITER);
			}
		}

	/**
	 * check input value of string passport
	 * @return string with passport valid value
	 */
	public static String checkPassportValue() {
		String passport = null;
		while (true) {
			System.out
					.println("Введите номер вашего паспорта в формате MPxxxxxxx :");
			passport = Operations.inputString();
			if (!passport.startsWith("MP")) {
				System.out
						.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
			} else {
				break;
			}
		}
		return passport;
	}

	/**
	 * write tariffs on which certain client is subscribed to a file
	 * @param filename - output filname to store information
	 * @param passport - unique passport of client
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void writeTariffsToFile(File filename, String passport, Map<Integer, MobileTariff> mapOfTariffs) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"utf-8")));
			for (MobileTariff tariff : mapOfTariffs.values()) {
				if (tariff.getClients().containsKey(passport)) {
					pw.println(tariff.toString());
				}
			}
			System.out.println("Запись успешно произведена в файл \"" + filename.getPath() + "\"");
		} 
		catch (IOException e) {
			System.out.println("Ошибка записи. Невозможно создать файл \"" + filename.getPath() + "\"");
			Logger.log(e);
		} finally {
			try {
				if (pw!=null) {
					pw.close();
				}
			} catch (Exception e){
				System.err.println("Ошибка закрыти потока вывода : "+e);
				Logger.log(e);
			}
		}
	}
}
