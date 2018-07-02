import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int num=Integer.parseInt(st.nextToken());
			int den=Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> list=new ArrayList<>();
			do {
				int b=num/den;
				num=num%den;
				
				list.add(b);
				
				int temp=num;
				num=den;
				den=temp;
			} while (den>1);
			if (den==1) list.add(num);
			
			StringBuilder sb=new StringBuilder();
			sb.append("[");
			sb.append(list.get(0));
			sb.append(";");
			for (int i=1;i<list.size();i++) {
				sb.append(list.get(i));
				sb.append(",");
			}
			sb.setLength(sb.length()-1);
			sb.append("]");
			System.out.println(sb.toString());
		}
	}

}
