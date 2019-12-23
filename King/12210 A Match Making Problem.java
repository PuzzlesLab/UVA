import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int B=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			if (B==0 && S==0) break;
			
			ArrayList<Integer> bs=new ArrayList<>();
			for (int b=0;b<B;b++) bs.add(Integer.parseInt(br.readLine()));
			Collections.sort(bs);
			Collections.reverse(bs);
			
			ArrayList<Integer> ss=new ArrayList<>();
			for (int i=0;i<S;i++) ss.add(Integer.parseInt(br.readLine()));
			Collections.sort(ss);
			Collections.reverse(ss);
			
			for (int i=0;i<bs.size();i++) {
				Integer p1=bs.get(i);
				int nearest=Integer.MAX_VALUE;
				int nearestIndex=-1;
				
				for (int i2=0;i2<ss.size();i2++) {
					Integer p2=ss.get(i2);
					int delta=Math.abs(p1-p2);
					if (nearestIndex==-1 || delta<nearest) {
						nearestIndex=i2;
						delta=nearest;
					}
				}
				
				if (nearestIndex!=-1) {
					bs.remove(i);
					ss.remove(nearestIndex);
					i--;
				}
			}
			
			System.out.printf("Case %d: %s\n", testCase++, bs.size() == 0? "0" : bs.size()+" "+bs.get(bs.size()-1));
		}
	}

}