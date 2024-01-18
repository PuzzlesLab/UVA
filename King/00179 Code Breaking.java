import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	private static String FindPPt;
	private static String FindPEnc;
	private static int [] FindPP;

	private static String decrypt(String s, int [] p) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<s.length();i+=p.length) {
			for (int i2=0;i2<p.length;i2++) {
				sb.append(s.charAt(i+p[i2]));
			}
		}
		return sb.toString();
	}

	private static void findPHelper(int [] p, int pIdx, boolean [] flag) {
		if (pIdx==p.length) {
			FindPP=Arrays.copyOf(p,pIdx);
			return;
		}
		for (int i=0;i<p.length;i++) if (!flag[i]) {
			boolean check=true;
			for (int start=0;start<FindPPt.length();start+=p.length) {
				check &= FindPPt.charAt(start+pIdx)==FindPEnc.charAt(start+i);
			}
			if (!check) continue;

			p[pIdx]=i;
			flag[i]=true;
			findPHelper(p,pIdx+1,flag);
			flag[i]=false;
		}
	};

	private static int [] findP(String pt, String enc, int k) {
		FindPPt=padString(pt,k);
		FindPEnc=padString(enc,k);
		FindPP=null;
		int [] p=new int [k];
		boolean [] flag=new boolean [k];
		findPHelper(p,0,flag);
		return FindPP;
	}

	private static String padString(String s, int m) {
		StringBuilder sb=new StringBuilder(s);
		while (sb.length()%m!=0) sb.append('?');
		return sb.toString();
	}

	private static boolean checkK(String s1, String s2, int k) {
		for (int i=0;i<s1.length();i+=k) {
			int [] exists1=new int[128];
			int [] exists2=new int[128];
			for (int i2=0;i2<k;i2++) {
				exists1[s1.charAt(i+i2)]++;
				exists2[s2.charAt(i+i2)]++;
			}
			for (int i2=0;i2<exists1.length;i2++) if (exists1[i2]!=exists2[i2]) return false;
		}
		return true;
	}

	private static ArrayList<Integer> findK(String pt, String ct1) {
		ArrayList<Integer> result=new ArrayList<>();
		for (int k=1;k<=pt.length();k++) if (checkK(padString(pt,k),padString(ct1,k),k)) result.add(k);
		return result;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String pt=br.readLine();
			if (pt.charAt(0)=='#') break;
			String ct1=br.readLine();
			String ct2=br.readLine();

			ArrayList<Integer> kList=findK(pt,ct1);
			if (kList.size()==0) {
				System.out.println(ct2);
				continue;
			}

			int [] p=null;
			for (int i=0;i<kList.size() && p==null;i++) p=findP(pt,ct1,kList.get(i));
			System.out.println(p==null?ct2:decrypt(padString(ct2,p.length),p));
		}
	}

}
