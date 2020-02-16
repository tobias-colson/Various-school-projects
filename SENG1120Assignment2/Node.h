/*
Node.h
SENG1120 Assignment 2
Tobias Colson, C3184056
*/

#ifndef Node_h
#define Node_h

#include "Card.h"
#include <cstdlib>

/*
Declaration of Node structure
To be used in the creation of the deck of cards
Through a Linked list
*/
namespace assignment2
{
	template <typename T>
	class Node
	{
	public:
		//Typedef
		//typedef Card T;
		// Member variables
		Node* next;
		Node* previous;
		T card;
		//Default constructor for node class, 
		Node(const T& initCard = T(), Node* init_next = NULL, Node* init_previous = NULL)
		{
			card = initCard;
			next = init_next;
			previous = init_previous;
		}
		//Accessors and mutators for handling data and pointers
		void setCard(const T& new_card) { card = new_card; }
		void setNext(Node* next_link) { next = next_link; }
		void setPrevious(Node* previous_link) { previous = previous_link; }
		T getCard() const { return card; }
		T& getCard() { return card; }
		const Node* getNext() const { return next; }
		Node* getNext() { return next; }
		const Node* getPrevious() const { return previous; }
		Node*  getPrevious() { return previous; }
	};
}
#endif