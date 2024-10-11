package ressources;
import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logements")
public class LogementRessources {
    public static LogementBusiness logementBusiness =new LogementBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLogement(Logement logement) {
        if (logementBusiness.addLogement(logement)) {
            return Response.status(Response.Status.CREATED)
                    .entity("Logement added successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Failed to add logement").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public   Response   getListLogements()
    {
        if(logementBusiness.getLogements().size()!=0){
            return Response.status(Response.Status.OK).entity(logementBusiness.getLogements()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("La liste est vide").build();

    }

    @GET
    @Path("/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDelegation(@PathParam("delegation") String delegation) {
        List<Logement> logements = logementBusiness.getLogementsByDeleguation(delegation);

        if (logements != null && !logements.isEmpty()) {
            return Response.status(Response.Status.OK).entity(logements).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun logement trouvé pour la délégation: " + delegation).build();
        }
    }

    @DELETE
    @Path("/{reference}")
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean isDeleted = logementBusiness.deleteLogement(reference);

        if (isDeleted) {
            return Response.status(Response.Status.OK).entity("Logement supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement non trouvé avec l'identifiant: " + reference).build();
        }
    }

    @PUT
    @Path("{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement updatedLogement) {
        boolean isUpdated = logementBusiness.updateLogement(reference, updatedLogement);

        if (isUpdated) {
            return Response.status(Response.Status.OK).entity("Logement modifié avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement non trouvé avec l'identifiant: " + reference).build();
        }
    }

    @GET
    @Path("reference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Logement logement = logementBusiness.getLogementsByReference(reference);
        if (logement != null) {
            return Response.status(Response.Status.OK).entity(logement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement non trouvé avec la référence: " + reference).build();
        }
    }
}




