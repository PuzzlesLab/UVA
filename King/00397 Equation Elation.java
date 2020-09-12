import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	private static abstract class Op {}
	private static class Operand extends Op{
		int value;
		public Operand(int v) { this.value=v; }
		public String toString() { return String.valueOf(this.value); }
	}
	private static class Opcode extends Op{
		char value;
		public Opcode(char v) { this.value=v; }
		public String toString() { return String.valueOf(this.value); }
	}
	private static class RHS extends Op {
		String value;
		public RHS(String v) { this.value=v; }
		public String toString() { return this.value; }
	}
	private static class Equation {
		ArrayList<Op> data=new ArrayList<>();
		
		public static Equation construct(String s) {
			Equation eq=new Equation();
			int lastNum=Integer.MAX_VALUE;
			boolean negLastNum=false;
			StringBuilder rhs=new StringBuilder();
			for (char c : s.toCharArray()) if (c!=' ') {
				if (c>='0' && c<='9') {
					if (lastNum==Integer.MAX_VALUE) lastNum=0;
					lastNum=lastNum*10+(c-'0');
				} else if (c=='+' || c=='-' || c=='*' || c=='/' || c=='=') {
					if (lastNum!=Integer.MAX_VALUE) {
						if (negLastNum) {
							lastNum=-lastNum;
							negLastNum=false;
						}
						eq.data.add(new Operand(lastNum));
					}
					lastNum=Integer.MAX_VALUE;
					
					if ((c=='+' || c== '-') && !eq.data.isEmpty() && eq.data.get(eq.data.size()-1) instanceof Opcode) negLastNum=c=='-';
					else eq.data.add(new Opcode(c));
				} else rhs.append(c);
			}
			eq.data.add(new RHS(rhs.toString()));
			return eq;
		}
		
		public boolean simplify() {
			for (int i=0;i<this.data.size();i++) if (this.data.get(i) instanceof Opcode) {
				Opcode castOp=(Opcode) this.data.get(i);
				if (castOp.value=='*' || castOp.value=='/') {
					int v1=((Operand)this.data.get(i-1)).value;
					int v2=((Operand)this.data.get(i+1)).value;
					
					this.data.remove(i-1);
					this.data.remove(i-1);
					this.data.remove(i-1);
					
					if (castOp.value=='*') this.data.add(i-1, new Operand(v1*v2));
					else this.data.add(i-1, new Operand(v1/v2));
					
					return true;
				}
			}
			for (int i=0;i<this.data.size();i++) if (this.data.get(i) instanceof Opcode) {
				Opcode castOp=(Opcode) this.data.get(i);
				if (castOp.value=='+' || castOp.value=='-') {
					int v1=((Operand)this.data.get(i-1)).value;
					int v2=((Operand)this.data.get(i+1)).value;
					
					this.data.remove(i-1);
					this.data.remove(i-1);
					this.data.remove(i-1);
					
					if (castOp.value=='+') this.data.add(i-1, new Operand(v1+v2));
					else this.data.add(i-1, new Operand(v1-v2));

					return true;
				}
			}
			return false;
		}
		
		public String toString () {
			StringBuilder sb=new StringBuilder();
			for (Op op : data) {
				sb.append(op.toString());
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);
			return sb.toString();
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=0;
		while ((s=br.readLine())!=null) {
			if (testCase++>0) System.out.println();
			Equation eq=Equation.construct(s);
			do {
				System.out.println(eq.toString());
			} while (eq.simplify());
		}
	}
}