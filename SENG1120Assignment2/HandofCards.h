/*
HandofCards.h
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#ifndef HandofCards_h
#define HandofCards_h
#include "Card.h"
#include "LinkedList.h"
#include "DeckofCards.h"
namespace assignment2
{
	class HandofCards
	{
		friend class DeckofCards;

	public:
		//Typedef
		typedef Card typeCard;
		//Constructor
		HandofCards();
		//Destructor
		~HandofCards();
		//Member functions
		int count();
		int countAll();
		std::string value() const;
		void faceUp();
		void add(typeCard newCard, bool updown);
		std::size_t handSize();

		//Overloaded << Operator to output the values stored within the hands of cards
		friend std::ostream& operator<<(std::ostream& faceValue,
			const HandofCards& D)
		{
			faceValue << D.value();
			return faceValue;
		}
	private:
		//Member variables
		LinkedList<typeCard>* hand;
		std::size_t length;
	};
}
#endif
