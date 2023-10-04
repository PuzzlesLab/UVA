import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int play(int p, int a, int b) {
		int p2=p^1;
		if (b==0) return p2;
		if (play(p2,b,a%b)==p) return p;

		if (a/b>1) {
			int f=(a/b)-1; // Purposely -1 for next 2 rounds.
			int r=a-f*b;
			if (play(p2,Math.max(r,b),Math.min(r,b))==p) return p;
		}

		return p2;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n1=Integer.parseInt(st.nextToken());
			int n2=Integer.parseInt(st.nextToken());
			if (n1==0 && n2==0) break;
			
			int a=Math.max(n1,n2);
			int b=Math.min(n1,n2);
			int winner=play(0,a,b);

			System.out.println(winner==0?"Stan wins":"Ollie wins");
		}
	}

}
