package com.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.model.Response;
import com.rewards.model.Transaction;
import com.rewards.service.RewardsService;

import io.swagger.annotations.ApiOperation;

@RestController
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	@ApiOperation(value = "/getRewards", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/getRewards")
	public Response getRewards(@RequestBody List<Transaction> transactions) {
		return rewardsService.calculateRewards(transactions);
	}

}
