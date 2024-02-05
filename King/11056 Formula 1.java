import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Driver implements Comparable<Driver> {
		public String name;
		private int min, sec, ms;

		public Driver (String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.name=st.nextToken();
			st.nextToken(); // :
			this.min=Integer.parseInt(st.nextToken());
			st.nextToken(); // min
			this.sec=Integer.parseInt(st.nextToken());
			st.nextToken(); // sec
			this.ms=Integer.parseInt(st.nextToken());
		}
		
		public int compareTo(Driver d) {
			if (this.min!=d.min) return this.min-d.min;
			if (this.sec!=d.sec) return this.sec-d.sec;
			if (this.ms!=d.ms) return this.ms-d.ms;
			return this.name.toLowerCase().compareTo(d.name.toLowerCase());
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			Driver [] drivers=new Driver [N];
			for (int n=0;n<N;n++) drivers[n]=new Driver(br.readLine());
			Arrays.sort(drivers);
			
			StringBuilder sb=new StringBuilder();
			for (int n=0;n<N;n+=2) {
				if ((n&1)==0) {
					sb.append("Row ");
					sb.append((n>>1)+1);
					sb.append('\n');
				}
				sb.append(drivers[n].name);
				sb.append('\n');
				if (n+1<N) {
					sb.append(drivers[n+1].name);
					sb.append('\n');
				}
			}

			System.out.println(sb);
			br.readLine(); // Empty
		}
	}

}
