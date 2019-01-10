import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int T=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [][] data=new int [N][];
			int minYear=0;
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int displayed=Integer.parseInt(st.nextToken());
				int start=Integer.parseInt(st.nextToken());
				int end=Integer.parseInt(st.nextToken());
				int bound=end-start;
				data[n]=new int [] {start,end,displayed,bound};
				minYear=Math.max(minYear, data[n][0]);
			}
			
			for (;minYear<10000;minYear++) {
				boolean flag=true;
				for (int n=0;n<N;n++) {
					int y=((minYear-data[n][0])%data[n][3])+data[n][0];
					flag &= (y == data[n][2]);
				}
				if (flag) break;
			}
			
			System.out.printf("Case #%d:\n", T++);
			if (minYear<10000) System.out.printf("The actual year is %d.\n\n", minYear);
			else System.out.println("Unknown bugs detected.\n");
		}
	}

}
