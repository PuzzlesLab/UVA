import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static class Player implements Comparable<Player> {
		int id;
		String name;
		char role;
		int years;
		boolean captain;
		
		public Player(int id, String name, char role, int years) {
			this.id=id;
			this.name=name;
			this.role=role;
			this.years=years;
		}
		
		public int compareTo(Player p) {
			return this.id-p.id;
		}
		
		public int cmpCaptain(Player p) {
			if (this.years!=p.years) return this.years-p.years;
			return this.id-p.id;
		}
		
		public int cmpPrint(Player p, int [] prio) {
			if (this.captain) return -100;
			if (prio[this.role]!=prio[p.role]) return prio[this.role]-prio[p.role];
			return this.id-p.id;
		}
	}

	public static void main (String [] args) throws Exception {
		final int [] PRIORITY=new int [128];
		PRIORITY['G']=0;
		PRIORITY['D']=1;
		PRIORITY['M']=2;
		PRIORITY['S']=3;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			ArrayList<Player> Gp=new ArrayList<>();
			ArrayList<Player> Dp=new ArrayList<>();
			ArrayList<Player> Mp=new ArrayList<>();
			ArrayList<Player> Sp=new ArrayList<>();
			String formation=null;

			while (true) {
				StringTokenizer st=new StringTokenizer(s);
				if (st.countTokens()==1) {
					formation=st.nextToken();
					break;
				}
				
				int id=Integer.parseInt(st.nextToken());
				String name=st.nextToken();
				char role=st.nextToken().charAt(0);
				int years=0;
				while (st.hasMoreTokens()) {
					StringTokenizer st2=new StringTokenizer(st.nextToken(),"-");
					int y1=Integer.parseInt(st2.nextToken());
					int y2=Integer.parseInt(st2.nextToken());
					years+=(y2-y1+1);
				}
				Player p=new Player(id,name,role,years);
				if (role=='G') Gp.add(p);
				else if (role=='D') Dp.add(p);
				else if (role=='M') Mp.add(p);
				else if (role=='S') Sp.add(p);
				
				s=br.readLine();
			}
			
			Collections.sort(Gp);
			Collections.sort(Dp);
			Collections.sort(Mp);
			Collections.sort(Sp);

			StringTokenizer st=new StringTokenizer(formation,"-");
			int D=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			int G=1;
			
			if (Gp.size()<G || Dp.size()<D || Mp.size()<M || Sp.size()<S) {
				System.out.println("IMPOSSIBLE TO ARRANGE\n");
				continue;
			}
			
			ArrayList<Player> selection=new ArrayList<>();
			for (int i=0;i<G;i++) selection.add(Gp.get(i));
			for (int i=0;i<D;i++) selection.add(Dp.get(i));
			for (int i=0;i<M;i++) selection.add(Mp.get(i));
			for (int i=0;i<S;i++) selection.add(Sp.get(i));
			Collections.sort(selection, (x,y) -> x.cmpCaptain(y));
			selection.get(selection.size()-1).captain=true;
			Collections.sort(selection, (x,y) -> x.cmpPrint(y,PRIORITY));
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<selection.size();i++) {
				Player p=selection.get(i);
				sb.append(p.id);
				sb.append(' ');
				sb.append(p.name);
				sb.append(' ');
				sb.append(p.role);
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}
}