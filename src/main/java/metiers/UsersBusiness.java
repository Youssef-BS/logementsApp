package metiers;

import entities.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersBusiness {

    static public List<Users> listUsers ;

    public UsersBusiness(){
    listUsers = new ArrayList<Users>();
    listUsers.add(new Users(1 , "test" , "test@gmail.com" , "12345678"));
    listUsers.add(new Users(2 , "test2" , "test2@gmail.com" , "12345678"));
    }

    public static List<Users> getListUsers() {
        return listUsers;
    }

    public boolean checkUserById(int id) {
        for ( Users user : listUsers ) {
            if ( user.getId() == id ) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmail(String email){
        for(Users user : listUsers){
            if(user.getEmail().equals(email))
                return true ;
        }
        return false ;
    }

    public Users getUserById(int id){
       if(checkUserById(id))
           return listUsers.get(id);
        return null;
    }

    public boolean addNewUsers(Users user){
       return listUsers.add(user);
    }

    public boolean deleteUser(int id) {
        if(checkUserById(id)){
            listUsers.remove(id);
            return true;
        }
        return false ;
    }

    public boolean authentification(String email, String password){
        for(Users user : listUsers){
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return true ;
        }
        return false ;
    }

    public Users registerNewAccount(Users user) {
        if(!checkEmail(user.getEmail())) {
            addNewUsers(user) ;
            return user ;
        }
        return null ;
    }

}
