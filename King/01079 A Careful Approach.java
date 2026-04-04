import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Plane {
		int from,to;
		
		public Plane(int from, int to) {
			this.from=from;
			this.to=to;
		}
	}

	private static final double MAX_ANS=1440*60;
	private static Plane [] Planes;
	private static double Ans;

	private static boolean feasible(Plane [] order, double interval) {
		double t=order[0].from;
		for (int n=1;n<order.length;n++) {
			double nt=t+interval;
			if (nt<=order[n].to) t=Math.max(order[n].from,nt);
			else return false;
		}
		return true;
	}

	private static void compute(Plane [] perm, int count, int mask) {
		if (count==Planes.length) {
			double minI=0;
			double maxI=MAX_ANS;
			for (int i=0;i<20;i++) {
				double midI=(minI+maxI)/2;
				if (feasible(perm,midI)) minI=midI;
				else maxI=midI;
			}
			Ans=Math.max(Ans,minI);
			return;
		}
		for (int i=0;i<Planes.length;i++) if ((mask&1<<i)==0) {
			perm[count]=Planes[i];
			compute(perm,count+1,mask|1<<i);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Planes=new Plane[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Planes[n]=new Plane(Integer.parseInt(st.nextToken())*60,Integer.parseInt(st.nextToken())*60);
			}

			Ans=0;
			compute(new Plane[N],0,0);
			int AnsI=(int)Math.round(Ans);
			System.out.printf("Case %d: %d:%02d\n",tc++,AnsI/60,AnsI%60);
		}
	}

}