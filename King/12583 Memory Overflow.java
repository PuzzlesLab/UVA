import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			char [] c=st.nextToken().toCharArray();
			
			ArrayList<Integer> [] list=new ArrayList[26];
			for (int i=0;i<n;i++) {
				if (list[c[i]-'A']==null) list[c[i]-'A']=new ArrayList<>();
				list[c[i]-'A'].add(i);
			}
			
			int count=0;
			for (int i=0;i<26;i++) if (list[i]!=null && list[i].size()>1)
				for (int i2=1;i2<list[i].size();i2++) if (list[i].get(i2)-list[i].get(i2-1)<=k) count++;
			
			System.out.printf("Case %d: %d\n", testCase, count);
		}
	}

}
