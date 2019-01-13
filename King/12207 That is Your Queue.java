import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Job {
		int id, priority;
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int t=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int P=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> queue=new LinkedList<>();
			LinkedList<Integer> smallQ=new LinkedList<>();
			P=Math.min(C, P);
			for (int p=0;p<P;p++) smallQ.addLast(p);

			for (int c=0;c<C;c++) {
				st=new StringTokenizer(br.readLine());
				if (st.countTokens()==2) {
					st.nextToken();
					Integer temp=Integer.parseInt(st.nextToken())-1;
					smallQ.remove(temp);
					smallQ.addFirst(temp);
				} else {
					queue.add(smallQ.getFirst());
					smallQ.addLast(smallQ.removeFirst());
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(t++);
			sb.append(":\n");
			for (int i : queue) {
				sb.append(i+1);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}