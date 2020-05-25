package user;

import post.Comment;
import post.Post;

public interface Fanatic extends User {
	void newPost(Post post);
	void newComment(Comment comment);
}
