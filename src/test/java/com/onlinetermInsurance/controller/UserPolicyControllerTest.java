package com.onlinetermInsurance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinetermInsurance.entity.UserPolicy;
import com.onlinetermInsurance.service.UserPolicyService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserPolicyController.class)
public class UserPolicyControllerTest {
	
	ObjectMapper om = new ObjectMapper();
	 @Autowired
	    private MockMvc mockMvc;
	 @MockBean
	    private UserPolicyService userPolicyService;

	 @Test
		public void testCreateUserPolicy() throws Exception{
		        String URI = "/api/v2/saveuserpolicy";
		        UserPolicy user = new UserPolicy();
		        user.setUserpolicyid(201);
		        user.setUserid(101);
			    user.setPolicyid(401);
			    user.setRegisteredDate(Date.valueOf("2000-09-08"));
		        user.setTotalTime(12);
		        user.setAmount(60000.00);
		        user.setMonthOver(2);
		        user.setAmountPaid(5000.00);

		        String jsonInput =converttoJson(user);
		        System.out.println(jsonInput);

		        Mockito.when(userPolicyService.saveUserPolicy(Mockito.any(UserPolicy.class))).thenReturn(user);
		        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI).content(jsonInput).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		        System.out.println("mock "+mvcResult.getResponse().getContentLengthLong());
		        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		        String jsonOutput = mockHttpServletResponse.getContentAsString();
		        System.out.println("hellllllooooooo"+jsonOutput+" helllooo");
		        assertThat(jsonInput).isEqualTo(jsonOutput);
		        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	 }
	 @Test
		public void testGetUserPolicyById() throws Exception {
			String URI= "/api/v2/finduserpolicy/{id}";
			UserPolicy user = new UserPolicy();
			user.setUserpolicyid(202);
	        user.setUserid(102);
		    user.setPolicyid(401);
		    user.setRegisteredDate(Date.valueOf("2000-11-10"));
	        user.setTotalTime(12);
	        user.setAmount(60000.00);
	        user.setMonthOver(2);
	        user.setAmountPaid(5000.00);
	        
	        String jsonInput = this.converttoJson(user);

	       Mockito.when(userPolicyService.findUserPolicyById(Mockito.any())).thenReturn(user);
	        //Assert.assertTrue(userpolicyService.findUserPolicyById());
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 202).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
		}
	 @Test
		public void testUpdateUserPolicy() throws Exception {
			String URI = "/api/v2/updateuserpolicy/{id}";
			UserPolicy user = new UserPolicy();
			user.setUserpolicyid(202);
	        user.setUserid(105);
		    user.setPolicyid(401);
		    user.setRegisteredDate(Date.valueOf("2000-11-10"));
	        user.setTotalTime(12);
	        user.setAmount(60000.00);
	        user.setMonthOver(2);
	        user.setAmountPaid(5000.00);
	        String jsonInput = this.converttoJson(user);

	        Mockito.when(userPolicyService.updateUserPolicy(Mockito.anyInt(),Mockito.anyInt(),Mockito.any())).thenReturn(user);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,105).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
		}
	 @Test
		public void testDeleteUserPolicy() throws Exception {
			String URI = "/api/v2/deleteuserpolicy/{id}";
			UserPolicy user = new UserPolicy();
			user.setUserpolicyid(201);
			 user.setUserid(105);
			    user.setPolicyid(401);
			    user.setRegisteredDate(Date.valueOf("2000-11-08"));
		        user.setTotalTime(12);
		        user.setAmount(60000.00);
		        user.setMonthOver(2);
		        user.setAmountPaid(5000.00);

	        Mockito.when(userPolicyService.findUserPolicyById(Mockito.any())).thenReturn(user);
	        Mockito.when(userPolicyService.deleteUserPolicy(Mockito.any())).thenReturn(true);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 105).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

	        Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		}
	 @Test
		public void testGetAllUserPolicies() throws Exception {
			String URI = "/api/v2/getAlluserpolicies";
			UserPolicy user = new UserPolicy();
			user.setUserpolicyid(201);
			 user.setUserid(105);
			    user.setPolicyid(401);
			    user.setRegisteredDate(Date.valueOf("2000-11-10"));
		        user.setTotalTime(12);
		        user.setAmount(60000.00);
		        user.setMonthOver(2);
		        user.setAmountPaid(5000.00);
		        
		        UserPolicy user1 = new UserPolicy();
		        user.setUserpolicyid(201);
				 user1.setUserid(105);
				    user1.setPolicyid(401);
				    user1.setRegisteredDate(Date.valueOf("2000-11-10"));
			        user1.setTotalTime(12);
			        user1.setAmount(60000.00);
			        user1.setMonthOver(2);
			        user1.setAmountPaid(5000.00);
		    	 List<UserPolicy> userPolicyList=new ArrayList<>();
		    	  userPolicyList.add(user);
		    	userPolicyList.add(user);
		    	 
		    	String jsonInput = this.converttoJson(userPolicyList);

		         Mockito.when(userPolicyService.getAllUserPolicies()).thenReturn(userPolicyList);
		         MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		         MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		         String jsonOutput = mockHttpServletResponse.getContentAsString();

		        assertThat(jsonInput).isEqualTo(jsonOutput);
		     }
		private String converttoJson(Object user) throws JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(user);
		}
	}
