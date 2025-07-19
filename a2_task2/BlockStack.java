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
	private int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 */
	private int iTop  = 3;

	/**
	 * stack[0:5] with four defined values
	 */
	private char acStack[] = new char[] {'a', 'b', 'c', 'd', '$', '$'};

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
	 * @throws EmptyStackException if stack is empty
	 */
	public char pick() throws EmptyStackException
	{
		if (isEmpty()) throw new EmptyStackException("Empty stack!!!");

		stackAccessCounter++;
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 * @return the element, char
	 * @throws InvalidStackAccessException if position is invalid
	 */
	public char getAt(final int piPosition) throws InvalidStackAccessException
	{
		if (piPosition < 0 || piPosition > iSize) throw new InvalidStackAccessException("Invalid stack position access!!!");

		stackAccessCounter++;
		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 * @throws FullStackException if stack is full
	 */
	public void push(final char pcBlock) throws FullStackException
	{
		if (isFull()) throw new FullStackException("Full stack!!!");

		char charToPush;
		if (isEmpty()) {
			charToPush = 'a';
		} else {
			charToPush = pcBlock;
		}

		this.acStack[++this.iTop] = charToPush;
		stackAccessCounter++;
		System.out.println("Successfully pushed to the block stack");
		System.out.println(this);
	}

	/**
	 * Standard pop operation
	 * @return ex-top element of the stack, char
	 * @throws EmptyStackException if stack is empty
	 */
	public char pop() throws EmptyStackException
	{
		if (isEmpty()) throw new EmptyStackException("Empty Stack!!!");

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
		if (isEmpty()) throw new RuntimeException("Stack Empty!!!");

		return acStack[iTop];
	}

	public int getAccessCounter()
	{
		return stackAccessCounter;
	}

	public boolean isEmpty() {
		return iTop == -1;
	}

	public boolean isFull() {
		return iTop + 1 == iSize;
	}
}

class EmptyStackException extends Exception {
	public EmptyStackException(String message) {
		super(message);
	}
}

class FullStackException extends Exception {
	public FullStackException(String message) {
		super(message);
	}
}

class InvalidStackAccessException extends Exception {
	public InvalidStackAccessException(String message) {
		super(message);
	}
}

// EOF
