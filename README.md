# poker-game

This is a poker game coded in java for homework 8 for COMSW1004 Intro to Java

There are two ways to run this program:
1) With command line arguments
2) Just the base game

By using the command line arguments you will choose the cards for the player and the output will be what kind of hand you have

To play the game run it without any command line arguments and follow the intructions in the terminal

In the Card class I have a lot of methods that help me compare cards and check their rank and suite.
Also each Card has a name formed by the rank followed by the suit.

In the Deck class I have an array of 52 cards and a few methods.
I shuffle the deck by swapping each index with a random value to the right of it

The Player class is the biggest one as it has the most methods, some for debugging and some for the actual game.
I have accessor methods for every instance variale except for the hand of cards.
The checkHand method by checking each combination, from the most important to the least.
I have a method for each type of hand that checks is the current hand is that kind of combination.
All of them are just checking if some cards have either the same suit or same rank.

The Game class runs the actual game in which you can choose how many cards you want to change and you can do that by either writing the index or the name of the card.
If the deck doesn't have more than 5 cards the game will end.
If you run out of tokens the game will end.
You can not enter a bet thats higher than your amount of tokens. You will be asked to re enter the input
