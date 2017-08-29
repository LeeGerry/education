package edu.auburn.utils.scal;

import java.util.List;

public class MatchInstanceTestWithWeight {
	
	public static void main(String[] args) {
		EDistanceWithOutputWithWeight editDistance = new EDistanceWithOutputWithWeight();
		PreProcess prePro = new PreProcess();
		
		
		String[] str1 = {"kitten", "blah", "ba", "abcd", "a", "d", "abcdefg", "abcdefg", "cat", "k@t", "dagi", "SuSuSu", "dagiwagilagimagi", "feT", "fT", "fes",
				"strIG", "TIkInig", "@nImxl", "-n-m-l", "nml", "kar", "gEr", "gV", "gV", "snobal", "gxzEl", "t\u0361ʃis", "te\u0361ɪisisiis"};
		
		String[] str2 = {"sitting", "blah", "ab", "dcba", "abcde", "abcdef", "bcd", "ceg", "cat1", "k@t1", "dagiz", "dabaS", "glu", "Tef", "Tf", "ruf",
				"sIG", "TIkInIn", "flEmxnxv", "fl-m-n-v", "flmnv", "krar", "grErt", "glV", "gl1V", "balun", "zElIs", "t\u0361ʃii", "teɪisisiis"};
		
		
		
//		String[] str1 = {"te\u0361ɪisisiis"};
//		
//		
//		String[] str2 = {"teɪisisiis"};
		
		System.out.println("------------Cases from excel file--------------------------------");	

		// One has to call the editDistance.dynamicEditDistance method first in order to invoke 
		// the printActualEdits method.
		for (int i = 0; i < str1.length; i++){ 
		//	ArrayList<String> strs1 = new ArrayList<>(Arrays.asList("k", "id", "t", "t", "e", "n"));
		//	ArrayList<String> strs2 = new ArrayList<>(Arrays.asList("k", "iddd", "t", "t", "e", "n"));
			
			List<String> strs1 = prePro.generateListOfStrings(str1[i]);
			List<String> strs2 = prePro.generateListOfStrings(str2[i]);
//			System.out.println(strs1.size());
//			System.out.println(strs2.size());
			String string1 = "";
			for (String str : strs1) {
				//System.out.println(str);
				string1 += str;
				
			}
			
			System.out.println();
			
			String string2 = "";
			for (String str : strs2) {
				//System.out.println(str);
				string2 += str;
				
			}
			
			double Score = editDistance.dynamicEditDistance(strs1, strs2) ;
			String result = editDistance.printActualEdits(strs1, strs2);
			System.out.println("case" + "\t" + (i + 1) +  "\t" + string1 + ", " + string2 + ": " + "result: " + result + " Score: " + Score);
		}
		
		System.out.println("------------------------------end-----------------------------------");
		//System.out.println("t1508".equals("t͡ʃ"));

	}
}
