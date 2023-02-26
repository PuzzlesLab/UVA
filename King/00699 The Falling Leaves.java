import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	private static int [] Preorder;
	private static int PreIdx;
	private static HashMap<Integer,Integer> Ans;

	private static void createTree(int pos) {
		PreIdx++;
		if (PreIdx>=Preorder.length) return;
		if (Preorder[PreIdx]==-1) return;

		Ans.put(pos,Ans.getOrDefault(pos,0)+Preorder[PreIdx]);
		createTree(pos-1);
		createTree(pos+1);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("-1")) {
			StringTokenizer st=new StringTokenizer(s);
			Preorder=new int [st.countTokens()];
			for (int i=0;i<Preorder.length;i++) Preorder[i]=Integer.parseInt(st.nextToken());
			
			PreIdx=-1;
			Ans=new HashMap<>();
			createTree(0);
			if (Ans.isEmpty()) continue;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase++);
			sb.append(":\n");
			for (int pos: new TreeSet<>(Ans.keySet())) {
				sb.append(Ans.get(pos));
				sb.append(' ');
			}
			if (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}
