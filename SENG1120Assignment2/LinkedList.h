/*
LinkedList.h
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#ifndef LinkedList_h
#define LinkedList_h

#include "Card.h"
#include "Node.h"
#include <cstdlib>
namespace assignment2
{
	template <typename T>
	class LinkedList
	{
		private:

			//	Tail of the list contains no data, 
			//	but points to the deck of cards.
			Node<T>* head;
			Node<T>* tail;
			std::size_t listLength;

		public:
			//Typedef
			//typedef Card cardType;
			//Constructors
			LinkedList();
			LinkedList(T& card);
			//Destructor
			~LinkedList();
			// Member Functions
			void addToHead(T& card);
			void addToTail(T& card);
			void copyCard(T& card, bool updown);
			T& delHead();
			T& delTail();
			T first() const;
			T last() const;
			void randNode(int j);
			void faceUp();
			int count();
			int countAll();
			std::size_t length();
			std::string face() const;
	}; 
}
#include "LinkedList.cpp"

#endif