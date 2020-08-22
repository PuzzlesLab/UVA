import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	public static int[] generateOccurence(String s) {
		int [] occurence=new int [128];
		for (char c : s.toCharArray()) occurence[c]++;
		Arrays.sort(occurence);
		return occurence;
	}
	
	public static boolean compareIntArray(int [] ints1, int[] ints2) {
		for (int i=0;i<128;i++) if (ints1[i]!=ints2[i]) return false;
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			System.out.println(compareIntArray(generateOccurence(s),generateOccurence(br.readLine())) ? "YES" : "NO" );
		}
	}
}