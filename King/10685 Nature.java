import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static String findSet(HashMap<String, String> parent, String key) {
		String value=parent.get(key);
		if (!value.equals(key)) parent.put(key, findSet(parent, value));
		return parent.get(key);
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int C=Integer.parseInt(st.nextToken()); int P=Integer.parseInt(st.nextToken());
			HashMap<String, String> parent=new HashMap<>();
			HashMap<String, Integer> rank=new HashMap<>();
			HashMap<String, Integer> size=new HashMap<>();
			
			for (int c=0;c<C;c++) {
				s=br.readLine();
				parent.put(s, s);
				rank.put(s, 0);
				size.put(s, 1);
			}
			
			int maxSize=1;
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				String left=findSet(parent, st.nextToken());
				String right=findSet(parent, st.nextToken());
				if (!left.equals(right)) {
					if (rank.get(left)>rank.get(right)) {
						parent.put(right, left);
						size.put(left, size.get(left)+size.get(right));
						maxSize=Math.max(maxSize, size.get(left));
					} else {
						if (rank.get(left)==rank.get(right)) rank.put(right, rank.get(right)+1);
						
						parent.put(left, right);
						size.put(right, size.get(left)+size.get(right));
						maxSize=Math.max(maxSize, size.get(right));
					}
				}
			}
			
			System.out.println(maxSize);
			br.readLine();
		}
	}

}