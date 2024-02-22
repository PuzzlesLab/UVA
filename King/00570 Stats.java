import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;

class Main {

	private static void printState(StringBuilder sb, String name, double [] stat) {
		double base=stat['K']+stat['E']+stat['H'];
		double hit=base!=0 ? (stat['K']-stat['E'])/base : 0;
		double kpg=stat['K']/stat['G'];
		double bpg=stat['B']/stat['G'];
		double dpg=stat['D']/stat['G'];

		sb.append(name);
		for (int i=name.length();i<8;i++) sb.append(' ');
		if (hit>=0) sb.append('+');
		sb.append(String.format("%.3f", hit));
		sb.append(String.format("%9.3f", kpg));
		sb.append(String.format("%9.3f", bpg));
		sb.append(String.format("%9.3f", dpg));
		sb.append('\n');
	}

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		ArrayList<String> inputs=new ArrayList<>();
		HashMap<String,double []> playerStats=new HashMap<>();
		double [] teamStats=new double [128];
		while (sc.hasNext()) {
			char key=sc.next().charAt(0);
			if (key=='C') {
				int P=Integer.parseInt(sc.next());
				for (int p=0;p<P;p++) {
					String pId=sc.next();
					if (!playerStats.containsKey(pId)) playerStats.put(pId, new double [128]);
					playerStats.get(pId)['G']++;
				}
				teamStats['G']++;
			} else if (key!='R') {
				playerStats.get(sc.next())[key]++;
				teamStats[key]++;
			} else { // R
				StringBuilder sb=new StringBuilder();
				sb.append("Player  Hit Pct    KPG      BPG      DPG\n");
				sb.append("-----------------------------------------\n");
				
				ArrayList<String> pList=new ArrayList<>(playerStats.keySet());
				Collections.sort(pList);
				for (int i=0;i<pList.size();i++) {
					String p=pList.get(i);
					printState(sb,p,playerStats.get(p));
				}
				printState(sb,"team",teamStats);

				System.out.println(sb.toString());
				inputs.clear();
				playerStats.clear();
				teamStats=new double [128];
			}
		}
	}
}
