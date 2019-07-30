import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {
	
	public static class Range {
		String s1, s2;
		public Range(String s1, String s2) { this.s1=s1; this.s2=s2;}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCaseCount=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);

			LinkedList<Range> ranges=new LinkedList<>();
			String [] nums=new String[N];
			for (int n=0;n<N;n++) nums[n]=br.readLine();
			
			ranges.add(new Range(nums[0], nums[0]));
			for (int i=1;i<N;i++) {
				long l1=Integer.parseInt(nums[i-1]);
				long l2=Integer.parseInt(nums[i]);
				if (l1+1!=l2) {
					ranges.add(new Range(nums[i], nums[i]));
				} else ranges.getLast().s2 = nums[i];
			}
			
			StringBuilder sb=new StringBuilder("Case ");
			sb.append(testCaseCount++);
			sb.append(":\n");
			for (Range r : ranges) {
				sb.append(r.s1);
				if (!r.s1.equals(r.s2)) sb.append('-');
				
				if (r.s1.length() != r.s2.length()) sb.append(r.s2);
				else {
					int i=0;
					for (i=0;i<r.s2.length();i++) if (r.s1.charAt(i)!=r.s2.charAt(i)) break;
					for (;i<r.s2.length();i++) sb.append(r.s2.charAt(i));
				}
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}

}
