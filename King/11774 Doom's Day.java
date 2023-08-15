import java.util.Scanner;

class Main {

	private static long gcd(long a, long b) {
		return (b==0) ? a : gcd(b,a%b);
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int TC=sc.nextInt();
		for (int tc=1;tc<=TC;tc++) {
			long M=sc.nextLong();
			long N=sc.nextLong();

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append((M+N)/gcd(M,N));
			System.out.println(sb);
		}
	}

}
