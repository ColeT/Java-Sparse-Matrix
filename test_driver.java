/*
 * test_driver.java
 * Cole Terlesky
 * Programming Assignment 2
 * CS 310 Professor Nordstrom
 */

import java.io.*;

public class test_driver {
	
	//these fields are stored to shorten code (avoids additional switches)
	public static SparseMatrix selected_matrix;
	public static SparseMatrix sparse_matrix_a;
	public static SparseMatrix sparse_matrix_b;
	public static SparseMatrix sparse_matrix_c;

	//main method contains menu options
	public static void main(String[] args) {
		sparse_matrix_a = new SparseMatrix(10,8);
		sparse_matrix_b = new SparseMatrix(10,8);
		sparse_matrix_c = new SparseMatrix(10,8);
		int row;
		int column;
		char selection = main_menu();
		while(selection != 'q'){
			switch(selection){
			case 'v': //adds a value to a matrix
				which_matrix();
				setvalue(selected_matrix);
				break;
			case 'r': //shows a given row
				which_matrix();
				System.out.print("Row value: ");
				row = user_int();
				selected_matrix.printRowValues(row);
				break;
			case 'g': //shows a value at given coordinates
				which_matrix();
				System.out.print("Row value: ");
				row = user_int();
				System.out.print("Column value: ");
				column = user_int();
				System.out.println(selected_matrix.getValue(row, column));
				break;
			case 'c': //shows a given column
				which_matrix();
				System.out.print("Column value: ");
				column = user_int();
				selected_matrix.printColValues(column);
				break;
			case 's': //prints a short list of values in matrix
				which_matrix();
				selected_matrix.printShort();
				break;
			case 'a': //adds two matrix together, long print result
				which_matrix();
				SparseMatrix mat_one = selected_matrix;
				which_matrix();
				mat_one.add(selected_matrix).printLong();
				break;
			case 'l': //prints a table of values
				which_matrix();
				selected_matrix.printLong();
				break;
			default: //used if the user makes an invalid selection
				System.out.println("This was an invalid selection.");
				break;
			}
			selection = main_menu();
		}
	}
	
	//this method is called when input is needed form menu selection
	public static char main_menu(){
		System.out.println("Set value:  v      Show row:     r");
		System.out.println("Get value:  g      Show column:  c");
		System.out.println("Show short: s      Add matrices: a");
		System.out.println("Show long:  l      Quit q:       q");
		System.out.print("    ->");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char opt = 'z';
		try{
			opt = (br.readLine()).charAt(0);
		}
		catch(Exception e){
			System.out.println("Exception: "+ e);
			return main_menu();
		}
		return (opt);
	}
	
	//this deals with the user's selection of a matrix
	public static void which_matrix(){
		System.out.println("Which Matrix (a,b,c): ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char opt = 'z';
		try{
			opt = (br.readLine()).charAt(0);
		}
		catch(Exception e){
			System.out.println("Exception: "+ e);
			which_matrix();
		}
		switch(opt){
		case 'a':
			selected_matrix = sparse_matrix_a;
			break;
		case 'b':
			selected_matrix = sparse_matrix_b;
			break;
		case 'c':
			selected_matrix = sparse_matrix_c;
			break;
		default:
			System.out.println("this was not a valid matrix, select again");
			which_matrix();
			break;
		}
	}
	
	//method for collecting integers from user
	public static int user_int(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int opt = 0;
		try{
			String opt_s;
			opt_s = br.readLine();
			opt = Integer.parseInt(opt_s);
		}
		catch(Exception e){
			System.out.println("Exception:" + e);
			return user_int();
		}
		return (opt);
	}
	
	//this method is used for setting a value in a given matrix
	public static void setvalue(SparseMatrix SM){
		System.out.println("Value: ");
		int value = user_int();
		System.out.println("Row: ");
		int row = user_int();
		System.out.println("Column: ");
		int col = user_int();
		SM.setValue(value, row, col);
	}
}
