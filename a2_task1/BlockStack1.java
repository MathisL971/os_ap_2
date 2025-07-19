/**
 * Class BlockStack
 * Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $
 * $Last Revision Date: 2019/02/02 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca;
 * Inspired by an earlier code by Prof. D. Probst

 */
class BlockStack
{
	/**
	 * # of letters in the English alphabet + 2
	 */
	public static final int MAX_SIZE = 28;

	/**
	 * Default stack size
	 */
	public static final int DEFAULT_SIZE = 6;

	/**
	 * Current size of the stack
	 */
	public int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 */
	public int iTop  = 3;

	/**
	 * stack[0:5] with four defined values
	 */
	public char acStack[] = new char[] {'a', 'b', 'c', 'd', '$', '$'};

	/**
	 * Stack access counter
	 */
	private int stackAccessCounter = 0;

	/**
	 * Default constructor
	 */
	public BlockStack()
	{
	}

	/**
	 * Supplied size
	 */
	public BlockStack(final int piSize)
	{
		if(piSize != DEFAULT_SIZE)
		{
			this.acStack = new char[piSize];

			// Fill in with letters of the alphabet and keep
			// 2 free blocks
			for(int i = 0; i < piSize - 2; i++)
				this.acStack[i] = (char)('a' + i);

			this.acStack[piSize - 2] = this.acStack[piSize - 1] = '$';

			this.iTop = piSize - 3;
			this.iSize = piSize;
		}
	}

	/**
	 * Picks a value from the top without modifying the stack
	 * @return top element of the stack, char
	 */
	public char pick()
	{
		stackAccessCounter++;
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 * @return the element, char
	 */
	public char getAt(final int piPosition)
	{
		stackAccessCounter++;
		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 */
	public void push(final char pcBlock)
	{
		this.acStack[++this.iTop] = pcBlock;
		stackAccessCounter++;
		System.out.println("Successfully pushed to the block stack");
		System.out.println(this);
	}

	/**
	 * Standard pop operation
	 * @return ex-top element of the stack, char
	 */
	public char pop()
	{
		char cBlock = this.acStack[this.iTop];
		this.acStack[this.iTop--] = '$'; // Leave prev. value undefined
		stackAccessCounter++;
		System.out.println("Successfully popped from the block stack");
		System.out.println(this);
		return cBlock;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (char c : acStack) {
			sb.append(c == '$' ? '*' : c);
			sb.append(" ");
		}
		return sb.toString();
	}

	public int getITop()
	{
		return iTop;
	}

	public int getISize()
	{
		return iSize;
	}

	public char getTop()
	{
		return acStack[iTop];
	}

	public int getAccessCounter()
	{
		return stackAccessCounter;
	}

	public boolean isEmpty() {
		return iTop == -1;
	}
}

// EOF
