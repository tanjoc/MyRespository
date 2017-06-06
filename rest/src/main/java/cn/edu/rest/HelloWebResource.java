package cn.edu.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("say")
public class HelloWebResource {

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_HTML)
    public String sayHello() {
        return "hello, This is a test!";
    }
}
