package com.onlinetermInsurance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinetermInsurance.entity.User;
import com.onlinetermInsurance.service.UserServiceImpl;

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
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
	@Autowired
    private MockMvc mockMvc;
 @MockBean
    private UserServiceImpl userService;
 @Test
public void testCreateUser() throws Exception{
	        String URI = "/api/v1/user";
	        User user = new User();
	        user.setUserId(101);
			 user.setFname("shilpa");
			    user.setLname("Kumari");
			    user.setPhoneno((long) 67123467);
		        user.setDob(Date.valueOf("2020-11-18"));
		        user.setAadhar("87543");
		        

	        String jsonInput = this.converttoJson(user);

	        Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
}
 @Test
public void testGetUserById() throws Exception {
		String URI= "/api/v1/user/{id}";
		User user = new User();
		user.setUserId(102);
		 user.setFname("shilpa");
		    user.setLname("Kumari");
		    user.setPhoneno((long) 67123467);
	        user.setDob(Date.valueOf("2020-11-18"));
	        user.setAadhar("87543");
     
     
     String jsonInput = this.converttoJson(user);

    Mockito.when(userService.findUserById(Mockito.any())).thenReturn(user);
     //Assert.assertTrue(userpolicyService.findUserPolicyById());
     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
     String jsonOutput = mockHttpServletResponse.getContentAsString();

     assertThat(jsonInput).isEqualTo(jsonOutput);
	}
 @Test
public void testUpdateUser() throws Exception {
		String URI = "/api/v1/user/{id}";
		User user = new User();
		user.setUserId(105);
		 user.setFname("shilpa");
		    user.setLname("Kumari");
		    user.setPhoneno((long) 67123467);
	        user.setDob(Date.valueOf("2020-11-18"));
	        user.setAadhar("87543");
    
     String jsonInput = this.converttoJson(user);

     Mockito.when(userService.updateUser(Mockito.any(),Mockito.any())).thenReturn(user);
     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,105).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
             .andReturn();
     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
     String jsonOutput = mockHttpServletResponse.getContentAsString();

     assertThat(jsonInput).isEqualTo(jsonOutput);
	}
 @Test
	public void testDeleteUser() throws Exception {
		String URI = "/api/v1/user/{id}";
		User user = new User();
		user.setUserId(105);
		 user.setFname("shilpa");
		    user.setLname("Kumari");
		    user.setPhoneno((long) 67123467);
	        user.setDob(Date.valueOf("2020-11-18"));
	        user.setAadhar("87543");

     Mockito.when(userService.findUserById(Mockito.anyInt())).thenReturn(user);
     Mockito.when(userService.deleteUser(Mockito.any(User.class))).thenReturn(true);
     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 105).accept(MediaType.APPLICATION_JSON)).andReturn();
     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

     Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
 @Test
	public void testGetAllUsers() throws Exception {
		String URI = "/api/v1/user";
		User user = new User();
		user.setUserId(105);
		 user.setFname("shilpa");
		    user.setLname("Kumari");
		    user.setPhoneno((long) 67123467);
	        user.setDob(Date.valueOf("2020-11-18"));
	        user.setAadhar("87543");
		
	        
	        User user1 = new User();
	        user1.setUserId(106);
			 user1.setFname("shilpa");
			    user1.setLname("Kumari");
			    user1.setPhoneno((long) 67123467);
		        user1.setDob(Date.valueOf("2020-11-18"));
		        user1.setAadhar("87543");
	    	 List<User> userList=new ArrayList<>();
	    	  userList.add(user);
	    	userList.add(user1);
	    	 
	    	String jsonInput = this.converttoJson(userList);

	         Mockito.when(userService.getAllUsers()).thenReturn(userList);
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
