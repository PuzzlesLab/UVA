import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
	
	public static ArrayList<int []> permutations=new ArrayList<>();
	
	public static boolean dfs (int result, int numIndex, int [] num) {
		if (numIndex==num.length-1) return result==23;
		
		numIndex++;
		int [] nextResults= {result+num[numIndex], result-num[numIndex], result*num[numIndex]};
		for (int next : nextResults) if (dfs(next, numIndex, num)) return true;
		return false;
	}
	
	public static void permutate(int [] num, int [] currNum, int size, boolean [] taken) {
		if (size==num.length) permutations.add(Arrays.copyOf(currNum, size));
		else {
			for (int i=0;i<num.length;i++) if (!taken[i]) {
				taken[i]=true;
				currNum[size]=num[i];
				permutate(num,currNum,size+1,taken);
				taken[i]=false;
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0 0")) {
			String [] numStr=s.split(" ");
			int [] num=new int [numStr.length];
			for (int i=0;i<num.length;i++) num[i]=Integer.parseInt(numStr[i]);
			
			Arrays.sort(num);
			permutations.clear();
			permutate(num, new int [num.length], 0, new boolean [num.length]);
			boolean flag=false;
			for (int [] permutation : permutations) {
				flag=dfs(permutation[0],0,permutation);
				if (flag) break;
			}
			if (flag) System.out.println("Possible");
			else System.out.println("Impossible");
		}
	}

}