package edu.miu.cs544.service.response;

import edu.miu.cs544.domain.Reservation;
import edu.miu.cs544.util.Constant.ReservationStatus;

public class ReservationResponse {
	private Integer id;
	private String code;
	private ReservationStatus reservationStatus;

	private PassengerResponse passenger;
	
	public ReservationResponse() {
		super();
	}
	public ReservationResponse(Reservation reservation) {
		super();
		if(reservation != null) {
			this.id = reservation.getId();
			this.code = reservation.getCode();
			this.reservationStatus = reservation.getReservationStatus();
			this.passenger = new PassengerResponse(reservation.getPassenger());
		}
	}

	public ReservationResponse(Integer id, String code, ReservationStatus reservationStatus,
			PassengerResponse passenger) {
		super();
		this.id = id;
		this.code = code;
		this.reservationStatus = reservationStatus;
		this.passenger = passenger;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public PassengerResponse getPassenger() {
		return passenger;
	}

	public void setPassenger(PassengerResponse passenger) {
		this.passenger = passenger;
	}
	
	
}
