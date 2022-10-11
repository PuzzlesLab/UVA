import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Room {
		int id;
		ArrayList<Edge> edges;
		
		int num, low;
		Room parent;

		public Room(int id) {
			this.id=id;
			this.edges=new ArrayList<>();
		}
	}
	
	private static class Edge {
		boolean isBridge;
		Room r1, r2;
		
		public Edge(Room r1, Room r2) {
			this.r1=r1;
			this.r2=r2;
		}
		
		public Room next(Room from) {
			return (from==this.r1) ? r2 : r1;
		}
	}

	private static int SeqNumMax, R;

	private static void disableEdges(Room curr) {
		curr.num=SeqNumMax++;
		curr.low=curr.num;
		for (int i=0;i<curr.edges.size();i++) {
			Edge edge=curr.edges.get(i);
			Room next=edge.next(curr);
			
			if (next.num==0) {
				next.parent=curr;
				disableEdges(next);
				if (next.low>curr.num) edge.isBridge=true;
				curr.low=Math.min(curr.low, next.low);
			} else if (next!=curr.parent) {
				curr.low=Math.min(curr.low, next.num);
			}
		}
	}
	
	private static int ufds(int roomId, int [] parent) {
		if (parent[roomId]==roomId) return roomId;

		parent[roomId]=ufds(parent[roomId], parent);
		return parent[roomId];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			if (R==0 && C==0 && Q==0) break;

			Room [] rooms=new Room[R+1];
			for (int r=1;r<=R;r++) rooms[r]=new Room(r);
			
			Edge [] edges=new Edge[C];
			for (int c=0;c<C;c++) {
				st=new StringTokenizer(br.readLine());
				int A=Integer.parseInt(st.nextToken());
				int B=Integer.parseInt(st.nextToken());
				edges[c]=new Edge(rooms[A],rooms[B]);
				rooms[A].edges.add(edges[c]);
				rooms[B].edges.add(edges[c]);
			}

			// Find out bridges and disable useless edges, the graph becomes "acyclic" (aka simple path)
			SeqNumMax=1;
			for (int r=1;r<=R;r++) if (rooms[r].num==0) disableEdges(rooms[r]);
			
			// Perform UFDS to group rooms into groups
			int [] parent=new int [R+1];
			for (int r=1;r<=R;r++) parent[r]=r;
			for (int c=0;c<C;c++) {
				Edge edge=edges[c];
				if (!edge.isBridge) continue;

				int r1R=ufds(edge.r1.id,parent);
				int r2R=ufds(edge.r2.id,parent);

				if (r1R>r2R) parent[r1R]=r2R;
				else if (r1R<r2R) parent[r2R]=r1R;
			}

			StringBuilder sb=new StringBuilder();
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int S=Integer.parseInt(st.nextToken());
				int T=Integer.parseInt(st.nextToken());
				sb.append(ufds(S, parent)==ufds(T, parent)?'Y':'N');
				sb.append('\n');
			}
			sb.append('-');

			System.out.println(sb.toString());
		}
	}

}