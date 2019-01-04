import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int L=Integer.parseInt(st.nextToken());
			int G=Integer.parseInt(st.nextToken());
			int [][] stations=new int [G][];
			for (int g=0;g<G;g++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int r=Integer.parseInt(st.nextToken());
				stations[g]=new int [] {Math.max(0, x-r), x+r};
			}
			Arrays.sort(stations, (a,b) -> {
				if (a[0]==b[0]) return a[1]-b[1];
				return a[0]-b[0];
			});
			
			int min=0, max=0;
			for (int g=0;g<G;g++) {
				if (stations[g][0]>max) {
					min=-1;
					break;
				}
				min=Math.min(min, stations[g][0]);
				max=Math.max(max, stations[g][1]);
			}
			
			int ans=0;
			if (max>=L) {
				max=0;
				int g=0;
				while (g<G) {
					if (max<L) {
						int count=0;
						int tempMax=max;
						while (g<G && stations[g][0]<=max) {
							tempMax=Math.max(tempMax, stations[g][1]);
							g++;
							count++;
						}
						max=tempMax;
						ans=ans+(count-1);
					} else {
						ans+=(G-g);
						break;
					}
				}
			} else ans=-1;
			
			System.out.println(ans);
		}
	}

}