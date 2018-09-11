import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	private static class Floor implements Comparable<Floor> {
		int S;
		boolean flag;
		
		public Floor(int S, boolean flag) {
			this.S=S;
			this.flag=flag;
		}

		@Override
		public int compareTo(Floor arg0) {
			return this.S-arg0.S;
		}
		 
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int P=Integer.parseInt(br.readLine());
		for (int p=1;p<=P;p++) {
			int N=Integer.parseInt(br.readLine());
			
			if (N==0) {
				System.out.println("0");
				continue;
			}
			
			Floor [] f=new Floor[N];
			for (int i=0;i<N;i++) {
				int in=Integer.parseInt(br.readLine());
				f[i]=new Floor(Math.abs(in), in>0);
			}
			
			Arrays.sort(f);
			int count=1;
			boolean lastFlag=!f[0].flag;
			for (int i=1;i<N;i++) if (f[i].flag==lastFlag) {
				lastFlag=!lastFlag;
				count++;
			}
			
			System.out.println(count);
		}
	}

}