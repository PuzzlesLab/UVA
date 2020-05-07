import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("END")) {
			double d=Double.parseDouble(s);
			
			if (d==0 || d==1) System.out.println("MEMBER");
			else {
				boolean member=true;
				for (int i=0;i<20 && d!=0.0 && member;i++) {
					d*=3; //Multiply 3 to convert to base 3 & shift left.
					int di=(int)d;
					d-=di;
					member=(di!=1);
				}
				System.out.println(member ? "MEMBER" : "NON-MEMBER");
			}

		}
	}

}