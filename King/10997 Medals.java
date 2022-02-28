import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Result {
		String country;
		int gold, silver, bronze;
		boolean canada;
		
		public int total() {
			return this.gold+this.silver+this.bronze;
		}
		
		public double calcScore(double w1, double w2, double w3) {
			return this.gold*w1+this.silver*w2+this.bronze*w3;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine().trim()).equals("0")) {
			int N=Integer.parseInt(s);
			Result [] results=new Result[N];
			Result canada=null;
			int total=0;
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				results[n]=new Result();
				results[n].country=st.nextToken();
				results[n].canada=results[n].country.equals("Canada");
				if (results[n].canada) canada=results[n];
				results[n].gold=Integer.parseInt(st.nextToken());
				results[n].silver=Integer.parseInt(st.nextToken());
				results[n].bronze=Integer.parseInt(st.nextToken());
				total+=results[n].total();
			}
			
			boolean win=false;
			if (canada!=null) {
				int maxIt=5;
				for (int j=1;j<=maxIt && !win;j++) {
					double wj=1/Math.pow(total, j);
					for (int k=1;k<=maxIt && !win;k++) {
						double wk=1/Math.pow(total, k);
						for (int l=1;l<=maxIt && !win;l++) {
							double wl=1/Math.pow(total, l);
							
							double max=0;
							for (int n=0;n<N;n++) max=Math.max(max, results[n].calcScore(wj, wk, wl));
							if (max==canada.calcScore(wj, wk, wl)) {
								win=true;
								break;
							}
						}
					}
				}
			}

			
			System.out.println(win? "Canada wins!" : "Canada cannot win.");
		}
	}

}