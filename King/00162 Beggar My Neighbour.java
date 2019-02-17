import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			LinkedList<Character> [] deck=new LinkedList [] {new LinkedList<Character>(), new LinkedList<Character>()}; //0 = dealer, 1 = player
			int pid=1;
			
			String [] lines= {s,br.readLine(),br.readLine(),br.readLine()};
			for (String line : lines) {
				StringTokenizer st=new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					deck[pid%2].addFirst(st.nextToken().charAt(1));
					pid=(pid+1)%2;
				}
			}
			
			int [] faces=new int[128];
			faces['A']=4;
			faces['J']=1;
			faces['Q']=2;
			faces['K']=3;
			
			LinkedList<Character> played=new LinkedList<>();
			int winner=-1;
			int owe=0;
			pid=1;
			while (winner==-1) { //Round
				if (deck[pid].isEmpty()) {
					winner=(pid+1)%2;
					break;
				}

				if (owe>0) {
					while (owe>0 && !deck[pid].isEmpty()) {
						played.addFirst(deck[pid].pop());
						owe--;
						if (faces[played.peek()]>0) break;
					}
					if (faces[played.peek()]>0) owe=faces[played.peek()];
					else {
						if (owe==0) while (!played.isEmpty()) deck[(pid+1)%2].addLast(played.removeLast());
						else {
							winner=(pid+1)%2;
							break;
						}
					}
				} else {
					played.addFirst(deck[pid].pop());
					owe = faces[played.peek()];
				}
				pid=(pid+1)%2;

			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(winner+1);
			
			int ansSize=deck[winner].size();
			sb.append(' ');
			if (ansSize<10) sb.append(' '); 
			sb.append(ansSize);
			System.out.println(sb.toString());
		}
	}

}
