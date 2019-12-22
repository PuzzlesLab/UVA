import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	private static int MinTotalDist=Integer.MAX_VALUE;
	
	public static void dfs(int [][] map, HashSet<Integer> offices, int currMax, TreeSet<Integer> ans) {
		if (offices.size()<5) {
			for (int i=currMax;i<25;i++) {
				offices.add(i);
				dfs(map, offices, i+1, ans);
				offices.remove(i);
			}
		} else {
			int totalDist=0;

			for (int x=0;x<5;x++) for (int y=0;y<5;y++) if (map[x][y]>0) {
				int minDist=Integer.MAX_VALUE;
				for (int office : offices) minDist=Math.min(minDist, (Math.abs(office/5-x)+Math.abs(office%5-y)) * map[x][y]);
				totalDist+=minDist;
			}
			
			if (totalDist<MinTotalDist) {
				MinTotalDist=totalDist;
				ans.clear();
				ans.addAll(offices);
			}
			
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());

		for (int t=0;t<T;t++) {
			int N=Integer.parseInt(br.readLine());
			int [][] map=new int [5][5];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=Integer.parseInt(st.nextToken());
			}
			
			TreeSet<Integer> ans=new TreeSet<>();
			MinTotalDist=Integer.MAX_VALUE;
			dfs(map, new HashSet<>(), 0, ans);
			
			ArrayList<Integer> l=new ArrayList<>(ans);
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<5;i++) {
				sb.append(l.get(i));
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}