import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {


	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int M=Integer.parseInt(br.readLine());
			char [] c=br.readLine().toCharArray();
			
			int [] count=new int [128];
			for (int i=0;i<c.length;i++) count[c[i]]++;
			
			String ans="";
			if (count['A']==M) ans="ABANDONED";
			else if (count['T']==0 && count['B']==M-count['A']) ans="BANGLAWASH";
			else if (count['T']==0 && count['W']==M-count['A']) ans="WHITEWASH";
			else if (count['B']==count['W']) ans="DRAW "+count['B']+" "+count['T'];
			else if (count['B']>count['W']) ans="BANGLADESH "+count['B']+" - "+count['W'];
			else if (count['B']<count['W']) ans="WWW "+count['W']+" - "+count['B'];
			
			System.out.printf("Case %d: %s\n", testCase, ans);
		}
	}

}
