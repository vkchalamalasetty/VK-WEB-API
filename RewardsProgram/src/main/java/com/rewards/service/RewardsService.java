package com.charterrewards.service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.charterrewards.model.CustomerRewardsData;
import com.charterrewards.model.Response;
import com.charterrewards.model.Transaction;

@Service
public class RewardsService {

	/**
	 * This method is used to populate rewards response for given list of transactions. 
	 * @param transactions
	 * @return
	 */
	public Response calculateRewards(List<Transaction> transactions) {
		Map<String, List<Transaction>> customerTransactionMap = getCustomerTransactionsMap(transactions);

		Response response = new Response();
		List<CustomerRewardsData> customersRewardsData = new ArrayList<CustomerRewardsData>();
		response.setCustomersRewardsData(customersRewardsData);

		for (String customer : customerTransactionMap.keySet()) {
			Map<String, List<Transaction>> monthlyTransactions = getMonthlyTransactionsMap(
					customerTransactionMap.get(customer));
			CustomerRewardsData customerData = new CustomerRewardsData();
			Map<String, Integer> rewardsMap = new TreeMap<>();
			customerData.setCustomerName(customer);
			int totalRewards = 0;
			for (String month : monthlyTransactions.keySet()) {
				int rewards = calculateRewardsPerMonth(monthlyTransactions.get(month));
				totalRewards += rewards;
				rewardsMap.put(month, rewards);
			}
			rewardsMap.put("Total Rewards", totalRewards);
			customerData.setRewards(rewardsMap);
			customersRewardsData.add(customerData);
		}

		return response;
	}

	/**
	 * This method is to group list of transactions based on month of transaction date and return as a map.
	 * @param transactions
	 * @return
	 */
	private Map<String, List<Transaction>> getMonthlyTransactionsMap(List<Transaction> transactions) {
		Map<String, List<Transaction>> transactionMap = new HashMap<>();
		transactionMap = transactions.stream().collect(
				Collectors.groupingBy(t -> new DateFormatSymbols().getMonths()[t.getTransactionDate().getMonth()]));
		return transactionMap;
	}

	/**
	 * This method is to group list of transactions based on customer ID and return as a map.
	 * @param transactions
	 * @return
	 */
	private Map<String, List<Transaction>> getCustomerTransactionsMap(List<Transaction> transactions) {
		Map<String, List<Transaction>> transactionMap = new HashMap<>();
		transactionMap = transactions.stream().collect(Collectors.groupingBy(t -> t.getCustomerID()));
		return transactionMap;
	}

	/**
	 * This method is to calculate reward points for the given list of transactions.
	 * @param transactions
	 * @return
	 */
	private Integer calculateRewardsPerMonth(List<Transaction> transactions) {
		Integer rewards = 0;
		for (Transaction transaction : transactions) {
			int amount = transaction.getAmount();
			if (amount >= 100) {
				rewards = rewards + 50;
				rewards = rewards + (amount - 100) * 2;
			} else if (amount > 50) {
				rewards = amount - 50;
			}
		}
		return rewards;
	}

}
