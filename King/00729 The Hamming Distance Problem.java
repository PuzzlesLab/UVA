import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
	
	public static BufferedWriter bw;
	
	public static void dfs(StringBuilder sb, int maxLength, int oneCount, int maxOneCount) throws Exception {
		if (sb.length()<maxLength) {
			if (maxLength-sb.length()>maxOneCount-oneCount) {
				sb.append('0');
				dfs(sb,maxLength,oneCount,maxOneCount);
				sb.setLength(sb.length()-1);
			}
			if (oneCount<maxOneCount) {
				sb.append('1');
				dfs(sb,maxLength,oneCount+1,maxOneCount);
				sb.setLength(sb.length()-1);
			}
		} else {
			bw.write(sb.toString());
			bw.write('\n');
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			if (testCase>0) bw.write('\n');
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			
			dfs(new StringBuilder(),N,0,H);
			
			bw.flush();
		}
		
	}

}