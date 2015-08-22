package kk.java.calc;

import java.util.Scanner;

import kk.java.subdiv.SubDiv;

public abstract class Calculator extends SubDiv{



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print("Enter Two no.");
		Scanner sc=new Scanner(System.in);
		int a= sc.nextInt();
		int b= sc.nextInt();
		char ch =sc.next().charAt(0);
		int c=0;

		switch(ch){
		case '+' : c=Addd(a,b);
		break;
		case '-': c=sub(a,b);
		break;
		case '*': c=multii(a, b);
		break;
		case '/': c=div(a,b);
		break;
		default:
			System.out.print("Enter Valid operator");

		}
	}

}
