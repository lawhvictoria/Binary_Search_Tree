import java.util.LinkedList;

/**
 *
 * @author Victoria
 */
public class LQueue<T> {
    
    private Node front;
    private Node end;
    private int size;
    
    public LQueue()
    {
        front = null;
        end = null;
        size = 0;
    }
    
    private class Node{
        private T item;
        private Node next;
    }
    
    public static class MyException extends RuntimeException
    {
        public MyException()
        {
            super();
        }
        
        public MyException(String s)
        {
            super(s);
        }
    }

    public void enqueue(T item)
    {
        Node oldEnd = end;
        end = new Node();
        end.item = item;
        end.next = null;
        if(isEmpty())
        {
            front = end;
        }
        else
        {
            oldEnd.next = end;
        }
        size++;
    }
    
    public T dequeue()
    {
        if(isEmpty())
        {
            throw new MyException();
        }
        T item = front.item;
        front = front.next;
        size--;
        if(isEmpty())
        {
            end = null;
        }
        return item;
    }
    
    public boolean isEmpty()
    {
        return front == null;
    }
}