import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		char [][] chars=new char [5][];
		for (int i=0;i<5;i++) chars[i]=br.readLine().toCharArray();
		
		StringBuilder ans=new StringBuilder();
		for (int n=0;n<N;n++) {
			int startY=n*4;
			//Check 1
			boolean flag=true;
			for (int x=0;x<chars.length;x++) flag&=chars[x][startY]=='.' && chars[x][startY+1]=='*' && chars[x][startY+2]=='.';
			if (flag) {
				ans.append('1');
				continue;
			}
			flag=true;
			for (int x=0;x<chars.length;x+=2) flag&=chars[x][startY]=='*' && chars[x][startY+1]=='*' && chars[x][startY+2]=='*';
			flag&=chars[1][startY]=='.' && chars[1][startY+1]=='.' && chars[1][startY+2]=='*';
			flag&=chars[3][startY]=='*' && chars[3][startY+1]=='.' && chars[3][startY+2]=='.';
			if (flag) {
				ans.append('2');
				continue;
			}
			flag=true;
			for (int x=0;x<chars.length;x+=2) flag&=chars[x][startY]=='*' && chars[x][startY+1]=='*' && chars[x][startY+2]=='*';
			flag&=chars[1][startY]=='.' && chars[1][startY+1]=='.' && chars[1][startY+2]=='*';
			flag&=chars[3][startY]=='.' && chars[3][startY+1]=='.' && chars[3][startY+2]=='*';
			if (flag) {
				ans.append('3');
				continue;
			}
		}
		System.out.println(ans);
	}

}