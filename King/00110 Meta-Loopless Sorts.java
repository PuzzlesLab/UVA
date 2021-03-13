import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	public static void recurseFind(Character [] vars, ArrayList<Character> currOrder, StringBuilder sb) {
		if (currOrder.size() == vars.length) {
			for (int l=0;l<currOrder.size();l++) sb.append("  ");
			sb.append("writeln(");
			for (Character c : currOrder) {
				sb.append(c);
				sb.append(",");
			}
			sb.setLength(sb.length()-1);
			sb.append(")\n");
		} else {

			for (int i=currOrder.size()-1;i>=0;i--) {
				for (int l=0;l<currOrder.size();l++) sb.append("  ");
				if (i!=currOrder.size()-1) sb.append("else ");
				sb.append("if "+currOrder.get(i)+" < "+vars[currOrder.size()]+" then\n");
				currOrder.add(i+1, vars[currOrder.size()]);
				recurseFind(vars,currOrder,sb);
				currOrder.remove(i+1);
			}
			for (int l=0;l<currOrder.size();l++) sb.append("  ");
			sb.append("else\n");
			currOrder.add(0, vars[currOrder.size()]);
			recurseFind(vars,currOrder,sb);
			currOrder.remove(0);
		}
	}
	
	private static String getAns(int varCount) {
		Character [] vars=new Character[varCount];
		for (int i=0;i<varCount;i++) vars[i]=(char)('a'+i);
		
		StringBuilder sb=new StringBuilder();
		sb.append("program sort(input,output);\nvar\n");
		for (char c : vars) {
			sb.append(c);
			sb.append(',');
		}
		sb.setLength(sb.length()-1);
		sb.append(" : integer;\nbegin\n  readln(");
		for (char c : vars) {
			sb.append(c);
			sb.append(',');
		}
		sb.setLength(sb.length()-1);
		sb.append(");\n");
		
		ArrayList<Character> clist=new ArrayList<>();
		clist.add(vars[0]);
		recurseFind(vars,clist,sb);
		
		sb.append("end.");
		return sb.toString();
	}
	
	private static String [] solutions=new String [9];
	
	public static void main (String [] args) throws Exception {
		for (int i=1; i<solutions.length; i++) solutions[i]=getAns(i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); //empty
			int varCount=Integer.parseInt(br.readLine());
			if (testCase>0) System.out.println();
			System.out.println(solutions[varCount]);
		}
	}
}