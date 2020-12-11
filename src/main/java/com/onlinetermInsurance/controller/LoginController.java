package com.onlinetermInsurance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onlinetermInsurance.entity.Login;
import com.onlinetermInsurance.exception.ResourceNotFoundException;
import com.onlinetermInsurance.service.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/Login")
@Api(description="Payment Controller class for all url based function")
public class LoginController {
	
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@GetMapping("/getAll")
	@ApiOperation(value="Get all Login details")
	public List<Login> getAll() {
		logger.info("Get all Login");
		return  loginServiceImpl.getAll();
	}

	@GetMapping("/login/{username}/{password}")
	@ApiOperation(value="Get Login by username and password")
	public ResponseEntity<Login> getLoginByUserName(@PathVariable(value = "username") String username,@PathVariable(value = "password") String password) throws ResourceNotFoundException {
		logger.info("Login by username and password");
		Login login=loginServiceImpl.getLoginByUserName(username, password);
		return  ResponseEntity.ok(login);
	}
	/*@PostMapping("/updateLogin")
	@ApiOperation(value="Create new policy ADD")
	public Policy saveUser( @RequestBody Policy policy1 ) {
		logger.info("Create Policy");
		return  policyService.savePolicy(policy1);
	}*/

	@PutMapping("/updateLogin/{username}/{password}")
	@ApiOperation(value="Update Login details")
	public ResponseEntity<Login> UpdateLoginByUserName(@PathVariable(value = "username") String username,@PathVariable(value = "password") String password,@RequestBody Login login) throws ResourceNotFoundException {
		logger.info("Update Login details");
		Login logintemp=loginServiceImpl.getLoginByUserName(username, password);
		loginServiceImpl.upateLoginIdPassword(logintemp);
		return  ResponseEntity.ok(logintemp);
	}
	 
}
