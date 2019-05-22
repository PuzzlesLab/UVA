import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static class Data {
		int num, move;
		public Data (int num, int move) { this.num=num; this.move=move; }
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] buttons=new int [N];
			for (int n=0;n<N;n++) buttons[n]=Integer.parseInt(st.nextToken());
			
			int ans=-1;
			boolean [] visited=new boolean [10000];
			LinkedList<Data> queue=new LinkedList<>();
			queue.add(new Data(start, 0));
			while (!queue.isEmpty()) {
				Data d=queue.removeFirst();
				if (d.num==end) {
					ans=d.move;
					break;
				}
				for (int n=0;n<N;n++) {
					int nextNum=(d.num+buttons[n])%10000;
					if (!visited[nextNum]) {
						visited[nextNum]=true;
						queue.add(new Data(nextNum,d.move+1));
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase++);
			sb.append(": ");
			if (ans==-1) sb.append("Permanently Locked");
			else sb.append(ans);
			System.out.println(sb.toString());
		}
	}

}