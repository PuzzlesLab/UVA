import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static String Ans=null;
	private static int AnsLength;
	
	private static void find(int [] points, LinkedList<Integer> selected, int rem) {
		if (rem==0 && selected.size()<AnsLength) {
			AnsLength=selected.size();
			StringBuilder sb=new StringBuilder();
			sb.append('[');
			sb.append(AnsLength);
			sb.append(']');
			for (int p: selected) {
				sb.append(' ');
				sb.append(p);
			}
			Ans=sb.toString();
		}

		if (selected.size()>=AnsLength) return;
		for (int i=0;i<points.length;i++) if (rem>=points[i]) {
			selected.addLast(points[i]);
			find(points,selected,rem-points[i]);
			selected.removeLast();
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] points = new int [N];
			for (int n=N-1;n>=0;n--) points[n]=Integer.parseInt(st.nextToken());
			
			Ans=null;
			AnsLength=Integer.MAX_VALUE;
			find(points,new LinkedList<>(),S);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			sb.append(Ans!=null? Ans : "impossible");
			System.out.println(sb);
		}
	}

}
