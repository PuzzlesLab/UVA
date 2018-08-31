import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

//Yet another bugged input. Use scanner instead!
class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		HashSet<Integer> [] qSet=new HashSet [3];
		TreeSet<Integer> [] qSetDiff=new TreeSet [3];
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			for (int f=0;f<3;f++) {
				qSet[f]=new HashSet<>();
				qSetDiff[f]=new TreeSet<>();
				
				int I=sc.nextInt();
				for (int i=0;i<I;i++) {
					int q=sc.nextInt();
					qSet[f].add(q);
					qSetDiff[f].add(q);
				}
			}
			
			qSetDiff[0].removeAll(qSet[1]);
			qSetDiff[0].removeAll(qSet[2]);
			
			qSetDiff[1].removeAll(qSet[0]);
			qSetDiff[1].removeAll(qSet[2]);
			
			qSetDiff[2].removeAll(qSet[0]);
			qSetDiff[2].removeAll(qSet[1]);
			
			int maxDiff=Math.max(Math.max(qSetDiff[0].size(), qSetDiff[1].size()), qSetDiff[2].size());
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase);
			sb.append(":\n");
			for (int f=0;f<3;f++) if (qSetDiff[f].size()==maxDiff) {
				sb.append(f+1);
				sb.append(" ");
				sb.append(maxDiff);
				sb.append(" ");
				for (int q : qSetDiff[f]) {
					sb.append(q);
					sb.append(" ");
				}
				sb.setLength(sb.length()-1);
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
	}

}
