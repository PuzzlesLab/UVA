import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int G=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());
			
			int [][] pid=new int [G][P];
			for (int g=0;g<G;g++) {
				st=new StringTokenizer(br.readLine());
				int p=0;
				while (st.hasMoreTokens()) pid[g][Integer.parseInt(st.nextToken())-1]=p++;
			}
			
			int S=Integer.parseInt(br.readLine());
			for (int ss=0;ss<S;ss++) {
				st=new StringTokenizer(br.readLine());
				int [] system=new int [Integer.parseInt(st.nextToken())];
				for (int i=0;i<system.length;i++) system[i]=Integer.parseInt(st.nextToken());
				
				int [] scores=new int [P];
				for (int g=0;g<G;g++) for (int p=0;p<system.length;p++) scores[pid[g][p]]+=system[p];
				
				int max=-1;
				for (int i=0;i<scores.length;i++) max=Math.max(max, scores[i]);
				
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<scores.length;i++) if (scores[i]==max) {
					sb.append(i+1);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
			}
		}
	}

}
