package edu.auburn.utils.scal;


public class MatchInstanceTestWithWeight {
	
	public static void main(String[] args) {
		EDistanceWithOutputWithWeight editDistance = new EDistanceWithOutputWithWeight();
		
		
		String[] str1 = {"kitten", "blah", "ba", "abcd", "a", "d", "abcdefg", "abcdefg", "cat", "k@t", "dagi", "SuSuSu", "dagiwagilagimagi", "feT", "fT", "fes",
				"strIG", "TIkInig", "@nImxl", "-n-m-l", "nml", "kar", "gEr", "gV", "gV", "snobal", "gxzEl"};
		
		String[] str2 = {"sitting", "blah", "ab", "dcba", "abcde", "abcdef", "bcd", "ceg", "cat1", "k@t1", "dagiz", "dabaS", "glu", "Tef", "Tf", "ruf",
				"sIG", "TIkInIn", "flEmxnxv", "fl-m-n-v", "flmnv", "krar", "grErt", "glV", "gl1V", "balun", "zElIs"};
		
		
		System.out.println("------------Cases from excel file--------------------------------");	

		// One has to call the editDistance.dynamicEditDistance method first in order to invoke 
		// the printActualEdits method.
		for (int i = 0; i < str1.length; i++){ 
			double Score = editDistance.dynamicEditDistance(str1[i], str2[i]) ;
			String result = editDistance.printActualEdits(str1[i], str2[i]);
			System.out.println("case"+ (i + 1) + "\t" + str1[i] + ", " + str2[i] + ": " + result + "; Score: " + Score);
		}
		
		System.out.println("------------------------------end-----------------------------------");

	}
}
