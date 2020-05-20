package user;

import post.Comment;

public interface Naive extends User {
	
	void newComment(Comment comment);
}
