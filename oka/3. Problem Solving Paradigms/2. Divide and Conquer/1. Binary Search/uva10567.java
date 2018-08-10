import java.util.*;
import java.io.*;

public class Main {
	public static int convert(char s) {
		if(s-'A'>=0&&s-'A'<=25)
			return s-'A'+26;
		else
			return s-'a';
	}
	public static int binarySearch(ArrayList<Integer>arr,int target) {
		int start = 0;
		int end = arr.size()-1;
		while(start<=end) {
			int mid = (start+end)/2;
			if(arr.get(mid)>target) {
				if(mid==0||arr.get(mid-1)<=target)
					return arr.get(mid);
				else
					end = mid-1;
			}else
				start = mid+1;
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int T = Integer.parseInt(br.readLine());
		Map<Integer,ArrayList<Integer>> m = new HashMap<>();
		for(int i=0;i<s.length();i++) {
			int cur = convert(s.charAt(i));
			if(m.containsKey(cur)) {
				m.get(cur).add(i);
			}else {
				ArrayList<Integer> arr = new ArrayList<>();
				arr.add(i);
				m.put(cur,arr);
			}
		}		
		for(int i=0;i<T;i++) {
			String n = br.readLine();
			int num[] = new int[52];
			int index = -1;
			boolean found = true;
			int start = -1;
			int end = -1;
			for(int j=0;j<n.length();j++) {
				int cur = convert(n.charAt(j));
				if(!m.containsKey(cur)) {
					found = false;
					break;
				}
				index = binarySearch(m.get(cur),index);
				if(index==-1) {
					found = false;
					break;
				}
				end = index;
				if(j==0)
					start = index;
			}
			if(found) {
				System.out.println("Matched "+start+" "+end);
			}else {
				System.out.println("Not matched");
			}
		}
	}
}
