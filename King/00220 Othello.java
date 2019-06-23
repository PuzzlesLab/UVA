import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	private static ArrayList<String> getMoves(char [][] map, char currPlayer) {
		ArrayList<String> moves=new ArrayList<>();
		char opp=currPlayer=='B' ? 'W' : 'B';
		for (int x=0;x<8;x++) for (int y=0;y<8;y++) if (map[x][y]=='-') {
			boolean flag=false;
			int tempx=0, tempy=0;
			//Up
			if (x-1>=0 && map[x-1][y]==opp) {
				tempx=x-2;
				while (tempx>=0 && map[tempx][y]!='-') {
					if (map[tempx][y]==currPlayer) {
						flag=true;
						break;
					}
					tempx--;
				}
			}
			
			//Down
			if (x+1<8 && map[x+1][y]==opp) {
				tempx=x+2;
				while (tempx<8 && map[tempx][y]!='-') {
					if (map[tempx][y]==currPlayer) {
						flag=true;
						break;
					}
					tempx++;
				}
			}
			
			//Left
			if (y-1>=0 && map[x][y-1]==opp) {
				tempy=y-2;
				while (tempy>=0 && map[x][tempy]!='-') {
					if (map[x][tempy]==currPlayer) {
						flag=true;
						break;
					}
					tempy--;
				}
			}
			
			//Right
			if (y+1<8 && map[x][y+1]==opp) {
				tempy=y+2;
				while (tempy<8 && map[x][tempy]!='-') {
					if (map[x][tempy]==currPlayer) {
						flag=true;
						break;
					}
					tempy++;
				}
			}
			
			//Left-Up
			if (x-1>=0 && y-1>=0 && map[x-1][y-1]==opp) {
				tempx=x-2;
				tempy=y-2;
				while (tempx>=0 && tempy>=0 && map[tempx][tempy]!='-') {
					if (map[tempx][tempy]==currPlayer) {
						flag=true;
						break;
					}
					tempx--; tempy--;
				}
			}

			
			//Right-Up
			if (x+1<8 && y-1>=0 && map[x+1][y-1]==opp) {
				tempx=x+2;
				tempy=y-2;
				while (tempx<8 && tempy>=0 && map[tempx][tempy]!='-') {
					if (map[tempx][tempy]==currPlayer) {
						flag=true;
						break;
					}
					tempx++; tempy--;
				}
			}
			
			//Left-Down
			if (x-1>=0 && y+1<8 && map[x-1][y+1]==opp) {
				tempx=x-2;
				tempy=y+2;
				while (tempx>=0 && tempy<8 && map[tempx][tempy]!='-') {
					if (map[tempx][tempy]==currPlayer) {
						flag=true;
						break;
					}
					tempx--; tempy++;
				}
			}
			
			//Right-Down
			if (x+1<8 && y+1<8 && map[x+1][y+1]==opp) {
				tempx=x+2;
				tempy=y+2;
				while (tempx<8 && tempy<8 && map[tempx][tempy]!='-') {
					if (map[tempx][tempy]==currPlayer) {
						flag=true;
						break;
					}
					tempx++; tempy++;
				}
			}
			
			if (flag) moves.add(String.format("(%d,%d)", x+1, y+1));
		}
		
		return moves;
	}
	
	
	private static void makeMove (char [][] map, int x, int y, char currPlayer) {
		//Up
		int tempx=x-1;
		while (tempx>=0 && map[tempx][y]!='-') {
			if (map[tempx][y]==currPlayer) {
				for (int i=tempx+1;i<x;i++) map[i][y]=currPlayer;
				break;
			}
			tempx--;
		}
		
		//Down
		tempx=x+1;
		while (tempx<8 && map[tempx][y]!='-') {
			if (map[tempx][y]==currPlayer) {
				for (int i=x+1;i<=tempx;i++) map[i][y]=currPlayer;
				break;
			}
			tempx++;
		}
		
		//Left
		int tempy=y-1;
		while (tempy>=0 && map[x][tempy]!='-') {
			if (map[x][tempy]==currPlayer) {
				for (int i=tempy+1;i<y;i++) map[x][i]=currPlayer;
				break;
			}
			tempy--;
		}
		
		//Right
		tempy=y+1;
		while (tempy<8 && map[x][tempy]!='-') {
			if (map[x][tempy]==currPlayer) {
				for (int i=y+1;i<=tempy;i++) map[x][i]=currPlayer;
				break;
			}
			tempy++;
		}
		
		//Left-Up
		tempx=x-1;
		tempy=y-1;
		while (tempx>=0 && tempy>=0 && map[tempx][tempy]!='-') {
			if (map[tempx][tempy]==currPlayer) {
				for (int delta=0;tempx+delta<x;delta++) {
					int dx=tempx+delta;
					int dy=tempy+delta;
					map[dx][dy]=currPlayer;
				}
				break;
			}
			tempx--; tempy--;
		}
		
		//Right-Up
		tempx=x+1;
		tempy=y-1;
		while (tempx<8 && tempy>=0 && map[tempx][tempy]!='-') {
			if (map[tempx][tempy]==currPlayer) {
				for (int delta=0;tempx-delta>x;delta++) {
					int dx=tempx-delta;
					int dy=tempy+delta;
					map[dx][dy]=currPlayer;
				}
				break;
			}
			tempx++; tempy--;
		}
		
		//Left-Down
		tempx=x-1;
		tempy=y+1;
		while (tempx>=0 && tempy<8 && map[tempx][tempy]!='-') {
			if (map[tempx][tempy]==currPlayer) {
				for (int delta=0;tempx+delta<x;delta++) {
					int dx=tempx+delta;
					int dy=tempy-delta;
					map[dx][dy]=currPlayer;
				}
				break;
			}
			tempx--; tempy++;
		}
		
		//Right-Down
		tempx=x+1;
		tempy=y+1;
		while (tempx<8 && tempy<8 && map[tempx][tempy]!='-') {
			if (map[tempx][tempy]==currPlayer) {
				for (int delta=0;tempx-delta>x;delta++) {
					int dx=tempx-delta;
					int dy=tempy-delta;
					map[dx][dy]=currPlayer;
				}
				break;
			}
			tempx++; tempy++;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [][] map=new char [8][];
			for (int i=0;i<8;i++) map[i]=br.readLine().trim().toCharArray();
			
			char currPlayer=br.readLine().charAt(0);
			StringBuilder sb=new StringBuilder();
			String s;
			while (!(s=br.readLine()).equals("Q")) {
				ArrayList<String> moves=getMoves(map,currPlayer);
				if (s.equals("L")) {
					if (moves.size()==0) sb.append("No legal move.\n");
					else {
						for (String move : moves) {
							sb.append(move);
							sb.append(' ');
						}
						sb.setCharAt(sb.length()-1, '\n');
					}
				} else if (s.charAt(0) == 'M') {
					int x=s.charAt(1)-'0';
					int y=s.charAt(2)-'0';
					
					String key=String.format("(%d,%d)", x, y);
					if (!moves.contains(key)) currPlayer=currPlayer=='B' ? 'W' : 'B';
					map[x-1][y-1]=currPlayer;
					makeMove(map,x-1,y-1,currPlayer);
					
					int bcount=0, wcount=0;
					for (int i=0;i<8;i++) for (int i2=0;i2<8;i2++)
						if (map[i][i2]=='B') bcount++; else if (map[i][i2]=='W') wcount++;
					sb.append(String.format("Black - %2d White - %2d\n", bcount, wcount));
					
					currPlayer=currPlayer=='B' ? 'W' : 'B';
				}
			}
			for (int i=0;i<8;i++) {
				sb.append(new String(map[i]));
				sb.append('\n');
			}
			
			if (testCase>0) System.out.println();
			System.out.print(sb.toString());
		}
	}

}
