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
	
	private String[] Vowels={"ɪ","ə","ɑ","e","ʊ","a","ʌ","ɛ","æ","i","ɜ","u","ɔ","ɑ","e","ɪ","ɔ","o", "e\u0361ɪ", "ɑ\u0361u","ɑ\u0361ʊ", "ɑ\u0361ɪ", "ɔ\u0361ɪ", "o\u0361ʊ", "o\u0361u","m̩","n̩", "l̩" };//, "ɪr", "ɛr", "ʊr"};
		//
	private String[] Consonants={"t\u0361ʃ", "d\u0361ʒ", "p","r","t","f","θ","s","ʃ","b","d","g","v","z","ʒ","h","m","n","ŋ","l","r","j","w", "l","r","w", "ð"};//, "dʒ","dr", "dz", "tr", "ts", "tʃ","ð"};
	private String[] Number={"1","2","3","4","5","6","7","8","9","0"};
	
	private String[] Dicritics1={"ˈ", "ˌ"};
	//private String[] Dicritics2={" ̃", " ͊", " ͋", "ː",  " ̥", " ̬", "ʰ", " ̝",  " ̞", " ̟", " ̠", " ̚", " ̤", " ̰", "m̩"};
	private String[] Dicritics2={"\u0303", "\u034A", "\u034B", "ː", "\u0325", "\u032C", "\u036A", "\u031D", "\u031E", "\u031F", "\u0320", "\u031A","\0324", "\0330", "\u0329", "ʰ"};
	
	private List<String> ConsonantList=Arrays.asList(Consonants);
	private List<String> VowelsList=Arrays.asList(Vowels);
	private List<String> NumberList=Arrays.asList(Number);
	private List<String> DicriticsList1=Arrays.asList(Dicritics1);
	private List<String> DicriticsList2=Arrays.asList(Dicritics2);
	
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
	
	public List<String> getDicriticsList1() {
		return DicriticsList1;
	}

	public void setDicriticsList1(List<String> dicriticsList1) {
		DicriticsList1 = dicriticsList1;
	}
	
	public List<String> getDicriticsList2() {
		return DicriticsList2;
	}

	public void setDicriticsList2(List<String> dicriticsList2) {
		DicriticsList2 = dicriticsList2;
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
	
	public String[] getDicritics1() {
		return Dicritics1;
	}
	
	public String[] getDicritics2() {
		return Dicritics2;
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

	public void setDicritics1(String[] dicritics1) {
		Dicritics1 = dicritics1;
	}
	
	public void setDicritics2(String[] dicritics2) {
		Dicritics2 = dicritics2;
	}
	
	
	
	
}
