import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	public static void main(String[] args) throws Exception {
		long [] cat=new long [21];
		HashMap<Long,Integer> dp=new HashMap<>();
		cat[0]=1;
		for (int i=0;i<cat.length-1;i++) {
			cat[i+1]=(((i<<2)+2)*cat[i])/(i+2);
			dp.put(cat[i+1],i+1);
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) System.out.println(dp.get(Long.parseLong(s)));
	}

}
