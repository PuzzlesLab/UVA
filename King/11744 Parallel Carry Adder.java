import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static void print(StringBuilder sb, int [] a, int [] b, boolean overflow) {
		for (int i=0;i<a.length;i++) sb.append(a[i]);
		sb.append(' ');
		if (!overflow) for (int i=0;i<b.length;i++) sb.append(b[i]);
		else sb.append("overflow");
		sb.append('\n');
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] a=new int [31], b=new int [31];
			int ai=0, bi=0;
			for (char c : st.nextToken().toCharArray()) a[ai++]=c-'0';
			for (char c : st.nextToken().toCharArray()) b[bi++]=c-'0';
			
			StringBuilder sb=new StringBuilder();
			print(sb,a,b,false);
			
			boolean overflow=false;
			while (true) {
				for (int i=0;i<a.length;i++) {
					int ci=(a[i]!=b[i])? 1 : 0;
					int di=(a[i]+b[i]==2) ? 1 : 0;
					a[i]=ci;
					b[i]=di;
				}
				overflow=b[0]==1;
				int [] temp=new int [b.length];
				for (int i=0;i<temp.length-1;i++) temp[i]=b[i+1];
				b=temp;
				print(sb,a,b,overflow);
				
				if (overflow) break;
				
				boolean zero=true;
				for (int i=0;i<temp.length;i++) zero&=(b[i]==0);
				if (zero) break;
			}
			
			if (testCase>0) System.out.println();
			System.out.print(sb.toString());
		}
		
	}

}