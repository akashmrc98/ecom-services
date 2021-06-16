package com.example.orders.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Image {
	@Id
	private Long imageId;
	@Column(length = 3000, nullable = false, unique = true)
	private String name;
	private long size;
	private Date uploadedAt;
	@Lob()
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] content;
}
