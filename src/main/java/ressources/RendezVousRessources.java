package ressources;

import entities.RendezVous;
import metiers.LogementBusiness;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("rendezvous")
public class RendezVousRessources {
    private static RendezVousBusiness rendezVousBusiness =new RendezVousBusiness();
    public static LogementBusiness logementBusiness =new LogementBusiness();


    public RendezVousRessources() {
        RendezVous r = new RendezVous(1, "31-10-2015", "15:30",
                logementBusiness.getLogementsByReference(4), "55214078");
        rendezVousBusiness.addRendezVous(r);
        RendezVous r2 = new RendezVous(2, "30-12-2020", "20:30",
                logementBusiness.getLogementsByReference(5), "523564078");
        rendezVousBusiness.addRendezVous(r2);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous)
    {
        if(rendezVousBusiness.addRendezVous(rendezVous))
            return Response.status(Response.Status.OK).entity( "added").build();
        return Response.status(Response.Status.NOT_FOUND).entity("echec d ajout").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRendezvous() {
        List<RendezVous> rendezvousList = rendezVousBusiness.getListeRendezVous();

        if (rendezvousList != null && !((List<?>) rendezvousList).isEmpty()) {
            return Response.status(Response.Status.OK).entity(rendezvousList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun rendez-vous trouvé").build();
        }
    }

    @GET
    @Path("/logement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousByLogementReference(@PathParam("reference") int reference) {
        List<RendezVous> rendezVousList = rendezVousBusiness.getListeRendezVousByLogementReference(reference);

        if (rendezVousList != null && !rendezVousList.isEmpty()) {
            return Response.status(Response.Status.OK).entity(rendezVousList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Aucun rendez-vous trouvé pour le logement avec la référence: " + reference).build();
        }
    }

    @GET
    @Path("/{id}")  // Spécifiez le chemin avec un paramètre
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rendezVous = rendezVousBusiness.getRendezVousById(id);
        if (rendezVous != null) {
            return Response.status(Response.Status.OK).entity(rendezVous).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous non trouvé avec l'identifiant: " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean isDeleted = rendezVousBusiness.deleteRendezVous(id);

        if (isDeleted) {
            return Response.status(Response.Status.OK).entity("deleted").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous non trouvé avec l'identifiant: " + id).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        boolean isUpdated = rendezVousBusiness.updateRendezVous(id, updatedRendezVous);
        if (isUpdated) {
            return Response.status(Response.Status.OK).entity("Rendez-vous modifié avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous non trouvé avec l'identifiant: " + id).build();
        }
    }

}
