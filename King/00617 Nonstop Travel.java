import java.util.Scanner;

class Main {
	
	private static class Signal {
		double distance;
		int g, y, r;
		int timeWindow;
		
		public Signal (double d, int g, int y, int r) {
			this.distance=d*3600.0;
			this.g=g;
			this.y=y;
			this.r=r;
			
			if (this.g==0 && this.y==0 && this.r==0) this.g=1;
			this.timeWindow=this.g+this.y+this.r;
		}
		
	}
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCase=1;
		while (true) {
			int N=sc.nextInt();
			if (N==-1) break;
			Signal [] signals=new Signal[N];
			
			for (int n=0;n<N;n++) signals[n]=new Signal(sc.nextDouble(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			boolean [] flag=new boolean [31];
			int failCount=0;
			for (int speed=30;speed<=60;speed++) {
				flag[speed-30]=true;
				for (Signal sig : signals) {
					if ((sig.distance/speed)%sig.timeWindow>sig.g+sig.y) {
						flag[speed-30]=false;
						break;
					}
				}
				if (!flag[speed-30]) failCount++;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			if (failCount!=flag.length) {
				int lastSpeed=0;
				for (int i=0;i<flag.length;i++) if (flag[i]) {
					if (lastSpeed==0) {
						sb.append(i+30);
						lastSpeed=i+30;
					} else if (i==flag.length-1 || !flag[i+1]){
						sb.append("-");
						sb.append(i+30);
						sb.append(", ");
						lastSpeed=0;
					}
				} else if (lastSpeed!=0) {
					sb.append(", ");
					lastSpeed=0;
				}
				if (sb.toString().endsWith(", ")) sb.setLength(sb.length()-2);
			} else sb.append("No acceptable speeds.");

			System.out.println(sb.toString());
			testCase++;
		}
	}

}
