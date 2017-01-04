package by.pvt.heldyieu.mobile.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import by.pvt.heldyieu.mobile.beans.interfaces.Constants;
import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;

public class Operations implements Constants {
public static Scanner input = new Scanner(System.in,"utf-8");
	
	/**
	 * Closed constructor <b>{@code Operations}</b>
	 */
	private Operations(){
	}
	
	/**
	 * Reads list of tariffs from file
	 * @param file - input file
	 * @param list - list of tariffs for returning
	 * @return entity of <b>AbstractService</b>
	 */
	public static List<String> readFile(List<String> list, File file){
		try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))){
			while(sc.hasNext()){
				list.add(sc.nextLine());
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Файл не найден..." + file.getName());
			Logger.log(e);
		}
		return list;
	}
	
	/**
	 * Splits string to tokens
	 * @param str - string
	 * @return list of tokens
	 */
	public static List<String> stringToTokens(String str){
		List<String> tokens = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(str, ";");
		while(st.hasMoreTokens()){
			tokens.add(st.nextToken());
		}
		return tokens;
	}
	
	/**
	 * Print all available tariffs at MobileTariff
	 * @param mapOfTariffs - HashMap off all tariffs
	 */
	public static void printTariffs(Map<Integer, MobileTariff> mapOfTariffs) {
		Set<Map.Entry<Integer, MobileTariff>> set = mapOfTariffs.entrySet(); 
		Iterator<Map.Entry<Integer, MobileTariff>> it = set.iterator();
		while (it.hasNext()) {
			MobileTariff temp =it.next().getValue();
			System.out.println("Название тарифного плана - "+temp.toString());
		}
	}
}
