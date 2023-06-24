import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static String getKey(int [] values) {
		StringBuilder sb=new StringBuilder();
		for (int n=0;n<values.length;n++) {
			sb.append(values[n]);
			sb.append('-');
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			int I=Integer.parseInt(st.nextToken())-1;
			int J=Integer.parseInt(st.nextToken())-1;
			
			st=new StringTokenizer(br.readLine());
			int [] values=new int [N];
			for (int n=0;n<N;n++) values[n]=Integer.parseInt(st.nextToken());

			HashMap<String,Integer> colMap=new HashMap<>();
			HashMap<Integer,int []> valueMap=new HashMap<>();
			colMap.put(getKey(values),0);
			valueMap.put(0,values);
			int currCol=1;
			int len=-1;
			while (true) {
				int [] newV=new int [N];
				newV[0]=1;
				newV[N-1]=1;
				for (int n=1;n<N-1;n++) {
					int A=newV[n-1];
					int B=values[n];
					int D=values[n+1];
					newV[n]=(A*D+1)/B;
				}
				String key=getKey(newV);
				if (colMap.containsKey(key)) {
					len=currCol-colMap.get(key);
					break;
				} else {
					colMap.put(key,currCol);
					valueMap.put(currCol++,newV);
					values=newV;
				}
			}
			
			J%=len;
			System.out.println(valueMap.get(J)[I]);
		}
	}

}
