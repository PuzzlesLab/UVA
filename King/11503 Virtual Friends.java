import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
	
	public static String findSet(Map<String, String> parent, String s) {
		String root=parent.get(s);
		if (root==null) parent.put(s, s);
		else if (!root.equals(s)) parent.put(s, findSet(parent, root));
		return parent.get(s);
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		
		StringBuilder sb=new StringBuilder();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			HashMap<String, String> parent=new HashMap<>();
			HashMap<String, Integer> rank=new HashMap<>();
			HashMap<String, Integer> size=new HashMap<>();
			
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String x=findSet(parent, st.nextToken()), y=findSet(parent, st.nextToken());
				
				if (!x.equals(y)) {
					int rankX=rank.getOrDefault(x, 0), rankY=rank.getOrDefault(y, 0);
					if ( rankX > rankY ) {
						parent.put(y, x);
						if (!x.equals(y)) size.put(x, size.getOrDefault(x,1)+size.getOrDefault(y,1));
						sb.append(size.getOrDefault(x, 1));
					} else {
						if (rankX == rankY) rank.put(y, ++rankY);
						parent.put(x,y);
						if (!x.equals(y)) size.put(y, size.getOrDefault(x,1)+size.getOrDefault(y,1));
						sb.append(size.getOrDefault(y, 1));
					}
				} else sb.append(size.getOrDefault(x, 1));
				sb.append('\n');
			}

		}
		System.out.print(sb.toString());
		
	}

}