package com.nag.auctions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nag.auctions.model.AuctionItem;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AuctionsApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired 
	private JacksonTester<AuctionItem> auctionItemJson;
	
	private static final String sampleAuctionJSON = "{\"reservePrice\":10001,\"item\": {\"itemId\":\"testItemId\",\"description\":\"jUnit Test\"}};";
	
	@Test
	public void testGetAllAuctions() throws Exception {
		//just testing that service works.
		this.mvc.perform(get("/auctions/auctionItems"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetOneAuction() throws Exception {
		this.mvc.perform(get("/auctions/auctionItems/1234"))
			.andExpect(status().isOk())
			.andExpect(content().string("{\"result\":{\"resultCode\":0,\"resultMessage\":null,\"auctionItem\":{\"auctionItemId\":\"1234\",\"reservePrice\":10450.0,\"currentBid\":9500.0,\"item\":{\"itemId\":\"abcd\",\"description\":\"test description\"}}},\"error\":null}"));
		
		this.mvc.perform(get("/auctions/auctionItems/doesntexist"))
			.andExpect(status().isOk())
			.andExpect(content().string("{\"result\":{\"resultCode\":0,\"resultMessage\":\"No auction items associated with the id: doesntexist\",\"auctionItem\":null},\"error\":null}"));
	}
	
	@Test
	public void createAuction() throws Exception {
		this.mvc.perform(post("/auctions/auctionItems"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(""));
	}

	@Test
	public void testAuctionsJSONSerialization() throws IOException {
        assertThat(this.auctionItemJson.parseObject(sampleAuctionJSON).getReservePrice()).isEqualTo(new BigDecimal("10001"));
	}
	
	//The above are just for example.
	//TODO: Add tests for all api's and cases.

}

