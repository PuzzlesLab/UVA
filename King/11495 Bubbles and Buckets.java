import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int Ans=0;
	
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
					Ans=(Ans+1)%2;
				} else {
					newValues[currIdx++]=values[rightCurr++];
					Ans=(Ans+(mid-leftCurr+1))%2;
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
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int [] values=new int [N];
			for (int n=0;n<N;n++) values[n]=Integer.parseInt(st.nextToken());
			
			Ans=0;
			mergeSort(values,0,values.length-1);
			System.out.println((Ans==0) ? "Carlos": "Marcelo");
		}
	}
}