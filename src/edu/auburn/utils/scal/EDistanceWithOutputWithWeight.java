package edu.auburn.utils.scal;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;



public class EDistanceWithOutputWithWeight {

	private Phonetic phonetic = new Phonetic();
	
	
	private double[][] T;
	
	
//	
//	public double recEditDistance(char[] str1, char str2[], int len1, int len2) {
//		if (len1 == str1.length) {
//			return str2.length - len2;
//		}
//		if (len2 == str2.length) {
//			return str1.length - len1;
//		}
//		return min(recEditDistance(str1, str2, len1 + 1, len2 + 1) + str1[len1] == str2[len2] ? 0 : 1,
//				recEditDistance(str1, str2, len1, len2 + 1) + 1, recEditDistance(str1, str2, len1 + 1, len2) + 1);
//	}

	/**
	 * Uses bottom up DP to find the edit distance
	 */
	public double dynamicEditDistance(List<String> strs1, List<String> strs2) {
		//char[] str1 = string1.toCharArray();
		//char[] str2 = string2.toCharArray();
		double temp[][] = new double[strs1.size() + 1][strs2.size() + 1];
		for (int i = 0; i < temp[0].length; i++) {
			
//			if ( i < strs1.size() && Arrays.asList(phonetic.getDicritics1()).contains( String.valueOf(strs1.get(i).charAt(0) )) 
//					|| Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(strs1.get(i).charAt(strs1.get(i).length() - 1) ))){
//					if ( i == 0){
//						temp[0][i] =  1.5 ;
//					}
//					else {
//						temp[0][i] =  temp[0][i] + 1.5 ;
//					}
//			}
//			
//			else {
//				if (i == 0){
//					temp[0][i] = 0;
//				}
//				else {
//					temp[0][i] =  temp[0][i] + 1;
//				}
//			}
			temp[0][i] = i;
			
		}
		for (int i = 0; i < temp.length; i++) {

				temp[i][0] = i;
			
		}
		
		//if (strs2.size() == 0 || strs1.size() == 0) return temp[strs1.size()][strs2.size()];
		
		
		
		
		for (int i = 1; i <= strs1.size(); i++) {
			for (int j = 1; j <= strs2.size(); j++) {
				if ( strs1.get(i - 1).equals(strs2.get(j-1)) ) {
					temp[i][j] = temp[i - 1][j - 1];
					//System.out.print(temp[i][j] + " ");
					//System.out.print(temp[i][j] + " " + "...");
				} else {
					
					double cost_dist = Arrays.asList(phonetic.getNumber()).contains( String.valueOf(strs2.get(j-1) )) || String.valueOf(strs2.get(j-1)).equals(".") || String.valueOf(strs1.get(i-1)).equals(".") ? 0.5 : 1.0;
					double sub_cost = 0.0;
					
					if 	( Arrays.asList(phonetic.getVowels()).contains(String.valueOf(strs1.get(i-1))) && 
							Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs2.get(j-1) )) ) 
						sub_cost = 100.0;
						
					else if ( Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs1.get(i-1))) && 
									Arrays.asList(phonetic.getVowels()).contains(String.valueOf(strs2.get(j-1) )) ) 
						sub_cost = 100.0;
					
					else if ( Arrays.asList(phonetic.getVowels()).contains(String.valueOf(strs1.get(i-1))) && 
							Arrays.asList(phonetic.getVowels()).contains(String.valueOf(strs2.get(j-1) )) ) 
						sub_cost = 0.0;
					
					else if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs1.get(i-1))) && 
							Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs2.get(j-1) )) ) 
						sub_cost = 0.0;
					else if (strs1.get(i-1).length() > 1  && strs2.get(j-1).length() > 1 ){
//						if (Arrays.asList(phonetic.getDicritics2()).contains(String.valueOf(strs1.get(i-1)).charAt(strs1.size() - 1)) && 
//							String.valueOf(strs2.get(i-1).substring(0, strs1.size() - 1)).equals(String.valueOf(strs2.get(j-1))  ))
//						cost_dist = 0.5;
						//System.out.println("Are we also ssss here????");
						
						// add on 26/09/2017
//						
//						if ( Arrays.asList(phonetic.getVowels()).contains(String.valueOf(strs1.get(i-1))) || 
//								Arrays.asList(phonetic.getVowels()).contains(String.valueOf(strs2.get(j-1) )) ) {
//							cost_dist = 0.0;
//						}	
//						
//						else if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs1.get(i-1))) ||
//								Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs2.get(j-1) )) ) {
//							cost_dist = 0.0;
//							
//						}
						// end of adding
						//System.out.println(strs1.get(i-1) + " " + strs2.get(j-1));
						//System.out.println(strs1.get(i-1).length() + " " + strs2.get(j-1).length());
						//System.out.println(strs1.get(i-1).charAt(2) == '\u0361');

						
						if ( ( Arrays.asList(phonetic.getDicritics1()).contains(String.valueOf(String.valueOf(strs1.get(i-1)).charAt(0))) && 
								String.valueOf(strs1.get(i-1).substring(1)).equals(String.valueOf(strs2.get(j-1))))  || ( Arrays.asList(phonetic.getDicritics1()).contains(String.valueOf(String.valueOf(strs2.get(j-1)).charAt(0))) && 
										String.valueOf(strs2.get(j-1).substring(1)).equals(String.valueOf(strs1.get(i-1)))) ){
								cost_dist = 0.5;
//								System.out.println("pppp");
							}
						
						else if (Arrays.asList(phonetic.getDicritics2()).contains(String.valueOf(String.valueOf(strs1.get(i-1)).charAt(strs1.get(i-1).length() - 1 ))) && 
								String.valueOf(strs1.get(i-1).substring(0, strs1.get(i-1).length() - 1)).equals(String.valueOf(strs2.get(j-1))  )){
								cost_dist = 0.5;
//								System.out.println("pppp");
							}
//							System.out.println();
						
						
						else if (strs1.get(i-1).length() == 5 && strs2.get(j-1).length() == 5 ){
							cost_dist = 1.0;
						}
						

							
						else {
								cost_dist = 1.5;
							}
						
						
					}
					
					else if (strs1.get(i-1).length() > 1 ){
//						if (Arrays.asList(phonetic.getDicritics2()).contains(String.valueOf(strs1.get(i-1)).charAt(strs1.size() - 1)) && 
//							String.valueOf(strs2.get(i-1).substring(0, strs1.size() - 1)).equals(String.valueOf(strs2.get(j-1))  ))
//						cost_dist = 0.5;
						//System.out.println("Are we also here????");
						
// add on 26/09/17
						
//						 if ( Arrays.asList(phonetic.getVowels()).contains(String.valueOf(strs1.get(i-1))) || 
//							Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs1.get(i-1))) ) 
//							cost_dist = 0.0;
//						// end of adding 

						
						if (Arrays.asList(phonetic.getDicritics1()).contains(String.valueOf(String.valueOf(strs1.get(i-1)).charAt(0))) && 
								String.valueOf(strs1.get(i-1).substring(1)).equals(String.valueOf(strs2.get(j-1))  )){
								cost_dist = 0.5;
//								System.out.println("pppp");
							}
						
						else if (Arrays.asList(phonetic.getDicritics2()).contains(String.valueOf(String.valueOf(strs1.get(i-1)).charAt(strs1.get(i-1).length() - 1 ))) && 
								String.valueOf(strs1.get(i-1).substring(0, strs1.get(i-1).length() - 1)).equals(String.valueOf(strs2.get(j-1))  )){
								cost_dist = 0.5;
//								System.out.println("pppp");
							}
//							System.out.println();
						else if (strs1.get(i-1).length() == 5 ){
							cost_dist = 1.0;
						} 
							
						
						
						else {
								cost_dist = 1.5;
							}
						
						
					}
					
					else if (strs2.get(j-1).length() > 1) {
//						System.out.print(strs2.get(j-1).length() + " ");
//						System.out.print(strs2.get(j-1) + " ");
//						System.out.print(strs2.get(j-1).length()  - 1 + " ");
//						System.out.print(String.valueOf(strs2.get(j-1)).charAt(strs2.get(j-1).length() - 1));
//						System.out.print(" ");
//						System.out.print(Arrays.asList(phonetic.getDicritics2()).contains(String.valueOf("\u0325")));
//						
//						System.out.print(Arrays.asList(phonetic.getDicritics2()).contains(String.valueOf(String.valueOf(strs2.get(j-1)).charAt(strs2.get(j-1).length() - 1 ))));
//						
//						
//						System.out.print(strs2.get(j-1).substring(0, strs2.get(j-1).length() - 1));
						//System.out.print("Are we?");
						
						// add on 26/09/2017
//						if ( 
//								Arrays.asList(phonetic.getVowels()).contains(String.valueOf(strs2.get(j-1) )) 
//								|| Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs2.get(j-1)))  ) 
//							cost_dist = 0.0;
//						
//						
//						// end of editing
						//System.out.println(strs2.get(j-1).length() == 3);
						//System.out.println(strs2.get(j-1).length());

						
						if (Arrays.asList(phonetic.getDicritics1()).contains(String.valueOf(String.valueOf(strs2.get(j-1)).charAt(0))) && 
								String.valueOf(strs2.get(j-1).substring(1)).equals(String.valueOf(strs1.get(i-1))  )){
							    //System.out.println("Are we here?");
								cost_dist = 0.5;
							
						}
						else if (Arrays.asList(phonetic.getDicritics2()).contains(String.valueOf(String.valueOf(strs2.get(j-1)).charAt(strs2.get(j-1).length() - 1 ))) && 
							String.valueOf(strs2.get(j-1).substring(0, strs2.get(j-1).length() - 1)).equals(String.valueOf(strs1.get(i-1))  )){
							cost_dist = 0.5;
//							System.out.println("pppp");
						}
						
						else if  (strs2.get(j-1).length() == 5 ){
							cost_dist = 1.0;
							//System.out.println("QAAAAA!");
						}
						


//						System.out.println();
						
						else {
							cost_dist = 1.5;
							//System.out.println("Hi");
						}
			
					}
					
					temp[i][j] = cost_dist + min(temp[i - 1][j - 1] + sub_cost, temp[i - 1][j], temp[i][j - 1]);
					//System.out.print(temp[i][j] + " ");
				}
			}
			//System.out.println();
		}
		
		
		T = temp;
		//System.out.println(printActualEdits(str1, str2));
		return temp[strs1.size()][strs2.size()];
	}

	/**
	 * Prints the actual edits which needs to be done.
	 */
	public String printActualEdits(List<String> strs1, List<String> strs2) {
		//char[] str1 = string1.toCharArray();
		//char[] str2 = string2.toCharArray();
		
		int i = T.length - 1;
		int j = T[0].length - 1;
		
		
		
		StringBuilder result = new StringBuilder();
		while (true) {
			//System.out.println(str1[i - 1 ] + " " + str2[j - 1]);
			if (i == 0 && j == 0) {
				break;
			}
			if (i == 0) { 
				//result.append("d");
				if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs2.get(j-1) ))) result.append("%");
				
				else result.append("i");	
				j--;
				continue;
			
			}
			
			if (j == 0) {
				result.append("d");
				i--;
				continue;
			}
			if (strs1.get(i-1).equals(strs2.get(j-1))) {
				if (Arrays.asList(phonetic.getConsonants()).contains( String.valueOf( strs1.get(i-1)  ))){  // && 
						//Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs2.get(j-1)))){
					result.append("|");
				}
				else {
//					System.out.println(strs1.get(i-1));
//					System.out.println( Arrays.asList(phonetic.getConsonants()).contains("g"));
//					System.out.println( Arrays.asList(phonetic.getConsonants()).contains( strs1.get(i-1))); 
//					System.out.println(".....");
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
				if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(strs2.get(j-1) ))) result.append("%");
				
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
				//throw new IllegalArgumentException("Some wrong with given data");
				
				 result.append("?");
					i = i - 1;
					j = j - 1;
				 
				
			}
		}
		return result.reverse().toString();
	}

	private double min(double a, double b, double c) {
		double l = Math.min(a, b);
		return Math.min(l, c);
	}
	
	
	

//	public static void main(String args[]) {
//		String str1 = "kitten";
//		String str2 = "sittig";
//		String str3 = "\u0276\u0276\u0234\u02A7";
//		String str4 = "\u0276\u0276\u0288\u0153";
//		System.out.println(str3.length());
//		System.out.println(str1);
//		System.out.println(str2);
//		System.out.println(str3);
//		EDistanceWithOutputWithWeight editDistance = new EDistanceWithOutputWithWeight();
//		
//		//double result = editDistance.dynamicEditDistance(str3, str4);
//		//String symbol = editDistance.printActualEdits(str3, str4);
//		//System.out.print(result);
//		System.out.println();
//		//System.out.println(symbol);
//	}

}
