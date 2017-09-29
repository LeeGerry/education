package edu.auburn.utils.scal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchInstanceTestWithWeight {
	
	public static void main(String[] args) {
		EDistanceWithOutputWithWeight editDistance = new EDistanceWithOutputWithWeight();
		PreProcess prePro = new PreProcess();
		
		
//		String[] str1 = {"kitten", "blah", "ba", "abcd", "a", "d", "abcdefg", "abcdefg", "cat", "k@t", "dagi", "SuSuSu", "dagiwagilagimagi", "feT", "fT", "fes",
//				"strIG", "TIkInig", "@nImxl", "-n-m-l", "nml", "kar", "gEr", "gV", "gV", "snobal", "gxzEl", "t\u0361ʃis", "te\u0361ɪisisiis"};
//		
//		String[] str2 = {"sitting", "blah", "ab", "dcba", "abcde", "abcdef", "bcd", "ceg", "cat1", "k@t1", "dagiz", "dabaS", "glu", "Tef", "Tf", "ruf",
//				"sIG", "TIkInIn", "flEmxnxv", "fl-m-n-v", "flmnv", "krar", "grErt", "glV", "gl1V", "balun", "zElIs", "t\u0361ʃii", "teɪisisiis"};
//		
//		
		
		String[] str1 = { "kitten", "blah", "ba", "abcd", "a", "d", "abcdefg", "abcdefg", "cat", "k@t", "dagi", "SuSuSu", "dagiwagilagimagi", "feT", "fT", "fes",
				"strIG", "TIkInig", "@nImxl", "-n-m-l", "nml", "kar", "gEr", "gV", "gV", "snobal", "gxzEl", "t\u0361ʃis",  "te\u0361ɪisisiis", "aa", "d̥" ,"d̥","d̥" , "", "",
				"dɪʃɪz",
				"dɪʃɪz",
				"dɪʃɪz",
				"ko͡ʊːt",
				"kætnɪp",
				"d̥ɪnɚ" ,
				"ski" ,
				"pʌt̚",
				"kʰɪŋ",
				"ˌkʰɪŋ",
				"kʰɪŋ",
				"rɛdi",
				"radi",
				"rɑ͡ɪɾɪŋ", 
				"rɪtn̩" ,
				"rɪtn̩",
				"rɪtn̩",
				"rɪtn̩",
				"rɪtm̩",
				"gʊd",
				"ɪkʰŋ",
				"ɪkʰŋ",
				"dd̥",
				"klow",
				"klo͡ʊn",
				"klo͡ʊo͡ʊo͡ʊn",
				"go͡ʊt",
				"geɪ͡t",
				"glot",
				"gɑ\u0361ɪt",
				"gɑ\u0361ɪt",
				"pa͡ʊ",
				"pe\u0361ɪd\u0361ʒ",
				"pæʃ",
				"pæd\u0361ʒ",
				"pɑ",
				"pɛ"};
		
		
		String[] str2 = {"sitting", "blah", "ab", "dcba", "abcde", "abcdef", "bcd", "ceg", "cat1", "k@t1", "dagiz", "dabaS", "glu", "Tef", "Tf", "ruf",
				"sIG", "TIkInIn", "flEmxnxv", "fl-m-n-v", "flmnv", "krar", "grErt", "glV", "gl1V", "balun", "zElIs", "t\u0361ʃii", "teɪisisiis","","","d","a", "d̥" ,"aa",
				"d̥ɪʃɪz",
				"ˈdɪʃɪz",
				"ˌdɪʃɪz",
				"ko͡ʊt",
				"kæt.nɪp",
				"dɪnɚ",
				"skis̃",
				"pʌt",
				"kɪŋ",
				"kʰɪŋ",
				"ˌkʰɪŋ",
				"rɛ̃di",
				"rɛ̃di",
				"rɑ͡ɪtɪŋ",
				"rɪtn",
				"rɪta",
				"rɪtl̩",
				"rɪtm",
				"rɪtm",
				"gʊdi",
				"ɪˌkʰŋ",
				"ɪˌˌˌkʰŋ",
				"dd",
				"klo͡ʊn",
				"klow",
				"clooow",
				"ge͡ɪt",
				"go͡ʊt",
				"glo\u0361ʊt",
				"ge\u0361ɪt",
				"geɪ\u0361t",
				"pa",
				"pe\u0361ɪ",
				"pæt\u0361ʃ",
				"pæt",
				"pa\u0361ɪ",
				"pe\u0361ɪ"};
		
		System.out.println("------------Cases from excel file--------------------------------");	

		// One has to call the editDistance.dynamicEditDistance method first in order to invoke 
		// the printActualEdits method.
		for (int i = 0; i < str1.length; i++){ 
		//	ArrayList<String> strs1 = new ArrayList<>(Arrays.asList("k", "id", "t", "t", "e", "n"));
		//	ArrayList<String> strs2 = new ArrayList<>(Arrays.asList("k", "iddd", "t", "t", "e", "n"));
			
			List<String> strs1 = prePro.generateListOfStrings(str1[i]);
			List<String> strs2 = prePro.generateListOfStrings(str2[i]);
			//System.out.println(strs1.size());
			//System.out.println(strs2.size());
			String string1 = "";
			for (String str : strs1) {
				//System.out.println(str);
				string1 += str;
				
			}
			
			//System.out.println();
			
			String string2 = "";
			for (String str : strs2) {
				//System.out.println(str);
				string2 += str;
				
			}
			
			//System.out.println();
			
			double Score = editDistance.dynamicEditDistance(strs1, strs2) ;
			String result = editDistance.printActualEdits(strs1, strs2);
			System.out.println("case" + "\t" + (i + 1) +  "\t" + str1[i] + ", " + str2[i] + ": " + "result: " + result + " Score: " + Score);
		}
		
		System.out.println("------------------------------end-----------------------------------");
		//System.out.println("go͡ʊt".length());
		//System.out.println("t1508".equals("t͡ʃ"));

	}
}
