import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Tuple implements Comparable<Tuple> {
		int r, c;
		
		public Tuple(int r, int c) {
			this.r=r;
			this.c=c;
		}
		
		public int compareTo(Tuple t) {
			if (this.r!=t.r) return this.r-t.r;
			return this.c-t.c;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			boolean [][] holderOccupied=new boolean[R][C+1];

			int P=Integer.parseInt(br.readLine());
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				s=st.nextToken();
				int r=s.charAt(0)-'A';
				int c=Integer.parseInt(s.substring(1))-1;
				
				boolean left=st.nextToken().charAt(0)=='-';
				holderOccupied[r][left?c:c+1]=true;
			}
			
			int Z=Integer.parseInt(br.readLine());
			PriorityQueue<Tuple> q=new PriorityQueue<>();
			for (int z=0;z<Z;z++) {
				s=br.readLine();
				q.offer(new Tuple(s.charAt(0)-'A', Integer.parseInt(s.substring(1))-1));
			}
			
			boolean canPut=true;
			while (!q.isEmpty()) {
				Tuple t=q.poll();
				if (!holderOccupied[t.r][t.c]) holderOccupied[t.r][t.c]=true;
				else if (!holderOccupied[t.r][t.c+1]) holderOccupied[t.r][t.c+1]=true;
				else {
					canPut=false;
					break;
				}
			}
			
			System.out.println(canPut? "YES" : "NO");
		}
	}

}