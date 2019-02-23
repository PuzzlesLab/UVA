import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static boolean unique (int v) {
		boolean [] flag=new boolean [10];
		while (v>0) {
			int digit=v%10;
			if (flag[digit]) return false;
			flag[digit]=true;
			v/=10;
		}
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int left=Integer.parseInt(st.nextToken());
			int right=Integer.parseInt(st.nextToken());
			int count=0;
			for (int i=left;i<=right;i++) if (unique(i)) count++;
			System.out.println(count);
		}
	}

}
