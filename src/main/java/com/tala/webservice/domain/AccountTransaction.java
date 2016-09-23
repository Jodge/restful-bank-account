package com.tala.webservice.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author George Otieno
 *
 */
@Entity
public class AccountTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int type;
	private double amont;
	private Date date;
	
	protected AccountTransaction() {}
	
	public AccountTransaction(int type, double amount, Date date) {
		this.type = type;
		this.amont = amount;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmont() {
		return amont;
	}

	public void setAmont(double amont) {
		this.amont = amont;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
