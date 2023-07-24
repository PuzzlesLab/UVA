import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=16;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String [] countries=new String [MAX];
		for (int i=0;i<MAX;i++) countries[i]=br.readLine();
		double [][] rate=new double [MAX][MAX];
		for (int i=0;i<MAX;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i2=0;i2<MAX;i2++) rate[i][i2]=Integer.parseInt(st.nextToken())/100.0;
		}
		
		
		HashMap<Integer,Double> [] ansList=new HashMap[MAX];
		for (int i=0;i<MAX;i++) {
			HashMap<Integer,Double> map=new HashMap<>();
			map.put(i,1.0);
			ansList[i]=map;
		}

		for (int lvl=1;lvl<=4;lvl++) {
			HashMap<Integer,Double> [] ansList2=new HashMap[ansList.length>>1];
			int ansList2Count=0;
			
			for (int i=0;i<ansList.length;i+=2) {
				HashMap<Integer,Double> map1=ansList[i];
				HashMap<Integer,Double> map2=ansList[i+1];

				HashMap<Integer,Double> newMap=new HashMap<>();
				for (int c1: map1.keySet()) {
					double curr=0.0;
					for (int c2: map2.keySet()) curr+=rate[c1][c2]*map2.get(c2);
					newMap.put(c1,curr*map1.get(c1));
				}
				for (int c2: map2.keySet()) {
					double curr=0.0;
					for (int c1: map1.keySet()) curr+=rate[c2][c1]*map1.get(c1);
					newMap.put(c2,curr*map2.get(c2));
				}
				
				ansList2[ansList2Count++]=newMap;
			}
			ansList=ansList2;
		}
		
		HashMap<Integer,Double> ans=ansList[0];
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<MAX;i++) {
			sb.append(countries[i]);
			for (int len=countries[i].length();len<10;len++) sb.append(' ');
			sb.append(' ');
			sb.append("p=");
			sb.append(String.format("%.2f",ans.get(i)*100.0));
			sb.append("%\n");
		}
		System.out.print(sb.toString());
	}

}