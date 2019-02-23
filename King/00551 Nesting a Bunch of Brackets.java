import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			char [] ch=s.toCharArray();
			boolean [] left=new boolean [128];
			left['(']=true; left['[']=true; left['{']=true; left['<']=true;
			
			char [] right=new char [128];
			right[')']='(';
			right[']']='[';
			right['}']='{';
			right['>']='<';
			
			int ans=0;
			int currPos=1;
			Stack<String> stk=new Stack<>();
			for (int i=0;i<ch.length;i++) {
				char c=ch[i];
				if (left[c]) {
					if (c!='(') stk.push(String.valueOf(c));
					else if (i<ch.length-1 && ch[i+1]=='*') {
						stk.push("(*");
						i++;
					}
					else stk.push("(");
				} else if (right[c]!=0) {
					if (stk.size()>0 && String.valueOf(right[c]).equals(stk.peek())) stk.pop();
					else {
						ans=currPos;
						break;
					}
				} else if (c=='*' && i<ch.length-1 &&  ch[i+1]==')') {
					i++;
					if (stk.size()>0 && stk.peek().equals("(*")) stk.pop();
					else {
						ans=currPos;
						break;
					}
				}
				currPos++;
			}
			
			if (stk.size()>0 && ans==0) ans=currPos;
			if (ans==0) System.out.println("YES");
			else System.out.println("NO "+ans);
		}
	}

}
