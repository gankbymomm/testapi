package vn.vnpay.controller;

import vn.vnpay.dto.ReqFromMerchantDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/merchant")
public class MerchantController extends BaseController{

    @POST
    @Path("/response")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestMerchant(ReqFromMerchantDTO reqFromMerchantDTO) {
        return Response.status(Response.Status.OK).entity(merchant.response(reqFromMerchantDTO)).build();
    }
}
