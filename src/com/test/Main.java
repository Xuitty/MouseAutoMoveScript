package com.test;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class Main {
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		MyThread t = null;
		int option = 0;
		boolean init = true;
		Long time = null;
		String guide = "|| Please insert option number";
		String select1 = "| 1:start(120s)";
		String select2 = "| 2:start with custom duration";
		String select3 = "| 3:pause the script";
		String select4 = "| 4:stop and terminate";
		guide = rightPadding(guide, 70, ' ') + "||\n";
		select1 = rightPadding(select1, 70, ' ') + "||\n";
		select2 = rightPadding(select2, 70, ' ') + "||\n";
		select3 = rightPadding(select3, 70, ' ') + "||\n";
		select4 = rightPadding(select4, 70, ' ');

		while (true) {
			System.out.println("=========================Mouse Auto Move Script=========================");
			System.out.println(guide + select1 + select2 + select3 + select4);
			System.out.println("========================================================================");
			Scanner input = new Scanner(System.in);
			try {
				if (init && args.length != 0 && "-t".equals(args[0])) {
					option = 2;
					time = Long.parseLong(args[1]);
				} else {
					option = input.nextInt();
				}
			} catch (InputMismatchException e) {
				option = 999;
			} catch (NumberFormatException e) {
				option = 999;
			}
			if (option == 1) {
				for (int i = 0; i < 50; i++) {
					System.out.println("\n");
				}
				System.out.println("=========================Script Started=========================");
				if (t != null) {
					t.interrupt();
				}
				t = new MyThread();
				t.start();
			} else if (option == 2) {
				for (int i = 0; i < 50; i++) {
					System.out.println("\n");
				}
				if (t != null) {
					t.interrupt();
				}
				if (time == null) {
					System.out.println("===============Input the detect duration(second)=================");
					time = input.nextLong();
				} else
					System.out.println("=========================Script Started=========================");
				t = new MyThread(time);
				t.start();
			} else if (option == 3) {
				for (int i = 0; i < 50; i++) {
					System.out.println("\n");
				}
				if (t == null) {
					System.out.println("*********You have to start task first for use pause function");
				} else {
					System.out.println("========================Script Paused=========================");
					t.interrupt();
					t = null;
				}

			} else if (option == 4) {
				if (t != null) {
					t.interrupt();
				}
				t = null;
				input.close();
				System.out.println("=========================application terminated=========================");
				break;
			} else if (option == 999) {
				System.out.println(
						"********************Your Input or Argument type error********************\n********************Please type number in.********************");

			} else {
				System.out.println("*************************Please use the numbers*************************");
			}
			init = false;
		}

	}

	private static String rightPadding(String str, int length, char padChar) {
		if (str == null) {
			str = "";
		}
		if (str.length() > length) {
			return str;
		}
		String pattern = "%-" + length + "s";
		return String.format(pattern, str).replace(' ', padChar);
	}

	@Test
	public void argTest() {
		String a = "123qwezxcx";
		System.out.println(Integer.parseInt(a));
	}

}
