import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	public static class Player implements Comparable<Player> {
		String name;
		int atk, def;
		
		
		public Player (String line) {
			StringTokenizer st=new StringTokenizer(line);
			this.name=st.nextToken();
			this.atk=Integer.parseInt(st.nextToken());
			this.def=Integer.parseInt(st.nextToken());
		}
		
		public int compareTo(Player p) {return this.name.compareTo(p.name);}
	}
	
	public static class Combi implements Comparable<Combi> {
		TreeSet<Player> atk, def;
		int atkSum, defSum;
		
		public Combi() {
			this.atk=new TreeSet<>();
			this.def=new TreeSet<>();
			this.atkSum=0;
			this.defSum=0;
		}
		
		public void addAtk(Player p) {
			this.atk.add(p);
			this.atkSum+=p.atk;
		}
		
		public void removeAtk(Player p) {
			this.atk.remove(p);
			this.atkSum-=p.atk;
		}
		
		public void addDef(Player p) {
			this.def.add(p);
			this.defSum+=p.def;
		}
		
		public void removeDef(Player p) {
			this.def.remove(p);
			this.defSum-=p.def;
		}
		
		public Combi copy() {
			Combi c=new Combi();
			c.atk.addAll(this.atk);
			c.def.addAll(this.def);
			c.atkSum=this.atkSum;
			c.defSum=this.defSum;
			return c;
		}
		
		public int compareTo(Combi c) {
			if (this.atkSum!=c.atkSum) return this.atkSum-c.atkSum;
			else if (this.defSum!=c.defSum) return this.defSum-c.defSum;
			else return c.atkList().compareTo(this.atkList());
		}
		
		public String atkList() {
			StringBuilder sb=new StringBuilder();
			sb.append('(');
			for (Player p : this.atk) {
				sb.append(p.name);
				sb.append(", ");
			}
			sb.setLength(sb.length()-2);
			sb.append(")");
			return sb.toString();
		}
		
		public String defList() {
			StringBuilder sb=new StringBuilder();
			sb.append('(');
			for (Player p : this.def) {
				sb.append(p.name);
				sb.append(", ");
			}
			sb.setLength(sb.length()-2);
			sb.append(")");
			return sb.toString();
		}
		
		public String toString() {
			StringBuilder sb=new StringBuilder();
			sb.append(this.atkList());
			sb.append('\n');
			sb.append(this.defList());
			return sb.toString();
		}
	}
	
	private static Combi BestCombi=null;
	
	public static void generateCombi(ArrayList<Combi> combis, Player [] players, int index, Combi currC) {
		if (index==players.length) {
			if (BestCombi==null || BestCombi.compareTo(currC)<0) BestCombi=currC.copy();
		} else {
			if (currC.atk.size()<5) {
				currC.addAtk(players[index]);
				generateCombi(combis, players, index+1, currC);
				currC.removeAtk(players[index]);
			}
			if (currC.def.size()<5) {
				currC.addDef(players[index]);
				generateCombi(combis, players, index+1, currC);
				currC.removeDef(players[index]);
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			Player [] players=new Player[10];
			for (int i=0;i<players.length;i++) players[i]=new Player(br.readLine());
			
			BestCombi=null;
			ArrayList<Combi> combis=new ArrayList<>();
			generateCombi(combis, players, 0, new Combi());
			
			System.out.printf("Case %d:\n", testCase);
			System.out.println(BestCombi);
		}
	}

}