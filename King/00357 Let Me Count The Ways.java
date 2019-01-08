import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int value=Integer.parseInt(s);
			int [] cents=new int []{1,5,10,25,50};
			long [] table=new long [value+1];
			
			table[0]=1;
			for (int c=0;c<cents.length;c++) for (int v=cents[c];v<table.length;v++) table[v]+=table[v-cents[c]];
			
			if (table[value]==1) System.out.println("There is only 1 way to produce "+value+" cents change.");
			else System.out.printf("There are %d ways to produce %d cents change.\n", table[value], value);
		}
	}

}
