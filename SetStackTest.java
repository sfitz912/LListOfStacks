import java.util.Random;

public class SetStackTest
{
   public static void main(String[] args)
   {
      Random random = new Random();
      int number;
      
      LListOfStacks<Integer> stuff = new LListOfStacks<Integer>();
      
      // add 30 random numbers
      for (int i = 0; i < 30; i++)
      {
         number = random.nextInt(100);
         System.out.println("Adding " + number + " to stuff...");
         stuff.push(number);
         System.out.println("Number of stacks: " + stuff.numStacks() );
         System.out.println("Calling top: " + stuff.top() );
      }
      
      // try to pop 31 items
      for (int i = 0; i < 31; i++)
      {
         try
         {
            System.out.println("Number of stacks: " + stuff.numStacks() );
            System.out.println("Top: " + stuff.top() );
            System.out.println("Calling pop");
            stuff.pop();
         }
         catch (Exception e)
         {
            System.out.println("Error: " + e.getMessage() );
         }
      }
      
      // now add 0-15
      for (int i = 0; i < 16; i++)
      {
         stuff.push( i );
      }
      
      // trying popAt
      System.out.println("popAt(11): " + stuff.popAt(11) );
      
      // popAt 3 - 8
      for (int i = 3; i <= 8; i++)
      {
         System.out.println("popAt(" + i + "): " + stuff.popAt(i) );
      }
      
      // pop the rest from the bottom
      while( !stuff.isEmpty() )
      {
         System.out.println("popAt(0)" + stuff.popAt(0) );
      }
      
      // Now add 30 things
      for (int i = 0; i < 30; i++)
      {
         stuff.push(i);
      }
      
      // Now popAt(10) 11 times
      for (int i = 0; i < 11; i++)
      {
         System.out.println("popAt(10): " + stuff.popAt(10) );
      }
   }
}