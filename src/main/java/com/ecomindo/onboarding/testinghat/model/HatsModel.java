package com.ecomindo.onboarding.testinghat.model;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "hats")
public class HatsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "product_code", length = 10)
	private String productCode;

	@Column(name = "product_name", length = 30)
	private String productName;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public HatsModel() {
		
	}

	public HatsModel(String productCode, String productName){
		this.productCode = productCode;
		this.productName = productName;
		this.createdDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



}