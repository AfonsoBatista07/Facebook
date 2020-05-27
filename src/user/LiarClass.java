package user;

import post.Comment;
import post.Post;
import user.exceptions.InadequateStanceException;
import user.exceptions.InvalidCommentStanceException;

/**
* Implements a Liar User.
* @author Afonso Batista 57796
* @author Joao Jorge 57994
*/
public class LiarClass extends UserClass {
	
	/**
	 * Constructor of the LiarClass.
	 * @param id - Id of the User
	 */
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
