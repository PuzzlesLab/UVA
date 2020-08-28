import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {
	
	private static boolean [][] rotateAnticlockwise90(boolean [][] mat) {
		boolean [][] bool=new boolean [6][6];
		for (int x=0;x<mat.length;x++) for (int y=0;y<mat.length;y++) bool[5-y][x]=mat[x][y];
		return bool;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean [][][] sandorf=new boolean [4][6][6];
		sandorf[3][0][1]=true;
		sandorf[3][0][3]=true;
		sandorf[3][0][5]=true;
		sandorf[3][1][4]=true;
		sandorf[3][2][2]=true;
		sandorf[3][3][1]=true;
		sandorf[3][3][4]=true;
		sandorf[3][4][5]=true;
		sandorf[3][5][3]=true;
		sandorf[0]=rotateAnticlockwise90(sandorf[3]);
		sandorf[1]=rotateAnticlockwise90(sandorf[0]);
		sandorf[2]=rotateAnticlockwise90(sandorf[1]);
		
		String s;
		while ((s=br.readLine())!=null) {
			StringBuilder content=new StringBuilder(s);
			LinkedList<String> strList=new LinkedList<>();
			for (int i=content.length();i>0;i-=36) {
				char [][] ch=new char [6][6];
				for (int x=0;x<6;x++) for (int y=0;y<6;y++) {
					int idx=(i-36+(x*6+y));
					if (idx>=0) ch[x][y]=content.charAt(idx);
				}
				
				LinkedList<Character> llist=new LinkedList<>();
				for (int rot=0;rot<4;rot++) {
					for (int x=5;x>=0;x--) for (int y=5;y>=0;y--) if (sandorf[rot][x][y]) {
						llist.addFirst(ch[x][y]);
					}
				}

				StringBuilder chars=new StringBuilder();
				for (Character c : llist) chars.append(c);
				strList.addFirst(chars.toString()); 
			}
			StringBuilder reverse=new StringBuilder();
			for (String str : strList) reverse.append(str);
			reverse=new StringBuilder(reverse.reverse());
			while (reverse.length()>0 && reverse.charAt(reverse.length()-1)=='#') reverse.setLength(reverse.length()-1);
			
			System.out.println(reverse);
		}
	}
}