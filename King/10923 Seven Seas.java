import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class Main {

	private static final int MAP_X=9;
	private static final int MAP_Y=8;
	
	private static class Pos {
		int x,y;
		
		public Pos(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static class State {
		char [][] map;
		int step;
		Pos me;
		ArrayList<Pos> enemies;
		private String key;

		public State(char [][] map, int s) {
			this.map=map;
			this.step=s;
			this.enemies=new ArrayList<>();
		}

		private char [][] copyRock() {
			char [][] newMap=new char [MAP_X][MAP_Y];
			for (int i=0;i<MAP_X;i++) for (int i2=0;i2<MAP_Y;i2++) newMap[i][i2]=(this.map[i][i2]=='#')?'#':'.';
			return newMap;
		}

 		public ArrayList<State> move() {
			ArrayList<State> ret=new ArrayList<>();
			
			for (int dx=-1;dx<=1;dx++) for (int dy=-1;dy<=1;dy++) {
				Pos nextMe=new Pos(this.me.x+dx,this.me.y+dy);
				if (nextMe.x<0 || nextMe.x>=MAP_X || nextMe.y<0 || nextMe.y>=MAP_Y) continue;
				if (map[nextMe.x][nextMe.y]!='.') continue;

				int [][] posCount=new int [MAP_X][MAP_Y];
				ArrayList<Pos> nEnemiesDraft=new ArrayList<>();
				for (int i=0;i<this.enemies.size();i++) {
					Pos nEnemy=State.enemyNextMove(map,nextMe,this.enemies.get(i));
					if (this.map[nEnemy.x][nEnemy.y]=='#') continue;
					posCount[nEnemy.x][nEnemy.y]++;
					nEnemiesDraft.add(nEnemy);
				}
				if (posCount[nextMe.x][nextMe.y]>0) continue; // enemy = me.

				ArrayList<Pos> nEnemies=new ArrayList<>();
				ArrayList<Pos> crashed=new ArrayList<>();
				for (int i=0;i<nEnemiesDraft.size();i++) {
					Pos currE=nEnemiesDraft.get(i);
					if (posCount[currE.x][currE.y]==1) nEnemies.add(currE);
					else crashed.add(currE);
				}

				char [][] nMap=this.copyRock();
				nMap[nextMe.x][nextMe.y]='S';
				for (int i=0;i<nEnemies.size();i++) nMap[nEnemies.get(i).x][nEnemies.get(i).y]='E';
				for (int i=0;i<crashed.size();i++) nMap[crashed.get(i).x][crashed.get(i).y]='#'; // Crashed, it behaves like rock.

				State nS=new State(nMap,this.step+1);
				nS.me=nextMe;
				nS.enemies=nEnemies;
				ret.add(nS);
			}
			
			return ret;
		}

 		private static Pos enemyNextMove(char [][] map, Pos me, Pos enemy) {
			return new Pos(enemy.x+Integer.compare(me.x,enemy.x),enemy.y+Integer.compare(me.y,enemy.y));
 		}

		public String getKey() {
			if (this.key==null) {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<map.length;i++) sb.append(new String(map[i]));
				this.key=sb.toString();
			}
			return this.key;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			char [][] map=new char [MAP_X][];
			for (int i=0;i<map.length;i++) map[i]=br.readLine().toCharArray();
			
			State init=new State(map,0);
			for (int i=0;i<map.length;i++) for (int i2=0;i2<map[i].length;i2++) {
				if (map[i][i2]=='S') init.me=new Pos(i,i2);
				else if (map[i][i2]=='E') init.enemies.add(new Pos(i,i2));
			}
			
			boolean ans=false;
			HashSet<String> visited=new HashSet<>();
			visited.add(init.getKey());
			LinkedList<State> q=new LinkedList<>();
			q.add(init);
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.enemies.isEmpty()) {
					ans=true;
					break;
				}
				if (curr.step>=10) break;

				ArrayList<State> nextStates=curr.move();
				for (int i=0;i<nextStates.size();i++) {
					State nS=nextStates.get(i);
					if (!visited.contains(nS.getKey())) {
						visited.add(nS.getKey());
						q.addLast(nS);
					}
				}
			}
			System.out.println(ans?"I'm the king of the Seven Seas!":"Oh no! I'm a dead man!");
			
			br.readLine(); // empty.
		}
	}

}