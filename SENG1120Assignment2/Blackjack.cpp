/*
Blackjack.cpp
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#include <iostream> // for cout and endl
#include <cstdlib>  // for srand, rand and atoi
#include <ctime>    // for time
#include "DeckofCards.h"
#include "Card.h"
#include "HandofCards.h"

using namespace assignment2;
int main(int argc, char* argv[]) {

	// Make sure we have at least two arguments
	// The first argument will be the program name e.g. myCode.exe
	// The second argument will be the seed for the random number generator
	if (argc >= 2) {
		// Use atoi (ascii to integer) to convert our argument to a number
		// note that if the user enters an invalid number this will be 0
		int seed = atoi(argv[1]);

		// Use that number to seed the random number generator
		srand(seed);
	}
	// If we don't have at least two arguments seed the random number generator using the current time
	else {
		// Get the current time as an integer
		int seed = time(NULL);

		// Use that number to seed the random number generator
		srand(seed);
	}
	//Variables used throughout the main body
	std::string suit, tempCard, printOut;
	//typedef Card cardType;
	int choice = 1;
	DeckofCards* test =  new DeckofCards;
	HandofCards* player = new HandofCards;
	HandofCards* dealer = new HandofCards;
	//cardType card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;

	for (int i = 0; i < 4; i++) //Creates the deck of cards
	{
		if (i == 0)	//Assigns the suit to each card
		{
			suit = "-S"; 
		}
		else if (i == 1)
		{
			suit = "-H";
		}
		else if (i == 2)
		{
			suit = "-C";
		}
		else
		{
			suit = "-D";
		}
		for (int j = 2; j < 15; j++)
		{
			if (j <= 10)
			{
				tempCard = std::to_string(j) + suit; //Concatenates the suit with the card value
				test->push(*(new Card(tempCard, j, false)));	//Creates a new card object and pushes it to
																//The deck of cards linked list
			}
			else if (j == 11)
			{
				tempCard = "J" + suit;
				test->push(*(new Card(tempCard, 10, false)));
			}
			else if (j == 12)
			{
				tempCard = "Q" + suit;
				test->push(*(new Card(tempCard, 10, false)));
			}
			else if (j == 13)
			{
				tempCard = "K" + suit;
				test->push(*(new Card(tempCard, 10, false)));
			}
			else
			{
				tempCard = "A" + suit;
				test->push(*(new Card(tempCard, 11, false)));
			}
		}
	}
	test->size(); //Initialises the size of the deck of cards, used in shuffle so necessary
	test->shuffle(); //Shuffles the deck

	player->add(test->deckFirst(), true); //First player card
	//card1 = test->deckFirst(); May be used later to allow for multiple hands to be played
	test->pop(); //Removes the first card on the deck

	dealer->add(test->deckFirst(), true); //First dealer card
	//card2 = test->deckFirst();
	test->pop();

	player->add(test->deckFirst(), true); //Second player card
	//card3 = test->deckFirst();
	test->pop();

	dealer->add(test->deckFirst(), false); //Second dealer card, facedown
	//card4 = test->deckFirst();
	test->pop();

	std::cout << "\nPlayer:	  " << *player << "(" << player->count() << " points)" << std::endl; //Prints the hands dealt to player/dealer
	std::cout << "Dealer:   " << *dealer << "(" << dealer->count() << " points)" << std::endl;

	if (player->count() == 21 && dealer->countAll() != 21) //Checks for Blackjack for the player/dealer
	{
		std::cout << "Blackjack! Player is the winner!";
		test->~DeckofCards(); //Destructs the various lists
		player->~HandofCards();
		dealer->~HandofCards();
		return 0;
	}
	else if (dealer->count() == 21 && player->countAll() != 21)
	{
		std::cout << "Blackjack! Dealer is the winner!";
		test->~DeckofCards();
		player->~HandofCards();
		dealer->~HandofCards();
		return 0;
	}
	else if (player->count() == 21 && dealer->countAll() == 21)
	{
		std::cout << "Push!";
		test->~DeckofCards();
		player->~HandofCards();
		dealer->~HandofCards();
		return 0;
	}
	else 
	{
		while (choice != 2) //Run while choice is not Stand
		{
			std::cout << "\nPlayer, do you want to Hit (1) or Stand (2)?\n";
			std::cin >> choice;
			if (choice == 1)
			{
				player->add(test->deckFirst(), true); //Gives player another card
				test->pop();
				std::cout << "\nPlayer:	  " << *player << "(" << player->count() << " points)" << std::endl; //Prints new hands
				std::cout << "Dealer:   " << *dealer << "(" << dealer->count() << " points)\n" << std::endl;
				if (player->count() > 21)//Checks for player going over 21
				{
					std::cout << "Bust! Dealer wins\n"; 
					test->~DeckofCards();
					player->~HandofCards();
					dealer->~HandofCards();
					return 0;
				}
			}
		}
		while (dealer->countAll() <= 16) //Ensures dealer hits at least 17
		{
			dealer->add(test->deckFirst(), true); //Gives dealer another card
			dealer->faceUp(); //Changes all faceup boolean values to true, means all cards will be displayed, no ?-? card
			test->pop();
			std::cout << "\nPlayer:	  " << *player << "(" << player->count() << " points)" << std::endl;
			std::cout << "Dealer:   " << *dealer << "(" << dealer->count() << " points)\n" << std::endl;
			if (dealer->countAll() > 21) //Checks for dealer going over 21
			{
				std::cout << "Bust! Player wins\n";
				test->~DeckofCards();
				player->~HandofCards();
				dealer->~HandofCards();
				return 0;
			}
		}
		dealer->faceUp();
		if (player->count() > dealer->count()) //Checks for player/dealer having highest total
		{
			if (dealer->handSize() == 2)
			{
				std::cout << "\nPlayer:	  " << *player << "(" << player->count() << " points)" << std::endl;
				std::cout << "Dealer:   " << *dealer << "(" << dealer->count() << " points)\n" << std::endl;
			}
			std::cout << "Player wins!\n";
			test->~DeckofCards();
			player->~HandofCards();
			dealer->~HandofCards();
			return 0;
		}
		else if (dealer->count() > player->count())
		{
			if (dealer->handSize() == 2)
			{
				std::cout << "\nPlayer:	  " << *player << "(" << player->count() << " points)" << std::endl;
				std::cout << "Dealer:   " << *dealer << "(" << dealer->count() << " points)\n" << std::endl;
			}
			std::cout << "Dealer wins!\n";
			test->~DeckofCards();
			player->~HandofCards();
			dealer->~HandofCards();
			return 0;
		}
		else
		{
			if (dealer->handSize() == 2)
			{
				std::cout << "\nPlayer:	  " << *player << "(" << player->count() << " points)" << std::endl;
				std::cout << "Dealer:   " << *dealer << "(" << dealer->count() << " points)\n" << std::endl;
			}
			std::cout << "Push!\n";
			test->~DeckofCards();
			player->~HandofCards();
			dealer->~HandofCards();
			return 0;
		}
	}	
	test->~DeckofCards();
	player->~HandofCards();
	dealer->~HandofCards();
	return 0;
}
