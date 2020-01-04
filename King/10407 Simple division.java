import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static int gcd(int a, int b) { return b == 0 ? a : gcd(b, Math.floorMod(a,b)); }
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine().trim()).equals("0")) {
			ArrayList<Integer> list=new ArrayList<>();
			StringTokenizer st=new StringTokenizer(s);
			while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
			list.remove(list.size()-1);
			
			ArrayList<Integer> diffs=new ArrayList<>();
			for (int i=1;i<list.size();i++) {
				int diff=list.get(i)-list.get(i-1);
				if (diff!=0) diffs.add(Math.abs(diff));
			}
			int gcd=diffs.get(0);
			for (int i=0;i<diffs.size();i++) gcd=gcd(diffs.get(i), gcd);
			
			System.out.println(gcd);
		}
	}

}