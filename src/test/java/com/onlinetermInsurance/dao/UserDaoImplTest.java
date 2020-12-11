package com.onlinetermInsurance.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.onlinetermInsurance.entity.User;
import com.onlinetermInsurance.repository.UserDaoImpl;


@RunWith(SpringRunner.class)
@DataJpaTest
class UserDaoImplTest {
	@Autowired
	private UserDaoImpl users;
	@Autowired
	private TestEntityManager testEntityManager;
	@Test
	public void testNewUsers() throws Exception{
		User user=getUsers();
	    User saveInDb=testEntityManager.persist(user);
		User getFromInDb=users.findById(saveInDb.getUserId()).get();
				assertThat(getFromInDb).isEqualTo(saveInDb);
}
	
	private User getUsers() {
		User user=new User();
		user.setFname("shilpa");
	    user.setLname("Kumari");
	    user.setPhoneno((long) 67123467);
        user.setDob(Date.valueOf("2020-11-18"));
        user.setAadhar("87543");
        return user;
		
	}
	
	
	@Test
	public void testGetUsersById() throws Exception{
		User user=new User();
		user.setFname("shilpa");
	    user.setLname("priya");
	    user.setPhoneno((long) 67123467);
        user.setDob(Date.valueOf("2020-11-18"));
        user.setAadhar("87543");
        User saveInDb=testEntityManager.persist(user);
         User getInDb=users.findById(user.getUserId()).get();
        assertThat(getInDb).isEqualTo(saveInDb);
	}
	@Test
	public void testGetAllUsers() throws Exception{
		User user1=new User();
		user1.setFname("shilpa");
	    user1.setLname("singh");
	    user1.setPhoneno((long) 67123467);
        user1.setDob(Date.valueOf("2020-11-18"));
        user1.setAadhar("785649");
        User user2=new User();
		user2.setFname("shilpa");
	    user2.setLname("Ram");
	    user2.setPhoneno((long) 67123467);
        user2.setDob(Date.valueOf("2020-11-18"));
        user2.setAadhar("456789");
        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        List<User> usersList=(List<User>) users.findAll();
        Assert.assertEquals(2, usersList.size());
        }
	@Test
	public void testDeleteUsersById() throws Exception{
		User user=new User();
		user.setFname("shilpa");
	    user.setLname("rubi");
	    user.setPhoneno((long) 67123467);
        user.setDob(Date.valueOf("2020-11-18"));
        user.setAadhar("456789");
       
        
        User user1=new User();
		user1.setFname("shilpa");
	    user1.setLname("jasmin");
	    user1.setPhoneno((long) 67123467);
        user1.setDob(Date.valueOf("2020-11-18"));
        user1.setAadhar("456789");
        User user2=testEntityManager.persist(user);
        testEntityManager.persist(user1);
        testEntityManager.remove(user2);
        List<User> usersList=(List<User>) users.findAll();
        Assert.assertEquals(usersList.size(),1);
        
	}
	@Test
	public void testUpdateUsers()
	{
		User user1=new User();
		user1.setFname("shilpa");
	    user1.setLname("priya");
	    user1.setPhoneno((long) 67123467);
        user1.setDob(Date.valueOf("2020-11-18"));
        user1.setAadhar("456789");
		
        testEntityManager.persist(user1);
        User getFromDb=users.findById(user1.getUserId()).get();
        getFromDb.setFname("shilpa");
        testEntityManager.persist(getFromDb);
        assertThat(getFromDb.getFname()).isEqualTo("shilpa");
	}

}

