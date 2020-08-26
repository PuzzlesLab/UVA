import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [][] ch=new char [10][];
			for (int h=0;h<10;h++) ch[h]=br.readLine().toCharArray();
			
			StringBuilder sb=new StringBuilder();
			for (int j=1;j<ch[0].length-1;j++) {
				int chCode=0;
				for (int i=1;i<=8;i++) if (ch[i][j]=='\\') chCode+=(int)Math.pow(2, i-1);
				sb.append((char)chCode);
			}
			System.out.println(sb.toString());
			br.readLine();
		}
	}
}