import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		int id;
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Tuple t) {
			return (this.x!=t.x) ? this.x-t.x : this.y-t.y;
		}
		
		public String toString() {
			return String.format("(%4d,%4d)",this.x,this.y);
		}
	}

	private static class InputReader {
		private BufferedReader br;
		private StringTokenizer st;
		public InputReader() {
			br=new BufferedReader(new InputStreamReader(System.in));
			st=new StringTokenizer("");
		}
		public int nextInt() throws Exception {
			while (!st.hasMoreTokens()) st=new StringTokenizer(br.readLine());
			return Integer.parseInt(st.nextToken());
		}
	}
	
	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	private static String makeKey(int x, int y) {
		StringBuilder sb=new StringBuilder();
		sb.append(x);
		sb.append(',');
		sb.append(y);
		return sb.toString();
	}

	public static void main(String [] args) throws Exception {
		InputReader reader=new InputReader();
		while (true) {
			ArrayList<Tuple> nodes=new ArrayList<>();
			while (true){
				Tuple t=new Tuple(reader.nextInt(),reader.nextInt());
				if (t.x==0 && t.y==0) break;
				nodes.add(t);
				t.id=nodes.size()-1;
			}
			if (nodes.isEmpty()) break;
			Collections.sort(nodes);
			int maxX=0;
			int maxY=0;
			HashMap<String,Tuple> map=new HashMap<>();
			for (int i=0;i<nodes.size();i++) {
				Tuple t=nodes.get(i);
				map.put(makeKey(t.x,t.y),t);
				maxX=Math.max(maxX,t.x);
				maxY=Math.max(maxY,t.y);
			}

			ArrayList<ArrayList<Tuple>> ans=new ArrayList<>();
			boolean [][] state=new boolean [nodes.size()][nodes.size()];
			for (int i=0;i<nodes.size();i++) {
				int x1=nodes.get(i).x;
				int y1=nodes.get(i).y;
				for (int i2=i+1;i2<nodes.size();i2++) {
					ArrayList<Tuple> sol=new ArrayList<>();
					boolean dup=false;
					if (x1==nodes.get(i2).x) { // check vertical.
						for (int i3=0;i3<nodes.size();i3++) if (nodes.get(i3).x==x1 && nodes.get(i3).y>=y1) {
							sol.add(nodes.get(i3));
							if (sol.size()>1) {
								Tuple n1=sol.get(sol.size()-2);
								Tuple n2=sol.get(sol.size()-1);
								if (state[n1.id][n2.id]) {
									dup=true;
									break;
								}
							}
						}
					} else if (y1==nodes.get(i2).y) { // check horizontal.
						for (int i3=0;i3<nodes.size();i3++) if (nodes.get(i3).y==y1 && nodes.get(i3).x>=x1) {
							sol.add(nodes.get(i3));
							if (sol.size()>1) {
								Tuple n1=sol.get(sol.size()-2);
								Tuple n2=sol.get(sol.size()-1);
								if (state[n1.id][n2.id]) {
									dup=true;
									break;
								}
							}
						}
					} else {
						int dx=nodes.get(i2).x-x1;
						int dy=nodes.get(i2).y-y1;
						int d=gcd(Math.abs(dx),Math.abs(dy));
						dx/=d;
						dy/=d;

						int nx=x1;
						int ny=y1;
						while (nx<=maxX && ny<=maxY) {
							String key=makeKey(nx,ny);
							if (map.containsKey(key)) {
								sol.add(map.get(key));
								if (sol.size()>1) {
									Tuple n1=sol.get(sol.size()-2);
									Tuple n2=sol.get(sol.size()-1);
									if (state[n1.id][n2.id]) {
										dup=true;
										break;
									}
								}
							}
							nx+=dx;
							ny+=dy;
						}
					}

					if (dup) continue;

					for (int i3=0;i3<sol.size()-1;i3++) {
						Tuple n1=sol.get(i3);
						Tuple n2=sol.get(i3+1);
						state[n1.id][n2.id]=true;
					}
					if (sol.size()>2) ans.add(sol);
				}
			}
			
			if (!ans.isEmpty()) {
				StringBuilder sb=new StringBuilder();
				sb.append("The following lines were found:\n");
				for (int i=0;i<ans.size();i++) {
					for (int i2=0;i2<ans.get(i).size();i2++) sb.append(ans.get(i).get(i2));
					sb.append('\n');
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb);
			} else System.out.println("No lines were found");
		}
	}

}
