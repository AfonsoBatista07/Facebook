package user;

import exceptions.InadequateStanceException;
import exceptions.InvalidCommentStanceException;
import post.Comment;
import post.Post;

public class LiarClass extends UserClass implements Liar {

	private int numberOfLies;
	
	public LiarClass(String id) {
		super(id, LIAR);
	}
	
	public void newPost(Post post) {
		if(!post.isHonest()) super.newPost(post);
		else throw new InadequateStanceException();
	}
	
	public void newComment(Comment comment) {
		if((comment.getPost().isHonest() && !comment.isPositive()) || (comment.getPost().isHonest() && comment.isPositive())) super.newComment(comment);
		else throw new InvalidCommentStanceException();
	}
	
	public int getNumberOfLies() {
		return numberOfLies;
	}
	
}
