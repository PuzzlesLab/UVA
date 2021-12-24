import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			HashSet<Integer> [] trouble=new HashSet [N];
			for (int n=0;n<N;n++) trouble[n]=new HashSet<>();
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int u=Integer.parseInt(st.nextToken())-1;
				int v=Integer.parseInt(st.nextToken())-1;
				
				trouble[u].add(v);
				trouble[v].add(u);
			}
			
			HashSet<Integer> class1=new HashSet<>();
			HashSet<Integer> class2=new HashSet<>();
			for (int n=0;n<N;n++) {
				int c1t=0; // Go to class1 trouble
				for (int t: trouble[n]) if (class1.contains(t)) c1t++;
				int c2t=0; // Go to class2 trouble
				for (int t: trouble[n]) if (class2.contains(t)) c2t++;
				
				if (c1t<c2t) class1.add(n);
				else class2.add(n);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase);
			sb.append(": ");
			sb.append(class2.size());
			sb.append('\n');
			TreeSet<Integer> class2Sorted=new TreeSet<>(class2);
			for (int n: class2Sorted) {
				sb.append(n+1);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}