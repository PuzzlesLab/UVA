import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {

	private static class Data implements Comparable<Data> {
		char c;
		int count, cost;
		
		public Data(char c) {
			this.c=c;
		}
		
		public int compareTo(Data d) {
			return d.count-this.count;
		}
	}

	public static void main(String[] args) throws Exception {
		int [] effort=new int [36]; // Effort list.
		int eIdx=0;
		for (int i=1;i<=6;i++) for (int i2=1;i2<=i;i2++) effort[eIdx++]=i+1;
		int temp=8;
		for (int i=5;i>=1;i--) {
			for (int i2=1;i2<=i;i2++) effort[eIdx++]=temp;
			temp++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			Data [] map=new Data [128];
			char [] ch=br.readLine().toCharArray();
			ArrayList<Data> dataList=new ArrayList<>();
			for (int i=0;i<ch.length;i++) {
				char c=ch[i];
				if (c==' ') continue;
				if (map[c]==null) {
					map[c]=new Data(c);
					dataList.add(map[c]);
				}
				map[c].count++;
			}
			
			Collections.sort(dataList); // Order in most used character first.
			int cEIdx=0;
			for (int i=0;i<dataList.size();i++) dataList.get(i).cost=effort[cEIdx++]; // Assign effort from low to high.

			int ans=0;
			for (int i=0;i<ch.length;i++) {
				char c=ch[i];
				if (c==' ') continue;
				ans+=map[c].cost;
			}
			System.out.println(ans);
		}
	}

}
