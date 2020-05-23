import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int [] count=new int [6];
			for (int i=0;i<count.length;i++) count[i]=Integer.parseInt(st.nextToken());
			
			int parcel=0;
			//6x6
			parcel+=count[5];
			count[5]=0;
			
			//5x5
			while (count[4]>0) {
				parcel++;
				count[0]-=Math.min(count[0],11); //Fill 1x1
				count[4]--;
			}
			
			//4x4
			while (count[3]>0) {
				parcel++;
				int fill2x2=Math.min(count[1],5);
				count[1]-=fill2x2; //Fill 2x2
				if (fill2x2<5) count[0]-=Math.min(count[0],20-4*fill2x2); //Fill 1x1 
				count[3]--;
			}
			
			//3x3
			while (count[2]>0) {
				parcel++;
				int remSize=36;
				int fill3x3=Math.min(count[2],4);
				remSize-=fill3x3*9;
				if (fill3x3==1) {
					int fill2x2=Math.min(count[1],5);
					count[1]-=fill2x2;
					remSize-=4*fill2x2;
					count[0]-=Math.min(count[0],remSize);
				} else if (fill3x3==2) {
					int fill2x2=Math.min(count[1],3);
					count[1]-=fill2x2;
					remSize-=4*fill2x2;
					count[0]-=Math.min(count[0],remSize);
				} else if (fill3x3==3) {
					if (count[1]>0) {
						count[1]--;
						count[0]-=Math.min(count[0],5);
					}
				}
				count[2]-=fill3x3;
			}
			
			//2x2
			while (count[1]>0) {
				parcel++;
				int fill2x2=Math.min(count[1],9);
				count[1]-=fill2x2;
				int rem=36-fill2x2*4;
				if (rem>0) count[0]-=Math.min(count[0],rem);
			}
			
			//1x1
			while (count[0]>0) {
				parcel++;
				count[0]-=Math.min(count[0],36);
			}
			
			System.out.println(parcel);
		}
	}

}