package com.onlinetermInsurance.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinetermInsurance.entity.Login;

import com.onlinetermInsurance.repository.LoginDao;

@Service
public class LoginServiceImpl {
	public static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	@Autowired
	LoginDao loginDao;
	
	public List<Login> getAll()
	{
		logger.info("Login Service get all");
		return loginDao.findAll();
		 
	}
	public Login getLoginByUserName(String username,String password)
	{
		return loginDao.getLoginByUserName(username, password);
	}
	public void upateLoginIdPassword(Login login)
	{
		int id=login.getlId();
		Login loginTemp=loginDao.findByLoginId(id);
		loginDao.save(loginTemp);
	}
	

}
