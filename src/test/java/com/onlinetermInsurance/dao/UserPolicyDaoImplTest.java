package com.onlinetermInsurance.dao;
import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.onlinetermInsurance.entity.UserPolicy;
import com.onlinetermInsurance.repository.UserPolicyDaoImpl;

import org.junit.Assert;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserPolicyDaoImplTest {
	@Autowired
	private UserPolicyDaoImpl userPolicyDaoImpl;
	@Autowired
	private TestEntityManager testEntityManager;
	@Test
	public void testNewUserPolicy() throws Exception{
		UserPolicy user=getUserPolicy();
		UserPolicy saveInDb=testEntityManager.persist(user);
		UserPolicy getFromInDb=userPolicyDaoImpl.findById(saveInDb.getUserpolicyid()).get();
				assertThat(getFromInDb).isEqualTo(saveInDb);
}
	private UserPolicy getUserPolicy() {
		UserPolicy user=new UserPolicy();
	    user.setUserid(101);
	    user.setPolicyid(401);
	    user.setRegisteredDate(Date.valueOf("2000-11-10"));
        user.setTotalTime(12);
        user.setAmount(60000.00);
        user.setMonthOver(2);
        user.setAmountPaid(5000.00);
	    return user;
	}
	@Test
	public void testGetUserPolicyById() throws Exception{
		UserPolicy user=new UserPolicy();
	    user.setUserid(102);
	    user.setPolicyid(402);
	    user.setRegisteredDate(Date.valueOf("2000-11-10"));
        user.setTotalTime(24);
        user.setAmount(80000.00);
        user.setMonthOver(4);
        user.setAmountPaid(7000.00);
        UserPolicy saveInDb=testEntityManager.persist(user);
        UserPolicy getInDb=userPolicyDaoImpl.findById(user.getUserpolicyid()).get();
        assertThat(getInDb).isEqualTo(saveInDb);
	}
	@Test
	public void testGetAllUserPolicies() throws Exception{
		UserPolicy user1=new UserPolicy();
	    user1.setUserid(105);
	    user1.setPolicyid(402);
	    user1.setRegisteredDate(Date.valueOf("2000-11-10"));
        user1.setTotalTime(24);
        user1.setAmount(80000.00);
        user1.setMonthOver(4);
        user1.setAmountPaid(7000.00);
       UserPolicy user2=new UserPolicy();
	    user2.setUserid(106);
	    user2.setPolicyid(402);
	    user2.setRegisteredDate(Date.valueOf("2000-11-10"));
        user2.setTotalTime(24);
        user2.setAmount(80000.00);
        user2.setMonthOver(4);
        user2.setAmountPaid(7000.00);
        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        List<UserPolicy> policyList=(List<UserPolicy>) userPolicyDaoImpl.findAll();
        Assert.assertEquals(2, policyList.size());
        
	}
	@Test
	public void testDeleteUserPolicyById() throws Exception{
		UserPolicy user1=new UserPolicy();
	    user1.setUserid(105);
	    user1.setPolicyid(402);
	    user1.setRegisteredDate(Date.valueOf("2000-11-10"));
        user1.setTotalTime(24);
        user1.setAmount(80000.00);
        user1.setMonthOver(4);
        user1.setAmountPaid(7000.00);
        UserPolicy user2=new UserPolicy();
	    user2.setUserid(107);
	    user2.setPolicyid(402);
	    user2.setRegisteredDate(Date.valueOf("2000-11-10"));
        user2.setTotalTime(24);
        user2.setAmount(80000.00);
        user2.setMonthOver(4);
        user2.setAmountPaid(7000.00);
        UserPolicy user3=testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.remove(user3);
        List<UserPolicy> policies=(List<UserPolicy>) userPolicyDaoImpl.findAll();
        Assert.assertEquals(policies.size(),1);
        
	}
	@Test
	public void testUpdateUserPolicy() {
		UserPolicy user=new UserPolicy();
	    user.setUserid(108);
	    user.setPolicyid(402);
	    user.setRegisteredDate(Date.valueOf("2000-11-10"));
        user.setTotalTime(24);
        user.setAmount(80000.00);
        user.setMonthOver(4);
        user.setAmountPaid(7000.00);
        testEntityManager.persist(user);
        UserPolicy getFromDb=userPolicyDaoImpl.findById(user.getUserpolicyid()).get();
        getFromDb.setAmount(100000.00);
        testEntityManager.persist(getFromDb);
        assertThat(getFromDb.getAmount()).isEqualTo(100000.00);
	}
	
}