import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			br.readLine();
			int N = Integer.parseInt(br.readLine());
			int start = 2;
			while(true) {
				if(N<start)
					break;
				N -= start;
				start++;
			}
			start--; // last position
			int s[] = new int[start-1];
			for(int j=0;j<s.length;j++) {
				s[j] = j+2;
			}
			while(N>0) {
				for(int j=s.length-1;j>=0;j--) {
					if(N>0) {
						s[j]++;
						N--;
					}else {
						break;
					}
				}
			}
			for(int j=0;j<s.length;j++) {
				if(j>0)
					System.out.print(" ");
				System.out.print(s[j]);
			}
			System.out.println();
			if(i<T-1) {
				System.out.println();
			}
		}
	}

}
