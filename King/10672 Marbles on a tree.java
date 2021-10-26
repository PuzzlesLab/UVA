import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static class Vertex {
		int marblesCount, rem;
		int [] adj;
		Vertex root;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			
			Vertex [] vertices=new Vertex[N];
			for (int n=0;n<N;n++) vertices[n]=new Vertex();
			
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int vNo=Integer.parseInt(st.nextToken())-1;
				Vertex curr = vertices[vNo];
				curr.marblesCount=Integer.parseInt(st.nextToken());
				curr.adj=new int [Integer.parseInt(st.nextToken())];
				for (int i=0;i<curr.adj.length;i++) {
					curr.adj[i]=Integer.parseInt(st.nextToken())-1;
					vertices[curr.adj[i]].root=curr;
				}
			}
			
			LinkedList<Vertex> q=new LinkedList<>();
			for (int n=0;n<N;n++) {
				vertices[n].rem=vertices[n].adj.length;
				if (vertices[n].rem==0) q.add(vertices[n]);
			}
		
			int ans=0;
			while (!q.isEmpty()) {
				Vertex curr=q.removeFirst();
				int diff=curr.marblesCount-1;
				ans+=Math.abs(diff);
				curr.marblesCount=1;
				
				if (curr.root!=null) {
					curr.root.marblesCount+=diff;
					curr.root.rem--;
					if (curr.root.rem==0) q.addLast(curr.root);
				}
			}

			System.out.println(ans);
		}
	}

}
