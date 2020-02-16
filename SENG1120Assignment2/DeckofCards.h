/*
DeckofCards.h
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#ifndef DeckofCards_h
#define DeckofCards_h
#include "Card.h"
#include "LinkedList.h"
namespace assignment2
{

	class DeckofCards
	{
	public:
		//Typedef
		typedef Card cardType;
		//Constructors
		DeckofCards();
		DeckofCards(cardType& card);
		//Destructor
		~DeckofCards();
		//Member functions
		void insertHead(cardType& card);
		void push(cardType& card);
		cardType& pop();
		cardType& removeTail();
		cardType deckFirst() const;
		cardType deckLast() const;
		void print();
		void shuffle();
		std::size_t size();
		bool empty();
	private:
		//Member variables
		LinkedList<cardType>* deck;
		std::size_t length;
	};
}
#endif
