package com.nikhil.reservation;

import java.util.ArrayList;

public class ReserveMain {
	
	public static void main(String[] args) {
		Theatre theatre=new Theatre("CS",10,14);
		
		//In order for this code to work, Seat should not be private..So I made it public
		ArrayList<Theatre.Seat> seats=theatre.getSeats();
		for(Theatre.Seat s:seats) {
			System.out.println(s.getSeatNumber()+" : Rs. "+s.getPrice());
		}
		System.out.println("**************************************************");
		
		theatre.reserveSeat("D12");
		theatre.reserveSeat("A04");
		theatre.reserveSeat("B9");
		theatre.reserveSeat("I12");
		theatre.reserveSeat("F13");
		theatre.reserveSeat("B14");
		theatre.reserveSeat("K2");
		theatre.reserveSeat("I15");
		
		theatre.cancelSeat("B01");
		theatre.cancelSeat("F13");
		theatre.cancelSeat("I12");
		
		
	}

}
