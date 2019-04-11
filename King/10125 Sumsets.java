import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [] ints=new int [N];
			HashSet<Long> set=new HashSet<>();
			for (int n=0;n<N;n++) {
				ints[n]=Integer.parseInt(br.readLine());
				set.add((long)ints[n]);
			}
			Arrays.sort(ints);
			
			long d=Long.MIN_VALUE;
			for (int a=0;a<N;a++) {
				long av=ints[a];
				for (int b=a+1;b<N && av+ints[b]<=ints[N-1];b++) {
					long bv=av+ints[b];
					for (int c=b+1;c<N && bv+ints[c]<=ints[N-1];c++) {
						long cv=bv+ints[c];
						if (cv!=ints[a] && cv!=ints[b] && cv!=ints[c] && set.contains(cv)) d=Math.max(d, cv);
					}
				}
			}
			if (d!=Long.MIN_VALUE) System.out.println(d);
			else System.out.println("no solution");
		}
	}

}