import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {
	
	private static boolean [] isOpcode=new boolean [128];
	private static int [] opcodeArgCount=new int [128];
	
	public static void main (String [] args) throws Exception {
		isOpcode['K']=true; isOpcode['A']=true; isOpcode['N']=true; isOpcode['C']=true; isOpcode['E']=true;
		opcodeArgCount['K']=2; opcodeArgCount['A']=2; opcodeArgCount['N']=1; opcodeArgCount['C']=2; opcodeArgCount['E']=2;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			LinkedList<Character> twoArgOpcode=new LinkedList<>();
			LinkedList<Character> oneArgOpcode=new LinkedList<>();
			LinkedList<Character> operand=new LinkedList<>();
			for (char c : s.toCharArray()) {
				if (isOpcode[c]) {
					if (opcodeArgCount[c]==2) twoArgOpcode.add(c);
					else oneArgOpcode.add(c);
				} else operand.add(c);
			}
			
			StringBuilder sb=new StringBuilder();
			int lack=1;
			while (lack<=operand.size()) {
				if (!twoArgOpcode.isEmpty() && lack+1<=operand.size()) {
					sb.append(twoArgOpcode.removeFirst());
					lack+=1;
				} else if (!oneArgOpcode.isEmpty()) {
					sb.append(oneArgOpcode.removeFirst());
				} else break;
			}
			if (sb.length()>0) while (lack-->0) sb.append(operand.removeFirst());
			
			System.out.println(sb.length()>0 ? sb.toString() : "no WFF possible");
		}
	}

}