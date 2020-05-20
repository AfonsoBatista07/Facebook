package user;

import exceptions.InvalidCommentStanceException;
import post.Comment;

public class NaiveClass extends UserClass implements Naive {

	public NaiveClass(String id) {
		super(id, NAIVE);
	}
	
	public void newComment(Comment comment) {
		if(comment.isPositive()) super.newComment(comment);
		else throw new InvalidCommentStanceException();
	}
}
