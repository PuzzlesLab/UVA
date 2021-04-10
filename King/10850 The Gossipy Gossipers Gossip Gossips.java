import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static class Tuple {
		int g1;
		int g2;
		
		public Tuple(int g1, int g2) {
			this.g1=g1;
			this.g2=g2;
		}
	}
	
	private static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent,parent[id]);
		return parent[id];
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			ArrayList<Tuple> [] meets=new ArrayList[100];
			for (int i=0;i<meets.length;i++) meets[i]=new ArrayList<>(); 

			for (int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine());
				int G1=Integer.parseInt(st.nextToken())-1;
				int G2=Integer.parseInt(st.nextToken())-1;
				int V=Integer.parseInt(st.nextToken());
				
				st=new StringTokenizer(br.readLine());
				for (int v=0;v<V;v++) {
					int instance=Integer.parseInt(st.nextToken());
					Tuple tup=new Tuple(G1,G2);
					meets[instance].add(tup);
				}
			}
			
			boolean [] informedFlag=new boolean[M];
			if (M>0) informedFlag[0]=true;
			int informedCount=1;
			int ans=-1;
			
			if (M>1) {
				int lastUpdate=0;
				for (int instance=0;instance-lastUpdate<100;instance++) {
					ArrayList<Tuple> currMeets=meets[instance%meets.length];
					if (!currMeets.isEmpty()) {
						int [] parent=new int [M];
						for (int i=0;i<M;i++) parent[i]=i;
						for (Tuple tup : currMeets) {
							int p1=getParent(parent,tup.g1);
							int p2=getParent(parent,tup.g2);
							if (p1<p2) parent[p2]=p1;
							else parent[p1]=p2;
						}
						boolean [] anyInformedInGroup=new boolean[M];
						for (int i=0;i<M;i++) if (informedFlag[i]) anyInformedInGroup[getParent(parent,i)]=true;
						for (int i=0;i<M;i++) if (anyInformedInGroup[getParent(parent,i)] && !informedFlag[i]) {
							informedFlag[i]=true;
							informedCount++;
							lastUpdate=instance;
						}
						
						if (informedCount==M) {
							ans=instance;
							break;
						}
					}
				}
			} else if (M==1) ans=0;

			System.out.println(ans);
		}
	}
}