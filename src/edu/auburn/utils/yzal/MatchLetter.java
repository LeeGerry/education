package edu.auburn.utils.yzal;

import java.util.Arrays;

public class MatchLetter {

	private static Phonetic phonetic = new Phonetic();

	public String findWrong(String word1st, String word2rd) {
		if ("".equals(word1st) || "".equals(word2rd) || null == word1st || null == word2rd) {
			return "nothing";
		}
		if (word1st.equals(word2rd)) {
			return translateToAlignmentCode(word1st);
		} else {
			String result = allreplace(word1st, word2rd);
			return result;
		}

	}

	/*
	 * finds LCS and locates them , the position[0,1] is started and ended LCS
	 * position of 1st word the position[2,3] is started and ended LCS position
	 * of 2rd word the position[5] is LCS length
	 */
	public int[] findLongestSubStringPosition(char[] word1, char[] word2) {

		int[] position = new int[5];
		// records which position is the longest.
		int[][] LCS_Matrix = new int[word1.length + 1][word2.length + 1];
		int max = -1;
		int word1_x = 0, word2_y = 0;
		for (int i = 0; i <= word1.length; i++) {
			for (int j = 0; j <= word2.length; j++) {
				if (i == 0 || j == 0) {
					LCS_Matrix[i][j] = 0;
				} else if (word1[i - 1] == word2[j - 1]) {
					LCS_Matrix[i][j] = LCS_Matrix[i - 1][j - 1] + 1;
				} else {
					LCS_Matrix[i][j] = 0;
				}
				if (LCS_Matrix[i][j] > max) {
					max = LCS_Matrix[i][j];
					word1_x = i;
					word2_y = j;
				}
			}
		}
		int LCS_length = max;
		char[] LCS_Array = new char[LCS_length];
		int word1_PositionLCS = word1_x - 1;
		int word2_positionLCS = word2_y - 1;
		position[1] = word1_x - 1;
		position[3] = word2_y - 1;
		while (word1_PositionLCS >= 0 && word2_positionLCS >= 0) {
			if (word1[word1_PositionLCS] == word2[word2_positionLCS]) {
				LCS_length--;
				LCS_Array[LCS_length] = word1[word1_PositionLCS];
				// word1_Array[word1_PositionLCS]=' ';
				word1_PositionLCS--;
				word2_positionLCS--;
			} else {
				break;
			}
		}
		position[0] = position[1] - (LCS_Array.length - 1);
		position[2] = position[3] - (LCS_Array.length - 1);
		position[4] = LCS_Array.length;// LCS_length
		// String result = String.valueOf(LCS_Array);
		return position;
	}

	/* if same type, like ruf and seb, they are consonant/vowel/consonant */
	public String matchOneByOne(char[] word1, char[] word2, String flag) {
		char[] flag_array = flag.toCharArray();
		StringBuffer resultString = new StringBuffer();
		for (int i = 0; i < word1.length; i++) {
			if (word1[i] == word2[i] && flag_array[i] == '*') {
				resultString.append("*");
			} else if (word1[i] == word2[i] && flag_array[i] == '|') {
				resultString.append("|");
			} else if (word1[i] != word2[i] && flag_array[i] == '*') {
				resultString.append("s");
			} else if (word1[i] != word2[i] && flag_array[i] == '|') {
				resultString.append("s");
			}
		}

		return resultString.toString();
	}

	// translates char[] to String
	public String translateCharArrayToString(char[] word) {
		String string = new String(word);
		return string;
	}

	// insertion
	public String insertion(char[] word) {
		// Phonetic phonetic = new Phonetic();
		if (word == null || word.length == 0) {
			return "";
		}
		StringBuffer resultInsertion = new StringBuffer();
		for (char c : word) {
			if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(c))) {
				resultInsertion.append("%");
			} else if (Arrays.asList(phonetic.getVowels()).contains(String.valueOf(c))) {
				resultInsertion.append("i");
			} else if (Arrays.asList(phonetic.getNumber()).contains(String.valueOf(c))) {
				resultInsertion.append("!");
			}
		}
		return resultInsertion.toString();
	}

	// deletion
	public String deletion(char[] word) {
		if (word == null || word.length == 0) {
			return "";
		}
		StringBuffer resultDeletion = new StringBuffer();
		for (char c : word) {
			resultDeletion.append("d");
		}
		return resultDeletion.toString();
	}

	/*
	 * when we get the LCS, means we locate the same part between two strings,
	 * we compare the prefix(front part of LCS) and suffix(behind part of LCS).
	 */
	public String compareByPostion(int[] position, char[] word1, char[] word2) {

		if (position == null || position.length == 0 || position[4] == 0) {
			return "";
		}
		char[] word1_left = new char[position[0]];
		char[] word2_left = new char[position[2]];
		char[] word1_right = new char[word1.length - position[1] - 1];
		char[] word2_right = new char[word2.length - position[3] - 1];
		// word1_left
		if (position[0] == 0) {
			word1_left = null;
		} else {
			for (int i = 0; i <= position[0] - 1; i++) {
				word1_left[i] = word1[i];
			}
		}
		// word1_right
		if (position[1] + 1 >= word1.length) {
			word1_right = null;
		} else {
			int word1_j = 0;
			for (int i = position[1] + 1; i < word1.length; i++) {
				word1_right[word1_j] = word1[i];
				word1_j++;
			}
		}
		// word2_left
		if (position[2] == 0) {
			word2_left = null;
		} else {
			for (int i = 0; i <= position[2] - 1; i++) {
				word2_left[i] = word2[i];
			}
		}
		// word2_right
		if (position[3] + 1 >= word2.length) {
			word2_right = null;
		} else {
			int word2_j = 0;
			for (int i = position[3] + 1; i < word2.length; i++) {
				word2_right[word2_j] = word2[i];
				word2_j++;
			}
		}

		String medium = new String();
		for (int i = position[0]; i <= position[1]; i++) {
			if (Arrays.asList(phonetic.getVowels()).contains(String.valueOf(word1[i]))) {
				medium += "*";
			} else if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(word1[i]))) {
				medium += "|";
			} else if (Arrays.asList(phonetic.getNumber()).contains(String.valueOf(word1[i]))) {
				medium += "!";
			}
		}
		String head = this.compareOneSidePart(word1_left, word2_left);

		String tail = this.compareOneSidePart(word1_right, word2_right);

		return head + medium + tail;
	}

	// compare right or left part
	public String compareOneSidePart(char[] word1_right, char[] word2_right) {
		if (word1_right == null && word2_right == null) {
			return "";
		}
		String resultRight = new String();
		if (word1_right == null) {
			resultRight = this.insertion(word2_right);
			return resultRight;
		}
		if (word2_right == null) {
			resultRight = this.deletion(word1_right);
			return resultRight;
		}
		// if it's not empty
		int[] position = this.findLongestSubStringPosition(word1_right, word2_right);
		// find same part, and there is no same part
		if (position == null || position.length == 0 || position[4] == 0) {
			String word1_replace = this.translateToAlignmentCode(word1_right);
			String word2_replace = this.translateToAlignmentCode(word2_right);
			// if there is same vowels and consonants, means they have same
			// length
			if (word1_replace.equals(word2_replace)) {
				resultRight = this.matchOneByOne(word1_right, word2_right, word2_replace);
			} else if (word1_right.length < word2_right.length) {
				// if they don't have same length
				int next_word1 = 0, next_word2 = 0;

				while (next_word1 < word1_right.length) {
					if (next_word2 >= word2_right.length) {
						break;
					}
					while (next_word2 < word2_right.length) {
						if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getConsonantList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "s";
							next_word1++;
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getVowelsList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "s";
							next_word1++;
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getConsonantList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "d";
							next_word2++;
							break;
						} else if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getVowelsList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "i";
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getNumberList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "!";
							next_word2++;
							break;
						} else if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								|| phonetic.getNumberList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "!";
							next_word2++;
							break;
						}

					}
				}
				if (next_word1 >= word1_right.length) {
					for (int i = next_word2; i < word2_right.length; i++) {
						if (phonetic.getVowelsList().contains(String.valueOf(word2_right[i]))) {
							resultRight += "i";
						} else if (phonetic.getConsonantList().contains(String.valueOf(word2_right[i]))) {
							resultRight += "%";
						} else if (phonetic.getNumberList().contains(String.valueOf(word2_right[i]))) {
							resultRight += "!";
						}
					}
				}else if (next_word2>=word2_right.length) {
					for (int i = next_word1; i < word1_right.length; i++) {
						resultRight += "d";
					}
				}
				return resultRight;
			} else if (word1_right.length > word2_right.length) {

				// if they don't have same length
				int next_word1 = 0, next_word2 = 0;
				// if they don't have same length
				while (next_word2 < word2_right.length) {
					while (next_word1 < word1_right.length) {
						if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getConsonantList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "s";
							next_word1++;
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getVowelsList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "s";
							next_word1++;
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getConsonantList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "d";
							next_word2++;
							break;
						} else if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getVowelsList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "i";
							next_word2++;
							break;
						} else if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getNumberList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "!";
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getNumberList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "!";
							next_word2++;
							break;
						}
					}
				}
				for (int i = next_word1; i < word1_right.length; i++) {
					resultRight += "d";
				}
				return resultRight;
			} else if (word1_right.length == word2_right.length) {
				int next_word1 = 0, next_word2 = 0;
				// if they have same length and same type
				String flag_word1 = this.translateToAlignmentCode(word1_right);
				String flag_word2 = this.translateToAlignmentCode(word2_right);
				if (flag_word1.equals(flag_word2)) {
					resultRight=this.matchOneByOne(word1_right, word2_right, flag_word1);
					return resultRight;
				}
				// if they only have same length
				while (next_word2 < word2_right.length) {
					while (next_word1 < word1_right.length) {
						if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getConsonantList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "s";
							next_word1++;
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getVowelsList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "s";
							next_word1++;
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getConsonantList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "d";
							next_word2++;
							break;
						} else if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getVowelsList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "i";
							next_word2++;
							break;
						} else if (phonetic.getConsonantList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getNumberList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "!";
							next_word2++;
							break;
						} else if (phonetic.getVowelsList().contains(String.valueOf(word1_right[next_word1]))
								&& phonetic.getNumberList().contains(String.valueOf(word2_right[next_word2]))) {
							resultRight += "!";
							next_word2++;
							break;
						}
					}
				}
				for (int i = next_word1; i < word1_right.length; i++) {
					resultRight += "d";
				}
				return resultRight;
			}
		} else {
			// if it's not empty, and has same part
			resultRight = this.compareByPostion(position, word1_right, word2_right);
		}

		return resultRight;
	}

	// this is the entrance function for program
	public double calculateScore(String result) {
		if (result.equals("Empty") || result == null || result.equals("") || result.length() == 0) {
			return -1.0;
		}
		double score = 0.0;
		char[] resultSet = result.toCharArray();
		for (char c : resultSet) {
			if (c == '*') {
				score += 0;
			} else if (c == '|') {
				score += 0;
			} else if (c == '%') {
				score += 1;
			} else if (c == 'i') {
				score += 1;
			} else if (c == 'd') {
				score += 1;
			} else if (c == 's') {
				score += 1;
			} else if (c == '!') {
				score += 0.5;
			}
		}

		return score;
	}
	//if student gives an empty string, then string will be wrong whatever it is. 
	public String ifMissStudentString(String word){
		String result="";
		for(int i=0;i<word.length();i++){
			result+="d";
		}
		return result;
	}
	//if teacher gives an empty string, then string will be right whatever it is. 
	public String ifMissTeacherString(String word){
		String result="";
		for(int i=0;i<word.length();i++){
			result+="|";
		}
		return result;
	}
	// checks if two words have same vowels and consonants at same position
	public String allreplace(String word1, String word2) {
		if (word2 == null || word2.equals("")|| word2.length() == 0) {
			return this.ifMissStudentString(word1);
		}
		if (word1 == null || word1.equals("") || word1.length() == 0) {
			return this.ifMissTeacherString(word2);
		}
		
		char[] word1_Array = word1.toCharArray();
		char[] word2_Array = word2.toCharArray();

		String word1_replace = this.translateToAlignmentCode(word1);
		String word2_replace = this.translateToAlignmentCode(word2);

		String result = new String();
		if (word1_replace.equals(word2_replace)) {
			// match one by one;
			result = this.matchOneByOne(word1_Array, word2_Array, word2_replace);
		} else {
			int[] position = this.findLongestSubStringPosition(word1_Array, word2_Array);
			result = this.compareByPostion(position, word1_Array, word2_Array);

		}
		return result;
	}

	/* For String: translates the original word to symbol string */
	public String translateToAlignmentCode(String inputLetter) {
		// Phonetic phonetic = new Phonetic();
		char[] getLetter = inputLetter.toCharArray();
		StringBuffer alignment = new StringBuffer();

		for (char letter : getLetter) {
			if (Arrays.asList(phonetic.getVowels()).contains(String.valueOf(letter))) {
				alignment.append("*");
			} else if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(letter))) {
				alignment.append("|");
			} else if (Arrays.asList(phonetic.getNumber()).contains(String.valueOf(letter))) {
				alignment.append("!");
			}
		}
		return alignment.toString();
	}

	/* For char[]: translates the original word to symbol string */
	public String translateToAlignmentCode(char[] inputLetters) {

		StringBuffer alignment = new StringBuffer();

		for (char letter : inputLetters) {
			if (Arrays.asList(phonetic.getVowels()).contains(String.valueOf(letter))) {
				alignment.append("*");
			} else if (Arrays.asList(phonetic.getConsonants()).contains(String.valueOf(letter))) {
				alignment.append("|");
			} else if (Arrays.asList(phonetic.getNumber()).contains(String.valueOf(letter))) {
				alignment.append("!");
			}
		}
		return alignment.toString();
	}
	/**
	 * get string format for teacher
	 * @param word1
	 * @param word2
	 * @return
	 */
	public String getWeightedString(String word1,String word2){
		String result=this.allreplace(word1, word2);
		if (result == null || result.equals("") || result.length() == 0) {
			return word1.length()>word2.length()?this.ifMissStudentString(word1):this.ifMissStudentString(word2);
		}
		return result;
	}
	
	public float finalCalculate(String word1,String word2){
		float distance=(float)this.calculateScore(this.allreplace(word1, word2));
		if(-1.0==distance){
			distance=word1.length()>word2.length()?word1.length():word2.length();
		}
		return distance;
	}
	
}
