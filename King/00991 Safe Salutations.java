import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		int [] cat=new int [11];
		cat[0]=1;
		for (int i=0;i<cat.length-1;i++) cat[i+1]=(((i<<2)+2)*cat[i])/(i+2);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while ((s=br.readLine())!=null) {
			if (!first) System.out.println();
			else first=false;

			System.out.println(cat[Integer.parseInt(s)]);
			br.readLine();
		}
	}

}
