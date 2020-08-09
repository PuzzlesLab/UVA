import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	private static class Variable {
		String name;
		int bytes;
		StringBuilder value;
		
		public Variable(String n, int b) {
			this.name=n;
			this.bytes=b;
			this.value=new StringBuilder();
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int B=Integer.parseInt(st.nextToken());
			int V=Integer.parseInt(st.nextToken());
			Variable [] variables=new Variable[V];
			HashMap<String, Variable> vMap=new HashMap<>();
			
			for (int v=0;v<V;v++) {
				st=new StringTokenizer(br.readLine());
				variables[v]=new Variable(st.nextToken(),Integer.parseInt(st.nextToken()));
				vMap.put(variables[v].name, variables[v]);
			}
			
			for (int v=0;v<V;v++) for (int i=0;i<variables[v].bytes;i++) variables[v].value.append(br.readLine());
			
			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				String name=br.readLine();
				if (vMap.containsKey(name)) System.out.printf("%s=%d\n", name, new BigInteger(vMap.get(name).value.toString(),2));
				else System.out.printf("%s=\n", name);
			}
		}
	}

}