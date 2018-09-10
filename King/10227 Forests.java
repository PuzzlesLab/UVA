import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();//empty
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			TreeSet<Integer> [] set=new TreeSet [P];
			for (int i=0;i<set.length;i++) set[i]=new TreeSet<>();
			
			String s;
			while ((s=br.readLine())!=null) {
				if (s.length()==0) break;
				st=new StringTokenizer(s);
				set[Integer.parseInt(st.nextToken())-1].add(Integer.parseInt(st.nextToken()));
			}
			
			HashSet<String> unique=new HashSet<>();
			for (int i=0;i<set.length;i++) unique.add(set[i].toString());
				
			System.out.println(unique.size());
			if (testCase<testCaseCount-1) System.out.println();
		}
	}

}