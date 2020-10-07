import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); //empty
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringBuilder sb=new StringBuilder();
			String last="";
			int counter=0;
			while (true) {
				String s=br.readLine();
				if (s==null || s.length()==0) break;
				int len=Math.min(last.length(), s.length());
				int match=0;
				for (int i=0;i<len;i++)
					if (last.charAt(i)==s.charAt(i)) match++;
					else break;
				for (int i=0;i<Math.min(counter,match);i++) sb.append(' ');
				counter=Math.min(counter,match)+1;
				last=s;
				sb.append(s);
				sb.append('\n');
			}
			if (testCase>0) System.out.println();
			System.out.print(sb.toString());
		}
	}
}