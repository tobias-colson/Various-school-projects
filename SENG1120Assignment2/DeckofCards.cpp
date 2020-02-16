/*
DeckofCards.cpp
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#include <iostream>
#include <string>
#include "DeckofCards.h"

//Most functions are simple calls to the LinkedList member functions
//Almost all "work" is handled by the linked list
namespace assignment2
{
	DeckofCards::DeckofCards()
	{
		deck = new LinkedList<cardType>();
	}

	DeckofCards::DeckofCards(cardType& card)
	{
		deck = new LinkedList<cardType>(card);
	}

	DeckofCards::~DeckofCards()
	{
		delete deck;
	}

	void DeckofCards::insertHead(cardType& card)
	{
		deck->addToHead(card);
	}

	void DeckofCards::push(cardType& card)
	{
		deck->addToTail(card);
	}

	DeckofCards::cardType DeckofCards::deckFirst() const
	{
		return deck->first();
	}

	DeckofCards::cardType DeckofCards::deckLast() const
	{
		return deck->last();
	}

	DeckofCards::cardType& DeckofCards::pop()
	{
		return deck->delHead();
	}

	DeckofCards::cardType& DeckofCards::removeTail()
	{
		return deck->delTail();
	}

	void DeckofCards::print()
	{
			std::string allCards = deck->face();
			std::cout << allCards;
	}

	std::size_t DeckofCards::size()
	{
		length = deck->length();
		return length;
	}

	bool DeckofCards::empty()
	{
		size_t i = 0;
		std::cout << (deck->length() > i) << "\n";
		return (deck->length() > i);
	}

	//Passes a random int to the shuffle function, looping til the list is shuffled
	void DeckofCards::shuffle()
	{
		for (int i = length; i > 0; i--)
		{	
			int j = (rand() % i);
			deck->randNode(j);
		}
	}
}