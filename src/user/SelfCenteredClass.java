package user;

import exceptions.UserCanNotCommentPostException;
import exceptions.InvalidCommentStanceException;
import post.Comment;

public class SelfCenteredClass extends UserClass {
	
	public SelfCenteredClass(String id) {
		super(id, SELF_CENTERED);
	}
	
	public void newComment( Comment comment) {
		if(comment.getPost().getAuthorId().equals(getId())) {
			if(comment.isPositive()) {
				super.newComment(comment);
			}else throw new InvalidCommentStanceException();
		}else throw new UserCanNotCommentPostException();
	}
}
