package com.ecom.wishlist.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Image {
	@Id
	private Long ImageId;
	@Column(length = 3000, nullable = false, unique = true)
	private String fileName;
	private long size;
	private Date uploadedAt;
	@Lob()
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] content;
}
