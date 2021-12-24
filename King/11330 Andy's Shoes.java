import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			
			int [] shoes=new int [2*N];
			HashMap<Integer, int []> idxMap=new HashMap<>(); // O(1) lookup on next position
			for (int i=0;i<shoes.length;i++) {
				shoes[i]=Integer.parseInt(st.nextToken());
				if (!idxMap.containsKey(shoes[i])) {
					idxMap.put(shoes[i], new int [2]);
					idxMap.get(shoes[i])[0]=i;
				} else idxMap.get(shoes[i])[1]=i;
			}

			int swap=0;
			for (int i=1;i<shoes.length;i+=2) if (shoes[i-1]!=shoes[i]) {
				swap++;
				
				int [] idx1=idxMap.get(shoes[i-1]);
				int [] idx2=idxMap.get(shoes[i]);
				
				int i2=idx1[1];
				shoes[i2]=shoes[i];
				if (idx2[0]==i) { // Update lookup table
					idx2[0]=i2;
					if (idx2[0]>idx2[1]) {
						int temp=idx2[0];
						idx2[0]=idx2[1];
						idx2[1]=temp;
					}
				} else idx2[1]=i2;
				shoes[i]=shoes[i-1]; // Update lookup table
				idx1[1]=i;
			}
			System.out.println(swap);
		}
	}

}
