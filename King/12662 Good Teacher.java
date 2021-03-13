import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		String [] name=new String [N];
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) name[i]=st.nextToken();

		int Q=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int q=0;q<Q;q++) {
			int p=Integer.parseInt(br.readLine())-1;
			if (!name[p].equals("?")) sb.append(name[p]);
			else {
				String leftName=null;
				int distToLeft=-1;
				for (int left=p-1;left>=0;left--) if (!name[left].equals("?")) {
					leftName=name[left];
					distToLeft=p-left;
					break;
				}
				String rightName=null;
				int distToRight=-1;
				for (int right=p+1;right<N;right++) if (!name[right].equals("?")) {
					rightName=name[right];
					distToRight=right-p;
					break;
				}
				if (distToLeft==-1 && distToRight!=-1) {
					for (int d=0;d<distToRight;d++) sb.append("left of ");
					sb.append(rightName);
				} else if (distToLeft!=-1 && distToRight==-1) {
					for (int d=0;d<distToLeft;d++) sb.append("right of ");
					sb.append(leftName);
				} else if (distToLeft!=-1 && distToRight!=-1) {
					if (distToLeft==distToRight) {
						sb.append("middle of ");
						sb.append(leftName);
						sb.append(" and ");
						sb.append(rightName);
					} else if (distToLeft<distToRight) {
						for (int d=0;d<distToLeft;d++) sb.append("right of ");
						sb.append(leftName);
					} else {
						for (int d=0;d<distToRight;d++) sb.append("left of ");
						sb.append(rightName);
					}
				}
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}