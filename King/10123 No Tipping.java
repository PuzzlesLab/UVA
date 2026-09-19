import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static final int FULCRUMS=3;
	private static int W;
	private static int [] PPos;
	private static int [] PWeight;
	private static int [] Sol;
	private static boolean [] Visited;

	private static void compute(int mask, int left, int right, int [] trace, int tId) {
		if (Sol!=null) return;
		if (tId==PPos.length) {
			Sol=Arrays.copyOf(trace,trace.length);
			return;
		}

		if (Visited[mask]) return;

		Visited[mask]=true;
		for (int i=0;i<PPos.length;i++) if ((mask&(1<<i))==0) {
			int nl=left+PWeight[i]*(PPos[i]+FULCRUMS);
			int nr=right+PWeight[i]*(PPos[i]-FULCRUMS);
			if (nl+W*3>=0 && nr-W*3<=0) {
				trace[tId]=i;
				compute(mask|1<<i,nl,nr,trace,tId+1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).equals("0 0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	int L=Integer.parseInt(st.nextToken());
        	W=Integer.parseInt(st.nextToken());
        	int N=Integer.parseInt(st.nextToken());

        	PPos=new int [N];
        	PWeight=new int [N];
        	for (int n=0;n<N;n++) {
        		st=new StringTokenizer(br.readLine());
        		PPos[n]=Integer.parseInt(st.nextToken())<<1;
        		PWeight[n]=Integer.parseInt(st.nextToken());
        	}

        	Sol=null;
        	Visited=new boolean [1<<N];
        	compute(0,0,0,new int [N],0);

        	StringBuilder sb=new StringBuilder();
        	sb.append("Case ");
        	sb.append(tc++);
        	sb.append(":\n");
        	if (Sol!=null) {
            	for (int i=Sol.length-1;i>=0;i--) {
            		sb.append(PPos[Sol[i]]>>1);
            		sb.append(' ');
            		sb.append(PWeight[Sol[i]]);
            		sb.append('\n');
            	}
        	} else sb.append("Impossible\n");
        	System.out.print(sb);
        }
	}

}