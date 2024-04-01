import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int alignPos(String s1, String s2) {
		for (int i=s1.length()-s2.length();i<s1.length();i++) {
			int temp=i;
			boolean flag=true;
			for (int i2=0;i2<s2.length() && temp<s1.length() && flag;i2++) {
				flag &= s1.charAt(temp)==s2.charAt(i2);
				temp++;
			}
			if (flag) return i;
		}
		return s1.length();
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			
			String [] lines=new String [W];
			for (int w=0;w<W;w++) lines[w]=br.readLine();
			
			int ans=lines[0].length();
			for (int w=1;w<W;w++) ans+=alignPos(lines[w-1],lines[w]);
			System.out.println(ans);
 		}
	}
}
