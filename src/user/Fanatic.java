package user;

import post.Comment;
import post.Post;

public interface Fanatic extends User {
	void newPost(Post post);
	void newComment(int postId, Comment comment);
	int getNumFanaticisms();
	boolean loves(String love);
	boolean hates(String hate);
}
