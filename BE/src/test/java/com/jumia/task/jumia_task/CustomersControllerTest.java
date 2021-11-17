package com.jumia.task.jumia_task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.jumia.task.jumia_task.model.Customer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class CustomersControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }

   @Test
   public void getCustomersList() throws Exception {
      String uri = "/api/customer";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Customer[] customerlist = super.mapFromJson(content, Customer[].class);
      assertTrue(customerlist.length > 0);
   }

   @Test
   public void filterCustomers() throws Exception {
      String uri = "/api/filter_customers";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"country\": \"Cameroon\", \"state\": \"valid\" }") 
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Customer[] customerlist = super.mapFromJson(content, Customer[].class);
      assertTrue(customerlist.length > 0);
   }

}