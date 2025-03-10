package com.fhbhub.javaspringapi.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Data
public class Person implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", nullable = false, length = 80)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 80)
	private String lastName;

	@Column(nullable = false, length = 100)
	private String address;

	@Column(nullable = false, length = 6)
	private String gender;

	@Column(nullable = false, length = 16)
	private String creditCard;

}
