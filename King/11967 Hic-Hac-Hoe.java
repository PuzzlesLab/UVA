import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		String key;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		
			StringBuilder sb=new StringBuilder();
			sb.append(x);
			sb.append(",");
			sb.append(y);
			this.key=sb.toString();
		}
	}
	
	private static boolean canWin(Tuple t, HashMap<String, Tuple> others, int K) {
		// check vertical
		int count=1;
		for (int dy=1;dy<K;dy++) {
			Tuple tn=new Tuple(t.x,t.y+dy);
			if (others.containsKey(tn.key)) count++;
			else break;
		}
		if (count==K) return true;
		
		// check horizontal
		count=1;
		for (int dx=1;dx<K;dx++) {
			Tuple tn=new Tuple(t.x+dx,t.y);
			if (others.containsKey(tn.key)) count++;
			else break;
		}
		if (count==K) return true;
		
		// check diagonal 1
		count=1;
		for (int d=1;d<K;d++) {
			Tuple tn=new Tuple(t.x+d,t.y+d);
			if (others.containsKey(tn.key)) count++;
			else break;
		}
		if (count==K) return true;
		
		// check diagonal 2
		count=1;
		for (int d=1;d<K;d++) {
			Tuple tn=new Tuple(t.x+d,t.y-d);
			if (others.containsKey(tn.key)) count++;
			else break;
		}
		if (count==K) return true;
		
		return false;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			HashMap<String,Tuple> cMap=new HashMap<>();
			HashMap<String,Tuple> nMap=new HashMap<>();
			for (int n=0;n<N;n++) {
				Tuple pos=new Tuple(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				if ((n&1)==0) cMap.put(pos.key,pos);
				else nMap.put(pos.key,pos);
			}

			boolean cWins=false;
			for (Tuple pos: cMap.values()) {
				cWins=canWin(pos,cMap,K);
				if (cWins) break;
			}
			boolean nWins=false;
			for (Tuple pos: nMap.values()) {
				nWins=canWin(pos,nMap,K);
				if (nWins) break;
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(t);
			sb.append(": ");
			if (!cWins && !nWins) sb.append("none");
			else if (cWins && nWins) sb.append("error");
			else if (cWins) sb.append("crosses");
			else sb.append("noughts");
			
			System.out.println(sb);
		}
	}

}