import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
	
	private static class Solution implements Comparable<Solution> {
		int small, large;
		
		public Solution (int a, int b) {
			this.small=Math.min(a, b);
			this.large=Math.max(a, b);
		}
		
		public int compareTo(Solution s) {
			return (this.large-this.small)-(s.large-s.small);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			TreeMap<Integer, Integer> map=new TreeMap<>();
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) {
				int v=Integer.parseInt(st.nextToken());
				map.put(v, map.getOrDefault(v, 0)+1);
			}
			int sum=Integer.parseInt(br.readLine());
			
			PriorityQueue<Solution> q=new PriorityQueue<>();
			ArrayList<Integer> list=new ArrayList<>();
			list.addAll(map.keySet());
			for (int i=0;i<list.size();i++) {
				int p1=list.get(i);
				int p2=sum-p1;
				if ((p1==p2 && map.get(p1)>=2) || (p1!=p2 && map.containsKey(p2))) q.offer(new Solution(p1, p2));
			}
			
			Solution sol=q.poll();
			System.out.printf("Peter should buy books whose prices are %d and %d.\n\n", sol.small, sol.large);
			br.readLine();
		}
	}

}