import ch02.stacks.*;

public class ArrayOfStacks<T>
{
   private ArrayBoundedStack<ArrayBoundedStack<T>> stacks;
   private final int STACK_CAPACITY = 10;
   private final int DEFAULT_TOTAL_CAPACITY = 1000;
   private int numberOfStacks;
   
   /**
      This no-arg constructor uses the default settings of up to 100 stacks
      of size 10 each.
   */
   public ArrayOfStacks()
   {
      this.stacks = new ArrayBoundedStack<ArrayBoundedStack<T>>(DEFAULT_TOTAL_CAPACITY / STACK_CAPACITY);
      stacks.push( new ArrayBoundedStack<T>( STACK_CAPACITY ) );
      numberOfStacks = 1;
   }
   
   /**
      The push method places element at the top of the top stack if 
      possible, or throws StackOverflowException if the top stack is full.  
      @param element The element to add to stack
      @throws StackOverflowException if top stack is full
   */
   public void push(T element)
   {
      if (stacks.top().isFull() )
      {
         if (stacks.isFull() )
         {
            throw new StackOverflowException("Push attempted on full SetOfStacks");
         }
         else
         {
            stacks.push( new ArrayBoundedStack<T>( STACK_CAPACITY ) );
            numberOfStacks++;
         }
      }
      
      stacks.top().push( element );
      
   }
   
   /**
      The pop method removes the top element from the SetOfStacksArray. Throws
      StackUnderflowException if empty.  
      @throws StackUnderflowException
   */
   public void pop()
   {
      if (stacks.top().isEmpty() )
      {
         throw new StackUnderflowException("Pop attempted on an empty SetOfStacksArray");
      }
      else
      {
         stacks.top().pop();                 // pop top item from top stack
         
         // If top stack is now empty, pop the stack
         if (stacks.top().isEmpty() )
         {
            stacks.pop();
            numberOfStacks--;
         }
      }
   }
   
   /**
      The top method returns the top element from the SetOfStacksArray.  Throws
      StackUnderflowException if empty.  
      @return The top element
      @throws StackUnderflowException
   */
   public T top()
   {
      if (stacks.top().isEmpty() )
      {
         throw new StackUnderflowException ("Top attempted on an empty SetOfStacksArray");
      }
      else
      {
         return stacks.top().top();
      }
   }
   
   
   /**
      The isEmpty method returns true if the SetOfStacksArray is empty, false
      otherwise.
      @return True if empty, false otherwise
   */
   public boolean isEmpty()
   {
      if (numberOfStacks == 1 && stacks.top().isEmpty() )
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
      The isFull method returns true if the SetOfStacksArray is full, 
      false otherwise.
      @return True if the SetOfStacksArray is full, false otherwise
   */
   public boolean isFull()
   {
      return (stacks.isFull() && stacks.top().isFull() );
   }
   
   
   /**
      The numStacks method returns the number of stacks.  An empty 
      SetOfStacksArray will always have one empty ArrayBoundedStack.
      @return The number of stacks
   */
   public int numStacks()
   {
      return numberOfStacks;
   }



}