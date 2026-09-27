import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	private static class Island implements Comparable<Island> {
		double x,y;
		int pass;

		public Island(double x, double y, int p) {
			this.x=x;
			this.y=y;
			this.pass=p;
		}
		
		public int compareTo(Island is) {
			return (this.x!=is.x) ? Double.compare(this.x,is.x) : Double.compare(this.y,is.y);
		}
		
		public double dist(Island is) {
			double dx=this.x-is.x;
			double dy=this.y-is.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}

	private static Island [] Islands;
	private static double [][] Dp;
	private static int [][] Pick;

	private static double compute(int l, int r, boolean firstPass) {
		int curr=1+Math.max(l,r);
		if (curr==Dp.length-1) return Islands[l].dist(Islands[curr])+Islands[curr].dist(Islands[r]);

		if (Dp[l][r]<0) {
			double ans=1000000000.0;
			int flag=0;
			if (Islands[curr].pass==-1 || Islands[curr].pass==1) {
				double temp=Islands[l].dist(Islands[curr])+compute(curr,r,true);
				if (temp<ans) {
					ans=temp;
					flag=1;
				}
			}
			if (Islands[curr].pass==-1 || Islands[curr].pass==2) {
				double temp=Islands[curr].dist(Islands[r])+compute(l,curr,false);
				if (temp<ans) {
					ans=temp;
					flag=2;
				}
			}
			Pick[l][r]=flag;
			Dp[l][r]=ans;
		}
		return Dp[l][r];
	}
	
	private static boolean isLarger(List<Integer> l1, List<Integer> l2) {
		for (int i=0;i<Math.min(l1.size(),l2.size());i++) {
			if (l1.get(i)>l2.get(i)) return true;
			else if (l1.get(i)<l2.get(i)) return false;
		}
		return l1.size()>l2.size();
	}

	private static void addTrace(StringBuilder sb, int N) {
    	List<Integer> p1=new ArrayList<>();
    	p1.add(0);
    	List<Integer> p2=new ArrayList<>();
    	p2.add(0);
    	int l=0;
    	int r=0;
   
    	while (true) {
    		int curr=1+Math.max(l,r);
    		if (curr==N-1) {
    			p2.add(curr);
    			break;
    		}
    		if (Pick[l][r]==1) {
    			p1.add(curr);
    			l=curr;
    		} else if (Pick[l][r]==2) {
    			p2.add(curr);
    			r=curr;
    		} else break;
    	}
    	
    	if (isLarger(p1,p2)) {
    		List<Integer> temp=p2;
    		p2=p1;
    		p1=temp;
    	}

    	for (int i=0;i<p1.size();i++) {
    		sb.append(p1.get(i));
    		sb.append(' ');
    	}
    	for (int i=p2.size()-1;i>=0;i--) {
    		sb.append(p2.get(i));
    		sb.append(' ');
    	}
    	if (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).equals("0 0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	int N=Integer.parseInt(st.nextToken());
        	int B1=Integer.parseInt(st.nextToken());
        	int B2=Integer.parseInt(st.nextToken());
        	
        	Islands=new Island[N];
        	for (int n=0;n<N;n++) {
        		st=new StringTokenizer(br.readLine());
        		Islands[n]=new Island(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),-1);
        		if (n==B1) Islands[n].pass=1;
        		else if (n==B2) Islands[n].pass=2;
        	}

        	Dp=new double [N][N];
        	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-100000);
        	Pick=new int [N][N];

        	StringBuilder sb=new StringBuilder();
        	sb.append("Case ");
        	sb.append(tc++);
        	sb.append(": ");
        	if (N==1) sb.append("0.00");
        	else sb.append(String.format("%.2f",compute(0,0,true)));
        	sb.append('\n');
        	
        	addTrace(sb,N);

        	System.out.println(sb.toString());
        }
	}

}