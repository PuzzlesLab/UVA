import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		int MAX=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int P=Integer.parseInt(br.readLine());

			HashMap<String,Integer> nameIdxMap=new HashMap<>();
			String [] names=new String [P];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int p=0;p<P;p++) {
				names[p]=st.nextToken();
				nameIdxMap.put(names[p],p);
			}

			int [][] cost=new int [P][P];
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				for (int p2=0;p2<P;p2++) {
					int c=Integer.parseInt(st.nextToken());
					if (c==-1) c=MAX;
					cost[p][p2]=c;
				}
			}
			int [][] parent=new int [P][P];
			for (int p=0;p<P;p++) Arrays.fill(parent[p],p);

			for (int k=0;k<P;k++) for (int i=0;i<P;i++) for (int j=0;j<P;j++) {
				int nDist=cost[i][k]+cost[k][j];
				if (nDist<cost[i][j]) {
					cost[i][j]=nDist;
					parent[i][j]=parent[k][j];
				}
			}

			int R=Integer.parseInt(br.readLine());
			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				String eName=st.nextToken();
				int from=nameIdxMap.get(st.nextToken());
				int to=nameIdxMap.get(st.nextToken());
				if (cost[from][to]==MAX) {
					System.out.printf("Sorry Mr %s you can not go from %s to %s\n",eName,names[from],names[to]);
				} else {
					StringBuilder sb=new StringBuilder();
					sb.append(String.format("Mr %s to go from %s to %s, you will receive %d euros\n",eName,names[from],names[to],cost[from][to]));
					sb.append("Path:");

					Stack<String> stk=new Stack<>();
					int temp=to;
					while (temp!=from) {
						stk.push(names[temp]);
						temp=parent[from][temp];
					}
					stk.push(names[from]);
					if (stk.size()==1) stk.push(names[from]); // Happens when from=to, needs to print the same name 2 times.
					while (!stk.isEmpty()) {
						sb.append(stk.pop());
						sb.append(' ');
					}
					sb.setLength(sb.length()-1);

					System.out.println(sb.toString());
				}
			}
		}
	}

}