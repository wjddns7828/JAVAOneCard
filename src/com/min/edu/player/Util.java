package com.min.edu.player;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 숫자만 입력할 수 있도록 하는 유틸클래스
 */
public class Util {
	public int scan(List<String> x) {
		int a;
			while (true) {
				Scanner scan = new Scanner(System.in);
				try {
					a = scan.nextInt();
					if (a >= 1 && a <= x.size()) {
						break;
					} else {
						System.out.printf("1부터 %d까지만 입력 가능합니다. 다시 입력해 주세요\n", x.size());
					}
				} catch (InputMismatchException e) {
					System.out.println("숫자만입력 가능합니다.");
				}
			}
			return a;
	}
	
	public int chknum() {
		int a;
		while (true) {
			Scanner scan = new Scanner(System.in);
			try {
				a = scan.nextInt();
				if (a==1 || a==2) {
					return a;
				}else {
					System.out.println("다시 입력해 주세요");
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만입력 가능합니다.");
			}
		}
	}
	
//	public void delayturn() {
//		Scanner scan = new Scanner(System.in);
//		scan.nextLine();
//	}
	public int oneCard() {
		int a = 0;
		while (true) {
			Scanner scan = new Scanner(System.in);
			try {
				a = scan.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("숫자만 입력 가능합니다.");
			}
		}
		return a;
	}
}
