import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class Main {

	private static class Preference {
		public int [] status=new int [128]; // 0-don't care, 1=want, 2=don't want
		public int satisfyCount=0;
	}

	private static String find(char [] toppings, boolean [] exists, int curr, Preference [] pfs) {
		if (curr==toppings.length) {
			boolean allOK=true;
			for (int i=0;i<pfs.length;i++) allOK&=pfs[i].satisfyCount>0;
			
			if (allOK) {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<toppings.length;i++) if (exists[i]) sb.append(toppings[i]);
				return sb.toString();
			}
			return null;
		}

		char currTopping=toppings[curr];

		int count=0;
		for (int i=0;i<pfs.length;i++) if (pfs[i].status[currTopping]==2) count++; // Don't want
		if (count>0) {
			for (int i=0;i<pfs.length;i++) if (pfs[i].status[currTopping]==2) pfs[i].satisfyCount++;
			String s=find(toppings,exists,curr+1,pfs);
			if (s!=null) return s;
			for (int i=0;i<pfs.length;i++) if (pfs[i].status[currTopping]==2) pfs[i].satisfyCount--;
		}

		count=0;
		for (int i=0;i<pfs.length;i++) if (pfs[i].status[currTopping]==1) count++; // Want
		if (count>0) {
			for (int i=0;i<pfs.length;i++) if (pfs[i].status[currTopping]==1) pfs[i].satisfyCount++;
			exists[curr]=true;
			String s=find(toppings,exists,curr+1,pfs);
			if (s!=null) return s;
			exists[curr]=false;
			for (int i=0;i<pfs.length;i++) if (pfs[i].status[currTopping]==1) pfs[i].satisfyCount--;
		}
		
		return null;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			// Read input
			ArrayList<String> lines=new ArrayList<>();
			while (true) {
				lines.add(s);
				s=br.readLine();
				if (s.equals(".")) break;
			}
			
			// Read +X-X
			Preference [] pfs=new Preference [lines.size()];
			HashSet<Character> toppingSet=new HashSet<>();
			for (int i=0;i<lines.size();i++) {
				pfs[i]=new Preference();
				s=lines.get(i);
				for (int i2=0;i2<s.length()-1;i2+=2) {
					char p=s.charAt(i2);
					char t=s.charAt(i2+1);
					toppingSet.add(t);
					pfs[i].status[t]=(p=='+') ? 1 : 2;
				}
			}
			
			// Convert toppings to array
			char [] toppings=new char [toppingSet.size()];
			int tN=0;
			for (Character c: toppingSet) toppings[tN++]=c;
			Arrays.sort(toppings);

			String ans=find(toppings,new boolean [toppings.length],0,pfs);
			if (ans==null) System.out.println("No pizza can satisfy these requests.");
			else System.out.printf("Toppings: %s\n", ans);
		}
	}

}
