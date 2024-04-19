package me.fit.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import me.fit.model.IPLog;

@Path("/data")
@RegisterRestClient
public interface IPClient {

	@GET
	@Path("/client-ip")
	IPLog getIp();

}
