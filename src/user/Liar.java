package user;

import post.Comment;
import post.Post;

public interface Liar extends User {
	
	void newPost(Post post);
	void newComment(Comment comment);
}
