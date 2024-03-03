package uva;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

class Main {

	private static int calc(String s, int st) {
		ArrayList<Integer> curr=new ArrayList<>();
		for (int i=0;i<s.length();i++) {
			int n=st+s.charAt(i)-'A';
			LinkedList<Integer> temp=new LinkedList<>();
			while (n>0) {
				temp.addFirst(n%10);
				n/=10;
			}
			while (!temp.isEmpty()) curr.add(temp.removeFirst());
		}

		while (curr.size()>2) {
			if (curr.size()==3 && curr.get(0)==1 && curr.get(1)==0 && curr.get(2)==0) break;
			ArrayList<Integer> next=new ArrayList<>();
			for (int i=0;i<curr.size()-1;i++) next.add((curr.get(i)+curr.get(i+1))%10);
			curr=next;
		}

		int ans=0;
		for (int i=0;i<curr.size();i++) ans=ans*10+curr.get(i);
		return ans;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while ((s=br.readLine())!=null) {
			int ans=-1;
			for (int i=1;i<=10000;i++) if (calc(s,i)==100) {
				ans=i;
				break;
			}
			sb.append(ans==-1?":(":ans);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}