# Tłı̨chǫ Dictionary

## Project Proposal

I would like to make a dictionary for the Tłı̨chǫ language. There Tłı̨chǫ language is an Indigenous
language with only several thousand speakers. The number of speakers is on a decline and a resource
like a digital dictionary will help to make the language more accessible. The users will be the
native speaker who have Tłı̨chǫ first language and want to share their knowledge with the world.
Another user base would be people who want to learn the Tłı̨chǫ language or to improve their
vocabulary.

This project is of very personal interest to me because I am Tłı̨chǫ myself. Growing up I was never
given the opportunity to learn my language. My parents never speak it to me and I often felt
like an outsider as I couldn't converse with my grandparents. Now that I live away from our traditional
lands it is near impossible to learn the Tłı̨chǫ language as there are so few speakers.

# Instructions

1. To find visual, start program.
2. To find first required action, click the "**start**" button, the new window that opens is the dictionary. At the
   bottom, you will see an "**add**" button click it and a new window will open. Fill out the new word and click
   "**enter**". You will
   see the new word added to the dictionary.
3. To find second required action, click on a word in the list and then click the "**delete**" button. The
   word will now be deleted.
4. To find the third required action, type a word into the search bar at the top and press enter.
   This new window will display a specific Tłı̨chǫ word, or translations for an english word.
5. To save the state, click the "**save**" button.
6. To load the state, restart the application and click the "**load**" button.

## Phase 4: Task 3

I want to refactor Favourites and Allwords. I don't think they are both needed.
I think that there may not need to be such a complex structure. I think maybe just having the WordSet class
not be abstract and differentiate between the two different classes in the GUI. Then I can have as many different kinds
lists as I want. Also, I'm thinking of adding a WordOfTheDay class to keep my classes focused on one thing.

## User Stories

- As a user, I want to be able to add an arbitrary amount of words to my dictionary.
- As a user, I want to be able to save all the words in my dictionary to file.
- As a user, I want to be able to delete a word.
- As a user, I want to be able to view a list of all the words in my dictionary.
- As a user, I want to be able to search for a specific word, or translation for an english word.

## Citations

Save functionality modelled after JsonSerializationDemo from CPSC 210.
