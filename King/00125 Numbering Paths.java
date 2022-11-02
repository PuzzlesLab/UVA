import java.util.Scanner;

class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int tc=0;
		while (sc.hasNextInt()) {
			long [][] ways=new long [30][30];

			int N=sc.nextInt();
			int largest=0;
			for (int n=0;n<N;n++) {
				int i=sc.nextInt();
				int j=sc.nextInt();
				ways[i][j]++;
				largest=Math.max(largest,i);
				largest=Math.max(largest,j);
			}

			for (int k=0;k<=largest;k++) for (int i=0;i<=largest;i++) for (int j=0;j<=largest;j++) ways[i][j]+=ways[i][k]*ways[k][j];
			for (int k=0;k<=largest;k++) {
				if (ways[k][k]<=0) continue;
				for (int i=0;i<=largest;i++) for (int j=0;j<=largest;j++) {
					if (ways[i][k]!=0 && ways[k][j]!=0) ways[i][j]=-1;
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("matrix for city ");
			sb.append(tc++);
			sb.append('\n');
			if (largest>0) {
				for (int i=0;i<=largest;i++) {
					for (int j=0;j<=largest;j++) {
						sb.append(ways[i][j]);
						sb.append(' ');
					}
					sb.setLength(sb.length()-1);
					sb.append('\n');
				}
			}
			
			System.out.print(sb.toString());
		}
	}

}