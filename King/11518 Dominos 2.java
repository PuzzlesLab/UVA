import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			
			HashSet<Integer> [] adjList=new HashSet [N];
			for (int i=0;i<N;i++) adjList[i]=new HashSet<>();
			for (int i=0;i<M;i++) {
				st=new StringTokenizer(br.readLine());
				adjList[Integer.parseInt(st.nextToken())-1].add(Integer.parseInt(st.nextToken())-1);
			}
			
			boolean [] down=new boolean [N];
			for (int i=0;i<L;i++) {
				int push=Integer.parseInt(br.readLine())-1;
			
				Stack<Integer> stk=new Stack<>();
				stk.add(push);
				down[push]=true;
				while (!stk.isEmpty()) {
					push=stk.pop();
					for (int next : adjList[push]) if (!down[next]) {
						down[next]=true;
						stk.push(next);
					}
				}
			}
			
			int size=0;
			for (int i=0;i<down.length;i++) if (down[i]) size++;
			System.out.println(size);
		}
	}

}