package user;

import post.Comment;
import post.Post;

public interface SelfCentered extends User {
	
	void newComment(Post post, Comment comment);
}
