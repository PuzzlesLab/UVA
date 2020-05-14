import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

class Main{

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); //empty
			int N=Integer.parseInt(br.readLine());
			StringBuilder sb=new StringBuilder();
			int timeTaken=0;
			LinkedList<Integer> left=new LinkedList<>();
			for (int n=0;n<N;n++) left.add(Integer.parseInt(br.readLine()));
			Collections.sort(left);
			
			while (!left.isEmpty()) {
				if (left.size()>3) {
					int A=left.removeFirst();
					int B=left.removeFirst();
					int D=left.removeLast();
					int C=left.removeLast();
					
					int T1=B+A+D+B;
					int T2=C+A+D+A;
					if (T1 < T2) {
						sb.append(A);
						sb.append(' ');
						sb.append(B);
						sb.append('\n');
						
						sb.append(A);
						sb.append('\n');

						sb.append(C);
						sb.append(' ');
						sb.append(D);
						sb.append('\n');
						
						sb.append(B);
						sb.append('\n');
						
						left.addFirst(B);
						left.addFirst(A);
						
						timeTaken+=T1;
					} else {
						left.addFirst(B);
						
						sb.append(A);
						sb.append(' ');
						sb.append(C);
						sb.append('\n');

						sb.append(A);
						sb.append('\n');
						
						sb.append(A);
						sb.append(' ');
						sb.append(D);
						sb.append('\n');
						
						sb.append(A);
						sb.append('\n');
						left.addFirst(A);
						
						timeTaken+=T2;
					}
				} else if (left.size()==3) {
					int A=left.removeFirst();
					int B=left.removeFirst();
					sb.append(A);
					sb.append(' ');
					sb.append(B);
					sb.append('\n');
					
					sb.append(A);
					sb.append('\n');
					left.addFirst(A);
					timeTaken+=(A+B);
				} else if (left.size()==2) {
					int A=left.removeFirst();
					int B=left.removeFirst();
					sb.append(A);
					sb.append(' ');
					sb.append(B);
					sb.append('\n');
					timeTaken+=B;
				} else {
					int A=left.removeFirst();
					sb.append(A);
					sb.append('\n');
					timeTaken+=A;
				}
			}
			
			if (testCase>0) System.out.println();
			System.out.println(timeTaken);
			System.out.print(sb.toString());
		}
	}

}