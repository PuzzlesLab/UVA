import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			LinkedList<Integer> nums=new LinkedList<>();
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) nums.add(Integer.parseInt(st.nextToken()));
			LinkedList<Integer> q=new LinkedList<>();
			
			int min=Integer.MAX_VALUE;
			int sum=0;
			while (!nums.isEmpty()) {
				q.addLast(nums.removeFirst());
				sum+=q.getLast();
				while (sum>=S && !q.isEmpty()) {
					min=Math.min(min, q.size());
					sum-=q.removeFirst();
				}
			}
			if (min==Integer.MAX_VALUE) min=0;
			System.out.println(min);
		}
	}

}
