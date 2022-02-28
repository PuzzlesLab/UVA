import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static int Ans=0;
	
	public static int check(char [][] ch) {
		int currAns=0;
		for (int c01=1;c01<11;c01++) {
			for (int c10=1;c10<11;c10++) {
				if (ch[0][c01]!=ch[1][c10]) continue;
				// Top+left
				for (int c21=1;c21<11;c21++) {
					for (int c12=c10+1;c12<11;c12++) {
						if (ch[1][c12]!=ch[2][c21]) continue;
						// Btm + left
						int height=c12-c10;

						for (int c03=c01+1;c03<11;c03++) {
							for (int c30=1;c30<11;c30++) {
								if (ch[0][c03]!=ch[3][c30]) continue;
								//Top+right
								int width=c03-c01;
								int c23=c21+width;
								int c32=c30+height;
								if (c23<11 && c32<11 && ch[2][c23]==ch[3][c32]) { // Check btm+right intersection
									currAns=Math.max(currAns, (width-1)*(height-1));
								}
							}
						}
					}
				}
			}
		}
		return currAns;
	}
	
	public static void permutate(char [][] ch, char [][] curr, boolean [] flag, int size) {
		if (size==4) Ans=Math.max(check(curr),Ans);
		else {
			for (int i=0;i<4;i++) if (!flag[i]) {
				flag[i]=true;
				curr[size]=ch[i];
				permutate(ch,curr,flag,size+1);
				flag[i]=false;
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (true) {
			boolean end=false;
			char [][] ch = new char [4][];
			for (int i=0;i<4;i++) {
				s=br.readLine();
				if (s.equals("Q")) {
					end=true;
					break;
				}
				ch[i]=s.toCharArray();
			}
			if (end) break;
			
			Ans=0;
			permutate(ch, new char [4][], new boolean [4], 0);
			System.out.println(Ans);
		}
	}

}
