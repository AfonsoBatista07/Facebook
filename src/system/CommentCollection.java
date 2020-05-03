package system;

import post.Comment;

public interface CommentCollection {

	void newComment(String idComment, String stance, String comment);
	int size();
}
