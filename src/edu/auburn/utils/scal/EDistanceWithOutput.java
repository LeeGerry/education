package edu.auburn.utils.scal;


import java.util.Arrays;

import java.util.List;



public class EDistanceWithOutput {

	private Phonetic phonetic = new Phonetic();
	
	private double[][] T;
	
	
	
	public double recEditDistance(char[] str1, char str2[], int len1, int len2) {
		if (len1 == str1.length) {
			return str2.length - len2;
		}
		if (len2 == str2.length) {
			return str1.length - len1;
		}
		return min(recEditDistance(str1, str2, len1 + 1, len2 + 1) + str1[len1] == str2[len2] ? 0 : 1,
				recEditDistance(str1, str2, len1, len2 + 1) + 1, recEditDistance(str1, str2, len1 + 1, len2) + 1);
	}

	/**
	 * Uses bottom up DP to find the edit distance
	 */
	public double dynamicEditDistance(char[] str1, char[] str2) {
		double temp[][] = new double[str1.length + 1][str2.length + 1];
		for (int i = 0; i < temp[0].length; i++) {
			temp[0][i] = i;
		}
		for (int i = 0; i < temp.length; i++) {
			temp[i][0] = i;
		}
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					temp[i][j] = temp[i - 1][j - 1];
					//System.out.print(temp[i][j] + " ");
				} else {
					
					double cost_dist = Character.isDigit(str2[j - 1]) ? 0.5 : 1.0;
					
					temp[i][j] = cost_dist + min(temp[i - 1][j - 1], temp[i - 1][j], temp[i][j - 1]);
					//System.out.print(temp[i][j] + " ");
				}
			}
			//System.out.println();
		}
		
		
		T = temp;
		//System.out.println(printActualEdits(str1, str2));
		return temp[str1.length][str2.length];
	}

	/**
	 * Prints the actual edits which needs to be done.
	 */
	public String printActualEdits(char[] str1, char[] str2) {
		int i = T.length - 1;
		int j = T[0].length - 1;
		
		
		
		StringBuilder result = new StringBuilder();
		while (true) {
			//System.out.println(str1[i - 1 ] + " " + str2[j - 1]);
			if (i == 0 && j == 0) {
				break;
			}
			if (i == 0) { 
				result.append("d");
				j--;
				continue;
			
			}
			
			if (j == 0) {
				result.append("d");
				i--;
				continue;
			}
			if (str1[i - 1] == str2[j - 1]) {
				if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(str1[i-1])) && 
						Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(str2[j-1]))){
					result.append("|");
				}
				else {
					result.append("*");
				}
				
				i = i - 1;
				j = j - 1;
			} else if (T[i][j] == T[i - 1][j - 1] + 1) {
				//System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
				
				result.append("s");
				i = i - 1;
				j = j - 1;
			} else if (T[i][j] == T[i - 1][j] + 1) {
				//System.out.println("Delete in string1 " + str1[i - 1]);
				result.append("d");
				i = i - 1;
			} else if (T[i][j] == T[i][j - 1] + 1) {
				//System.out.println("Delete in string2 " + str2[j - 1]);
				if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(str2[j-1]))) result.append("%");
				
				else result.append("i");	
				j = j - 1;
				
			} else if (T[i][j] == T[i][j - 1] + 0.5 ) {
				
				result.append("!");
				j = j - 1;
			}	
			else if  (T[i][j] == T[i-1][j - 1] + 0.5 ){
				result.append("!");
				i = i - 1;
				j = j - 1;
				
			}
			 else  {
				throw new IllegalArgumentException("Some wrong with given data");
				
			}
		}
		return result.reverse().toString();
	}

	private double min(double a, double b, double c) {
		double l = Math.min(a, b);
		return Math.min(l, c);
	}

	public static void main(String args[]) {
		String str1 = "dagiwagilagimagi";
		String str2 = "rɑ͡ɪɾɪŋ";
		System.out.println(str1);
		System.out.println(str2);
		EDistanceWithOutput editDistance = new EDistanceWithOutput();
		
		double result = editDistance.dynamicEditDistance(str1.toCharArray(), str2.toCharArray());
		String symbol = editDistance.printActualEdits(str1.toCharArray(), str2.toCharArray());
		System.out.println(result);
		
		System.out.println(symbol);
	}

}
