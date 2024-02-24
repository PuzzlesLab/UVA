import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			int H=Integer.parseInt(s);
			
			ArrayList<Integer> left=new ArrayList<>();
			int currSum=0;
			for (int i=1;i<H;i++) {
				if (currSum+i+i+1>H) {
					left.add(H-currSum);
					break;
				}
				left.add(i);
				currSum+=i;
			}
			ArrayList<Integer> right=new ArrayList<>();
			for (int i=0;i<left.size()-2;i++) right.add(left.get(i));
			right.add(left.get(left.size()-2)+left.get(left.size()-1));

			char [][] ch=new char [H][(left.size()+right.size()+1)<<1];
			for (int i=0;i<ch.length;i++) Arrays.fill(ch[i],' ');
			
			// Fill asterisks & hashes
			int cx=ch.length-1;
			int cy=0;
			for (int i=left.size()-1;i>=0;i--) {
				int height=left.get(i);
				for (int i2=0;i2<height;i2++) ch[cx--][cy]='#';
				ch[cx+1][++cy]='*';
				ch[cx+1][++cy]='*';
			}
			cx++;
			cy++;
			for (int i=0;i<right.size();i++) {
				int height=right.get(i);
				ch[cx][cy]='*';
				ch[cx][++cy]='*';
				cy++;
				for (int i2=0;i2<height;i2++) ch[cx++][cy]='#';
			}

			// Fill periods
			for (int i=0;i<ch.length;i++) {
				int bound=0;
				for (int i2=0;i2<ch[i].length;i2++) {
					if (ch[i][i2]=='#') bound++;
					else if (ch[i][i2]==' ') {
						if (bound==1) {
							ch[i][i2]='.';
						} else if (bound==2) ch[i][i2]=0;
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Tower #");
			sb.append(tc);
			sb.append('\n');
			for (int i=0;i<ch.length;i++) {
				for (int i2=0;i2<ch[i].length;i2++) {
					if (ch[i][i2]==0) continue;
					sb.append(ch[i][i2]);
				}
				sb.append('\n');
			}
			System.out.println(sb.toString());
			
			tc++;
		}
	}
}
