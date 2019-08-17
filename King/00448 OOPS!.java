import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static String [] operandPrefix= {"R","$","PC+",""};
	
	private static String getOperand(String s) {
		StringBuilder sb=new StringBuilder();
		sb.append(operandPrefix[Integer.parseInt(s.substring(0, 2),2)]);
		sb.append(Integer.parseInt(s.substring(2, s.length()),2));
		return sb.toString();
	}
	
	public static void main (String [] args) throws Exception {
		String [] binary=new String[128];
		for (int i=0;i<16;i++) {
			int hex=Integer.toString(i,16).toUpperCase().charAt(0);
			binary[hex]=Integer.toBinaryString(i);
			while (binary[hex].length()<4) binary[hex]="0"+binary[hex];
		}
		
		String [] opCodeMap= {"ADD","SUB","MUL","DIV","MOV","BREQ","BRLE","BRLS","BRGE",
							  "BRGR","BRNE","BR","AND","OR","XOR","NOT"};
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;

		StringBuilder ans=new StringBuilder();
		StringBuilder sb=new StringBuilder(); // || 
		while ((s=br.readLine())!=null) for (char c : s.toCharArray()) sb.append(binary[c]);
		
		String binStr=sb.toString();
		char [] bin=binStr.toCharArray();
		int idx=0;
		while (idx<bin.length) {
			int opCodeInt=Integer.parseInt(binStr.substring(idx,idx+4),2);
			idx+=4;
			
			ans.append(opCodeMap[opCodeInt]);
			ans.append(' ');
			
			int operandCount=0;
			if (opCodeInt<=4) operandCount=2;
			else if (opCodeInt<=11 || opCodeInt==15) operandCount=1;
			else if (opCodeInt<=14) operandCount=3;
			
			for (int i=0;i<operandCount;i++) {
				ans.append(getOperand(binStr.substring(idx,idx+16)));
				ans.append(',');
				idx+=16;
			}
			
			ans.setLength(ans.length()-1);
			ans.append('\n');
		}
		System.out.print(ans.toString());
	}

}