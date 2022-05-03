import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		double capacity;
		double milesPerGallon;
		double refillCost;
		double destDist;
		double snackCost;
	}

	private static class Station implements Comparable<Station> {
		double dist;
		double price;

		public int compareTo(Station o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	private static double find(Station[] stations, boolean[] visited, int currAt,
			double currCost, double currFuel, Context ctx) {
		if (currAt==stations.length-1) return currCost;
		if (currFuel<=ctx.capacity*0.5) {
			double fuelRefill=ctx.capacity-currFuel;
			double refillCost=ctx.snackCost+fuelRefill*stations[currAt].price;
			refillCost = ((int)((refillCost+0.005)*100))/100.0;
			currCost+=refillCost;
			currFuel=ctx.capacity;
		}

		double minCost = Double.MAX_VALUE;
		double maxReachableMile=stations[currAt].dist+ctx.capacity*ctx.milesPerGallon;
		for (int i=currAt+1;i<stations.length;i++) if (!visited[i] && maxReachableMile>=stations[i].dist) {
			visited[i]=true;
			double milesToNext=stations[i].dist-stations[currAt].dist;
			double fuelNeeded=milesToNext/ctx.milesPerGallon;
			double remFuel=currFuel-fuelNeeded;
			double cost=currCost;
			
			//Refill
			if (fuelNeeded>currFuel) {
				double fuelRefill=ctx.capacity-currFuel;
				double refillCost=ctx.snackCost+fuelRefill*stations[currAt].price;
				refillCost = ((int)((refillCost+0.005)*100))/100.0;
				remFuel=ctx.capacity-fuelNeeded;
				cost+=refillCost;
			}

			minCost=Math.min(minCost,find(stations,visited,i,cost,remFuel,ctx));
			visited[i]=false;
		}
		return minCost;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = 1;
		while (true) {
			double dist = Double.parseDouble(br.readLine());
			if (dist < 0)
				break;

			StringTokenizer st = new StringTokenizer(br.readLine());
			Context ctx = new Context();
			ctx.capacity = Double.parseDouble(st.nextToken());
			ctx.milesPerGallon = Double.parseDouble(st.nextToken());
			ctx.refillCost = Double.parseDouble(st.nextToken());
			ctx.destDist = dist;
			ctx.snackCost = 2.0;
			int N = Integer.parseInt(st.nextToken());

			Station[] stations = new Station[N+2];
			stations[0]=new Station();
			for (int n=1;n<=N;n++) {
				st = new StringTokenizer(br.readLine());
				stations[n] = new Station();
				stations[n].dist = Double.parseDouble(st.nextToken());
				stations[n].price = Double.parseDouble(st.nextToken())/100.0;
			}
			stations[N+1]=new Station();
			stations[N+1].dist=ctx.destDist;
			Arrays.sort(stations);

			boolean [] visited=new boolean[stations.length];
			visited[0]=true;
			double minCost = find(stations, visited, 0, ctx.refillCost, ctx.capacity, ctx);
			System.out.printf("Data Set #%d\nminimum cost = $%.2f\n", testCase++, minCost);
		}
	}

}
