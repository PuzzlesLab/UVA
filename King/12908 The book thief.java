import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		// 0.5(N^2+N) = 100000000, N=14142.
		// Considering the missing page, we need N=14143.
		int [] Sn=new int [14143];
		for (int i=0;i<Sn.length;i++) Sn[i]=(i*i+i)>>1;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while (!(s=br.readLine()).equals("0")) {
			int S=Integer.parseInt(s);

			int min=0;
			int max=Sn.length-1;
			while (max>min+1) {
				int mid=(min+max)>>1;
				if (Sn[mid]>S) max=mid;
				else min=mid;
			}
			if (Sn[min]>S) max=min;

			sb.append(Sn[max]-S);
			sb.append(' ');
			sb.append(max);
			sb.append('\n');
		}
		System.out.print(sb); // Print on every query will get TLE!
	}

}