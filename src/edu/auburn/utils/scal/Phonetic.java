package edu.auburn.utils.scal;

/*
 * This is implemented by Yingzhi yang.
 * The purpose is to identify the vowels, consonants, and numbers in a string.
 * 
 */

import java.util.Arrays;
import java.util.List;

public class Phonetic {
	/*private String[] Vowels={"A","a","E","e","I","i","O","o","U","u"};
	private String[] Consonants={"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z",
			"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
	private String[] Number={"1","2","3","4","5","6","7","8","9","0"};
	*/
	
	//private String[] Vowels={"i","I","E","e","@","a","W","Y","^","c","O","o","U","u","X","x","R","N","M","L","-", "\u025B", "\u0153", "\u0251","e\u026A\u0361" };
	//private String[] Consonants={"p","t","k","b","d","g","C","J","s","S","z","Z","f","T","v","D","h","n","m","G","q",
			//"l","r","w","j","V", "\u02A7\u0361", "\u02A4\u0361", "\u0283", "\u0292", "\u03B8", "\u00F0", "\u014B", "\u026B", "\u027E", "\u0294", "\u029D", "\u0267"};
	//
	
	private String[] Vowels={"ɪ","ɚ","ɝ","ə","ɑ","e","ʊ","a","ʌ","ɛ","æ","i","ɜ","u","ɔ","ɑ","e","ɪ","ɔ","o", "e\u0361ɪ", "ɑ\u0361u","ɑ\u0361ɪ", "ɔ\u0361ɪ", "o\u0361ʊ"};//, "ɪr", "ɛr", "ʊr"};
		//
	private String[] Consonants={"ʔ","ɾ","ɫ","t\u0361ʃ", "d\u0361ʒ", "p","r","t","f","θ","s","ʃ","b","d","ɡ","v","z","ʒ","h","m","n","ŋ","l","r","j","w", "l","r","w", "ð"};//, "dʒ","dr", "dz", "tr", "ts", "tʃ","ð"};
	private String[] Number={"1","2","3","4","5","6","7","8","9","0"};
	
	private String[] Dicritics={};
	
	private List<String> ConsonantList=Arrays.asList(Consonants);
	private List<String> VowelsList=Arrays.asList(Vowels);
	private List<String> NumberList=Arrays.asList(Number);
	private List<String> DicriticsList=Arrays.asList(Dicritics);
	
	public List<String> getConsonantList() {
		return ConsonantList;
	}

	public void setConsonantList(List<String> consonantList) {
		ConsonantList = consonantList;
	}

	public List<String> getVowelsList() {
		return VowelsList;
	}

	public void setVowelsList(List<String> vowelsList) {
		VowelsList = vowelsList;
	}

	public List<String> getNumberList() {
		return NumberList;
	}

	public void setNumberList(List<String> numberList) {
		NumberList = numberList;
	}
	
	public List<String> getDicriticsList() {
		return DicriticsList;
	}

	public void setDicriticsList(List<String> dicriticsList) {
		DicriticsList = dicriticsList;
	}

	
	
	/*private String VowelSignal="*";
	private String ConsonantSignal="|";
	private String NumberSignal="!";
	
	public String getNumberSignal() {
		return NumberSignal;
	}

	public void setNumberSignal(String numberSinal) {
		NumberSignal = numberSinal;
	}

	public String getVowelSignal() {
		return VowelSignal;
	}

	public void setVowelSignal(String vowelSigel) {
		VowelSignal = vowelSigel;
	}

	public String getConsonantSignal() {
		return ConsonantSignal;
	}

	public void setConsonantSignal(String consonantSigel) {
		ConsonantSignal = consonantSigel;
	}*/

	public String[] getNumber() {
		return Number;
	}

	public void setNumber(String[] number) {
		Number = number;
	}

	public String[] getVowels() {
		return Vowels;
	}
	
	public String[] getDicritics() {
		return Dicritics;
	}

	public void setVowels(String[] vowels) {
		Vowels = vowels;
	}

	public String[] getConsonants() {
		return Consonants;
	}

	public void setConsonants(String[] consonants) {
		Consonants = consonants;
	}
	
	
	//public String[] getDicritics1() {
		//return Dicritics;
	//}

	public void setDicritics(String[] dicritics) {
		Consonants = dicritics;
	}
	
	
	
}
