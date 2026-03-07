import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		long [] pos=new long [9];
		long [] pow10=new long [9];
		pow10[0]=1;
		long curr=1;
		for (int i=1;i<pos.length;i++) {  // Compute the start index of n digits number.
			pow10[i]=curr*10;
			pos[i]=pos[i-1]+((curr*10)-curr)*i;
			curr*=10;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			 long N=Integer.parseInt(s);
			 for (int i=1;i<pos.length;i++) {
				 long min=pos[i-1];
				 long max=pos[i];
				 if (min<=N && N<=max) {
					 long startDelta=N-min-1;
					 long ans=(startDelta/i)+pow10[i-1];
					 int digitPos=(int)startDelta%i;
					 System.out.println(String.valueOf(ans).charAt(digitPos));
					 break;
				 }
			 }
		}
	}

}
