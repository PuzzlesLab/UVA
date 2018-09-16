import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int M=Integer.parseInt(s.trim());
			int [][] point1s=new int [M*M][2];
			int point1sCount=0;
			
			int [][] point3s=new int [M*M][2];
			int point3sCount=0;
			
			for (int i=0;i<M;i++) {
				char [] c=br.readLine().toCharArray();
				for (int i2=0;i2<M;i2++) {
					if (c[i2]=='1') point1s[point1sCount++]=new int [] {i,i2};
					if (c[i2]=='3') point3s[point3sCount++]=new int [] {i,i2};
				}
			}
			
			int max=-1;
			for (int i=0;i<point1sCount;i++) {
				int min=Integer.MAX_VALUE;
				for (int i2=0;i2<point3sCount;i2++) min=Math.min(min, Math.abs(point1s[i][0]-point3s[i2][0])+Math.abs(point1s[i][1]-point3s[i2][1]));
				max=Math.max(max, min);
			}
			
			System.out.println(max);
		}
		
	}

}
