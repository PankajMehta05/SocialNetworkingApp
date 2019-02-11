package android.com.myapplication;

public class User {

    int id;
    String email;
    String username;
    String image;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String email, String username) {
    }

    public User(String email, String username, String image, int id) {
        this.email = email;
        this.username = username;
        this.image = image;
        this.id = id;
    }


}
