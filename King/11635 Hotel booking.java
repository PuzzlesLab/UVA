import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		int city;
		int len;
		
		public Tuple(int c, int l) {
			this.city=c;
			this.len=l;
		}

		public int compareTo(Tuple s) {
			return this.len-s.len;
		}
	}

	private static final int NULL=100000;
	private static final int MAX_DUR=600;
	private static int N;
	private static ArrayList<Tuple> [] DurEdges;

	private static int [] getDuration(int from) {
		PriorityQueue<Tuple> q=new PriorityQueue<>();
		int [] minDur=new int [N];
		Arrays.fill(minDur,NULL);
		minDur[from]=0;
		q.offer(new Tuple(from,0));
		while (!q.isEmpty()) {
			Tuple curr=q.poll();
			if (curr.len>minDur[curr.city]) continue;
			if (DurEdges[curr.city]==null) continue;
			for (int i=0;i<DurEdges[curr.city].size();i++) {
				Tuple edge=DurEdges[curr.city].get(i);
				int nDur=curr.len+edge.len;
				if (nDur>MAX_DUR) continue;
				if (minDur[edge.city]>nDur) {
					minDur[edge.city]=nDur;
					q.offer(new Tuple(edge.city,nDur));
				}
			}
		}
		return minDur;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);

			StringTokenizer st=new StringTokenizer(br.readLine());
			HashSet<Integer> hotelSet=new HashSet<>();
			int H=Integer.parseInt(st.nextToken());
			for (int h=0;h<H;h++) hotelSet.add(Integer.parseInt(st.nextToken())-1);
			hotelSet.add(0);
			hotelSet.add(N-1);
			ArrayList<Integer> hotels=new ArrayList<>(hotelSet);

			int M=Integer.parseInt(br.readLine());
			DurEdges=new ArrayList [N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int A=Integer.parseInt(st.nextToken())-1;
				int B=Integer.parseInt(st.nextToken())-1;
				int dur=Integer.parseInt(st.nextToken());
				if (DurEdges[A]==null) DurEdges[A]=new ArrayList<>();
				if (DurEdges[B]==null) DurEdges[B]=new ArrayList<>();
				DurEdges[A].add(new Tuple(B,dur));
				DurEdges[B].add(new Tuple(A,dur));
			}

			ArrayList<Integer> [] hotelEdges=new ArrayList [N];
			for (int i=0;i<hotels.size();i++) {
				int n=hotels.get(i);
				int [] dur=getDuration(n);
				for (int i2=i+1;i2<hotels.size();i2++) if (dur[hotels.get(i2)]<=MAX_DUR) {
					int n2=hotels.get(i2);
					if (hotelEdges[n]==null) hotelEdges[n]=new ArrayList<>();
					if (hotelEdges[n2]==null) hotelEdges[n2]=new ArrayList<>();
					hotelEdges[n].add(n2);
					hotelEdges[n2].add(n);
				}
			}

			int ans=-1;
			boolean [] visited=new boolean [N];
			LinkedList<Tuple> q=new LinkedList<>();
			q.addLast(new Tuple(0,0));
			visited[0]=true;
			while (!q.isEmpty()) {
				Tuple curr=q.poll();
				if (curr.city==N-1) {
					ans=curr.len-1;
					break;
				};
				if (hotelEdges[curr.city]==null) continue;
				for (int i=0;i<hotelEdges[curr.city].size();i++) {
					int n=hotelEdges[curr.city].get(i);
					if (visited[n]) continue;
					Tuple next=new Tuple(n,curr.len+1);
					visited[n]=true;
					q.addLast(next);
				}
			}

			System.out.println(ans);
		}
	}

}
