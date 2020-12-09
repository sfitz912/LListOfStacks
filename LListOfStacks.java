import ch02.stacks.*;
import support.DLLNode;

public class LListOfStacks<T>
{
   private LinkedStack<ArrayBoundedStack<T>> stacks;
   private final int STACK_CAPACITY = 10;
   private int numberOfStacks;
   private DLLNode<AccessStack<T>> top;
   private DLLNode<AccessStack<T>> bottom;
   
   public LListOfStacks()
   {
      /*
      this.stacks = new LinkedStack<ArrayBoundedStack<T>>();
      stacks.push( new ArrayBoundedStack<T>( STACK_CAPACITY ) );
      */
      bottom = new DLLNode<AccessStack<T>>( new AccessStack<T>(STACK_CAPACITY) );
      top = bottom;
      numberOfStacks = 1;
   }
   
   /**
      The push method places element at the top of the top stack, 
      adds another stack if necessary.    
      @param element The element to add to stack
   */
   public void push(T element)
   {
      /*
      if (stacks.top().isFull() )
      {
         stacks.push( new ArrayBoundedStack<T>( STACK_CAPACITY ) );
         numberOfStacks++;
      }
      
      stacks.top().push( element );
      */
      if (top.getInfo().isFull() )
      {
         DLLNode<AccessStack<T>> newTop = new DLLNode<AccessStack<T>>(new AccessStack<T>(STACK_CAPACITY));
         top.setForward( newTop );
         top = newTop;
      }
      top.getInfo().push(element);
   }
   
   /**
      The pop method throws StackUnderflowException if the SetOfStacks is empty,
      otherwise it removes the top element of the top stack.
      @throws StackUnderflowException
   */
   public void pop()
   {
      if (top.getInfo().isEmpty() )
      {
         throw new StackUnderflowException("Pop attempted on empty LListOfStacks");
      }
      else
      {
         top.getInfo().pop();
         // If top is now empty
         if (top.getInfo().isEmpty() )
         {
            top.setInfo(null);
            top = top.getBack();
            top.setForward(null);
            numberOfStacks--;
         }
      }
      /*
      if (stacks.top().isEmpty() )
      {
         throw new StackUnderflowException("Pop attempted on empty SetOfStacks");
      }
      else
      {
         stacks.top().pop();     // Remove top element of top stack
         
         // If top stack is now empty, pop it, but keep one stack at all times
         if (stacks.top().isEmpty() && (numberOfStacks > 1) )
         {
            stacks.pop();
            numberOfStacks--;
         }
      }
      */
   }
   
   /**
      The top method returns the top element in the SetOfStacks.  Throws StackUnderflowException
      if top stack is empty
      @return The top element of top stack
      @throws StackUnderflowException
   */
   public T top()
   {
      /*
      if (stacks.top().isEmpty() )
      {
         throw new StackUnderflowException("Top attempted on an empty SetOfStacks");
      }
      else
      {
         return stacks.top().top();
      }
      */
   }
   
   /**
      The isFull method returns false, because stacks is never full.
      @return false
   */
   public boolean isFull()
   {
      return false;
   }
   
   /**
      The isEmpty method returns true if the SetOfStacks is empty, false
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
      The numStacks method returns the number of stacks.  An empty SetOfStacks
      will always have one empty ArrayBoundedStack
      @return The number of stacks
   */
   public int numStacks()
   {
      return numberOfStacks;
   }
}