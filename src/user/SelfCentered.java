package user;

import post.Comment;

public interface SelfCentered extends User {
	
	void newComment(int posId, Comment comment);
}
