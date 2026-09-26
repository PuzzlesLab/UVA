import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Pupil {
		int h;
		char g;
		String music;
		String sport;
		
		public Pupil(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.h=Integer.parseInt(st.nextToken());
			this.g=st.nextToken().charAt(0);
			this.music=st.nextToken();
			this.sport=st.nextToken();
		}
		
		public boolean isResist(Pupil p) {
			if (Math.abs(this.h-p.h)>40) return true;
			// No need gender check here. We already split by gender.
			if (!this.music.equals(p.music)) return true;
			return this.sport.equals(p.sport);
		}
	}
	
	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static int [] Pair;
	
	private static int mcbm(int curr) {
		if (Visited[curr]) return 0;
		
		Visited[curr]=true;
		for (int i=0;i<AdjList[curr].size();i++) {
			int next=AdjList[curr].get(i);
			if (Pair[next]==-1 || mcbm(Pair[next])==1) {
				Pair[next]=curr;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	int N=Integer.parseInt(br.readLine());

        	ArrayList<Pupil> male=new ArrayList<>();
        	ArrayList<Pupil> female=new ArrayList<>();
        	for (int n=0;n<N;n++) {
        		Pupil p=new Pupil(br.readLine());
        		if (p.g=='M') male.add(p);
        		else if (p.g=='F') female.add(p);
        	}
        
        	AdjList=new ArrayList [male.size()];
        	for (int n=0;n<AdjList.length;n++) AdjList[n]=new ArrayList<>();
        	
        	for (int i=0;i<male.size();i++) for (int i2=0;i2<female.size();i2++) {
        		if (!male.get(i).isResist(female.get(i2))) AdjList[i].add(i2);
        	}

        	Pair=new int [female.size()];
        	Arrays.fill(Pair,-1);
        	int count=0;
        	for (int i=0;i<male.size();i++) {
        		Visited=new boolean [male.size()];
        		count+=mcbm(i);
        	}
        	System.out.println(N-count);
        }
	}

}