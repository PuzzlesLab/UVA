import java.util.HashSet;
import java.util.Scanner;

class Main {

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	private static String simplify(int num, int dem) {
		if (dem==0) return null;
		if (num==0) return "0 / 1";

		boolean isNegative=num<0^dem<0;
		num=Math.abs(num);
		dem=Math.abs(dem);
		int gcd=gcd(num,dem);
		num/=gcd;
		dem/=gcd;

		StringBuilder sb=new StringBuilder();
		if (isNegative) sb.append('-');
		sb.append(num);
		sb.append(" / ");
		sb.append(dem);
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in); // Malicious input, can't use BufferedReader. :/
		while (sc.hasNextInt()) {
			int N=sc.nextInt();

			HashSet<String> visited=new HashSet<>();
			int direction=0;
			int directionChangeCount=0;
			int extendMax=1;
			int extendLeft=1;
			int num=0;
			int dem=0;
			String fraction;
			while (true) {
				if (direction==0) num++;
				else if (direction==1) dem++;
				else if (direction==2) num--;
				else if (direction==3) dem--;
				extendLeft--;

				fraction=simplify(num,dem);
				if (fraction!=null && !visited.contains(fraction)) {
					visited.add(fraction);
					if (N==0) break;
					else N--;
				}

				if (extendLeft==0) {
					direction=(direction+1)%4;
					directionChangeCount++;
					if (directionChangeCount==2) extendMax=2;
					else if (directionChangeCount%2==0) extendMax++;
					extendLeft=extendMax;
				}
			}
			
			System.out.println(fraction);
		}
	}

}
