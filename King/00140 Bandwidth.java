import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main{
	
	public static int SolutionDist=0;
	public static String Solution=null;
	
	public static void search(boolean [][] conn, char [] ch, boolean [] flag, char [] curr, int currSize) {
		if (currSize==curr.length) {
			int dist=0;
			for (int i=0;i<curr.length;i++) {
				int idx=curr[i]-'A';
				for (int i2=i+1;i2<curr.length;i2++) if (conn[idx][curr[i2]-'A']) dist=Math.max(dist, i2-i);
			}
			if (dist<SolutionDist) {
				SolutionDist=dist;
				StringBuilder sb=new StringBuilder();
				for (char c : curr) {
					sb.append(c);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				Solution=sb.toString();
			}
		} else {
			for (int i=0;i<ch.length;i++) if (!flag[i]) {
				flag[i]=true;
				curr[currSize]=ch[i];
				search(conn,ch,flag,curr,currSize+1);
				flag[i]=false;
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s,";");
			
			boolean [][] conn=new boolean [26][26];
			TreeSet<Character> list=new TreeSet<>();
			while (st.hasMoreTokens()) {
				StringTokenizer st2=new StringTokenizer(st.nextToken(),":");
				char src=st2.nextToken().charAt(0);
				list.add(src);
				String dest=st2.nextToken();
				for (int i=0;i<dest.length();i++) {
					char destC=dest.charAt(i);
					conn[src-'A'][destC-'A']=true;
					conn[destC-'A'][src-'A']=true;
					list.add(destC);
				}
			}
			
			SolutionDist=Integer.MAX_VALUE;
			char [] ch=new char[list.size()];
			int chCount=0;
			for (char c : list) ch[chCount++]=c;
			search(conn,ch,new boolean[ch.length],new char[ch.length],0);
			System.out.printf("%s -> %d\n",Solution,SolutionDist);
		}
	}

}