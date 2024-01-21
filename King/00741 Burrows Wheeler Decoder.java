import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		char c;
		int pos;
		
		public Tuple(char c, int p) {
			this.c=c;
			this.pos=p;
		}
		
		public int compareTo(Tuple t) {
			if (this.c==t.c) return this.pos-t.pos;
			return this.c-t.c;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean first=true;
		while (true) {
			String s=br.readLine();
			int N=Integer.parseInt(br.readLine());
			if (s.equals("END") && N==0) break;
			N--;

			Tuple [] c=new Tuple[s.length()];
			for (int i=0;i<s.length();i++) c[i]=new Tuple(s.charAt(i),i);
			Arrays.sort(c);

			StringBuilder sb=new StringBuilder();
			Tuple curr=c[N];
			for (int i=0;i<c.length;i++) {
				sb.append(curr.c);
				curr=c[curr.pos];
			}
			if (!first) System.out.println();
			System.out.println(sb.toString());

			first=false;
		}
	}

}
