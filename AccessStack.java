import ch02.stacks.ArrayBoundedStack;

public class AccessStack<T> extends ArrayBoundedStack<T>
{
   public AccessStack()
   {
      super();
   }
   
   public AccessStack(int capacity)
   {
      super(capacity);
   }
   
   
   /**
      The popAt method removes and returns the element at the given index.  
      @param index The index of the item to pop.
      @return The element at the index
   */
   public T popAt(int index)
   {
      T toPop = elements[index];
      if (index < topIndex)
      {
         for (int i = index; i < topIndex; i++)
         {
            elements[i] = elements[i + 1];
         }
         elements[topIndex] = null;
         topIndex--;
      }
      else
      {
         pop();
      }
      return toPop;
   }
   
   /**
      The size method returns the number of elements in the stack.  
      @return The number of elements
   */
   public int size()
   {
      return (topIndex + 1);
   }
}