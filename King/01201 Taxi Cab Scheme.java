import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static int [] Pair;

	private static class Ride {
		int start, dur, end;
		int a,b,c,d;
		
		public Ride(String s) {
			StringTokenizer st=new StringTokenizer(s);
			String [] stt=st.nextToken().split(":");
			this.a=Integer.parseInt(st.nextToken());
			this.b=Integer.parseInt(st.nextToken());
			this.c=Integer.parseInt(st.nextToken());
			this.d=Integer.parseInt(st.nextToken());
			
			this.start=Integer.parseInt(stt[0])*60+Integer.parseInt(stt[1]);
			this.dur=Math.abs(a-c)+Math.abs(b-d);
			this.end=(this.start+this.dur)%1440;
		}

		public boolean canPair(Ride r) {
			int newEnd=this.end+Math.abs(this.c-r.a)+Math.abs(this.d-r.b);
			if (this.start<=newEnd) return newEnd<r.start;
			else return newEnd+1440<r.start;
		}
	}

	private static int mcbm(int n) {
		if (Visited[n]) return 0;
		
		Visited[n]=true;
		for (int i=0;i<AdjList[n].size();i++) {
			int next=AdjList[n].get(i);
			if (Pair[next]==-1 || mcbm(Pair[next])==1) {
				Pair[next]=n;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine().trim());
        for (int tc=0;tc<TC;tc++) {
        	int N=Integer.parseInt(br.readLine());
        	
        	Ride [] rides=new Ride[N];
        	for (int n=0;n<N;n++) rides[n]=new Ride(br.readLine());
        	
        	AdjList=new ArrayList [N];
        	for (int n=0;n<N;n++) AdjList[n]=new ArrayList<>();
        	
        	for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) if (n!=n2) {
        		if (rides[n].canPair(rides[n2])) AdjList[n].add(n2);
        	}
        	
        	Pair=new int [N];
        	Arrays.fill(Pair,-1);
        	int ans=0;
        	for (int n=0;n<N;n++) {
        		Visited=new boolean [N];
        		ans+=mcbm(n);
        	}
        	System.out.println(N-ans);
        }
	}

}