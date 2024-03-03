import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {

	private static int cut(TreeMap<Integer,String> tokens, int center) {
		Entry<Integer,String> entry=tokens.floorEntry(center);
		int start=entry.getKey();
		String cut=entry.getValue();
		String left=cut.substring(0,center-start+1);
		String right=cut.substring(center-start+1);

		int cost=0;
		boolean [][] exists=new boolean [2][26];
		for (int i2=0;i2<left.length();i2++) exists[0][left.charAt(i2)-'a']=true;
		for (int i2=0;i2<right.length();i2++) exists[1][right.charAt(i2)-'a']=true;
		for (int i2=0;i2<exists[0].length;i2++) if (exists[0][i2]!=exists[1][i2]) cost++;

		tokens.remove(start);
		tokens.put(start,left);
		tokens.put(start+left.length(),right);
		return cost;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			int K=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] cuts=new int [K];
			for (int k=0;k<K;k++) cuts[k]=Integer.parseInt(st.nextToken())-1;
			String s=br.readLine();
			
			TreeMap<Integer,String> tokens=new TreeMap<>(); // Binary search the nearest <= index.
			tokens.put(0,s);

			int total=0;
			for (int k=0;k<K;k++) if (cuts[k]>=0 && cuts[k]<s.length()) total+=cut(tokens,cuts[k]);
			System.out.println(total);
		}
	}
}