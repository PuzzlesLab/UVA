import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	private static char [] chars;
	private static int maxDist=Integer.MIN_VALUE;
	private static String maxStr="";
	
	private static String prevPermutation (String s) {
		//	https://www.geeksforgeeks.org/lexicographically-previous-permutation-in-c/
		char [] c=s.toCharArray();
		int p=s.length()-1;
		while (p>0 && c[p-1]<=c[p]) p--;
		if (p<=0) return "";

		int q=p-1;
		while (q+2<=s.length() && c[q+1]<=c[p-1]) q++;
		
		char temp=c[p-1];
		c[p-1]=c[q];
		c[q]=temp;
		
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<p;i++) sb.append(c[i]);
		for (int i=c.length-1;i>=p;i--) sb.append(c[i]);
		
		return sb.toString();
	}
	
	private static String nextPermutation (String s) {
		//	https://leetcode.com/problems/next-permutation/description/
		char [] c=s.toCharArray();
		int p=s.length()-2;
		while (p>=0 && c[p]>c[p+1]) p--;
		if (p<0) return "";
		
		int q=s.length()-1;
		while (q>p && c[q]<c[p]) q--;
		
		char temp=c[p];
		c[p]=c[q];
		c[q]=temp;
		
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<=p;i++) sb.append(c[i]);
		for (int i=c.length-1;i>p;i--) sb.append(c[i]);
		
		return sb.toString();
	}
	
	private static int minDist(String s) {
		int dist=Integer.MAX_VALUE;
		for (int i=0;i<s.length()-1;i++) dist=Math.min(dist, Math.abs(s.charAt(i)-s.charAt(i+1)));
		return dist;
	}
	
	private static void updateMax(String s, boolean equal) {
		int d=minDist(s);
		if ((!equal && d>maxDist) || (equal && d>=maxDist)) {
			maxDist=d;
			maxStr=s;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			chars=s.toCharArray();
			Arrays.sort(chars);
			maxDist=Integer.MIN_VALUE;
			maxStr="";
			
			String start=s;
			for (int i=0;i<10;i++) {
				start=prevPermutation(start);
				if (start.length()>0) updateMax(start, true);
				else break;
			}
			
			updateMax(s, false);
			
			start=s;
			for (int i=0;i<10;i++) {
				start=nextPermutation(start);
				if (start.length()>0) updateMax(start, false);
				else break;
			}
			
			System.out.println(maxStr+maxDist);
		}
	}

}
