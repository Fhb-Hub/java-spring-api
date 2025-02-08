package com.fhbhub.javaspringapi.data.vo.v2;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PersonVOV2 implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	private LocalDate birthDay;

}