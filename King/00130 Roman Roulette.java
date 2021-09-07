import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static boolean perform(int N, int K, int start) {
		ArrayList<Integer> nodes=new ArrayList<>();
		for (int n=1;n<=N;n++) nodes.add(n);
		int toDieIdx=Math.floorMod(start+K-2, nodes.size());
		while (nodes.size()>1) {
			Integer toDie=nodes.remove(toDieIdx);
			if (toDie==1) return false;
			int toBurryIdx=(toDieIdx-1+K)%nodes.size();
			Integer toBurry=null;
			if (toBurryIdx>=toDieIdx) {
				toBurry=nodes.remove(toBurryIdx);
				nodes.add(toDieIdx,toBurry);
			} else if (toBurryIdx<toDieIdx) {
				toBurry=nodes.remove(toBurryIdx);
				nodes.add(toDieIdx-1,toBurry);
			}
			toDieIdx=(nodes.indexOf(toBurry)+K)%nodes.size();
		}
		return true;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			int sol=-1;
			for (int n=1;n<=N;n++) if (perform(N,K,n)) {
				sol=n;
				break;
			}

			System.out.println(sol);
		}
	}

}
