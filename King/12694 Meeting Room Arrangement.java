import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static int Solution=0;

	public static void search(ArrayList<Integer> start, ArrayList<Integer> end, int currIdx, int currCount, boolean [] occupied) {
		for (int i=currIdx+1;i<start.size();i++) {
			boolean flag=true;
			int currStart=start.get(i);
			int currEnd=end.get(i);
			for (int test=currStart;test<currEnd;test++) flag&=!occupied[test];
			if (flag) {
				for (int test=currStart;test<currEnd;test++) occupied[test]=true;
				search(start,end,i,currCount+1,occupied);
				Solution=Math.max(currCount+1,Solution);
				for (int test=currStart;test<currEnd;test++) occupied[test]=false;
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine().trim()); // error without .trim(). :/
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			ArrayList<Integer> startTime=new ArrayList<>();
			ArrayList<Integer> endTime=new ArrayList<>();

			while (true) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int start=Integer.parseInt(st.nextToken());
				int end=Integer.parseInt(st.nextToken());
				if (start==0 && end==0) break;
				startTime.add(start);
				endTime.add(end);
			}
			
			Solution=0;
			search(startTime,endTime,-1,0,new boolean[10]);
			System.out.println(Solution);
		}
	}
}
