import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static class Data {
		int Ca, Cb;
		String sol;
		
		public Data(int Ca, int Cb, String sol) {
			this.Ca=Ca;
			this.Cb=Cb;
			this.sol=sol;
		}
		
		public String appendSol(String newStep) {
			StringBuilder sb=new StringBuilder(this.sol);
			sb.append('\n');
			sb.append(newStep);
			return sb.toString();
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int CaMax=Integer.parseInt(st.nextToken());
			int CbMax=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			boolean [][] state=new boolean [CaMax+1][CbMax+1];
			LinkedList<Data> queue=new LinkedList<>();
			queue.addLast(new Data(0,0,""));
			Data sol=null;
			while (!queue.isEmpty()) {
				Data d=queue.removeFirst();
				if (d.Cb==N) {
					sol=d;
					break;
				}
				if (state[d.Ca][d.Cb]) continue;
				state[d.Ca][d.Cb]=true;
				if (d.Ca<CaMax) queue.addLast(new Data(CaMax,d.Cb,d.appendSol("fill A")));
				if (d.Cb<CbMax) queue.addLast(new Data(d.Ca,CbMax,d.appendSol("fill B")));
				if (d.Ca>0) queue.addLast(new Data(0,d.Cb,d.appendSol("empty A")));
				if (d.Cb>0) queue.addLast(new Data(d.Ca,0,d.appendSol("empty B")));
				if (d.Ca>0 && d.Cb<CbMax) {
					int delta=Math.min(CbMax-d.Cb, d.Ca);
					queue.addLast(new Data(d.Ca-delta,d.Cb+delta,d.appendSol("pour A B")));
				}
				if (d.Cb>0 && d.Ca<CaMax) {
					int delta=Math.min(CaMax-d.Ca, d.Cb);
					queue.addLast(new Data(d.Ca+delta,d.Cb-delta,d.appendSol("pour B A")));
				}
			}
			System.out.println(sol.appendSol("success").trim());
		}
	}

}
