import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int [][] data=new int [Integer.parseInt(s)][Integer.parseInt(br.readLine())];
			for (int i=0;i<data.length;i++) {
				char [] c=br.readLine().toCharArray();
				for (int i2=0;i2<data[i].length;i2++) data[i][i2]=c[i2]-'0';
			}
			
			int [][] sum=new int [data.length][data[0].length];
			for (int i=0;i<data.length;i++) for (int i2=0;i2<data[i].length;i2++) {
				sum[i][i2]=data[i][i2];
				if (i>0) sum[i][i2]+=sum[i-1][i2];
				if (i2>0) sum[i][i2]+=sum[i][i2-1];
				if (i>0 && i2>0) sum[i][i2]-=sum[i-1][i2-1];
			}
			
			int count=0;
			for (int x1=0;x1<data.length;x1++) for (int y1=0;y1<data[x1].length;y1++) for (int x2=x1;x2<data.length;x2++) for (int y2=y1;y2<data[x2].length;y2++) {
				int area=(x2-x1+1)*(y2-y1+1);
				int areaSum=sum[x2][y2];
				if (x1>0) areaSum-=sum[x1-1][y2];
				if (y1>0) areaSum-=sum[x2][y1-1];
				if (x1>0 && y1>0) areaSum+=sum[x1-1][y1-1];

				if (area==areaSum) count++;
			}
			
			System.out.println(count);
		}
	}

}
