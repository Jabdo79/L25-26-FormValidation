package jon.abdo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
	private int id;
	
	private String username;
	
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());
        byte byteData[] = md.digest();
 
        final StringBuilder builder = new StringBuilder();
        
        for(byte b : byteData) {
            builder.append(String.format("%02x", b));
        }
        
        this.password = builder.toString();
	}
	
	public void resetPassword(){
		this.password = null;
	}
}
