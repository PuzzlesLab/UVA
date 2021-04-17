import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static class DNA implements Comparable<DNA>{
		String text;
		int sortedness;
		
		public DNA(String text) {
			this.text=text;
			this.calcSortness();
		}
		
		private void calcSortness() {
			char [] ch=text.toCharArray();
			this.sortedness=0;
			for (int i=0;i<ch.length-1;i++) for (int i2=i+1;i2<ch.length;i2++) if (ch[i]>ch[i2]) this.sortedness++;
		}
		
		public int compareTo(DNA d) {
			return this.sortedness-d.sortedness;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			DNA [] dnas=new DNA[M];
			for (int m=0;m<M;m++) dnas[m]=new DNA(br.readLine());
			
			Arrays.sort(dnas);
			
			if (testCase>0) System.out.println();
			StringBuilder sb=new StringBuilder();
			for (int m=0;m<M;m++) {
				sb.append(dnas[m].text);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}
}