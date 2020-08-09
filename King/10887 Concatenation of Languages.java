import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			HashSet<String> set1=new HashSet<>();
			for (int m=0;m<M;m++) set1.add(br.readLine());
			HashSet<String> set2=new HashSet<>();
			for (int n=0;n<N;n++) set2.add(br.readLine());
			
			HashSet<String> together=new HashSet<>();
			for (String s1 : set1) for (String s2 : set2) together.add(s1+s2);
			
			System.out.printf("Case %d: %d\n", testCase, together.size());
		}
	}

}