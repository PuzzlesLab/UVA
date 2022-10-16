import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			boolean [][] adjMat=new boolean [26][26];
			StringTokenizer st=new StringTokenizer(s);
			StringTokenizer st2=new StringTokenizer(st.nextToken(),";");
			int posP=st.nextToken().charAt(0)-'A';
			int posL=st.nextToken().charAt(0)-'A';
			boolean [] visitedP=new boolean [26];
			visitedP[posP]=true;
			boolean trappedP=false;
			boolean [] visitedL=new boolean [26];
			visitedL[posL]=true;
			boolean trappedL=false;

			while (st2.hasMoreTokens()) {
				s=st2.nextToken();
				int from=s.charAt(0)-'A';
				for (int i=2;i<s.length();i++) {
					int to=s.charAt(i)-'A';
					if (to<0) continue;
					adjMat[from][to]=true;
					adjMat[to][from]=true;
				}
			}
			
			if (posP==posL) {
				System.out.println("Both annihilated in node "+(char)(posP+'A'));
				continue;
			}

			StringBuilder sb=new StringBuilder();
			while (true) {
				int nextP=-1;
				if (!trappedP) {
					for (int i=0;i<26;i++) {
						int next=Math.floorMod(posP+i,26);
						if (adjMat[posP][next] && !visitedP[next] && !visitedL[next]) {
							nextP=next;
							break;
						}
					}
				}
				if (nextP==-1 && !trappedP) {
					if (sb.length()>0) sb.append(' ');
					sb.append("Paskill trapped in node ");
					sb.append((char)(posP+'A'));
					trappedP=true;
				}

				int nextL=-1;
				if (!trappedL) {
					for (int i=0;i<26;i++) {
						int next=Math.floorMod(posL-i,26);
						if (adjMat[posL][next] && !visitedL[next]) {
							nextL=next;
							break;
						}
					}
				}
				if (nextL==-1 && !trappedL) {
					if (sb.length()>0) sb.append(' ');
					sb.append("Lisper trapped in node ");
					sb.append((char)(posL+'A'));
					trappedL=true;
				}

				if (!trappedP) {
					posP=nextP;
					visitedP[posP]=true;
				}
				if (!trappedL) {
					posL=nextL;
					visitedL[posL]=true;
				}
				if (posP==posL) {
					if (sb.length()>0) sb.append(' ');
					sb.append("Both annihilated in node ");
					sb.append((char)(posP+'A'));
					break;
				}
				if (visitedP[posL]) {
					if (sb.length()>0) sb.append(' ');
					sb.append("Lisper destroyed in node ");
					sb.append((char)(posL+'A'));
					break;
				}

				if (trappedP || trappedL) break;
			}
			System.out.println(sb.toString());
		}
	}

}