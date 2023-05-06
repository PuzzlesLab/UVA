import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int cToInt(char c) {
		switch (c) {
			case 'V': return 0;
			case 'U': return 1;
			case 'C': return 2;
			case 'D': return 3;
		}
		return -1;
	}
	
	private static int strToInt(String s) {
		int multi=1;
		char [] ch=s.toCharArray();
		int value=0;
		for (int i=ch.length-1;i>=0;i--) {
			value+=cToInt(ch[i])*multi;
			multi*=4;
		}
		return value;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder("COWCULATIONS OUTPUT\n");
		for (int n=0;n<N;n++) {
			int num1=strToInt(br.readLine());
			int num2=strToInt(br.readLine());
			char [] opcodes={
				br.readLine().charAt(0),
				br.readLine().charAt(0),
				br.readLine().charAt(0),
			};
			int result=strToInt(br.readLine());
			for (int i=0;i<opcodes.length;i++) {
				char opcode=opcodes[i];
				if (opcode=='A') num2+=num1;
				else if (opcode=='R') num2>>=2;
				else if (opcode=='L') num2<<=2;
			}
			sb.append(num2==result ? "YES" : "NO");
			sb.append('\n');
		}
		sb.append("END OF OUTPUT");
		System.out.println(sb.toString());
	}
}