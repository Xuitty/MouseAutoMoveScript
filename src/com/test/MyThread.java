package com.test;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Robot;

public class MyThread extends Thread {
	Long time = null;

	public MyThread() {
		super();
	}

	public MyThread(Long time) {
		super();
		this.time = time;
	}

	@Override
	public void run() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int maxWidth = gd.getDisplayMode().getWidth();
		int maxHeight = gd.getDisplayMode().getHeight();
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long times = 0L;
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		while (true) {
			int x1 = MouseInfo.getPointerInfo().getLocation().x;
			int y1 = MouseInfo.getPointerInfo().getLocation().y;
			if (x == x1 && y == y1) {
				robot.mouseMove((int) Math.floor(Math.random() * maxWidth) + 1,
						(int) Math.floor(Math.random() * maxHeight) + 1);
				x1 = MouseInfo.getPointerInfo().getLocation().x;
				y1 = MouseInfo.getPointerInfo().getLocation().y;
				System.out.println(++times);
			} else {
				System.out.println(++times + "(moved)");
			}
			x = x1;
			y = y1;
			try {
				Thread.sleep(time == null ? 120000L : time * 1000);
			} catch (InterruptedException e) {
				break;
			}
		}

	}

}
