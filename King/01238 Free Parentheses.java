import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static HashSet<Integer> Sol;
	private static ArrayList<Integer> Nums;
	private static ArrayList<Integer> Signs;
	private static final int VALUE_D=3100;
	private static boolean [][][] Visited;

	private static void compute(int n, int sum, int negBracket) {
		if (n==Nums.size()) {
			Sol.add(sum);
			return;
		}
		if (Visited[n][sum+VALUE_D][negBracket]) return;

		Visited[n][sum+VALUE_D][negBracket]=true;
		int num=Nums.get(n);
		int nSum=sum+num*(((negBracket&1)!=0)?-1:1);
		if (Signs.get(n)==-1) compute(n+1,nSum,negBracket+1); // Instead of num<0 to handle -0 case.
		if (negBracket>0) compute(n+1,nSum,negBracket-1);
		compute(n+1,nSum,negBracket);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null) {
        	StringTokenizer st=new StringTokenizer(s);
        	Nums=new ArrayList<>();
        	Signs=new ArrayList<>();
        	int sign=1;
        	while (st.hasMoreTokens()) {
        		s=st.nextToken();
        		if (s.equals("+")) sign=1;
        		else if (s.equals("-")) sign=-1;
        		else {
        			Signs.add(sign);
        			Nums.add(sign*Integer.parseInt(s));
        		}
        	}

        	Sol=new HashSet<>();
        	if (!Nums.isEmpty()) {
            	Visited=new boolean [Nums.size()+1][(VALUE_D<<1)+1][Nums.size()+1];
            	compute(0,0,0);
        	}
        	System.out.println(Sol.size());
        }
	}

}