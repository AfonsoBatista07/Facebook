package user;

import post.Comment;
import user.exceptions.InvalidCommentStanceException;
import user.exceptions.UserCanNotCommentPostException;

public class SelfCenteredClass extends UserClass {
	
	public SelfCenteredClass(String id) {
		super(id, SELF_CENTERED);
	}
	
	public void newComment( Comment comment) {
		if(!comment.getPost().getAuthorId().equals(getId())) throw new UserCanNotCommentPostException();
		if(!comment.isPositive()) throw new InvalidCommentStanceException();
		super.newComment(comment);
	}
}
