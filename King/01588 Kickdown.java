import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int trySolution(char [] below, char [] top) {
		int minLength=below.length+top.length;
		for (int start=0;start<=below.length;start++) {
			boolean feasible=true;
			for (int i=0;i<top.length && start+i<below.length && feasible;i++) {
				int belowI=below[start+i]-'0';
				int topI=top[i]-'0';
				feasible &= (belowI+topI) <= 3;
			}
			if (feasible) minLength=Math.min(minLength, Math.max(below.length, start+top.length));
		}
		return minLength;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			char [] below=s.toCharArray();
			char [] top=br.readLine().toCharArray();
			
			int solution=below.length+top.length;
			solution=Math.min(solution, trySolution(below, top));
			solution=Math.min(solution, trySolution(top, below));

			System.out.println(solution);
		}
	}
}