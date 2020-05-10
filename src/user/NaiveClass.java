package user;

import exceptions.InadequateStanceException;
import post.Comment;
import post.Post;

public class NaiveClass extends UserClass implements Naive {

	public NaiveClass(String id) {
		super(id, NAIVE);
	}
	
	public void newComment(Post post, Comment comment) {
		if(comment.isPositive()) super.newComment(comment);
		else throw new InadequateStanceException();
	}
}
