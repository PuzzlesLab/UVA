import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Main {

	private static final String [] CODONS={
			"UUU", "UCU", "UAU", "UGU",
			"UUC", "UCC", "UAC", "UGC",
			"UUA", "UCA",
			"UUG", "UCG",        "UGG",
			
			"CUU", "CCU", "CAU", "CGU",
			"CUC", "CCC", "CAC", "CGC",
			"CUA", "CCA", "CAA", "CGA",
			"CUG", "CCG", "CAG", "CGG",
			
			"AUU", "ACU", "AAU", "AGU",
			"AUC", "ACC", "AAC", "AGC",
			"AUA", "ACA", "AAA", "AGA",
			"AUG", "ACG", "AAG", "AGG",
			
			"GUU", "GCU", "GAU", "GGU",
			"GUC", "GCC", "GAC", "GGC",
			"GUA", "GCA", "GAA", "GGA",
			"GUG", "GCG", "GAG", "GGG",
		};
	private static final String [] PROTEINS={
			"Phe", "Ser", "Tyr", "Cys",
			"Phe", "Ser", "Tyr", "Cys",
			"Leu", "Ser",
			"Leu", "Ser",        "Trp",

			"Leu", "Pro", "His", "Arg",
			"Leu", "Pro", "His", "Arg",
			"Leu", "Pro", "Gln", "Arg",
			"Leu", "Pro", "Gln", "Arg",
			
			"Ile", "Thr", "Asn", "Ser",
			"Ile", "Thr", "Asn", "Ser",
			"Ile", "Thr", "Lys", "Arg",
			"Met", "Thr", "Lys", "Arg",
			
			"Val", "Ala", "Asp", "Gly",
			"Val", "Ala", "Asp", "Gly",
			"Val", "Ala", "Glu", "Gly",
			"Val", "Ala", "Glu", "Gly",
		};
	private static final String START="AUG";
	private static final HashSet<String> END=new HashSet<>();
	private static final char [] PAIR=new char [128];
	private static final String INVALID="*** No translatable DNA found ***";
	private static final HashMap<String,String> MAP=new HashMap<>();

	private static String complement(String s) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<s.length();i++) {
			char c=PAIR[s.charAt(i)];
			if (c=='T') c='U';
			sb.append(c);
		}
		return sb.toString();
	};

	private static String replaceTU(String s) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (c=='T') c='U';
			sb.append(c);
		}
		return sb.toString();
	}

	private static String reverse(String s) {
		StringBuilder sb=new StringBuilder();
		for (int i=s.length()-1;i>=0;i--) sb.append(s.charAt(i));
		return sb.toString();
	}

	private static ArrayList<String> testSequence(String s) {
		ArrayList<String> tokens=null;
		for (int i=0;i+3<=s.length();i++) {
			if (!s.substring(i,i+3).equals(START)) continue;

			tokens=new ArrayList<>();
			boolean hasEnd=false;
			for (int i2=i+3;i2+3<=s.length();i2+=3) {
				String token=s.substring(i2,i2+3);
				if (END.contains(token)) {
					hasEnd=true;
					break;
				}
				if (!MAP.containsKey(token)) return null;
				tokens.add(MAP.get(token));
			}
			if (tokens.size()>0 && hasEnd) break;
			else tokens=null;
		}
		return tokens;
	}

	public static void main (String [] args) throws Exception {
		END.add("UAA");
		END.add("UAG");
		END.add("UGA");
		PAIR['A']='T';
		PAIR['T']='A';
		PAIR['C']='G';
		PAIR['G']='C';
		for (int i=0;i<CODONS.length;i++) MAP.put(CODONS[i],PROTEINS[i]);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			if (s.equals("*")) break;
			ArrayList<String> candidates=new ArrayList<>();
			candidates.add(reverse(complement(s)));
			candidates.add(complement(s));
			candidates.add(replaceTU(reverse(s)));
			candidates.add(replaceTU(s));

			ArrayList<String> output=null;
			for (int i=0;i<candidates.size() && output==null;i++) output=testSequence(candidates.get(i));

			if (output!=null) {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<output.size();i++) {
					sb.append(output.get(i));
					sb.append('-');
				}
				if (sb.length()>0) sb.setLength(sb.length()-1);
				System.out.println(sb);
			}
			else System.out.println(INVALID);
		}
	}

}
