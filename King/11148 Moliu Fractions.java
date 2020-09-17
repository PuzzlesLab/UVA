import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static class Fraction {
		BigInteger top, btm;
		
		public Fraction(int t) {
			this.top=BigInteger.valueOf(t);
			this.btm=BigInteger.ONE;
		}
		
		public Fraction(int t, int b) {
			this.top=BigInteger.valueOf(t);
			this.btm=BigInteger.valueOf(b);
		}
		
		public Fraction(int f, int t, int b) {
			this.top=BigInteger.valueOf(f*b+t);
			this.btm=BigInteger.valueOf(b);
		}
		
		public void multiply(Fraction f) {
			this.top=this.top.multiply(f.top);
			this.btm=this.btm.multiply(f.btm);
			BigInteger gcd=this.top.gcd(this.btm);
			this.top=this.top.divide(gcd);
			this.btm=this.btm.divide(gcd);
		}
		
		public String toString() {
			if (this.top.equals(BigInteger.ZERO)) return "0";
			else if (this.top.compareTo(this.btm)<0) return String.format("%s/%s",this.top,this.btm);
			else if (this.top.mod(this.btm).equals(BigInteger.ZERO)) return String.format("%s",this.top.divide(this.btm));
			else {
				BigInteger f=this.top.divide(this.btm);
				this.top=this.top.mod(this.btm);
				return String.format("%s-%s/%s",f, this.top, this.btm);
			}
		}
	}
	
	public static Fraction parseFraction(String s) {
		if (s.contains("-")) {
			String [] split=s.split("-");
			int f=Integer.parseInt(split[0]);
			split=split[1].split("/");
			return new Fraction(f, Integer.parseInt(split[0]),Integer.parseInt(split[1]));
		} else if (s.contains("/")) {
			String [] split=s.split("/");
			return new Fraction(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
		} else return new Fraction(Integer.parseInt(s));
	}
	
	public static String clean(String s) {
		StringBuilder sb=new StringBuilder();
		for (int i=s.length()-1;i>=0;i--) {
			char c=s.charAt(i);
			if ((c>='0' && c<='9') || c=='-' || c=='/') sb.append(c);
		}
		sb.reverse();
		return sb.toString();
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			ArrayList<Fraction> fractions=new ArrayList<>();
			while (st.hasMoreTokens()) {
				try { fractions.add(parseFraction(clean(st.nextToken()))); } catch (Exception e) {};
			}
			for (int i=1;i<fractions.size();i++) fractions.get(0).multiply(fractions.get(i));
			System.out.println(fractions.get(0).toString());
		}
		
	}
}