/*
 * SparseMatrix.java
 * Cole Terlesky
 * Programming Assignment 2
 * CS 310 Professor Nordstrom
 */

public class SparseMatrix {
	
	private int rowCount;
	private int colCount;
	Node enter;
	boolean empty_matrix;

	//Constructor: the new matrix should have rowCount rows and colCount columns.
    public SparseMatrix(int rowCount, int colCount){
    	this.rowCount = rowCount;
    	this.colCount = colCount;
    	this.enter = new Node(-1);
    	this.enter.setrow(-1);
    	this.enter.setcolumn(-1);
    	this.enter.setdown(enter);
    	this.enter.setright(enter);
    	this.empty_matrix = true;
    	setValue(0,0,0);
    }
    
    //Return the value at this position in the matrix. 
    //Return 0 if there is no node at this position.
    public int getValue(int row, int column){
    	Node column_iterator = enter;
    	while(column_iterator.getright() != enter){
    		column_iterator = column_iterator.getright();
    		if (column_iterator.getcolumn() == column){
    			Node row_iterator = column_iterator;
    			while (row_iterator.getdown() != column_iterator){
    				row_iterator = row_iterator.getdown();
    				if(row_iterator.getrow()==row){
    	    			return row_iterator.getvalue();
    	    		}
    			}
    		}
    	}
    	return 0;
    }
    
    //Put value into the matrix. 
    //If there is already a node at this position overwrite the value stored there. 
    //If there is no node create one.
    public void setValue(int value, int row, int col){
    	Node new_node = new Node(value);
    	new_node.setrow(row);
    	new_node.setcolumn(col);
    	
    	//checks for and fills a totally empty sparsematrix
    	if(empty_matrix){
    		Node row_header = new Node(-2, row, -1, new_node, enter);
    		Node col_header = new Node(-2, -1, col, enter, new_node);
    		new_node.setright(row_header);
    		new_node.setdown(col_header);
    		enter.setright(col_header);
    		enter.setdown(row_header);
    		this.empty_matrix = false;
    		return;
    	}else{
    	
    	//checks for and fills a sparsematrix that doesn't already have a row header
    	if(!row_check(row)){
    		//iterates through row_headers
    		Node row_iterator = enter;
    		while(row_iterator.getdown() != enter){
    			//if the current row is less than the row value
    			//and the next row is greater than the row value
    			//make a new row_header between them
    			row_iterator = row_iterator.getdown();
    			if( 
    	    	(row_iterator.getrow() < row) && 
    	    	((row_iterator.getdown().getrow() > row) ||
    	    	(row_iterator.getdown() == enter))
    	    	){
    				Node row_header = 
    					new Node(row, row, -1, new_node, row_iterator.getdown());
    				row_iterator.setdown(row_header);
    				new_node.setright(row_header);
    			}
    		}
    	}
    	//if the row already exists this code is executed:
    	else{
    		Node row_iterator = enter;
    		while( row_iterator.getdown() != enter){
    			//if the row header row matches the row for the new node
    			row_iterator = row_iterator.getdown();
    			if(row_iterator.getrow()==row){
    				//the code below makes sure it is in the middle
    				Node column_iterator = row_iterator;
    				//if statement when new value is 
    				//between beginning and the first node
    				if(column_iterator.getright().getcolumn() > col){
    					new_node.setright(column_iterator.getright());
	    				column_iterator.setright(new_node);
    				}
    				else{
    				while( column_iterator.getright() != row_iterator){
    					column_iterator = column_iterator.getright();
    					if(column_iterator.getcolumn()==col){
    						column_iterator.setvalue(value);
    					}
    					if(
    					(column_iterator.getcolumn() < col) &&
    					((column_iterator.getright().getcolumn() > col)||
    					(column_iterator.getright() == row_iterator))
    					){
    						//once it is in the middle, set the new pointers
    						new_node.setright(column_iterator.getright());
    	    				column_iterator.setright(new_node);
    					}
    				}
    				}
    			}
    		}
    	}
    	
    	
    	//the column set value code should work the same as the row set value code
    	if(!column_check(col)){
    		Node column_iterator = enter;
    		while( column_iterator.getright() != enter){
    			column_iterator = column_iterator.getright();
    			if( 
    	    	(column_iterator.getcolumn() < col) && 
    	    	((column_iterator.getright().getcolumn() > col) ||
    	    	(column_iterator.getright() == enter))
    	    	){
    				Node column_header = 
    					new Node(col, -1, col, column_iterator.getright(), new_node);
    				column_iterator.setright(column_header);
    				new_node.setdown(column_header);
    			}
    		}
    	}
    	else{
    		Node column_iterator = enter;
    		while( column_iterator.getright() != enter){
    			//if the row header row matches the row for the new node
    			column_iterator = column_iterator.getright();
    			if(column_iterator.getcolumn()==col){
    				//the code below makes sure it is in the middle
    				Node row_iterator = column_iterator;
    				//if statement when new value is 
    				//between beginning and the first node
    				if(row_iterator.getdown().getrow() > col){
    					new_node.setdown(row_iterator.getdown());
	    				row_iterator.setdown(new_node);
    				}
    				else{
    				while( row_iterator.getdown() != column_iterator){
    					row_iterator = row_iterator.getdown();
    					if(row_iterator.getrow()==row){
    						row_iterator.setvalue(value);
    					}
    					if(
    					(row_iterator.getrow() < row) &&
    					((row_iterator.getdown().getrow() > row)||
    					(row_iterator.getdown() == column_iterator))
    					){
    						//once it is in the middle, set the new pointers
    						new_node.setdown(row_iterator.getdown());
    	    				row_iterator.setdown(new_node);
    					}
    				}
    				}
    			}
    		}
    	}
    	}
    	
    	
    	
    }
    
    //returns boolean true if the row value already exists
    //moves downwards through the references looking for 
    public boolean row_check(int row){
    	if(empty_matrix){
    		return false;
    	}
    	Node iterator_node = enter;
    	while (iterator_node.getdown() != enter){
    		iterator_node = iterator_node.getdown();
    		if(iterator_node.getrow()==row){
    			return true;
    		}
    	}
    	return false;
    }
    
    //returns boolean true if the column value already exists
    public boolean column_check(int col){
    	if(empty_matrix){
    		return false;
    	}
    	Node iterator_node = enter;
    	while ( (iterator_node.getright() != enter)){
    		iterator_node = iterator_node.getright();
    		if(iterator_node.getcolumn()==col){
    			return true;
    		}
    	}
    	return false;
    	
    }
    
    //returns true if there is a value in the given row and column
    public boolean spot_check(int row, int column){
    	if (!row_check(row)){
    		return false;
    	}
    	
    	if (!column_check(column)){
    		return false;
    	}
    	
    	Node column_iterator = enter;
    	while(column_iterator.getright().getcolumn() != column){
    		column_iterator = column_iterator.getright();
    	}
    	Node row_iterator = column_iterator;
    	while(row_iterator.getdown()!=column_iterator){
    		row_iterator = row_iterator.getdown();
    		if(row_iterator.getrow()==row){
    			return true;
    		}
    	}
    	return false;
    }
    
    //Display the values from positions represented by nodes. 
    //Displayed values are in "short form": (row, col) : value.
    public void printShort(){
    	Node row_iterator = enter;
    	while(row_iterator.getdown() != enter){
    		row_iterator = row_iterator.getdown();
    		printRowValues(row_iterator.getrow());
    	}
    }
    
    //Display, in short form, values represented by nodes from one row.
    public void printRowValues(int rowNumber){
    	//if there is no row of that value there is nothing to return
    	if(!row_check(rowNumber)){
    		return;
    	}
    	Node row_iterator = enter;
    	while(row_iterator.getrow() != rowNumber){
    		row_iterator = row_iterator.getdown();
    	}
    	Node column_iterator = row_iterator;
    	while(column_iterator.getright() != row_iterator){
    		column_iterator = column_iterator.getright();
    		System.out.print("   ("+column_iterator.getrow()+", "
    							+column_iterator.getcolumn()+"):"
    							+column_iterator.getvalue());
    	}
    }
    
    //Display, in short form, values represented by nodes from one column.
    public void printColValues(int colNumber){
    	//if there is no column of that value there is nothing to return
    	if(!column_check(colNumber)){
    		return;
    	}
    	
    	Node column_iterator = enter;
    	while(column_iterator.getcolumn() != colNumber){
    		column_iterator = column_iterator.getright();
    	}
    	Node row_iterator = column_iterator;
    	while(row_iterator.getdown() != column_iterator){
    		row_iterator = row_iterator.getdown();
    		System.out.print("   ("+row_iterator.getrow()+", "
    							+row_iterator.getcolumn()+"):"
    							+row_iterator.getvalue());
    	}
    }
    
    //Display the entire matrix with a dot for positions not represented by a node. 
    //The display is arranged in rows and columns with row labels and column heads. 
    //The values will be printed right justified with a field width of 6. 
    //This method will only be of use for matrices with a small # of columns.
    public void printLong(){
    	for(int row=0; row<rowCount; row++ ){
    		for(int col=0; col<colCount; col++){
    			int val = getValue(row, col);
    			if(val == 0){
    				System.out.printf("%6c", '.');
    			}
    			else{
    				System.out.printf("%6d", val);
    			}
    		}
    		System.out.println("");
    	}
    }
    
    //Add matrix this and matrix m returning the sum matrix.
    public SparseMatrix add(SparseMatrix matrix){
    	SparseMatrix m = new SparseMatrix(rowCount, colCount);
    	int val;
    	int row;
    	int col;
    	Node row_iterator = enter;
    	Node column_iterator;
    	while (row_iterator.getdown() != enter){
    		row_iterator = row_iterator.getdown();
    		column_iterator = row_iterator;
    		while(column_iterator.getright() != row_iterator){
    			column_iterator = column_iterator.getright();
    			row = column_iterator.getrow();
    			col = column_iterator.getcolumn();
    			val = column_iterator.getvalue() + 
    					matrix.getValue(row, col);
    			m.setValue(val, row, col);
    		}
    	}
    	return m;
    }

}
