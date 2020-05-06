import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int Y=Integer.parseInt(s);
			int P=Integer.parseInt(br.readLine());
			int [] popes=new int [P];
			for (int p=0;p<P;p++) popes[p]=Integer.parseInt(br.readLine());
			int A=-1, B=-1, C=-1;
			for (int p=0;p<popes.length;p++) {
				int target=popes[p]+Y-1;
				int minIdx=p;
				int maxIdx=popes.length-1;
				int targetIdx=p;
				while (minIdx<maxIdx) {
					//If (p to max index) size less than current max, skip.
					if (maxIdx-p+1<A) {
						targetIdx=p;
						break;
					}
					
					//Do binary search
					int mid=(minIdx+maxIdx)/2;

					if (target==popes[mid]) {
						while (mid+1<popes.length && target==popes[mid+1]) mid++;
						targetIdx=mid;
						break;
					}
					else if (popes[mid]<target) {
						if (mid+1<=maxIdx && popes[mid+1]<=target) {
							minIdx=mid+1;
							targetIdx=minIdx;
						} else {
							targetIdx=mid;
							break;
						}
					} else {
						maxIdx=mid-1;
						targetIdx=maxIdx;
					}
				}

				int size=targetIdx-p+1;
				if (size>A) {
					A=size;
					B=popes[p];
					C=popes[targetIdx];
				}
			}
			
			System.out.printf("%d %d %d\n",A,B,C);
			br.readLine(); //empty
		}

	}

}