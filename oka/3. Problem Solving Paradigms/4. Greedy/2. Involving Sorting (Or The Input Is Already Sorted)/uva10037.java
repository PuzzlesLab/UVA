import java.util.*;
import java.io.*;

public class Main {
	public static StringBuilder sb;
	public static int count(int endIndex,int a[]) {
		if(endIndex<=1) {
			if(endIndex==0) {
				sb.append(a[0]+"\n");
			}else {
				sb.append(a[0]+" "+a[1]+"\n");
			}
			return a[endIndex];
		}else if(endIndex==2){
			sb.append(a[0]+" "+a[1]+"\n");
			sb.append(a[0]+"\n");
			sb.append(a[0]+" "+a[2]+"\n");
			return a[0]+a[1]+a[2];
		}else{
			int first = 2*a[0]+a[endIndex-1]+a[endIndex];
			int second = a[0]+2*a[1]+a[endIndex];
			if(first<second) {
				sb.append(a[0]+" "+a[endIndex]+"\n");
				sb.append(a[0]+"\n");
				sb.append(a[0]+" "+a[endIndex-1]+"\n");
				sb.append(a[0]+"\n");
				return first+count(endIndex-2,a);
			}else {
				sb.append(a[0]+" "+a[1]+"\n");
				sb.append(a[0]+"\n");
				sb.append(a[endIndex-1]+" "+a[endIndex]+"\n");
				sb.append(a[1]+"\n");
				return second+count(endIndex-2,a);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			br.readLine();
			int N = Integer.parseInt(br.readLine());
			int a[] = new int[N];
			for(int j=0;j<N;j++) {
				a[j] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(a);
			sb = new StringBuilder();
			int count = count(N-1,a);
			System.out.println(count);
			System.out.print(sb.toString());
			if(i<T-1)
				System.out.println();
		}
	}

}
