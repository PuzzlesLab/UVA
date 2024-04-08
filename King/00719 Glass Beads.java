import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			char [] c=br.readLine().toCharArray();
			int s1=0;
			int s2=1;
			while (s1<c.length && s2<c.length) {
				int d=0;
				while (d<c.length) {
					if (c[(s1+d)%c.length]!=c[(s2+d)%c.length]) break;
					d++;
				}
				if (d==c.length) break;
				
				if (c[(s1+d)%c.length]>c[(s2+d)%c.length]) s1+=d+1;
				else s2+=d+1;
				if (s1==s2) s2=s1+1;
			}

			System.out.println(Math.min(s1,s2)+1);
		}
	}
}