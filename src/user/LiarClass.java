package user;

import post.Comment;
import post.Post;
import user.exceptions.InadequateStanceException;
import user.exceptions.InvalidCommentStanceException;

public class LiarClass extends UserClass implements Liar {
	
	public LiarClass(String id) {
		super(id, LIAR);
	}
	
	public void newPost(Post post) {
		if(!post.isHonest()) super.newPost(post);
		else throw new InadequateStanceException();
	}
	
	public void newComment(Comment comment) {
		if((comment.getPost().isHonest() && !comment.isPositive()) || (!comment.getPost().isHonest() && comment.isPositive())) super.newComment(comment);
		else throw new InvalidCommentStanceException();
	}
	
}
