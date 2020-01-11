import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Cube {
		int id, top, btm, incoming;
		String face;
		ArrayList<Cube> adjList;
		
		public Cube (int id, int t, int b, String f) {
			this.id=id;
			this.top=t;
			this.btm=b;
			this.face=f;
			this.adjList=new ArrayList<>();
			this.incoming=0;
		}
	}
	
	public static class Data {
		Cube c;
		int height;
		public Data(Cube c, int h) {
			this.c=c;
			this.height=h;
		}
	}
	
	public static void main (String [] args) throws Exception {
		String [] face= {"front", "back", "left", "right", "top", "bottom"};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			ArrayList<Cube> cubes=new ArrayList<>();
			for (int n=0;n<N;n++) {
				int [] c=new int [6];
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i=0;i<6;i++) c[i]=Integer.parseInt(st.nextToken());
				for (int i=0;i<6;i+=2) {
					cubes.add(new Cube(n+1,c[i],c[i+1],face[i+1]));
					cubes.add(new Cube(n+1,c[i+1],c[i],face[i]));
				}
			}
			
			for (Cube c1: cubes) for (Cube c2 : cubes) if (c1.id<c2.id && c1.top==c2.btm) {
				c1.adjList.add(c2);
				c2.incoming++;
			}
			
			LinkedList<Data> q=new LinkedList<>();
			HashMap<Cube, Cube> ancestor=new HashMap<>();
			HashMap<Cube, Integer> maxLength=new HashMap<>();
			for (Cube c : cubes) if (c.incoming==0) q.add(new Data(c, 1));
			while (!q.isEmpty()) {
				Data dat=q.removeFirst();
				for (Cube neighbour : dat.c.adjList) {
					neighbour.incoming--;
					if (neighbour.incoming==0) {
						ancestor.put(neighbour, dat.c);
						maxLength.put(neighbour, dat.height+1);
						q.add(new Data(neighbour,dat.height+1));
					}
				}
			}
			
			int max=0;
			Cube maxCube=null;
			for (Cube c : cubes) {
				int l=maxLength.getOrDefault(c,-1);
				if (l>max) {
					max=Math.max(max,l);
					maxCube=c;
				}
			}
			LinkedList<Cube> ans=new LinkedList<>();
			while (maxCube!=null) {
				ans.addFirst(maxCube);
				maxCube=ancestor.getOrDefault(maxCube, null);
			}
			
			
			StringBuilder sb=new StringBuilder();
			if (testCase>1) sb.append('\n');
			sb.append("Case #");
			sb.append(testCase++);
			sb.append('\n');
			sb.append(max);
			sb.append('\n');
			while (!ans.isEmpty()) {
				Cube c=ans.removeFirst();
				sb.append(c.id);
				sb.append(' ');
				sb.append(c.face);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}