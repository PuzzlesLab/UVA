import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Street {
		int x1, y1, x2, y2;
		
		public Street(int x1, int y1, int x2, int y2) {
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
		}
		
		public double dist() {
			double dx=(this.x1-this.x2);
			double dy=(this.y1-this.y2);
			return Math.sqrt(dx*dx+dy*dy)/1000.0;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();  //hangar is part of street, so we don't need to explicitly read it.
			ArrayList<Street> streets=new ArrayList<>();

			while (true) {
				String s=br.readLine();
				if (s==null || s.equals("")) break;
				StringTokenizer st=new StringTokenizer(s);
				streets.add(new Street(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}

			double totalDist=0.0;
			for (int i=0;i<streets.size();i++) totalDist+=streets.get(i).dist()*2; // 2 lanes per street, travel once per lane.
			int travelMinutes=(int)Math.round((totalDist/20)*60);

			if (testCase>0) System.out.println();
			System.out.printf("%d:%02d\n",travelMinutes/60,travelMinutes%60);
		}
	}
}
