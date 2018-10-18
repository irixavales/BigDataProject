package mapreduce.parser;

public class User {
	
	private String id_str; // string representation of the unique identifier for this User
	private String screen_name; // screen name, handle, or alias that this user identifies themselves with. Is unique but subject to change
	
	// Constructor
	public User() {}

	// Getters
	public String getId_str() {return id_str;}
	public void setId_str(String id_str) {this.id_str = id_str;}

	// Setters
	public String getScreen_name() {return screen_name;}
	public void setScreen_name(String screen_name) {this.screen_name = screen_name;}
	
}
