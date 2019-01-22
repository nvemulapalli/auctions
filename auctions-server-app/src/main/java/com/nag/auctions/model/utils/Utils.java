package com.nag.auctions.model.utils;

import java.util.Random;
import java.util.stream.Collectors;

import com.nag.auctions.services.server.core.IServerResponse;
import com.nag.auctions.services.server.core.IServerResponseResult;
import com.nag.auctions.services.server.impl.ServerResponse;
import com.nag.auctions.services.server.impl.ServerResponseError;

public class Utils {

	public static String getGeneratedId() {
		int length = 8;
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		             + "abcdefghijklmnopqrstuvwxyz"
		             + "0123456789";
		return new Random().ints(length, 0, chars.length())
		                         .mapToObj(i -> "" + chars.charAt(i))
		                         .collect(Collectors.joining());
	}
	
	public static IServerResponse generateErrorResponse(String errorMessage) {
		ServerResponseError errorResponse = new ServerResponseError();
		errorResponse.setErrorMessage(errorMessage);
		
		ServerResponse response = new ServerResponse();		
		response.setError(errorResponse);
		
		return response;
	}
	
	public static IServerResponse generateResponse(IServerResponseResult result) {
		ServerResponse response = new ServerResponse();		
		response.setResult(result);
		
		return response;
	}
	
}
