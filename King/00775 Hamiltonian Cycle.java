import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] Edges;
	private static int [] Ans;

	private static void find(int n, int [] remVisit, int [] seq, int seqI) {
		if (Ans!=null) return;
		if (seqI==seq.length) {
			Ans=new int [seq.length];
			for (int i=0;i<Ans.length;i++) Ans[i]=seq[i];
			return;
		}
		
		for (int i=0;i<Edges[n].size();i++) {
			int nN=Edges[n].get(i);
			if (remVisit[nN]>0) {
				if (nN==0 && seqI+1<seq.length) continue;

				remVisit[nN]--;
				seq[seqI]=nN;
				find(nN,remVisit,seq,seqI+1);
				remVisit[nN]++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			Edges=new ArrayList [N];
			for (int n=0;n<N;n++) Edges[n]=new ArrayList<>();
			
			while (!(s=br.readLine()).equals("%")) {
				StringTokenizer st=new StringTokenizer(s);
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				Edges[n1].add(n2);
				Edges[n2].add(n1);
			}
			
			Ans=null;
			int [] remVisit=new int [N];
			Arrays.fill(remVisit,1);
			remVisit[0]=2;
			int [] seq=new int [N+1];
			seq[0]=0;
			find(0,remVisit,seq,1);

			if (Ans==null) System.out.println("N");
			else {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<Ans.length;i++) {
					sb.append(Ans[i]+1);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb);
			}
		}
	}

}