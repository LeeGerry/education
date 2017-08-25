package edu.auburn.utils.yzal;

import java.util.Arrays;
import java.util.List;

public class Phonetic {
	/*private String[] Vowels={"A","a","E","e","I","i","O","o","U","u"};
	private String[] Consonants={"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z",
			"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
	private String[] Number={"1","2","3","4","5","6","7","8","9","0"};
	*/
	String a="d̥ɪʃɪz".substring(1,2);
	String b="d̥ɪʃɪz".substring(3,4);
	//private String[] Vowels={"i","I","E","e","@","a","W","Y","^","c","O","o","U","u","X","x","R","N","M","L","-","ɪ","ɑ"};
	//private String[] Consonants={"p","t","k","b","d","g","C","J","s","S","z","Z","f","T","v","D","h","n","m","G","q",
	//		"l","r","w","j","V",b};
	
	private String[] Vowels={"ɪ","ə","ɑ","e","ʊ","a","ʌ","ɛ","æ","i","ɜ","u","ɔ","ɑ","e","a","ɪ","ɔ","o","ɑ"};
	private String[] Consonants={"p","r","t","f","θ","s","ʃ","b","d","ɡ","v","z","ʒ","h","m","n","ŋ","l","r","j","w",
			"l","r","w",b};
	private String[] Number={"1","2","3","4","5","6","7","8","9","0",a,"ˈ","ˌ"};
	private String[] Diacritic={"d̃","2","3","4","5","6","7","8","9","0"};
	
	public String[] getDiacritic() {
		return Diacritic;
	}

	public void setDiacritic(String[] diacritic) {
		Diacritic = diacritic;
	}

	private List<String> ConsonantList=Arrays.asList(Consonants);
	private List<String> VowelsList=Arrays.asList(Vowels);
	private List<String> NumberList=Arrays.asList(Number);
	
	
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

	public void setVowels(String[] vowels) {
		Vowels = vowels;
	}

	public String[] getConsonants() {
		return Consonants;
	}

	public void setConsonants(String[] consonants) {
		Consonants = consonants;
	}
}
