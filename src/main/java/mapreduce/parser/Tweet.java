package mapreduce.parser;

public class Tweet {
	
	private String id_str; // string representation of the unique identifier for this Tweet
	private String text; // actual UTF-8 text of the status update
	private User user; // user who posted this Tweet
	private int retweet_count; // number of times this Tweet has been retweeted
	private String in_reply_to_status_id_str; // Nullable. If the represented Tweet is a reply, this field will contain the string representation of the original Tweet’s ID
	
	// Constructor
	public Tweet() {}
	
	// Getters
	public String getId_str() {return this.id_str;}
	public String getText() {return this.text;}
	public User getUser() {return this.user;}
	public int getRetweet_count() {return this.retweet_count;}
	public String getIn_reply_to_status_id_str () {return this.in_reply_to_status_id_str;}
	
	// Setters
	public void setId_str(String id_str) {this.id_str = id_str;}
	public void setText(String text) {this.text = text;}
	public void setUser(User user) {this.user = user;}
	public void setRetweet_count(int retweet_count) {this.retweet_count = retweet_count;}
	public void setIn_reply_to_status_id_str (String in_reply_to_status_id_str) {this.in_reply_to_status_id_str = in_reply_to_status_id_str;}
	
	@Override
	public String toString () {		
		// print: id_str,user_id,user_sreen_name,retweet_count,in_reply_to_status_id,text
		return this.id_str+","+this.user.getId_str()+","+this.user.getScreen_name()+","+this.retweet_count+","+this.in_reply_to_status_id_str+","+this.text;
	}

}
