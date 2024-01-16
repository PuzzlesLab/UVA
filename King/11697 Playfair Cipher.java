import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

class Main {

	private static class Tuple {
		int r, c;
		
		public Tuple(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			String kp=br.readLine().toUpperCase();
			boolean [] used=new boolean [128];
			used['Q']=true;
			LinkedList<Character> keyQ=new LinkedList<>();
			for (int i=0;i<kp.length();i++)  {
				char c=kp.charAt(i);
				if (c>='A' && c<='Z' && !used[c]) {
					used[c]=true;
					keyQ.add(c);
				}
			}
			for (int i='A';i<='Z';i++) if (!used[i]) keyQ.add((char)i);

			char [][] table=new char [5][5];
			Tuple [] chPos=new Tuple [128];
			for (int i=0;i<5;i++) for (int i2=0;i2<5;i2++) {
				table[i][i2]=keyQ.removeFirst();
				chPos[table[i][i2]]=new Tuple(i,i2);
			}

			ArrayList<String> tokens=new ArrayList<>();
			StringBuilder temp=new StringBuilder();
			String s=br.readLine().toUpperCase();
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (c>='A' && c<='Z') {
					if (temp.length()==1 && temp.charAt(0)==c) {
						temp.append('X');
						tokens.add(temp.toString());
						temp.setLength(0);
						i--;
					} else {
						temp.append(c);
						if (temp.length()==2) {
							tokens.add(temp.toString());
							temp.setLength(0);
						}
					}
				}
			}
			if (temp.length()>0) {
				temp.append('X');
				tokens.add(temp.toString());
			}

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<tokens.size();i++) {
				char c1=tokens.get(i).charAt(0);
				char c2=tokens.get(i).charAt(1);
				Tuple p1=chPos[c1];
				Tuple p2=chPos[c2];
				if (p1.r==p2.r) {
					sb.append(table[p1.r][(p1.c+1)%table.length]);
					sb.append(table[p2.r][(p2.c+1)%table.length]);
				} else if (p1.c==p2.c) {
					sb.append(table[(p1.r+1)%table.length][p1.c]);
					sb.append(table[(p2.r+1)%table.length][p2.c]);
				} else {
					sb.append(table[p1.r][p2.c]);
					sb.append(table[p2.r][p1.c]);
				}
			}
			System.out.println(sb);
		}
	}

}
