package com.test;

import java.util.ArrayList;

class ThreadX implements Runnable {
	private Thread t;
	private String threadName;
	
	private ArrayList<Integer> mListData;
	
	public ThreadX(String name, ArrayList<Integer> listData) {
		// TODO Auto-generated constructor stub
		this.threadName = name;
		mListData = listData;
	}
	
	public void start()
	{
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	public void run(){
		switch (threadName) {
		
		
		case "INCREASE":
			for (int i = 0; i < mListData.size(); i++) {
				Integer tmp = mListData.get(i);
				synchronized (tmp) {
					mListData.set(i, tmp+1);
					System.out.println("INCREASED AT " + i);
				}
//				if (i == mListData.size()-1) i = 0;
			}
			break;
			
			
		case "DECREASE":
			for (int i = 0; i < mListData.size(); i++) {
				Integer tmp = mListData.get(i);
				synchronized (tmp) {
					mListData.set(i, tmp-1);
					System.out.println("DECREASED AT " + i);
				}
//				if (i == mListData.size()-1) i = 0;
			}
			break;
			
			
		case "REMOVE":
			try {
				while (!mListData.isEmpty()) {
					synchronized (mListData.get(0)) {
						mListData.remove(mListData.get(0));
						System.out.println("REMOVED HEAD " + mListData.size());
					}
					Thread.sleep(20);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		
			
		case "ADD":
			try {
				while (true) {
					synchronized (mListData) {
						mListData.add(0);
						System.out.println("ADD TAIL " + mListData.size());
					}
					Thread.sleep(20);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

public class ThreadTest {
   public static void main(String args[]) {
	   
	   //Init list
	   ArrayList<Integer> arrayList = new ArrayList<>();
	   for (int i = 0; i < 10; i++) {
		   arrayList.add(i);
	   }
	   
	   //Init thread
	   ThreadX t1 = new ThreadX("INCREASE", arrayList);
	   ThreadX t2 = new ThreadX("DECREASE", arrayList);
	   ThreadX t3 = new ThreadX("ADD", arrayList);
	   ThreadX t4 = new ThreadX("REMOVE", arrayList);
	   
	   t1.start();
	   t2.start();
	   t3.start();
	   t4.start();
	   
   }   
}
