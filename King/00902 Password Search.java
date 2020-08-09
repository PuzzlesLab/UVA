import java.util.HashMap;
import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N=sc.nextInt();
			HashMap<String, Integer> map=new HashMap<>();
			String encrypted=sc.next();
			
			String maxSubstr=null;
			int maxSubstrLen=0;
			for (int i=0;i<encrypted.length()-N+1;i++) {
				String substr=encrypted.substring(i,i+N);
				map.put(substr,map.getOrDefault(substr,0)+1);
				if (map.get(substr)>=maxSubstrLen) {
					maxSubstrLen=map.get(substr);
					maxSubstr=substr;
				}
			}
			System.out.println(maxSubstr);
		}
	}

}