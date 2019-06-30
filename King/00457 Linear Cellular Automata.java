import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		char [] print= {' ','.','x','W'};
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int [] dna=new int [10];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<10;i++) dna[i]=Integer.parseInt(st.nextToken());

			int [][] map=new int [50][40];
			for (int i=0;i<10;i++) map[0][19]=1;
			for (int day=1;day<50;day++) {
				int [] newMap=new int [map[day].length];
				for (int i=0;i<map[day].length;i++) {
					newMap[i]=map[day-1][i];
					if (i>0) newMap[i]+=map[day-1][i-1];
					if (i<map[day].length-1) newMap[i]+=map[day-1][i+1];
					newMap[i]=dna[newMap[i]];
				}
				map[day]=newMap;
			}
			
			if (testCase>0) System.out.println();
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<map.length;i++) {
				for (int i2=0;i2<map[0].length;i2++) sb.append(print[map[i][i2]]);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}