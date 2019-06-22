import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("Game Over")) {
			StringTokenizer st=new StringTokenizer(s);
			char [] ch=new char [st.countTokens()];
			for (int i=0;i<ch.length;i++) ch[i]=st.nextToken().charAt(0);
			
			int [] frame=new int [ch.length];
			int frameCount=1; boolean frameEnd=false;
			int [] scoreGotten1=new int [ch.length];
			int [] scoreGotten2=new int [ch.length];
			int score=0;
			for (int i=0;i<ch.length;i++) {
				if (ch[i]=='X') {
					scoreGotten1[i]=10;
					frame[i]=frameCount++;
					frameEnd=false;
				} else if (ch[i]=='/') {
					scoreGotten1[i]=10-scoreGotten1[i-1];
					frame[i]=frameCount++;
					frameEnd=false;
				} else {
					scoreGotten1[i]=ch[i]-'0';
					if (frameEnd) frame[i]=frameCount++;
					else frame[i]=frameCount;
					frameEnd=!frameEnd;
				}
			}
			for (int i=0;i<ch.length;i++) {
				if (ch[i]=='/' && i<ch.length && frame[i]<=9) scoreGotten2[i]=scoreGotten1[i+1];
				else if (ch[i]=='X' && i<ch.length && frame[i]<=9) scoreGotten2[i]+=scoreGotten1[i+1]+scoreGotten1[i+2];
			}
			for (int i=0;i<ch.length;i++) score+=scoreGotten1[i]+scoreGotten2[i];
			System.out.println(score);
		}
	}

}