package com.nikhil.reservation;

import java.util.ArrayList;
import java.util.Collections;

public class Theatre {
	
	private String name;
	private ArrayList<Seat> seats=new ArrayList<>();
	
	public Theatre(String name,int totalrows,int seatsperrow) {
		this.name=name;
		int lastrow='A'+totalrows-1;
		for(char row='A';row<=lastrow;row++) {
			for(int seatnum=1;seatnum<=seatsperrow;seatnum++) {
				double price =120.00;
				
				if(row<'D') {
					price=200.00;
				}
				
				if(row>='D' && (seatnum>=4 && seatnum<=9)) {
					price=150.00;
				}else if(row>'I' || (seatnum<4 || seatnum>9)) {
					price=100.00;
				}
				seats.add(new Seat(row+String.format("%02d", seatnum),price));
				
			}
		}
	}
	public String getName() {
		return name;
	}
	
	public ArrayList<Seat> getSeats() {
		return seats;
	}
	public boolean reserveSeat(String seatNumber) {
		int position=findSeat(seatNumber);
		if(position>=0) {
			return seats.get(position).reserve();
		}
		System.out.println("Seat number doesn't exist");
		return false;
	}
	public boolean cancelSeat(String seatNumber) {
		int position=findSeat(seatNumber);
		if(position>=0) {
			return seats.get(position).cancel();
		}
		System.out.println("Seat number doesn't exist");
		return false;
	}
	
	//Here I'm doing a liner search which takes O(n*n) time.
	
//	public Seat findSeat(String seatNumber) {
//		for(Seat s:seats) {
//			if(s.getSeatNumber().equalsIgnoreCase(seatNumber));
//			return s;
//		}
//		return null;
//	}
	
	//Now I find the seat index using Collections.binarySearch()
	public int findSeat(String seatNumber) {
		Seat findSeat=new Seat(seatNumber,0);
		int position=Collections.binarySearch(seats, findSeat, null);//null because I have already defined comparator
		return position;
	}
	
	//Normally inner classes are supposed to be private, I made it public to test a few factors.
	//Code works fine by making Seat private also.
	public class Seat implements Comparable<Seat>{
		
		private String seatNum;
		private double price;
		private boolean reserved=false;
		
		public Seat(String seatNum,double price) {
			this.seatNum=seatNum;
			this.price=price;
		}
		public String getSeatNumber() {
			return this.seatNum;
		}
		public double getPrice() {
			return price;
		}
		
		@Override
		public int compareTo(Seat seat) {
			return this.seatNum.compareToIgnoreCase(seat.getSeatNumber());
		}
		
		public boolean reserve() {
			if(!reserved) {
				System.out.println("Seat reserved");
				reserved=true;
				return true;
			}
			System.out.println("Seat is not available currently..seems reserved already");
			return false;
		}
		
		public boolean cancel() {
			if(reserved) {
				System.out.println("Seat unreserved");
				reserved=false;
				return true;
			}
			System.out.println("Seat is not reserved yet");
			return false;
		}

		
	}
	

}
