package user;

import post.Comment;

public interface SelfCentered extends User {
	
	void newComment(Comment comment);
	
}
