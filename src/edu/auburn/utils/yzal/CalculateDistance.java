package edu.auburn.utils.yzal;

public class CalculateDistance {
	
	public int countDistance(String result) {
		
		char[] resultArray=result.toCharArray();
		int count=0;
		for (char c : resultArray) {
			if (c=='*') {
				count+=0;
			}else if (c=='|') {
				count+=0;
			}else if (c=='%') {
				count+=1;
			}else if (c=='i') {
				count+=1;
			}else if (c=='d') {
				count+=1;
			}else if (c=='s') {
				count+=1;
			}else if (c=='i') {
				count+=0;
			}else if (c=='!') {
				count+=0.5;
			}
		}
		
		return count;
	}
}
