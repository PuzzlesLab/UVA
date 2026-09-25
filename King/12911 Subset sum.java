import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static void makeMap(long [] nums, int n, long currSum, HashMap<Long,BigInteger> map) {
		if (n==nums.length) {
			map.put(currSum,map.getOrDefault(currSum,BigInteger.ZERO).add(BigInteger.ONE));
			return;
		}
		makeMap(nums,n+1,currSum,map);
		makeMap(nums,n+1,currSum+nums[n],map);
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null) {
        	StringTokenizer st=new StringTokenizer(s);
        	int N=Integer.parseInt(st.nextToken());
        	int T=Integer.parseInt(st.nextToken());
        	
        	st=new StringTokenizer(br.readLine());
        	long [] nums1=new long [N/2];
        	long [] nums2=new long [N-nums1.length];
        	for (int n=0;n<nums1.length;n++) nums1[n]=Integer.parseInt(st.nextToken());
        	for (int n=0;n<nums2.length;n++) nums2[n]=Integer.parseInt(st.nextToken());

        	HashMap<Long,BigInteger> map1=new HashMap<>();
        	HashMap<Long,BigInteger> map2=new HashMap<>();
        	makeMap(nums1,0,0,map1);
        	makeMap(nums2,0,0,map2);

        	// T=0, we will get a answer of (empty set 1 + empty set 2). We need to choose at least 1 number, so exclude this.
        	BigInteger ans=(T==0)?new BigInteger("-1"):BigInteger.ZERO;
        	for (long left: map1.keySet()) {
        		long right=T-left;
        		if (map2.containsKey(right)) ans=ans.add(map1.get(left).multiply(map2.get(right)));
        	}
        	System.out.println(ans);
        }
	}

}