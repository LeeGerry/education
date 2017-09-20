package edu.auburn.utils.scal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreProcess {
	//private String[] press = {"ˈ", "ˌ"};
	
	//private String[] dicritics = {"\u0303", "\u034A"};
	
	private Phonetic phonetic = new Phonetic();
	
	
	public List<String> generateListOfStrings(String str){
		//char[] str1 = str.toCharArray();
		List<String> res = new ArrayList<>();
		if (str.length() == 0 ){
			return  res;
		}
		
		for (int i = 0; i < str.length(); i++){
			String temp =  str.charAt(i)  + "";
			
			if  ( i + 2 < str.length() && str.charAt(i+1) == '\u0361' ) { // || (  i + 2 < str.length() && str.charAt(i+1) == '.' )){
//				if ( str1[i] == 'e' && str1[i+1] == 'ɪ'){
					temp +=  str.charAt(i+1)  + str.charAt(i+2);
//					res.add(temp);
//					i += 2;
					if (i + 3 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+3) ))){
						temp += str.charAt(i+3);
						i++;
					}
					res.add(temp);
					i += 2;
				}
			
			
			else if (i + 1 < str.length() && Arrays.asList(phonetic.getDicritics1()).contains( String.valueOf(str.charAt(i) ))){
				temp +=  str.charAt(i+1) ;
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
				
			}
			
			else if (i + 1 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+1) ))){
				//System.out.println("We are here!");
				temp +=  str.charAt(i+1) ;
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
				
			}
			
			else if ( i + 1 < str.length() && str.charAt(i) == 'a' && str.charAt(i+1)   == 'ɪ' ){
//				if ( str1[i] == 'e' && str1[i+1] == 'ɪ'){
					temp +=  str.charAt(i+1) ;
					if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
						temp += str.charAt(i+2);
						i++;
					}
					res.add(temp);
					i++;
				}
//			else if ( i + 1 < str.length() &&  str.charAt(i) == 'ɑ' && str.charAt(i+1) == 'u'){
//					temp += str.charAt(i+1);
//					res.add(temp);
//					i++;
//			 }
			
			else if ( i + 1 < str.length() &&  str.charAt(i) == 'ɑ' && str.charAt(i+1) == 'ʊ'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			else if ( i + 1 < str.length() &&  str.charAt(i) == 'ɪ' && str.charAt(i+1) == 'r'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			else if ( i + 1 < str.length() &&  str.charAt(i) == 'ɛ' && str.charAt(i+1) == 'r'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			else if ( i + 1 < str.length() &&  str.charAt(i) == 'ʊ' && str.charAt(i+1) == 'r'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			else if ( i + 1 < str.length() &&  str.charAt(i) == 'ɪ' && str.charAt(i+1) == 'r'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			
			
			
			else if ( i + 1 < str.length() &&  str.charAt(i) == 'ɔ' && str.charAt(i+1) == 'ɪ'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			
			
			
			else if ( i + 1 < str.length() && str.charAt(i) == 'e' && str.charAt(i+1) == 'ɪ'){
					temp += str.charAt(i+1);
					if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
						temp += str.charAt(i+2);
						i++;
					}
					res.add(temp);
					i++;
				}
			else if (i + 1 < str.length() && str.charAt(i) == 't' && str.charAt(i+1) == 'ʃ' ){
					temp += str.charAt(i+1);
					if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
						temp += str.charAt(i+2);
						i++;
					}
					res.add(temp);
					i++;
				}
			else if (i + 1 < str.length() && str.charAt(i) == 'd' && str.charAt(i+1) == 'ʒ'){
					temp += str.charAt(i+1);
					if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
						temp += str.charAt(i+2);
						i++;
					}
					res.add(temp);
					i++;
				}
			
			else if (i + 1 < str.length() && str.charAt(i) == 'd' && str.charAt(i+1) == 'z'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			
			else if (i + 1 < str.length() && str.charAt(i) == 'd' && str.charAt(i+1) == 'r'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			else if (i + 1 < str.length() && str.charAt(i) == 't' && str.charAt(i+1) == 'r'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			else if (i + 1 < str.length() && str.charAt(i) == 't' && str.charAt(i+1) == 's'){
				temp += str.charAt(i+1);
				if (i + 2 < str.length() && Arrays.asList(phonetic.getDicritics2()).contains( String.valueOf(str.charAt(i+2) ))){
					temp += str.charAt(i+2);
					i++;
				}
				res.add(temp);
				i++;
			}
			
			
			
			
			
				
			else {
				res.add(temp);
				
			}
		}
		
		return res;
		
	}

}
