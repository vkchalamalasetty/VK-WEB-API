package com.rewards.model;

import java.util.Map;

public class CustomerRewardsData {

	private String customerName;
	private Map<String, Integer> rewards;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Map<String, Integer> getRewards() {
		return rewards;
	}

	public void setRewards(Map<String, Integer> rewards) {
		this.rewards = rewards;
	}

}
