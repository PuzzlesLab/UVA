import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class City implements Comparable<City> {
		int population, box, average;
		
		public City(int p) {
			this.population=p;
			this.average=p;
			this.box=1;
		}
		
		public void addBox() {
			this.box++;
			this.average=this.population/this.box;
			if (this.population%this.box!=0) this.average++;
		}
		
		public int compareTo(City c) {
			return c.average-this.average;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			if (N==-1 && B==-1) break;
			
			PriorityQueue<City> pq=new PriorityQueue<>();
			for (int n=0;n<N;n++) pq.offer(new City(Integer.parseInt(br.readLine())));
			
			B-=N;
			while (B>0) {
				City c=pq.poll();
				c.addBox();
				pq.offer(c);
				B--;
			}
			
			City largest=pq.poll();
			System.out.println(largest.average);
			br.readLine();
		}
	}
}