package user;

import post.Comment;
import post.Post;

public interface Liar extends User {
	
	public void newPost(Post post);
	void newComment(Post post,Comment comment);
}
