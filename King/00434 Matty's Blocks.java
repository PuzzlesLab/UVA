import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int size=Integer.parseInt(br.readLine());
			
			Integer [] front=new Integer [size];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<size;i++) front[i]=Integer.parseInt(st.nextToken());
			
			Integer [] right=new Integer [size];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<size;i++) right[i]=Integer.parseInt(st.nextToken());
			
			Arrays.sort(front, Collections.reverseOrder()); Arrays.sort(right, Collections.reverseOrder());
			int min=0, fi=0, ri=0;
			while (fi<size && ri<size) {
				if (front[fi]==right[ri]) {
					min+=front[fi++]; ri++;;
				}
				else if (right[ri]>front[fi]) min+=right[ri++];
				else min+=front[fi++];
			}
			if (fi<size) while (fi<size) min+=front[fi++];
			else if (ri<size) while (ri<size) min+=right[ri++];
			
			int max=0;
			for (int h=0;h<size;h++) for (int l=0;l<size;l++) max+=Math.min(front[h], right[l]);
			max-=min;
			System.out.printf("Matty needs at least %d blocks, and can add at most %d extra blocks.\n", min, max);
		}
	}

}
