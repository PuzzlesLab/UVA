import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			String s=br.readLine();

			char [][] ans=new char [120][s.length()+3];
			for (int x=0;x<ans.length;x++) Arrays.fill(ans[x],' ');
			for (int x=0;x<ans.length-1;x++) ans[x][0]='|';
			ans[ans.length-1][0]='+';
			for (int y=1;y<ans[0].length;y++) ans[ans.length-1][y]='-';

			int cx=ans.length/2;
			int cy=2;
			boolean [] hasPoint=new boolean [ans.length];
			hasPoint[ans.length-1]=true;
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				hasPoint[cx]=true;
				if (c=='R') {
					ans[cx][cy]='/';
					if (i+1<s.length() && s.charAt(i+1)!='F') {
						cx--;
					}
				} else if (c=='C') {
					ans[cx][cy]='_';
					if (i+1<s.length() && s.charAt(i+1)=='F') {
						cx++;
					}
				} else if (c=='F') {
					ans[cx][cy]='\\';
					if (i+1<s.length() && s.charAt(i+1)=='F') {
						cx++;
					}
				}
				cy++;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase);
			sb.append(":\n");
			for (int x=0;x<ans.length;x++) if (hasPoint[x]) {
				StringBuilder curr=new StringBuilder();
				for (int y=0;y<ans[x].length;y++) curr.append(ans[x][y]);
				while (curr.length()>0 && curr.charAt(curr.length()-1)==' ') curr.setLength(curr.length()-1);
				sb.append(curr);
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}

}
