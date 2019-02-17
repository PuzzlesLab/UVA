import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int [] ints= {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int ans=(int)Math.ceil(ints[0]*ints[1]/2.0);
			if (Math.min(ints[0], ints[1])==1) ans=Math.max(ints[0], ints[1]);
			else if (Math.min(ints[0], ints[1])==2) {
				int max=Math.max(ints[0], ints[1])-2;
				int quotient=(max/4)+1;
				int remainder=max%4;
				ans=4*quotient;
				if (remainder==3) ans+=2;
			}
			
			System.out.println(String.format("%d knights may be placed on a %d row %d column board.", ans , ints[0], ints[1]));
		}
	}

}
