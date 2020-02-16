/*
HandofCards.cpp
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#include <iostream> // for cout and endl
#include "HandofCards.h"

//Similar to DeckofCards.cpp, most functions are simple calls to linked list functions
namespace assignment2
{
	HandofCards::HandofCards()
	{
		hand = new LinkedList<typeCard>();
	}

	HandofCards::~HandofCards()
	{
		delete hand;
	}

	int HandofCards::count()
	{
		return hand->count();
	}

	int HandofCards::countAll()
	{
		return hand->countAll();
	}

	std::string HandofCards::value() const
	{
		std::string cardPrint;
		return (cardPrint = hand->face());
	}

	void HandofCards::faceUp()
	{
		hand->faceUp();
	}

	void HandofCards::add(typeCard newCard, bool updown)
	{
		hand->copyCard(newCard, updown);
	}

	std::size_t HandofCards::handSize()
	{
		length = hand->length();
		return length;
	}
}