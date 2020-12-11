package com.onlinetermInsurance.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.onlinetermInsurance.entity.Payments;
import com.onlinetermInsurance.repository.PaymentDaoImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentJpaDaoTest {

	 @Autowired
	 private TestEntityManager testEntityManager;
	 @Autowired
	 private PaymentDaoImpl dao;
	 
	 @Test
	 public void testNewTicket() throws Exception{
	        Payments payment = getPayment();
	        Payments saveInDb = testEntityManager.persist(payment);
	        Payments getFromInDb =dao.findByReceiptNo(saveInDb.getReceiptNo());
	        assertThat(getFromInDb).isEqualTo(saveInDb);
	    }
	 
	 @Test
	 public void getTotalAmountPaid() throws Exception{
	        Payments payment = getPayment();
	        testEntityManager.persist(payment);
	        Double getFromInDb =dao.findTotalAmountPaid(payment.getUserId(), payment.getPolicyNo());
	        assertThat(getFromInDb).isEqualTo(1000.0);
	    }
	 @Test
	 public void getfindByUserIdAndPolicyNo() throws Exception{
	        Payments payment = getPayment();
	        testEntityManager.persist(payment);
	        List<Payments> getFromInDb =dao.findByUserIdAndPolicyNo(payment.getUserId(), payment.getPolicyNo());
	        assertThat(getFromInDb).isEqualTo(dao.findByUserIdAndPolicyNo(payment.getUserId(), payment.getPolicyNo()));
	    }
	 private Payments getPayment() 
	 {
		 Payments payment=new Payments();
	     payment.setUserId(155);
	     payment.setAmount(1000);
	     payment.setPolicyNo(101);
	     payment.setDateOfPayment(Date.valueOf("2020-11-18"));
	        
	     return payment;
	 
	 }
	 
	 
}
