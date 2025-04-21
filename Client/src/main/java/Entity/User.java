package Entity;

import Enums.Roles;
import bsuir.client.PasswordHasher;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int user_id;
    private String username;
    private String password;
    private Buyers buyers;
    private Roles role;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public User(int user_id, String username, String password, Buyers buyers, Roles role)
    {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.buyers = buyers;
        this.role = role;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Buyers getBuyers()
    {
        return buyers;
    }

    public void setBuyers(Buyers buyers)
    {
        this.buyers = buyers;
    }

    public Roles getRole()
    {
        return role;
    }

    public void setRole(Roles role)
    {
        this.role = role;
    }
}
