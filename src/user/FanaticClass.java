package user;

import java.util.Iterator;
import java.util.LinkedList;

import exceptions.InadequateStanceException;
import exceptions.InvalidCommentStanceException;
import post.Comment;
import post.Post;

public class FanaticClass extends UserClass implements Fanatic {
	
	private LinkedList<String>loves, hates, hashTags;
	private int numFanaticisms;
	private static final String LOVES = "loves";
	
	public FanaticClass(String id, int numFanaticisms, LinkedList<String> hashTags) {
		super(id, FANATIC);
		this.numFanaticisms = numFanaticisms;
		loves = new	LinkedList<String>();	
		hates = new	LinkedList<String>();
		this.hashTags = hashTags;
		separateTags();
	}
	
	public void newPost(Post post) {
		Iterator<String> it = post.getHashTags();
		while (it.hasNext()) {
			String tag = it.next();
			if((post.isHonest() && hates(tag)) || (!post.isHonest() && loves(tag)) || !hasFanaticism(tag) ) throw new InadequateStanceException();
		}
		super.newPost(post);
	}
	
	public void newComment(int postId, Comment comment) {
		Post post = getPost(postId);
		String fanaticism = getFanaticism(post);
		if(loves(fanaticism)) {
			if((post.isHonest() && !comment.isPositive()) || (!post.isHonest() && comment.isPositive())) throw new InvalidCommentStanceException();
		}
		if(hates(fanaticism)) {
			if((post.isHonest() && comment.isPositive()) || (!post.isHonest() && !comment.isPositive())) throw new InvalidCommentStanceException();
		}
		super.newComment(postId, comment);
	}
	
	private void separateTags() {
		Iterator<String> it = hashTags.iterator();
		for(int i = 0; i < numFanaticisms; i++) {
			if(it.next().equals(LOVES)) loves.addLast(it.next());
			else hates.addLast(it.next());
		}
	}
	
	public int getNumFanaticisms() {
		return numFanaticisms;
	}
	
	public boolean loves(String love) {
		return find(love, loves);
	}
	
	public boolean hates(String hate) {
		return find(hate, hates);
	}
	
	private String getFanaticism(Post post) {
		Iterator<String> it = post.getHashTags();
		while (it.hasNext()) {
			for(int i = 0; i < numFanaticisms; i++) {
				if(hashTags.get(i).equals(it.next())) return hashTags.get(i);
			}
		}
		return null;
	}
	
	private boolean hasFanaticism(String fanaticism) {
		Iterator<String> itLove = loves.iterator();
		Iterator<String> itHate = hates.iterator();
		while(itLove.hasNext()) {
			if(fanaticism.equals(itLove.next())) return true;
		}
		while(itHate.hasNext()) {
			if(fanaticism.equals(itHate.next())) return true;
		}
		return false;
	}
	private boolean find(String word, LinkedList<String> type) {
		Iterator<String> it = type.iterator();
		while(it.hasNext()) {
			String w = it.next();
			if(w.equalsIgnoreCase(word)) return true;
		}
		return false;
	}
}
