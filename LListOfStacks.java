import ch02.stacks.*;
import support.DLLNode;

public class LListOfStacks<T>
{
   private DLLNode<AccessStack<T>> top;
   private DLLNode<AccessStack<T>> bottom;
   
   private final int STACK_CAPACITY = 10;
   private int numberOfStacks;
   
   /**
      This constructor sets up a doubly linked list of stacks with one node,
      containing one empty stack
   */
   public LListOfStacks()
   {
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
      // If the top is full, create a new stack, link it back to the existing top, link 
      // the existing top to it, and make it the top.  
      if (top.getInfo().isFull() )
      {
         DLLNode<AccessStack<T>> newTop = new DLLNode<AccessStack<T>>(new AccessStack<T>(STACK_CAPACITY));
         top.setForward( newTop );
         newTop.setBack( top );
         top = newTop;
         numberOfStacks++;
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
      if ( isEmpty() )
      {
         throw new StackUnderflowException("Pop attempted on empty LListOfStacks");
      }
      else
      {
         top.getInfo().pop();
         // If top is now empty
         if (top.getInfo().isEmpty() )
         {
            // If this is not the only stack, get rid of it.  If there's only one, and it's
            // empty, we'll keep it
            if (top != bottom)
            {
               top.setInfo(null);
               top = top.getBack();
               top.setForward(null);
               numberOfStacks--;
            }
         }
      }
   }
   
   /**
      The top method returns the top element in the SetOfStacks.  Throws StackUnderflowException
      if top stack is empty
      @return The top element of top stack
      @throws StackUnderflowException
   */
   public T top()
   {
      if (top.getInfo().isEmpty() )
      {
         throw new StackUnderflowException("Top attempted on an empty SetOfStacks");
      }
      else
      {
         return top.getInfo().top();
      }
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
      // If there's only one stack, and it's empty
      if ((top == bottom ) && top.getInfo().isEmpty() )
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
   
   /**
      The popAt method removes and returns the element at the given index.  
      @param index The index of the item to pop.
      @return The element at the index
   */
   public T popAt(int index)
   {
      int accumulator = 0;
      int nextAccumulator = 0;
      T output = null;
      DLLNode<AccessStack<T>> current = bottom;
      
      // Loop until accumulator < index < nextAccumulator
      while (accumulator <= index)
      {
         nextAccumulator = accumulator + current.getInfo().size();
         if (nextAccumulator > index)
         {
            output = current.getInfo().popAt( index - accumulator );
            break;
         }
         accumulator = nextAccumulator;
         current = current.getForward();
      }
      
      // Remove empty node if necessary
      if (current.getInfo().isEmpty() )
      {
         // If the bottom stack is empty
         if (current == bottom)
         {
            // And it's not the only stack
            if (bottom != top)
            {
               bottom = bottom.getForward();
               bottom.setBack( null );
            }
         }
         // For stacks other than the bottom
         else
         {
            current.getBack().setForward( current.getForward() );
            if (current != top)
            {
               current.getForward().setBack( current.getBack() );
            }
         }
         
      }
      
      return output;
   }
}