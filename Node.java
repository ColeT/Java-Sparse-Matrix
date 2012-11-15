/*
 * Node.java
 * Cole Terlesky
 * Programming Assignment 2
 * CS 310 Professor Nordstrom
 */

public class Node {
	private int value;
	private Node right;
	private Node down;
	private int row;
	private int column;
	
	//basic constructor for a node
	public Node(int value){
		this.value = value;
	}
	
	//more complex constructor if it makes sense to have it
	public Node(int value, int row, int column, Node right, Node down){
		this.value = value;
		this.row = row;
		this.column = column;
		this.right = right;
		this.down = down;
	}
	
	//getter and setter for the node pointer 'right'
	public Node getright(){
		return right;
	}
	public void setright(Node right){
		this.right = right;
	}
	
	//getter and setter for the node pointer 'down'
	public Node getdown(){
		return down;
	}
	public void setdown(Node down){
		this.down = down;
	}
	
	//getter and setter for the value
	public int getvalue(){
		return value;
	}
	public void setvalue(int value){
		this.value = value;
	}
	
	//getter and setter for row
	public int getrow(){
		return row;
	}
	public void setrow(int row){
		this.row = row;
	}
	
	//getter and setter for row
	public int getcolumn(){
		return column;
	}
	public void setcolumn(int column){
		this.column = column;
	}	
}
