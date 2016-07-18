import java.util.Iterator;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<? super T>> {

	private class BSTNode
	{
		T element;
		BSTNode left;
		BSTNode right;
		
		public BSTNode(T element)
		{
			this.element = element;
		}
	}
	
	private BSTNode root;
	
	private class PreIter implements Iterator<T>
	{
		public MyStack<BSTNode> stack;
		
		public PreIter()
		{
			stack = new MyStack<BSTNode>();
			if(root != null)
			{
				stack.push(root);
			}
		}
		
		public boolean hasNext()
		{
			return !stack.isEmpty();
		}
		
		public T next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			
			BSTNode node = stack.pop();
			
			if(node.right != null)
			{
				stack.push(node.right);
			}
			if(node.left != null)
			{
				stack.push(node.left);
			}
			return node.element;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	private class InIter implements Iterator<T>
	{
		public MyStack<BSTNode> stack;
		
		public InIter()
		{
			stack = new MyStack<BSTNode>();
			if(root != null)
			{
				stack.push(root);
				BSTNode node = root;
				while(node.left != null)
				{
					stack.push(node.left);
					node = node.left;
				}
			}
		}
		
		public boolean hasNext()
		{
			return !stack.isEmpty();
		}
		
		public T next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			
			BSTNode node = stack.pop();
			T res = node.element;
			
			if(node.right != null)
			{
				stack.push(node.right);
				node = node.right;
				while(node.left != null)
				{
					stack.push(node.left);
					node = node.left;
				}
			}
			
			return res;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	private class LevelIter implements Iterator<T>
	{	
		public LQueue<BSTNode> queue;
		
		public LevelIter()
		{
			queue = new LQueue<BSTNode>();
			if(root != null)
			{
				queue.enqueue(root);
			}
		}
		
		public boolean hasNext()
		{
			return !queue.isEmpty();
		}
		
		public T next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			
			BSTNode node = queue.dequeue();
			if(node.left != null)
			{
				queue.enqueue(node.left);
			}
			if(node.right != null)
			{
				queue.enqueue(node.right);
			}
			return node.element;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public static class MyException extends RuntimeException //this is to prevent deleteMin from crashing. 
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
	
	public BST()
	{
		root = null;
	}
	
	public void insert(T item)
	{
		root = insert(item, root);
	}
	
	private BSTNode insert(T item, BSTNode subtree)
	{
		if(subtree == null)
		{
			return new BSTNode(item);
		}
		if(item.compareTo(subtree.element) == 0)
		{
			return subtree;
		}
		if(item.compareTo(subtree.element) < 0)
		{
			subtree.left = insert(item, subtree.left);
		}
		else
		{
			subtree.right = insert(item, subtree.right);
		}
		return subtree;
	}
	
	public void delete(T item)
	{
		root = delete(item, root);
	}
	
	private BSTNode delete(T item, BSTNode subtree)
	{
		if(subtree != null)
		{
			int comp = item.compareTo(subtree.element);
			if(comp == 0)
			{
				subtree = deleteRoot(subtree);
			}
			else if(comp < 0)
			{
				subtree.left = delete(item, subtree.left);
			}
			else
			{
				subtree.right = delete(item, subtree.right);
			}
		}
		return subtree;
	}
	
	private BSTNode deleteRoot(BSTNode subtree)
	{
		BSTNode newRoot;
		if(subtree.left != null && subtree.right != null)
		{
			T next = next(subtree);
			subtree.element = next;
			subtree.right = delete(subtree.element, subtree.right);
			newRoot = subtree;
		}

		else
		{
			if(subtree.left != null)
			{
				newRoot = subtree.left;
			}
			else
			{
				if(subtree.right != null)
				{
					newRoot = subtree.right;
				}
				else
				{
					newRoot = null;
				}
			}
		}
		return newRoot;
	}

	private T next(BSTNode t)
	{
		BSTNode temp = t.right;
		while(temp.left != null)
		{
			temp = temp.left;
		}
		return temp.element;
	}
	
	public boolean find(T item)
	{
		return find(item, root);
	}
	
	private boolean find(T item, BSTNode subtree)
	{
		if(subtree == null)
		{
			return false;
		}
		int comp = item.compareTo(subtree.element);
		if(comp == 0)
		{
			return true;
		}
		if(comp < 0)
		{
			return find(item, subtree.left);
		}
		else
		{
			return find(item, subtree.right);
		}
	}
	
	public boolean isEmpty()
	{
		return root == null;
	}
	
	public void makeEmpty()
	{
		root = null;
	}
	
	public int size()
	{
		return size(root);
	}
	
	private int size(BSTNode x) //helper function for size
	{
		if(x == null) //if tree is empty, return 0.
		{
			return 0;
		}
		
		return size(x.left) + 1 + size(x.right);
	}
	
	public T findMinimum()
	{
		BSTNode node = root;
		if(root == null)
		{
			throw new MyException();
		}
		while(node.left != null)
		{
			node = node.left;
		}
		return node.element;
	}
	
	public T findMaximum()
	{
		BSTNode curr = root;
		if(root == null)
		{
			throw new MyException("The tree is empty.");
		}
		while(curr.right != null)
		{
			curr = curr.right;
		}

		return curr.element;
	}
	
	public Iterator<T> iteratorPre()
	{
		return new PreIter();
	}
	
	public Iterator<T> iteratorIn()
	{
		return new InIter();
	}
	
	public Iterator<T> iteratorLevel()
	{
		return new LevelIter();
	}
	
	public void printTree()
	{
		printTree(root, 0);
	}
	
	private void printTree(BSTNode t, int space)
	{
		String indent = "  ";
		
		for (int level = 0; level < space; level++)
        {
            System.out.print(indent);
        }
		
        System.out.println(t.element);
        
        if (t.left != null)
        {
            printTree(t.left, space + 1);
        }
        if (t.right != null)
        {
            printTree(t.right, space + 1);
        }
	}
	
	public String toString()
	{
		return toString(root);
	}
	
	private String toString(BSTNode t)
	{
		String output = "";
		if(t == null)
		{
			return "";
		}

		output += t.element + " ";
		if(t.left != null)
		{
			output += toString(t.left);
		}
		if(t.right != null)
		{
			output += toString(t.right);
		}
		return output;
	}
}
