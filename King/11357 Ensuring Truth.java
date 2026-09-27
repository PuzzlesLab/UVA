import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	String s=br.readLine();
        	ArrayList<String> clauses=new ArrayList<>();
        	
        	StringBuilder sb=new StringBuilder();
        	for (int i=0;i<s.length();i++) {
        		char c=s.charAt(i);
        		if (c=='|') {
        			clauses.add(sb.toString());
        			sb.setLength(0);
        		} else if (c!='(' && c!=')') sb.append(c);
        	}
        	if (sb.length()>0) clauses.add(sb.toString());
        	
        	boolean ans=false;
        	for (int ci=0;ci<clauses.size() && !ans;ci++) {
        		String clause=clauses.get(ci);
        		HashSet<Character> pos=new HashSet<>();
        		HashSet<Character> neg=new HashSet<>();
        		
        		char lastC='!';
        		boolean isNeg=false;
            	for (int i=0;i<clause.length();i++) {
            		char c=clause.charAt(i);
            		if (c=='&') {
            			if (isNeg) neg.add(lastC);
            			else pos.add(lastC);

            			lastC='!';
            			isNeg=false;
            		} else if (c=='~') isNeg=true;
            		else lastC=c;
            	}
            	if (lastC!='!') {
        			if (isNeg) neg.add(lastC);
        			else pos.add(lastC);
            	}
            	
            	boolean conflict=false;
            	for (char c: pos) if (neg.contains(c)) conflict=true;
            	for (char c: neg) if (pos.contains(c)) conflict=true;
            	
            	if (!conflict) ans=true;
        	}
        	
        	System.out.println(ans?"YES":"NO");
        }
	}

}