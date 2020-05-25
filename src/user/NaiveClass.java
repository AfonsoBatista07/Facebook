package user;

import post.Comment;
import user.exceptions.InvalidCommentStanceException;

public class NaiveClass extends UserClass implements Naive {

	public NaiveClass(String id) {
		super(id, NAIVE);
	}
	
	public void newComment(Comment comment) {
		if(comment.isPositive()) super.newComment(comment);
		else throw new InvalidCommentStanceException();
	}
}
