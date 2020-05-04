import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	public static class Event {
		int dist;
		char op;
		int value;
		
		public Event(int d, char o, int v) {
			this.dist=d;
			this.op=o;
			this.value=v;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		HashMap<String, Character> opMap=new HashMap<>();
		opMap.put("Fuel", 'F');	opMap.put("Leak", 'L');	opMap.put("Gas", 'G');	opMap.put("Mechanic", 'M');	opMap.put("Goal", 'X');
		while (!(s=br.readLine()).equals("0 Fuel consumption 0")) {
			ArrayList<Event> events=new ArrayList<>();
			while (true) {
				StringTokenizer st=new StringTokenizer(s);
				int d=Integer.parseInt(st.nextToken());
				String e=st.nextToken();
				char o=opMap.get(e);
				if (e.equals("Fuel") || e.equals("Gas")) st.nextToken();
				int v=0;
				if (o=='F') v=Integer.parseInt(st.nextToken());
				events.add(new Event(d,o,v));
				if (o!='X') s=br.readLine();
				else break;
			}
			
			double min=0.0;
			double max=10000000.0;
			double ans=0.0;
			while (max-min>0.0001) {
				double mid=(min+max)/2;
				if (canReach(events, mid)) {
					ans=mid;
					max=mid;
				} else min=mid+0.0001;
			}
			System.out.printf("%.3f\n", ans);
		}
	}
	
	public static boolean canReach(ArrayList<Event> events, double tankSize) {
		double leakRatePerKM=0;
		double consumeRatePerKM=0;
		double tankAmount=tankSize;
		int lastDist=0;
		for (Event e : events) {
			int currDist=e.dist;
			double leaked=(currDist-lastDist)*leakRatePerKM;
			double consumed=(currDist-lastDist)*consumeRatePerKM;
			double needed=leaked+consumed;
			if (tankAmount<needed) return false;
			else tankAmount-=needed;
			
			if (e.op == 'F') consumeRatePerKM=e.value / 100.0;
			else if (e.op == 'L') leakRatePerKM++;
			else if (e.op == 'G') tankAmount=tankSize;
			else if (e.op == 'M') leakRatePerKM=0;
			
			lastDist=currDist;
		}
		return true;
	}
}