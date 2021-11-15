import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	private static HashMap<Long, Integer> dp=new HashMap<>();
	
	private static int findSolution(long N) {
		if (!dp.containsKey(N)) {
			int min=Integer.MAX_VALUE;
			if (N%2==0) min=findSolution(N/2);
			else {
				min=Math.min(findSolution(N-1), min);
				if (N>1) min=Math.min(findSolution(N+1), min);
			}
			dp.put(N, 1+min);
		}
		return dp.get(N);
	}
	
	
	public static void main (String [] args) throws Exception {
		dp.put(0L, 0);
		dp.put(1L, 1);
		dp.put(2L, 2);
		dp.put(3L, 3);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			long target=Long.parseLong(s);
			System.out.println(findSolution(target));
		}
	}

}
