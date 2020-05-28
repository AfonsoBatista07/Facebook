package user;

import post.Comment;
import user.exceptions.InvalidCommentStanceException;

/**
* Implements a Naive User.
* @author Afonso Batista 57796
* @author Joao Jorge 57994
*/
public class NaiveClass extends UserClass {

	/**
	 * Constructor of the NaiveClass.
	 * @param id - Id of the User
	 */
	public NaiveClass(String id) {
		super(id, NAIVE);
	}
	
	public void newComment(Comment comment) {
		if(comment.isPositive()) super.newComment(comment);
		else throw new InvalidCommentStanceException();
	}
}
