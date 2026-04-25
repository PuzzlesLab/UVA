import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas= {{-1,0},{0,1},{1,0},{0,-1}};
	private static final int [] DirIdx=new int [128];

	private static class State {
		int x,y,dir;
		State parent;
		
		public State(int x, int y, int dir, State p) {
			this.x=x;
			this.y=y;
			this.dir=dir;
			this.parent=p;
		}
	}

	private static void initialize() {
		DirIdx['N']=0;
		DirIdx['E']=1;
		DirIdx['S']=2;
		DirIdx['W']=3;
	}


	public static void main(String[] args) throws Exception {
		initialize();
 
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("END")) {
			String name=s;
			StringTokenizer st=new StringTokenizer(br.readLine());
			State init=new State(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),DirIdx[st.nextToken().charAt(0)],null);
			int [] end={Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};

			ArrayList<Integer> [][][] nextDir=new ArrayList[10][10][4];
			while (!(s=br.readLine()).equals("0")) {
				st=new StringTokenizer(s);
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				while (st.hasMoreTokens()) {
					s=st.nextToken();
					if (s.equals("*")) break;
					
					int d=DirIdx[s.charAt(0)];
					if (nextDir[x][y][d]==null) nextDir[x][y][d]=new ArrayList<>();
					for (int i=1;i<s.length();i++) {
						char c=s.charAt(i);
						int nD=-1;
						if (c=='F') nD=d;
						else if (c=='L') {
							nD=d-1;
							if (nD<0) nD+=Deltas.length;
						}
						else if (c=='R') nD=(d+1)%Deltas.length;
						nextDir[x][y][d].add(nD);
					}
				}
			}
			// Add sentinel forward to top start.
			if (nextDir[init.x][init.y][init.dir]==null) nextDir[init.x][init.y][init.dir]=new ArrayList<>();
			nextDir[init.x][init.y][init.dir].add(init.dir);

			State sol=null;
			LinkedList<State> q=new LinkedList<>();
			boolean [][][] visited=new boolean [10][10][Deltas.length];
			q.add(init);

			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.x==end[0] && curr.y==end[1] && curr.parent!=null) {
					sol=curr;
					break;
				}

				if (nextDir[curr.x][curr.y][curr.dir]==null) continue;
				for (int i=0;i<nextDir[curr.x][curr.y][curr.dir].size();i++) {
					int nD=nextDir[curr.x][curr.y][curr.dir].get(i);
					int nx=curr.x+Deltas[nD][0];
					int ny=curr.y+Deltas[nD][1];
					if (nx>=1 && nx<10 && ny>=1 && ny<10 && !visited[nx][ny][nD]) {
						visited[nx][ny][nD]=true;
						q.addLast(new State(nx,ny,nD,curr));
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(name);
			sb.append('\n');
			if (sol==null) sb.append("  No Solution Possible");
			else {
				Stack<State> stk=new Stack<>();
				while (sol!=null) {
					stk.push(sol);
					sol=sol.parent;
				}
				int count=0;
				while (!stk.isEmpty()) {
					sol=stk.pop();
					
					if (count%10==0) {
						if (count!=0) sb.append('\n');
						sb.append("  ");
					}
					sb.append('(');
					sb.append(sol.x);
					sb.append(',');
					sb.append(sol.y);
					sb.append(')');
					
					count++;
					
					if (count%10!=0 && !stk.isEmpty()) sb.append(' ');
				}
			}
			System.out.println(sb);
		}
	}

}