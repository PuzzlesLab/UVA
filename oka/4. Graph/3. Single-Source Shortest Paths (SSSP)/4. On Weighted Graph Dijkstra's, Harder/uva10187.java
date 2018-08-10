import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			int N = Integer.parseInt(br.readLine());
			Map<String,Integer> m = new HashMap<>();
			int index = -1;
			ArrayList<ArrayList<Route>> r = new ArrayList<>();
			for(int j=0;j<100;j++) {
				r.add(new ArrayList<>());
			}
			for(int j=0;j<N;j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String u = st.nextToken();
				String v = st.nextToken();
				if(!m.containsKey(u)) {
					index++;
					m.put(u,index);
				}
				if(!m.containsKey(v)) {
					index++;
					m.put(v,index);
				}
				int start = Integer.parseInt(st.nextToken());
				int travel = Integer.parseInt(st.nextToken());
				if(start<=6)
					start += 24;
				int end = start+travel;
				if(start<18||end>30)
					continue;
				int city1 = m.get(u);
				int city2 = m.get(v);
				r.get(city1).add(new Route(start,end,city2));
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			String source = st.nextToken();
			String dest = st.nextToken();
			System.out.printf("Test Case %d.\n",i+1);
			if(source.equals(dest)) {
				System.out.println("Vladimir needs 0 litre(s) of blood.");
				continue;
			}
			if(!m.containsKey(source)||!m.containsKey(dest)) {
				System.out.println("There is no route Vladimir can take.");
				continue;
			}
			int start = m.get(source);
			int end = m.get(dest);
			Queue<Station>q = new PriorityQueue<>();
			q.add(new Station(start,18,0));
			boolean path = false;
			boolean visited[] = new boolean[index+1];
			while(!q.isEmpty()) {
				Station cur = q.poll();
				int city = cur.city;
				int depart = cur.depart; 
				int litre = cur.litre;
				if(city==end) {
					System.out.printf("Vladimir needs %d litre(s) of blood.\n",litre);
					path = true;
					break;
				}
				if(visited[city])
					continue;
				visited[city] = true;
				for(int j=0;j<r.get(city).size();j++) {
					int nextLitre = litre;
					if(depart>r.get(city).get(j).depart)
						nextLitre++;		
					q.add(new Station(r.get(city).get(j).to,r.get(city).get(j).arrive,nextLitre));
				}
			}
			if(!path) {
				System.out.println("There is no route Vladimir can take.");
			}
		}
	}
}

class Route{
	int depart;
	int arrive;
	int to;
	Route(int d,int a,int t){
		this.depart = d;
		this.arrive = a;
		this.to = t;
	}
}

class Station implements Comparable<Station>{
	int city;
	int depart;
	int litre;
	Station(int c,int d,int l){
		this.city = c;
		this.depart = d;
		this.litre = l;
	}
	@Override
	public int compareTo(Station s) {
		if(litre-s.litre!=0)
			return litre-s.litre;
		return depart-s.depart;
	}
}


