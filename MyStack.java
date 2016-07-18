import java.util.EmptyStackException;

/**
 *
 * @author Victoria
 * vhlaw@calpoly.edu
 * January 21, 2016
 * Project 1
 */
public class MyStack<T> {
    private Node<T> front;
    
    private class Node<T>{
        private T item;
        private Node<T> next;
        
        public Node(T item)
        {
            this.item = item;
        }
    }
    
    public MyStack()
    {
        front = null;
    }

    public static class MyException extends EmptyStackException
    {
        public MyException()
        {
            super();
        }
    }
    
    public void push(T item)
    {
        Node<T> n = new Node<T>(item);
        n.next = front;
        front = n;
    }
    
    public T pop()
    {
        if(isEmpty())
        {
            throw new MyException();
        }
        T old = front.item;
        front = front.next;
        return old;
    }
    
    public T peek()
    {
        if(isEmpty())
        {
            throw new MyException();
        }
        else
        {
            return front.item;
        }        
    }
    
    public boolean isEmpty()
    {
        return front == null;
    }
}