import java.util.Arrays;
import java.util.Scanner;

class Main {
  
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N=sc.nextInt();
			int [] num=new int [N];
			for (int i=0;i<N;i++) num[i]=sc.nextInt();
			
			if (N==1) {
				System.out.println(num[0]+" 1 1");
				continue;
			}
			
			StringBuilder sb=new StringBuilder();
			Arrays.sort(num);
			
			if (N%2==0) {
				int A=num[(N>>1)-1];
				int A2=num[(N>>1)];
				sb.append(A);
				int c=0;
				for (int i=0;i<N;i++) if (num[i]==A || num[i]==A2) c++;
				sb.append(' ');
				sb.append(c);
				sb.append(' ');
				sb.append(A2-A+1);
			} else {
				int A=num[(N>>1)];
				sb.append(A);
				int c=0;
				for (int i=0;i<N;i++) if (num[i]==A) c++;
				sb.append(' ');
				sb.append(c);
				sb.append(' ');
				sb.append(1);
			}

			System.out.println(sb.toString());
		}
	}

}
