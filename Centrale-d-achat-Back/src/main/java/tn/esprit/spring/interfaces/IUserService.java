package tn.esprit.spring.interfaces;


import tn.esprit.spring.entities.User;

import java.util.List;

public interface IUserService {
    public void initRoleAndUser();
    public User registerNewUser(User user);
    public String getEncodedPassword(String password);
    public List<User> getAll();
    public User findOne(String userName);
    public Boolean delete(String userName);
    public void update(User user);
    public long count();
    public long countFournisseur();
    public long countadmin();





}
