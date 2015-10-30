package facades;

import deploy.DeploymentConfiguration;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.PasswordHash;

public class UserFacade {
  
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
  
  private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
  private final  Map<String, User> users = new HashMap<>();
  

  public UserFacade() {
    
  }
  
  public User getUserByUserId(String id){
    EntityManager em = getEntityManager();
    try{
        return em.find(User.class, id);
    } finally{
        em.close();
    }
  }
  /*
  Return the Roles if users could be authenticated, otherwise null
  */
  public List<String> authenticateUser(String userName, String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
    EntityManager em = getEntityManager();
    try {
        User user = em.find(User.class, userName);
        if(PasswordHash.validatePassword(password, user.getPassword())){
            return user.getRoles();
        }
        return null;
    } finally{
        em.close();
    }
  }
}



