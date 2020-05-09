package system;

import post.Post;

public interface PostCollection {
	
	void newPost(String userId, int numHashTags, String[] hashtags, String type, String message);
	void newComment(String idComment, String idPost, String stance, String comment);
	int size();
	Post getPost(String id, String idPost);
}
