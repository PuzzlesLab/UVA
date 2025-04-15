import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static double [] sq(double [] coef) {
		double [] ans=new double [coef.length<<1];
		for (int i=0;i<coef.length;i++) for (int i2=0;i2<coef.length;i2++) ans[i+i2]+=coef[i]*coef[i2];
		return ans;
	}

	private static double [] integrate(double [] coef) {
		double [] ans=new double [coef.length+1];
		for (int i=0;i<coef.length;i++) ans[i+1]=coef[i]/(i+1);
		return ans;
	}

	private static double calc(double [] coef, double x) {
		double ans=0;
		for (int i=0;i<coef.length;i++) ans+=coef[i]*Math.pow(x,i);
		return ans;
	}

	private static double volume(double xl, double xh, double [] coef) {
		return Math.PI*(calc(coef,xh)-calc(coef,xl));
	}
	
	private static double search(double min, double max, double target, double [] eq) {
		double start=min;
		while (min<max) {
			double mid=(min+max)/2;
			double curr=volume(start,mid,eq);
			if (Math.abs(curr-target)<1e-6) return mid;
			if (curr>target) max=mid;
			else min=mid;
		}
		return start-10000;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			double [] coef=new double[N+2];
			for (int n=0;n<=N;n++) coef[n]=Double.parseDouble(st.nextToken());
			double [] volumeEq=integrate(sq(coef));

			st=new StringTokenizer(br.readLine());
			double xl=Double.parseDouble(st.nextToken());
			double xh=Double.parseDouble(st.nextToken());
			double maxVol=volume(xl,xh,volumeEq);
			double volStep=Double.parseDouble(st.nextToken());

			ArrayList<Double> ans=new ArrayList<>();
			double currVol=volStep;
			double cLow=xl;
			int count=0;
			while (currVol<=maxVol && count<8) {
				double mark=search(cLow,xh,volStep,volumeEq);
				if (mark<xl) break;
				ans.add(mark);
				cLow=mark;
				currVol+=volStep;
				count++;
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			sb.append(String.format("%.2f",volume(xl,xh,volumeEq)));
			sb.append('\n');
			if (ans.isEmpty()) sb.append("insufficient volume");
			else {
				for (int i=0;i<ans.size();i++) sb.append(String.format("%.2f ", ans.get(i)-xl));
				if (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
			}
			System.out.println(sb);
		}
	}

}
