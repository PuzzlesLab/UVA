import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		                     //8am    6pm    10pm   8am    6pm    10pm
		int [] rateRange = {0, 8*60, 18*60, 22*60, 32*60, 42*60, 48*60 };
		double [][] rate= {{0.02, 0.10, 0.06, 0.02, 0.10, 0.06},
							{0.05, 0.25, 0.15, 0.05, 0.25, 0.15},
							{0.13, 0.53, 0.33, 0.13, 0.53, 0.33},
							{0.17, 0.87, 0.47, 0.17, 0.87, 0.47},
							{0.3, 1.44, 0.8, 0.3, 1.44, 0.8}};
		
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s);
			char dist=st.nextToken().charAt(0);
			String number=st.nextToken();
			int timeStart=Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken());
			int timeEnd=Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken());
			double cost=0.0;
			
			if (timeEnd<=timeStart) timeEnd=24*60+timeEnd;
			
			double [] currRate=rate[dist-'A'];
			int [] rateCount=new int [3];
			while (timeStart!=timeEnd) {
				int idx=-1;
				for (int i=0;i<rateRange.length-1;i++) {
					int rateStart=rateRange[i];
					int rateEnd=rateRange[i+1];

					if (timeStart>=rateStart && timeStart<rateEnd) {
						idx=i;
						break;
					}
				}
				int minutes= Math.min(rateRange[idx+1], timeEnd) - timeStart;
				timeStart=Math.min(rateRange[idx+1], timeEnd);
				cost += minutes * currRate[idx];
				
				if (idx==0) idx=2;
				else if (idx<4) idx-=1;
				else idx-=4;
				rateCount[idx]+=minutes;
			}
			
			while (number.length()<10) number=" "+number;
			System.out.printf("%s%6d%6d%6d%3s%8.2f\n",number,rateCount[0],rateCount[1],rateCount[2],dist+"",cost);
		}
	}

}