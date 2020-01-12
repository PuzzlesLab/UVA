import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Junction {
		int x, y;
		HashSet<Junction> neighbours;
		
		public Junction(int x, int y) {
			this.x=x;
			this.y=y;
			this.neighbours=new HashSet<>();
		}
		
		public String toString() {
			return this.x+","+this.y;
		}
	}
	
	public static class Data {
		Junction j;
		long w;
		public Data(Junction j, long w) {
			this.j=j;
			this.w=w;
		}
	}
	
	public static String coorToKey(int i, int i2) {
		StringBuilder sb=new StringBuilder();
		sb.append(i);
		sb.append(' ');
		sb.append(i2);
		return sb.toString();
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			HashMap<String,Junction> junctions=new HashMap<>();
			for (int i=1;i<=N;i++) for (int i2=1;i2<=N;i2++) junctions.put(coorToKey(i,i2), new Junction(i,i2));
			for (int i=1;i<=N;i++) for (int i2=1;i2<=N;i2++) {
				Junction source=junctions.get(coorToKey(i,i2));
				
				if (i+1<=N) {
					Junction dest=junctions.get(coorToKey(i+1,i2));
					source.neighbours.add(dest);
				}
				if (i2+1<=N) {
					Junction dest=junctions.get(coorToKey(i,i2+1));
					source.neighbours.add(dest);
				}
			}

			
			StringTokenizer st=new StringTokenizer(br.readLine());
			Junction start=junctions.get(coorToKey(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			st=new StringTokenizer(br.readLine());
			Junction end=junctions.get(coorToKey(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			
			int W=Integer.parseInt(br.readLine());
			for (int i=0;i<W;i++) {
				st=new StringTokenizer(br.readLine());
				int x1=Integer.parseInt(st.nextToken());
				int y1=Integer.parseInt(st.nextToken());
				
				int x2=x1, y2=y1;
				char direction=st.nextToken().charAt(0);
				if (direction=='N') y2++;
				else if (direction =='E') x2++;
				else if (direction=='S') y2--;
				else if (direction=='W') x2--;
				
				Junction j1=junctions.get(coorToKey(x1,y1));
				Junction j2=junctions.get(coorToKey(x2,y2));
				j1.neighbours.remove(j2);
				j2.neighbours.remove(j1);
			}
			
			HashMap<Junction, Long> ways=new HashMap<>();
			ways.put(start,1L);
			LinkedList<Data> q=new LinkedList<>();
			q.add(new Data(start,1L));
			while (!q.isEmpty()) {
				Data dat=q.removeFirst();
				if (dat.w==ways.get(dat.j)) {
					for (Junction nj : dat.j.neighbours) {
						ways.put(nj, ways.getOrDefault(nj,0L)+ways.get(dat.j));
						q.addLast(new Data(nj,ways.get(nj)));
					}
				}
			}
				
			System.out.println(ways.getOrDefault(end,0L));
		}
	}

}