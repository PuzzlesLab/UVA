import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		char [] lookup=new char[128];
		for (int i=0;i<lookup.length;i++) lookup[i]=(char)i;
		lookup['0']='O';
		lookup['1']='I';
		lookup['2']='Z';
		lookup['3']='E';
		lookup['4']='A';
		lookup['5']='S';
		lookup['6']='G';
		lookup['7']='T';
		lookup['9']='P';
		lookup['8']='B';
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringBuilder sb=new StringBuilder();
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				for (char c : s.toCharArray()) sb.append(lookup[c]);
				sb.append('\n');
			}
			
			if (testCase>0) System.out.println();
			System.out.print(sb.toString());
		}
	}
}