import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			int P=Integer.parseInt(br.readLine());
			int [] neighbour=new int [P];
			
			int minN=Integer.MAX_VALUE;
			for (int p=0;p<P;p++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				neighbour[p]=st.countTokens();
				
				minN=Math.min(neighbour[p], minN);
			}
			
			StringBuilder sb=new StringBuilder();
			for (int p=0;p<P;p++) if (neighbour[p]==minN) {
				sb.append(p+1);
				sb.append(" ");
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
			
			br.readLine();//empty.
		}

	}

}
