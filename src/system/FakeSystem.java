package system;

import exceptions.InadequateStanceException;
import exceptions.InvalidFanaticismListException;
import exceptions.UnknownUserKindException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserCanNotComentPostException;
import exceptions.UserDoesNotExistExeption;
import exceptions.UserHasNoPostsException;
import exceptions.UserNoAccessToPostException;

public interface FakeSystem {

	boolean isFanatic(String type);
	void addUser(String type, String id, int numberFanaticisms, String[] sequence) throws UnknownUserKindException, UserAlreadyExistsException, InvalidFanaticismListException;
	void addComment(String idUserComment, String idUserAuthor, String idPost, String stance, String comment) throws UserDoesNotExistExeption, UserHasNoPostsException, UserCanNotComentPostException, UserNoAccessToPostException, InadequateStanceException;
}
