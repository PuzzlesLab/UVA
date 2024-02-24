import java.util.Scanner;

class Main {

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int tc=1;
		while (true) {
			int N=sc.nextInt();
			if (N==0) break;
			int r1=sc.nextInt();
			int c1=sc.nextInt();
			int r2=sc.nextInt();
			int c2=sc.nextInt();
			int N2=(N<<1)-1;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(":\n");
			for (int x=r1;x<=r2;x++) {
				for (int y=c1;y<=c2;y++) {
					int tx=x%N2;
					int ty=y%N2;
					int d=Math.abs(N-1-tx)+Math.abs(N-1-ty);
					sb.append(d<N?(char)('a'+d%26):'.');
				}
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}
}
