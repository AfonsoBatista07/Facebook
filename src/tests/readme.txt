Mooshak tests will incrementally check your implementation of the commands:

fakebook00in.txt (1 point):
Tested commands: EXIT
Context: Tests what happens in the presence of unknown commands and the EXIT command. Some of the made up commands even have a few silly parameters. Note how there is one unknown command per line!

fakebook01in.txt (2 points):
Tested commands: HELP, EXIT
Context: No error conditions are tested. Checks that commands are case insensitive.

fakebook02in.txt (10 points):
Tested commands: REGISTER, USERS, EXIT
Context: No error conditions are tested. In the listing of USERS, all counters return 0, because no friendships, posts or comments exist yet.

fakebook03in.txt (4 points):
Tested commands: REGISTER, USERS, EXIT
Context: All error conditions for USERS and REGISTER are tested at least once. As in fakebook02in, all counters return 0.

fakebook04in.txt (10 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, EXIT
Context: No error conditions are tested.

fakebook05in.txt (4 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, EXIT
Context: All error conditions for ADDFRIEND and FRIENDS are tested.

fakebook06in.txt (10 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, EXIT
Context: No error conditions are tested.

fakebook07in.txt (4 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, EXIT
Context: All error conditions of POST and USERPOSTS are tested.

fakebook08in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, COMMENT, EXIT
Context: No error conditions are tested.

fakebook09in.txt (3 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, COMMENT, EXIT
Context: All error conditions of the COMMENT command are tested.

fakebook10in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, EXIT
Context: No error conditions are tested.

fakebook11in.txt (2 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, EXIT
Context: All error conditions of the READPOST command are tested.

fakebook12in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, COMMENTSBYUSER, EXIT
Context: COMMENTSBYUSER command tested with and without error conditions.

fakebook13in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, TOPICFANATICS, EXIT
Context: TOPICFANATICS command tested with and without error conditions.

fakebook14in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, TOPICPOSTS, EXIT
Context: TOPICPOSTS command tested with and without error conditions.

fakebook15in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, POPULARPOST, EXIT
Context: POPULARPOST command tested with and without error conditions.

fakebook16in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, TOPPOSTER, EXIT
Context: TOPPOSTER command tested with and without error conditions.

fakebook17in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, RESPONSIVE, EXIT
Context: RESPONSIVE command tested with and without error conditions.

fakebook18in.txt (5 points):
Tested commands: REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, READPOST, COMMENT, SHAMELESS, EXIT
Context: SHAMELESS command tested with and without error conditions.

fakebook19in.txt (5 points):
Tested commands: All commands are tested.
Context: All commands tested with and without error conditions.




