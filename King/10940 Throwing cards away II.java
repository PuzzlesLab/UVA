import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	public static void main (String [] args) throws Exception {
		/*
		 * 
		 * Pattern :
		 * 0 (placeholder)
		 * 1
		 * 2
		 * 2 4
		 * 2 4 6 8
		 * 2 4 6 8 10 12 14 16
		 * 2 4 6 8 10 12 14 16 18 20 22 24 26 28 30 32
		 * 
		 */
		int LIMIT=500000;
		ArrayList<Integer> ans=new ArrayList<>();
		ans.add(0);
		ans.add(1);
		int currSize=1;
		while (ans.size()<=LIMIT) {
			for (int i=1;i<=currSize && ans.size()<=LIMIT;i++) ans.add(i<<1);
			currSize<<=1;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine().trim());
			if (N==0) break;
			System.out.println(ans.get(N));
		}
	}

}