package com.crm.demo.resource;

import com.crm.demo.entity.Customer;
import com.crm.demo.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    
    @Inject
    CustomerService customerService;
    
    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return Response.ok(customers).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"客户不存在\"}")
                    .build();
        }
        return Response.ok(customer).build();
    }
    
    @GET
    @Path("/search")
    public Response searchCustomers(@QueryParam("keyword") String keyword) {
        List<Customer> customers = customerService.searchCustomers(keyword);
        return Response.ok(customers).build();
    }
    
    @GET
    @Path("/active")
    public Response getActiveCustomers() {
        List<Customer> customers = customerService.getActiveCustomers();
        return Response.ok(customers).build();
    }
    
    @POST
    public Response createCustomer(@Valid Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") Long id, @Valid Customer customer) {
        Customer updated = customerService.updateCustomer(id, customer);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"客户不存在\"}")
                    .build();
        }
        return Response.ok(updated).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"客户不存在\"}")
                    .build();
        }
        return Response.noContent().build();
    }
    
    @PATCH
    @Path("/{id}/toggle-status")
    public Response toggleCustomerStatus(@PathParam("id") Long id) {
        Customer customer = customerService.toggleCustomerStatus(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"客户不存在\"}")
                    .build();
        }
        return Response.ok(customer).build();
    }
    
    @GET
    @Path("/stats")
    public Response getStats() {
        return Response.ok(new Stats(
                customerService.getTotalCount(),
                customerService.getActiveCount(),
                customerService.getInactiveCount()
        )).build();
    }
    
    public static class Stats {
        public long total;
        public long active;
        public long inactive;
        
        public Stats(long total, long active, long inactive) {
            this.total = total;
            this.active = active;
            this.inactive = inactive;
        }
    }
}
