package com.onlinetermInsurance.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import com.onlinetermInsurance.entity.Payments;
import com.onlinetermInsurance.service.PaymentService;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PaymentControllerTest {


	 @InjectMocks
	    PaymentController paymentController;
	     
	    @Mock
	    PaymentService paymentService;
	
	
	@Test
    public void testGetAll() throws Exception{
        
        Payments payment=getPayment();
		
		List<Payments> paymentList = new ArrayList<>();
		
		Payments payment1=new Payments();
		payment1.setAmount(1000);
		payment1.setDateOfPayment(Date.valueOf("2020-11-18"));
		payment1.setPolicyNo(102);
		payment1.setUserId(102);
		
		paymentList.add(payment1);
		paymentList.add(payment);

		when(paymentService.getall()).thenReturn(paymentList);
		 
        // when
        List<Payments> result = paymentController.getAll();
 
        // then
        assertThat(result.size()).isEqualTo(2);
         
        assertThat(result.get(0).getUserId())
                        .isEqualTo(payment1.getUserId());
         
        assertThat(result.get(1).getPolicyNo())
                        .isEqualTo(payment.getPolicyNo());

        }
	
	
	@Test
    public void testMakePayment() throws Exception{
       
        Payments payment=getPayment();
		
		
		
		Payments payment1=new Payments();
		payment1.setAmount(1000);
		payment1.setDateOfPayment(Date.valueOf("2020-11-18"));
		payment1.setPolicyNo(102);
		payment1.setUserId(102);
		
    
        

		when(paymentService.MakePayment(payment)).thenReturn(payment);
		 
        // when
        Payments result = paymentController.MakePayment(payment);
 
        // then
        assertThat(result.getUserId()).isEqualTo(0);
        assertThat(result.getPolicyNo()).isEqualTo(0);

    }
	@Test
    public void testTotalAmount() throws Exception{
        String URI= "/api/payment/pay/{userId}/{policyNo}";
        Payments payment=getPayment();
        when(paymentService.findTotalAmountPaid(payment.getUserId(), payment.getPolicyNo())).thenReturn(1000.0);
		 
        // when
        double result = paymentController.getTotalAmountPaid(payment.getUserId(), payment.getPolicyNo());
 
        // then
        assertThat(result).isEqualTo(1000); 
        assertThat(payment.getAmount()).isEqualTo(1000);
    }
	
	@Test
    public void testFindByUserId() throws Exception{
        String URI= "/api/payment/payment/{userId}/{policyNo}";
        Payments payment=getPayment();
        List<Payments> paymentList = new ArrayList<>();
		
		Payments payment1=new Payments();
		payment1.setAmount(1000);
		payment1.setDateOfPayment(Date.valueOf("2020-11-18"));
		payment1.setPolicyNo(102);
		payment1.setUserId(102);
		
		paymentList.add(payment1);
		paymentList.add(payment);

		when(paymentService.findByUserIdAndPolicyNo(payment1.getUserId(), payment1.getPolicyNo())).thenReturn(paymentList);
		 
        // when
        List<Payments> result = paymentController.getPaymentByUserId(payment1.getUserId(), payment1.getPolicyNo());
 
        // then
        assertThat(result.size()).isEqualTo(2);
         
        assertThat(result.get(0).getUserId())
                        .isEqualTo(payment1.getUserId());
         
        assertThat(result.get(1).getPolicyNo())
                        .isEqualTo(payment.getPolicyNo());



        assertThat(payment.getAmount()).isEqualTo(1000);
    }
	
	
	
	@Test
    public void testGetByReciept() throws Exception{
       
        Payments payment=getPayment();
        when(paymentService.findByReceiptNo(185)).thenReturn(payment);
		 
        // when
        Payments result = paymentController.getReciept(185);
        int a=result.getReceiptNo();
 
        // then
        assertThat(result.getReceiptNo()).isEqualTo(a);
        assertThat(result.getPolicyNo()).isEqualTo(101);
        assertThat(result.getAmount()).isEqualTo(1000);
    }
	
	@Test
    public void testDeleteTicketById() throws Exception{
        String URI = "/api/payment/payment/{receiptNo}";
        Payments payment=getPayment();
        int del=payment.getReceiptNo();
        when(paymentService.deletePayment(payment)).thenReturn(true);
		 
        // when
        Map<String, Boolean> response = new HashMap<>();
        Map<String, Boolean> app = new HashMap<>();
       response = paymentController.deletePayment(del);
        app.put("deleted", Boolean.TRUE);
 
        // then
        assertThat(response).isEqualTo(app);
        assertThat(payment.getAmount()).isEqualTo(1000);

        

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
