import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			
			TreeSet<Integer> [] adjList=new TreeSet [N];
			for (int i=0;i<N;i++) {
				adjList[i]=new TreeSet<>();
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<N;i2++) if (Integer.parseInt(st.nextToken())==1) adjList[i].add(i2);
			}
			
			int count=0;
			StringBuilder sb=new StringBuilder();
			for (int n1=0;n1<N;n1++) for (int n2 : adjList[n1]) for (int n3 : adjList[n2]) if (n1!=n3 && adjList[n3].contains(n1)) {
				if (n1>n2 && n2>n3 || n1<n2 && n2<n3) {
					sb.append(n1+1);
					sb.append(' ');
					sb.append(n2+1);
					sb.append(' ');
					sb.append(n3+1);
					sb.append('\n');
					count++;
				}
			}
			sb.append("total:");
			sb.append(count);
			sb.append('\n');
			
			System.out.println(sb.toString());
		}
	}

}