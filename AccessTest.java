public class AccessTest
{
   public static void main(String[] args)
   {
      AccessStack<Integer> stack = new AccessStack<Integer>(10);
      stack.push(1);
      stack.push(2);
      stack.push(3);
      System.out.println(stack.top() );
      System.out.println( stack.popAt(2));
      System.out.println( stack.popAt(2));
   }
}