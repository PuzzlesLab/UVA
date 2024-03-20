import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			String [] list=new String [N];
			for (int n=0;n<N;n++) list[n]=br.readLine();
			Arrays.sort(list);
			
			boolean flag=true;
			for (int n=0;n<N-1 && flag;n++) flag &= !(list[n+1].startsWith(list[n]));
			System.out.println(flag?"YES":"NO");
		}
	}
}