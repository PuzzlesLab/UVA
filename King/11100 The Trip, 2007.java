import java.util.Arrays;
import java.util.Scanner;

class Main {

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		boolean first=true;
		while (true) {
			int N=sc.nextInt();
			if (N==0) break;
			if (first) first=!first;
			else System.out.println();
			
			int [] bags=new int [N];
			for (int n=0;n<N;n++) bags[n]=sc.nextInt();
			Arrays.sort(bags);
			
			int last=-1, currCount=0, maxCount=0;
			for (int n=0;n<N;n++) {
				if (bags[n]!=last) {
					currCount=0;
					last=bags[n];
				}
				currCount+=1;
				maxCount=Math.max(maxCount,currCount);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(maxCount); sb.append('\n');
			boolean [] used=new boolean[N];
			for (int i=0;i<used.length;i++) if (!used[i]) {
				for (int i2=i;i2<used.length;i2+=maxCount) {
					used[i2]=true;
					sb.append(bags[i2]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}