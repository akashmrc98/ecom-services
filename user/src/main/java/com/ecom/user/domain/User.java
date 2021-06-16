package com.ecom.user.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(unique = true)
	private String phone;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String username;

	private String firstName;
	private String lastName;
	private String gender;

	@OneToMany
	private List<Address> addressList;
}
