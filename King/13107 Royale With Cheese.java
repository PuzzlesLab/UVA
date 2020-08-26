import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			char [] ch=s.toCharArray();
			String [] map=new String [128];
			int mapCount=0;
			for (char c : ch) if (map[c]==null) map[c]=String.valueOf(++mapCount);
			for (int i=0;i<map.length;i++) if (map[i]!=null) {
				StringBuilder newValue=new StringBuilder();
				for (char cv : map[i].toCharArray()) {
					if (cv=='2') newValue.append(5);
					else if (cv=='5') newValue.append(2);
					else if (cv=='6') newValue.append(9);
					else if (cv=='9') newValue.append(6);
					else newValue.append(cv);
				}
				map[i]=newValue.toString();
			}
			
			StringBuilder sb=new StringBuilder();
			for (char c : ch) sb.append(map[c]);
			System.out.println(sb.toString());
		}
	}
}