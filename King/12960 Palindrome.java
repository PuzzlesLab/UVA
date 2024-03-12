import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static String Text;
	private static boolean [] IsSpecial;
	private static Tuple [][] Dp;

	private static class Tuple implements Comparable<Tuple> {
		int sp, length;
		
		public Tuple(int s, int l) {
			this.sp=s;
			this.length=l;
		}

		public int compareTo(Tuple t) {
			if (this.sp!=t.sp) return this.sp-t.sp;
			return this.length-t.length;
		}
		
		public static Tuple max(Tuple t1, Tuple t2) {
			return (t1.compareTo(t2)>=0) ? t1 : t2;
		}
	}

	private static Tuple find(int i, int i2) {
		if (i==i2) return new Tuple(IsSpecial[i]?1:0,1);
		if (Dp[i][i2]==null) {
			Tuple ans=new Tuple(0,0);
			ans=Tuple.max(ans,find(i+1,i2));
			ans=Tuple.max(ans,find(i,i2-1));
			if (Text.charAt(i)==Text.charAt(i2)) {
				int v1=IsSpecial[i]?1:0;
				int v2=IsSpecial[i2]?1:0;
				if (i+1==i2) {
					ans=Tuple.max(ans,new Tuple(v1+v2,2));
				} else {
					Tuple n=find(i+1,i2-1);
					ans=Tuple.max(ans,new Tuple(v1+v2+n.sp,n.length+2));
				}
			}
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while ((s=br.readLine())!=null) {
			Text=s;
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			IsSpecial=new boolean [Text.length()];
			for (int n=0;n<N;n++) IsSpecial[Integer.parseInt(st.nextToken())-1]=true;

			Dp=new Tuple [Text.length()][Text.length()];
			Tuple ans=find(0,Text.length()-1);
			sb.append(ans.length);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}