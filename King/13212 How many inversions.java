import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static long Ans=0;
	
	private static void mergeSort(int [] values, int start, int end) {
		if (start>=end) return;
		else {
			int mid=(start+end)/2;
			mergeSort(values,start,mid);
			mergeSort(values,mid+1,end);
			
			int [] newValues=new int [end-start+1];
			
			int currIdx=0;
			int leftCurr=start;
			int rightCurr=mid+1;
			while (leftCurr<=mid && rightCurr<=end) {
				if (values[leftCurr]<values[rightCurr]) {
					newValues[currIdx++]=values[leftCurr++];
				} else if (values[leftCurr]==values[rightCurr]) {
					newValues[currIdx++]=values[leftCurr++];
					newValues[currIdx++]=values[rightCurr++];
					Ans++;
				} else {
					newValues[currIdx++]=values[rightCurr++];
					Ans+=mid-leftCurr+1;
				}
			}
			while (leftCurr<=mid) newValues[currIdx++]=values[leftCurr++];
			while (rightCurr<=end) newValues[currIdx++]=values[rightCurr++];
			for (int i=0;i<newValues.length;i++) values[start+i]=newValues[i];
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [] values=new int [N];
			for (int n=0;n<N;n++) values[n]=Integer.parseInt(br.readLine());
			Ans=0;
			mergeSort(values,0,values.length-1);
			System.out.println(Ans);
		}
	}
}