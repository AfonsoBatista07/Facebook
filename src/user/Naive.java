package user;

import post.Comment;
import post.Post;

public interface Naive extends User {
	
	void newComment(Post post, Comment comment);
}
