package ru.khrebtov.report;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/report")
public interface ReportServiceRest {
    @GET
    String createReport();
}
