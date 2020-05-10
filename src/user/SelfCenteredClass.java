package user;

import exceptions.UserCanNotComentPostException;
import post.Comment;
import post.Post;

public class SelfCenteredClass extends UserClass {
	
	public SelfCenteredClass(String id) {
		super(id, SELF_CENTERED);
	}
	
	public void newComment(Post post, Comment comment) {
		if(post.getAuthorId().equals(super.getId()) && comment.isPositive()) super.newComment(comment);
		else throw new UserCanNotComentPostException();
	}
}
