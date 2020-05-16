import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static class Problem implements Comparable<Problem> {
		int S, G;
		
		public Problem (int S, int G) {
			this.S=S;
			this.G=G;
		}
		
		public int compareTo(Problem p) {
			int thisPTime=this.S+Math.max(this.G, p.S)+p.G;
			int pThisTime=p.S+Math.max(p.G, this.S)+this.G;
			return (thisPTime-pThisTime);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			
			Problem [] problems=new Problem [N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			StringTokenizer st2=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) problems[i]=new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken()));
			Arrays.sort(problems);
			
			int sEnd=0;
			int gEnd=0;
			for (int i=0;i<N;i++) {
				sEnd+=problems[i].S;
				gEnd=Math.max(gEnd, sEnd)+problems[i].G;
			}
			
			System.out.println(gEnd);
		}
	}

}