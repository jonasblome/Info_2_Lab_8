import java.util.Scanner;

public class Postfix {
  public static void main(String[] args) {
    Postfix p = new Postfix();
    p.evaluate("35+");
    p.evaluate("91-2-32*-1-");
    p.evaluate("12+34-");
    p.evaluate("12*3+");
    p.evaluate("335++");
    p.evaluate("35+");
    
    Scanner scanner = new Scanner( System.in );
    String s = scanner.next();
    
    System.out.println("Input string is:" + s); 
    System.out.println(p.infixToPostfix(s));
  }
  
  public String infixToPostfix (String ifx) { 
    StackAsList<Character> stack = new StackAsList<Character>(); 
    String r = ""; 
    char t = ' '; 
    int i = 0; 
    while(i < ifx.length()) { 
      t = ifx.charAt(i); 
      if (t == '0' || t == '1' || t == '2' || t == '3' || t == '4' || t == '5' || t == '6' || t == '7' || t == '8' || t == '9') {
        r = r + t;
      }
      else if (t == '(') {
        stack.push(t);
      }
      else if (t == ')') {
        while(stack.top() != '(') {
          r = r + stack.top();
          stack.pop();
        }
        stack.pop();
      }

      else if (t == '-' || t == '+' || t == '/' || t == '*' || t == '%' )
      {
        while(precedence(stack.top()) < precedence(t)) { 
          r = r + stack.top(); 
          stack.pop(); 
          if(stack.top() == null) { 
            break; 
          }
        }
        stack.push(t); 
      }

      while(stack.top() != null) { 
        r = r + stack.top();
        stack.pop();
      }
      
      i++;
    }
    return r; 
  } 

  public int precedence(char t) { 
    if(t == '+' || t == '-') {
    return 1;
    }
    if(t == '*' || t == '/' || t == '%') {
    return 2;
    }
    return 0;
  }
  
  public void evaluate (String pfx) {
    StackAsList<Character> stack = new StackAsList<Character>();
    String r = "";
    char t = ' ';
    
    for(int i = 0; i < pfx.length(); i++) {
      t = pfx.charAt(i);

      if(t == '1' || t == '2' || t == '3' || t == '4' || t == '5' || t == '6' || t == '7' || t == '8' || t == '9') {
        stack.push(t);
      }

      if(t == '*' || t == '+' || t == '-' || t=='/') {
        int rhs = Character.getNumericValue(stack.top());
        stack.pop();

        int lhs = Character.getNumericValue(stack.top());
        stack.pop();

        int a = 0;
        if(t == '*') {
          a = lhs * rhs;
        }
        if(t == '+') {
          a = lhs + rhs;
        }
        if(t == '-') {
          a = lhs - rhs;
        }
        if(t == '/') {
          a = lhs / rhs;
        }

        stack.push((char) (a + '0'));
      }
    }
    System.out.println("result is:" + Character.getNumericValue(stack.top()));
  }
}
