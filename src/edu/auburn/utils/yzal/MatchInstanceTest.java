package edu.auburn.utils.yzal;

public class MatchInstanceTest {
	
	public static void main(String[] args) {
		MatchLetter mLetter=new MatchLetter();
		mLetter.allreplace("aaa", "bbb");
		mLetter.allreplace("@nImxl", "flEmxnxv");
		//System.out.println("d̥ɪʃɪz".substring(1,2));
		
		System.out.println("case_extra_1, dɪʃɪz, d̥ɪʃɪz"+": "+mLetter.allreplace("dɪʃɪz", "d̥ɪʃɪz")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("dɪʃɪz", "d̥ɪʃɪz")));
		System.out.println("case_extra_2, dISIz, d1ISIz"+": "+mLetter.allreplace("dISIz", "d1ISIz")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("dISIz", "d1ISIz")));
		System.out.println();
		
		int i=1;
		System.out.println("------------Cases from excel file--------------------------------");		
		System.out.println("case"+i+" kitten, sitting"+": "+mLetter.allreplace("kitten", "sitting")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("kitten", "sitting")));
		i++;
		System.out.println("case"+i+" blah, blah"+": "+mLetter.allreplace("blah", "blah")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("blah", "blah")));
		i++;
		System.out.println("case"+i+" ba, ab"+": "+mLetter.allreplace("ba", "ab")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("ba", "ab")));
		i++;
		System.out.println("case"+i+" abcd, dcba"+": "+mLetter.allreplace("abcd", "dcba")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("abcd", "dcba")));
		i++;
		System.out.println("case"+i+" a, abcde"+": "+mLetter.allreplace("a", "abcde")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("a", "abcde")));
		i++;
		System.out.println("case"+i+" d, abcdef"+": "+mLetter.allreplace("d", "abcdef")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("d", "abcdef")));
		i++;
		System.out.println("case"+i+" abcdefg, bcd"+": "+mLetter.allreplace("abcdefg", "bcd")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("abcdefg", "bcd")));
		i++;
		System.out.println("case"+i+" abcdefg, ceg"+": "+mLetter.allreplace("abcdefg", "ceg")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("abcdefg", "ceg")));
		i++;
		System.out.println("case"+i+" cat, cat1"+": "+mLetter.allreplace("cat", "cat1")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("cat", "cat1")));
		i++;
		System.out.println("case"+i+" k@t, k@t1"+": "+mLetter.allreplace("k@t", "k@t1")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("k@t", "k@t1")));
		i++;
		System.out.println("case"+i+" dagi, dagiz"+": "+mLetter.allreplace("dagi", "dagiz")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("dagi", "dagiz")));
		i++;
		System.out.println("case"+i+" SuSuSu, dabaS"+": "+mLetter.allreplace("SuSuSu", "dabaS")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("SuSuSu", "dabaS")));
		i++;
		System.out.println("case"+i+" dagiwagilagima, glu"+": "+mLetter.allreplace("dagiwagilagima", "glu")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("dagiwagilagima", "glu")));
		i++;
		System.out.println("case"+i+" feT, Tef"+": "+mLetter.allreplace("feT", "Tef")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("feT", "Tef")));
		i++;
		System.out.println("case"+i+" fT, Tf"+": "+mLetter.allreplace("fT", "Tf")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("fT", "Tf")));
		i++;
		System.out.println("case"+i+" fes, ruf"+": "+mLetter.allreplace("fes", "ruf")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("fes", "ruf")));
		i++;
		System.out.println("case"+i+" strIG, sIG"+": "+mLetter.allreplace("strIG", "sIG")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("strIG", "sIG")));
		i++;
		System.out.println("case"+i+" TIkInIG, TIkInin"+": "+mLetter.allreplace("TIkInIG", "TIkInin")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("TIkInIG", "TIkInin")));
		i++;
		System.out.println("case"+i+" @nImxl, flEmxnxv"+": "+mLetter.allreplace("@nImxl", "flEmxnxv")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("@nImxl", "flEmxnxv")));
		i++;
		System.out.println("case"+i+" -n-m-l, fl-m-n-v"+": "+mLetter.allreplace("-n-m-l", "fl-m-n-v")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("-n-m-l", "fl-m-n-v")));
		i++;
		System.out.println("case"+i+" nml, flmnv"+": "+mLetter.allreplace("nml", "flmnv")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("nml", "flmnv")));
		i++;
		System.out.println("case"+i+" kar, krar"+": "+mLetter.allreplace("kar", "krar")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("kar", "krar")));
		i++;
		System.out.println("case"+i+" gEr, grErt"+": "+mLetter.allreplace("gEr", "grErt")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("gEr", "grErt")));
		i++;
		System.out.println("case"+i+" gV, glV"+": "+mLetter.allreplace("gV", "glV")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("gV", "glV")));
		i++;
		System.out.println("case"+i+" gV, gl1V"+": "+mLetter.allreplace("gV", "gl1V")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("gV", "gl1V")));
		i++;
		System.out.println("case"+i+" snobal, balun"+": "+mLetter.allreplace("snobal", "balun")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("snobal", "balun")));
		i++;
		System.out.println("case"+i+" gxzEl, zElIs"+": "+mLetter.allreplace("gxzEl", "zElIs")+"; Score:"+mLetter.calculateScore(mLetter.allreplace("gxzEl", "zElIs")));
		i++;
		System.out.println("------------------------------end-----------------------------------");

	}
}
