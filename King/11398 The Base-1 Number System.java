import java.util.Scanner;

class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		long ans=0;
		int currFlag=0;
		while (true) {
			String s=sc.next();
			if (s.equals("~")) break;
			if (s.equals("#")) {
				System.out.println(ans);
				ans=0;
				currFlag=0;
			}
			
			int len=s.length();
			if (len==1) currFlag=1;
			else if (len==2) currFlag=0;
			else {
				int rep=len-2;
				for (int r=0;r<rep;r++) ans=(ans<<1)+currFlag;
			}
		}
	}
}
