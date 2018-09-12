import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static class Number implements Comparable<Number> {
		int num, mod;
		boolean odd;
		
		public Number(int num, int M) {
			this.num=num;
			this.mod=num%M;
			this.odd=Math.abs(num%2)==1;
		}

		@Override
		public int compareTo(Number arg0) {
			if (this.num == arg0.num) return 0;
			else if (this.mod>arg0.mod) return 1;
			else if (this.mod<arg0.mod) return -1;
			else if (this.odd ^ arg0.odd) return (this.odd) ? -1 : 1;
			else if (this.odd && arg0.odd) return (this.num>arg0.num) ? -1 : 1;
			else if (!this.odd && !arg0.odd) return (this.num>arg0.num) ? 1 : -1;
			return 0;
		}
		 
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
			Number [] num=new Number[N];
			for (int n=0;n<N;n++) num[n]=new Number(Integer.parseInt(br.readLine()), M);
			Arrays.sort(num);
			
			StringBuilder sb=new StringBuilder();
			sb.append(N); sb.append(' '); sb.append(M); sb.append('\n');
			for (int i=0;i<N;i++) {
				sb.append(num[i].num);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
		System.out.println("0 0");
	}

}
