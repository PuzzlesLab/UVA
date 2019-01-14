import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int t=1;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			HashSet<Integer> rSet=new HashSet<>();
			HashSet<Integer> cSet=new HashSet<>();
			
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				rSet.add(Integer.parseInt(st.nextToken()));
				cSet.add(Integer.parseInt(st.nextToken()));
			}
			
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			boolean flag1=rSet.contains(r);
			boolean flag2=cSet.contains(c);
			
			if (!flag1 || !flag2) {
				if (r-1>=0) flag1 &= rSet.contains(r-1);
				if (r+1<R) flag1 &= rSet.contains(r+1);

				if (c-1>=0) flag2 &= cSet.contains(c-1);
				if (c+1<C) flag2 &= cSet.contains(c+1);
			}
			if (!flag1 && !flag2) System.out.printf("Case %d: Escaped again! More 2D grid problems!\n",t++);
			else System.out.printf("Case %d: Party time! Let's find a restaurant!\n",t++);

		}
	}

}