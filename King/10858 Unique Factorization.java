import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Main {

	public static ArrayList<String> solutions=new ArrayList<>();
	
	public static String listToStr(List<Integer> list) {
		StringBuilder sb=new StringBuilder();
		for (int factor : list) {
			sb.append(factor);
			sb.append(' ');
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
	
	public static void search (int remaining, int currFactorMin, LinkedList<Integer> factors) {
		if (remaining>1) {
			for (int i=currFactorMin;i<=Math.sqrt(remaining);i++) if (remaining%i==0) {
				factors.addLast(i);
				search(remaining/i,i,factors);
				factors.addLast(remaining/i);
				solutions.add(listToStr(factors));
				factors.removeLast();
				factors.removeLast();
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			solutions.clear();
			int N=Integer.parseInt(s);
			if (N>=4) search(N, 2, new LinkedList<>());
			
			StringBuilder sb=new StringBuilder();
			sb.append(solutions.size());
			sb.append('\n');
			for (String sol : solutions) {
				sb.append(sol);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}