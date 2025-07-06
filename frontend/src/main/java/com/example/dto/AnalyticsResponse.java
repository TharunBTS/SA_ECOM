package com.example.dto;

public class AnalyticsResponse {
	
	private Long placed;
	
	private Long shipped;
	
	private Long delivered;
	
	private Long currentMonthOrder;
	
	private Long previousMonthOrder;
	
	private Long currentMonthEarnings;
	
	private Long previousMonthEarnings;

	public Long getPlaced() {
		return placed;
	}

	public void setPlaced(Long placed) {
		this.placed = placed;
	}

	public Long getShipped() {
		return shipped;
	}

	public void setShipped(Long shipped) {
		this.shipped = shipped;
	}

	public Long getDelivered() {
		return delivered;
	}

	public void setDelivered(Long delivered) {
		this.delivered = delivered;
	}

	public Long getCurrentMonthOrder() {
		return currentMonthOrder;
	}

	public void setCurrentMonthOrder(Long currentMonthOrder) {
		this.currentMonthOrder = currentMonthOrder;
	}

	public Long getPreviousMonthOrder() {
		return previousMonthOrder;
	}

	public void setPreviousMonthOrder(Long previousMonthOrder) {
		this.previousMonthOrder = previousMonthOrder;
	}

	public Long getCurrentMonthEarnings() {
		return currentMonthEarnings;
	}

	public void setCurrentMonthEarnings(Long currentMonthEarnings) {
		this.currentMonthEarnings = currentMonthEarnings;
	}

	public Long getPreviousMonthEarnings() {
		return previousMonthEarnings;
	}

	public void setPreviousMonthEarnings(Long previousMonthEarnings) {
		this.previousMonthEarnings = previousMonthEarnings;
	}

	public AnalyticsResponse(Long placed, Long shipped, Long delivered, Long currentMonthOrder, Long previousMonthOrder,
			Long currentMonthEarnings, Long previousMonthEarnings) {

		this.placed = placed;
		this.shipped = shipped;
		this.delivered = delivered;
		this.currentMonthOrder = currentMonthOrder;
		this.previousMonthOrder = previousMonthOrder;
		this.currentMonthEarnings = currentMonthEarnings;
		this.previousMonthEarnings = previousMonthEarnings;
	}
	
	
	
	
	

}
