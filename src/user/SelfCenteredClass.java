package user;

import exceptions.UserCanNotCommentPostException;
import exceptions.InvalidCommentStanceException;
import post.Comment;

public class SelfCenteredClass extends UserClass {
	
	public SelfCenteredClass(String id) {
		super(id, SELF_CENTERED);
	}
	
	public void newComment(int postId, Comment comment) {
		if(getPost(postId).getAuthorId().equals(getId())) super.newComment(postId, comment);
		else if(comment.isPositive()) throw new InvalidCommentStanceException();
		else throw new UserCanNotCommentPostException();
	}
}
