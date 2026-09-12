import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static final int SALE_PRICE=10;

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).trim().equals("0")) {
        	int W=Integer.parseInt(s.trim());
        	
        	int totalMax=0;
        	HashSet<Integer> ansSet=new HashSet<>();
        	ansSet.add(0);
        	for (int w=0;w<W;w++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());

        		int [] piles=new int [Integer.parseInt(st.nextToken())];
        		for (int i=0;i<piles.length;i++) piles[i]=SALE_PRICE-Integer.parseInt(st.nextToken());

        		ArrayList<Integer> buys=new ArrayList<>();
        		int currMax=0;
        		int culSum=0;
        		for (int i=0;i<piles.length;i++) {
        			culSum+=piles[i];
        			if (culSum>currMax) {
        				currMax=culSum;
        				buys.clear();
        				buys.add(i+1);
        			} else if (culSum==currMax) {
        				buys.add(i+1);
        			}
        		}
        		totalMax+=currMax;
        		if (currMax==0) buys.add(0);

        		HashSet<Integer> nextAns=new HashSet<>();
        		for (int currBuy: ansSet) for (int i=0;i<buys.size();i++) nextAns.add(buys.get(i)+currBuy);
        		ansSet=nextAns;
        	}

        	ArrayList<Integer> ans=new ArrayList<>(ansSet);
        	Collections.sort(ans);

        	StringBuilder sb=new StringBuilder();
        	if (tc>1) sb.append('\n');
        	sb.append("Workyards ");
        	sb.append(tc++);
        	sb.append("\nMaximum profit is ");
        	sb.append(totalMax);
        	sb.append(".\nNumber of pruls to buy: ");
        	for (int i=0;i<Math.min(ans.size(),10);i++) {
        		sb.append(ans.get(i));
        		sb.append(' ');
        	}
        	sb.setLength(sb.length()-1);
        	System.out.println(sb);
        }
	}

}