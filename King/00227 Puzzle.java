import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void swap(char [][] table, int x1, int y1, int x2, int y2) {
		char temp=table[x1][y1];
		table[x1][y1]=table[x2][y2];
		table[x2][y2]=temp;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		int testCaseCount=0;
		while (!s.equals("Z")) {
			char [][] table=new char [5][5];
			int ex=-1, ey=-1;
			for (int i=0;i<5;i++) {
				table[i]=s.toCharArray();
				if (ex==-1) for (int i2=0;i2<5 && ex==-1;i2++) if (table[i][i2]==' ') {
					ex=i;
					ey=i2;
				}
				s=br.readLine();
			}
			boolean valid=true;
			while (true) {
				boolean quit=s.endsWith("0");
				for (char c : s.toCharArray()) {
					switch (c) {
						case 'L':	if (ey>0) {
										swap(table,ex,ey-1,ex,ey);
										ey--;
									}
									else valid=false;
									break;
						case 'R':	if (ey<4) {
										swap(table,ex,ey+1,ex,ey);
										ey++;
									}
									else valid=false;
									break;
						case 'A':	if (ex>0) {
										swap(table,ex-1,ey,ex,ey);
										ex--;
									} else valid=false;
									break;
						case 'B':	if (ex<4) {
										swap(table,ex+1,ey,ex,ey);
										ex++;
									} else valid=false;
									break;
						case '0':	break;
						default :   valid=false;
									break;
					}
					if (!valid) break;
				}
				s=br.readLine();
				if (quit) break;
			}
			
			StringBuilder sb=new StringBuilder("Puzzle #");
			sb.append(++testCaseCount);
			sb.append(':');
			if (valid) {
				for (int i=0;i<5;i++) {
					sb.append('\n');
					for (int i2=0;i2<5;i2++) {
						sb.append(table[i][i2]);
						sb.append(' ');
					}
					sb.setLength(sb.length()-1);
				}
			} else {
				sb.append("\nThis puzzle has no final configuration.");
			}
			
			if (testCaseCount>1) System.out.println();
			System.out.println(sb.toString());
		}
	}

}
