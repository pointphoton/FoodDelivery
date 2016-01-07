/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws.model;

import java.util.Date;

public class OrderMessageModel {

	private Long orderId ;
	private Double totalPrice ;
	private Date  orderTime;
	private String OrderMessage;

	public OrderMessageModel() {
	}


        
        

	public Long getOrderId() {
		return orderId;
	}

	public OrderMessageModel setOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public OrderMessageModel setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public OrderMessageModel setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
		return this;
	}

	public String getOrderMessage() {
		return OrderMessage;
	}

	public OrderMessageModel setOrderMessage(String orderMessage) {
		OrderMessage = orderMessage;
		return this;
	}

}

