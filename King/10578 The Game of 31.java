import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static int [] Cards=new int [7];

	private static boolean play(int sum) {
		for (int i=1;i<Cards.length;i++) if (Cards[i]>0) {
			int ns=sum+i;
			if (ns>31) break;

			Cards[i]--;
			boolean flag=!play(ns);
			Cards[i]++;
			if (flag) return true;
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			Arrays.fill(Cards,4);
			
			int sum=0;
			boolean a=true;
			for (int i=0;i<s.length();i++) {
				int card=s.charAt(i)-'0';
				Cards[card]--;
				sum+=card;
				a=!a;
			}

			boolean flag=play(sum);
			if (!flag) a=!a;
			System.out.printf("%s %s\n",s,a?"A":"B");
		}
	}

}
