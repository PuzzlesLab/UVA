import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		String [] keypad= {" ", ".,?\"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int keysCount=Integer.parseInt(br.readLine());
			
			int [] pressKeys=new int [keysCount];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<keysCount;i++) pressKeys[i]=Integer.parseInt(st.nextToken());
			
			int [] pressCount=new int [keysCount];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<keysCount;i++) pressCount[i]=Integer.parseInt(st.nextToken())-1;
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<keysCount;i++) sb.append(keypad[pressKeys[i]].charAt(pressCount[i]));
			System.out.println(sb.toString());
		}
	}
}