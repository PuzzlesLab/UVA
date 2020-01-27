import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge> {
		int [] filled;
		int dist;
		
		public Edge (int a, int b, int c, int dist) {
			this.filled= new int [] {a,b,c};
			this.dist=dist;
		}
		
		public Edge(Edge e) {
			this.filled=Arrays.copyOf(e.filled,e.filled.length);
			this.dist=e.dist;
		}
		
		public int compareTo(Edge e) {
			return this.dist-e.dist;
		}
		
		public String toString() {
			StringBuilder sb=new StringBuilder();
			sb.append(this.filled[0]);
			sb.append('_');
			sb.append(this.filled[1]);
			sb.append('_');
			sb.append(this.filled[2]);
			return sb.toString();
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] maxFilled= {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int D=Integer.parseInt(st.nextToken());
			
			int [] maxPour=new int [D+1];
			Arrays.fill(maxPour, Integer.MAX_VALUE);
			
			HashMap<String, Integer> stateDist=new HashMap<>();
			Edge start=new Edge(0,0,maxFilled[2],0);
			stateDist.put(start.toString(), 0);
			PriorityQueue<Edge> q=new PriorityQueue<>();
			q.offer(start);
			while (!q.isEmpty()) {
				Edge e=q.poll();
				for (int i=0;i<e.filled.length;i++) if (e.filled[i]<maxPour.length) maxPour[e.filled[i]]=Math.min(maxPour[e.filled[i]], e.dist);
				for (int jugS=0;jugS<e.filled.length;jugS++) if (e.filled[jugS]>0) for (int jugD=0;jugD<e.filled.length;jugD++) if (e.filled[jugD]<maxFilled[jugD] && jugS!=jugD) {
					int pour=Math.min(e.filled[jugS], maxFilled[jugD]-e.filled[jugD]);
					Edge next=new Edge(e);
					next.filled[jugS]-=pour;
					next.filled[jugD]+=pour;
					next.dist+=pour;
					String nextKey=next.toString();
					if (next.dist < stateDist.getOrDefault(nextKey, Integer.MAX_VALUE)) {
						stateDist.put(nextKey, next.dist);
						q.offer(next);
					}
				}
			}
			
			int ans=-1, ansPour=-1;
			for (int i=D;i>=0;i--) if (maxPour[i]!=Integer.MAX_VALUE) {
				ans=i;
				ansPour=maxPour[i];
				break;
			}
			
			System.out.printf("%d %d\n",ansPour,ans);
		}
	}

}