import java.util.Scanner;

class Main {

	private static class Contestant {
		double runSpeed, cycSpeed;
		
		public Contestant(double rs, double cs) {
			this.runSpeed = rs;
			this.cycSpeed = cs;
		}

		public double timeTaken(double runD, double cycD) {
			return runD/this.runSpeed+cycD/this.cycSpeed;
		}
	}

	public static double getMaxDiff(Contestant [] conts, double r, double c) {
		double diff=Double.MAX_VALUE;
		double refT = conts[conts.length-1].timeTaken(r, c);
		for (int n=0;n<conts.length-1;n++) {
			double currT=conts[n].timeTaken(r,c);
			diff=Math.min(diff, currT-refT);
		}
		return diff;
	}

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextDouble()) {
			double T=sc.nextDouble();
			int N=sc.nextInt();
			Contestant [] conts = new Contestant[N];
			for (int n=0;n<N;n++) {
				conts[n]=new Contestant(sc.nextDouble(), sc.nextDouble());
			}

			double minR=0.0;
			double maxR=T;
			for (int i=0;i<64;i++) {
				double part=(maxR-minR)/3;
				double r1=minR+part;
				double r2=maxR-part;

				double d1=getMaxDiff(conts,r1,T-r1);
				double d2=getMaxDiff(conts,r2,T-r2);
				if (d1>d2) {
					maxR=r2;
				} else {
					minR=r1;
				}
			}
			
			double ans=getMaxDiff(conts,maxR,T-maxR)*3600; // km/h
			if (ans<=0) System.out.println("The cheater cannot win.");
			else System.out.printf("The cheater can win by %.0f seconds with r = %.2fkm and k = %.2fkm.\n", ans, maxR, T-maxR);
		}
	}

}
