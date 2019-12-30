import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] safe=new int[31];
		safe[1]=2; //U, L
		safe[2]=4; //UU, LL, LU, UL
		safe[3]=7; //LUU, ULU, UUL, LUL, LLU, ULL, LLL
		safe[4]=13; //ULUU, UULU
		            //LLUU, LULU, LUUL, ULLU, ULUL, UULL
				    //ULLL, LULL, LLUL, LLLU
		            //LLLL
					//  safe[i]=safe[i-1]+safe[i-2]+safe[i-3]
		for (int i=5; i<safe.length;i++) safe[i]=safe[i-1]+safe[i-2]+safe[i-3];
		int [] pow2=new int [31]; pow2[0]=1;
		int [] unsafe=new int [31];
		for (int i=1; i<unsafe.length;i++) {
			pow2[i]=pow2[i-1]*2;
			unsafe[i]=pow2[i]-safe[i];
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) System.out.println(unsafe[Integer.parseInt(s)]);
	}

}