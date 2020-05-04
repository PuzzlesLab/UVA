import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static double npv(int [] nums, double irr) {
		double npv=nums[0];
		for (int i=1;i<nums.length;i++) npv+=(nums[i]/Math.pow(1+irr, i));
		return npv;
	}
	
	public static double bs(int [] nums, double min, double max) {
		double ans=-999.9;
		while (min<max) {
			double mid=(min+max)/2;
			double npv=npv(nums,mid);
			if (Math.abs(npv)<0.00001) {
				ans=mid;
				break;
			} else if (npv>0) min=mid;
			else max=mid;
		}
		return ans;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;

		while (!(s=br.readLine()).equals("0")) {
			int T=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] nums=new int [T+1];
			for (int t=0;t<=T;t++) nums[t]=Integer.parseInt(st.nextToken());
			
			double ans1=bs(nums,0.0,10000.0);
			double ans2=bs(nums,-1.0,0.0);
			if (ans1 == -999.9 && ans2 == 999.9) System.out.println("No");
			else if (ans1 != -999.9 && ans2 != -999.9) System.out.println("Too many");
			else if (ans1 != -999.9) System.out.printf("%.2f\n", ans1);
			else System.out.printf("%.2f\n", ans2);
		}
	}

}