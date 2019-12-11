package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exception_handling_trycatch {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int A,B,C;
		try {
			System.out.println("Enter value for A");
			A=in.nextInt();
			System.out.println("Enter value for B");
			B=in.nextInt();
			
			C=A/B;
			
			System.out.println("Result of A/B :"+C);
		}catch(ArithmeticException Exception  ) {
			System.out.println("B value is given as zero.A numericvalue should be given ");
			
		}catch(InputMismatchException ime) {
            System.out.println("Given input value for A or B is not matching with the data type required.");
		}finally {
			System.out.println("The script is ended here");
			in.close();
		}

	}

}
