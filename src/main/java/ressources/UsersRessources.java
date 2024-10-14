package ressources;


import entities.Users;
import metiers.UsersBusiness;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
public class UsersRessources {

    private static Users user = new Users();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        List<Users> users = UsersBusiness.getListUsers() ;
        return Response.status(200).entity(UsersBusiness.getListUsers()).build();
    }

}
