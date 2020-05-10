package user;

import exceptions.InadequateStanceException;
import post.Comment;
import post.Post;

public class LiarClass extends UserClass implements Liar {

	public LiarClass(String id) {
		super(id, LIAR);
	}
	
	public void newPost(Post post) {
		if(!post.isHonest()) super.newPost(post);
		else throw new InadequateStanceException();
	}
	
	public void newComment(Post post,Comment comment) {
		if((post.isHonest() && !comment.isPositive()) || (!post.isHonest() && comment.isPositive())) super.newComment(comment);
		else throw new InadequateStanceException();
	}
	
}
