import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final double Probability [] = new double [128];
	private static final char [] OddChars = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
	private static final char [] EvenChars = {'a','e','i','o','u'};
	
	private static void setup() {
		Probability['a']=12.53;
		Probability['b']=1.42;
		Probability['c']=4.68;
		Probability['d']=5.86;
		Probability['e']=13.68;
		Probability['f']=.69;
		Probability['g']=1.01;
		Probability['h']=.7;
		Probability['i']=6.25;
		Probability['j']=.44;
		Probability['k']=.0;
		Probability['l']=4.97;
		Probability['m']=3.15;
		Probability['n']=6.71;
		Probability['o']=8.68;
		Probability['p']=2.51;
		Probability['q']=.88;
		Probability['r']=6.87;
		Probability['s']=7.98;
		Probability['t']=4.63;
		Probability['u']=3.93;
		Probability['v']=.9;
		Probability['w']=.02;
		Probability['x']=.22;
		Probability['y']=.9;
		Probability['z']=.52;
	}
	
	private static double currTotal=.0;
	private static int currCount=0;

	private static void calcAll(char [] curr, int currLength) {
		if (curr.length==currLength) {
			currCount++;
			double currValue=.0;
			for (int i=0;i<curr.length;i++) currValue+=Probability[curr[i]]*(i+1);
			currTotal+=currValue;
		} else {
			char [] candidates=(currLength+1)%2==0 ? EvenChars : OddChars;
			for (char c: candidates) {
				curr[currLength]=c;
				calcAll(curr,currLength+1);
				curr[currLength]=0;
			}
		}
	}
	public static void main (String [] args) throws Exception {
		setup();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			String s=br.readLine();
			double sValue=.0;
			for (int i=0;i<s.length();i++) sValue+=Probability[s.charAt(i)]*(i+1);
			
			currTotal=.0;
			currCount=0;
			char [] ch=new char [s.length()];
			ch[0]=s.charAt(0);
			calcAll(ch,1);
			double avg=currTotal/currCount;

			System.out.println(sValue<avg?"below":"above or equal");
		}
	}

}
