package com.wolfhack.service.admin.view.page.dashboard;

import lombok.Data;

@Data
public class ServiceHealth {

	enum Status {
		EXCELLENT, OK, FAILING
	}

	private Status status;

	private String city;

	private int input;

	private int output;

	private String theme;

	public ServiceHealth(Status status, String city, int input, int output) {
		this.status = status;
		this.city = city;
		this.input = input;
		this.output = output;
	}

}
