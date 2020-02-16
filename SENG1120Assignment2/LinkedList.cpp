/*
LinkedList.cpp
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#include <assert.h>
#include <string>

//using namespace std;
namespace assignment2
{
	//Default constructor initialising head and tail pointers to NULL
	template <typename T>
	LinkedList<T>::LinkedList()
	{
		head = NULL;
		tail = NULL;
		listLength = 0;
	}

	//Parameterized constructor
	template <typename T>
	LinkedList<T>::LinkedList(T& card)
	{
		head = new Node<T>(card);
		tail = head;
		listLength = 1;
	}

	//Destructor to remove list from memory
	template <typename T>
	LinkedList<T>::~LinkedList()
	{
		Node<T>* temp;
		while (head != NULL)
		{
			temp = head->next;
			delete head;
			head = temp;
		}
		tail = NULL;
	}

	//Creates new node and inserts it at the head of the list
	template <typename T>
	void LinkedList<T>::addToHead(T& card)
	{
		if (listLength == 0) //Deals with the empty list case, initialising the list
		{
			head = new Node<T>(card);
			tail = head;
			listLength = 1;
		}
		else					//Inserts nodes at head
		{
			Node<T>* headInsert = new Node<T>(card);
			head->setPrevious(headInsert);
			headInsert->setNext(head);
			head = headInsert;
			listLength++;      //Increments listLength, which is used to track list size
			headInsert = NULL;
		}
	}

	//Does the same as addToHead, only at the tail of the linked list instead
	template <typename T>
	void LinkedList<T>::addToTail(T& card)
	{
		if (listLength == 0)
		{
			head = new Node<T>(card);
			tail = head;
			listLength = 1;
		}
		else
		{
			Node<T>* tailInsert = new Node<T>(card);
			tail->setNext(tailInsert);
			tailInsert->setPrevious(tail);
			tail = tailInsert;
			listLength++;
			tailInsert = NULL;
		}
	}

	//Returns the data contained within the head node
	template <typename T>
	T LinkedList<T>::first() const
	{
		return head->getCard();
	}

	//Returns the data contained within the tail node
	template <typename T>
	T LinkedList<T>::last() const
	{
		return tail->getCard();
	}

	//Deletes the head node whilst returning a copy of the data contained within it
	template <typename T>
	T& LinkedList<T>::delHead()
	{

		assert(listLength != 0); //Ensures the list isn't empty, therefore not removing non-existent nodes

		if (listLength == 1)
		{
			Node<T>* tempHead = head;
			head = NULL;
			tail = NULL;
			listLength--;
			return tempHead->getCard();
		}
		else
		{
			Node<T>* tempHead = head;
			head = tempHead->next;
			listLength--;
			return tempHead->getCard();
		}
	}

	//Same as delHead, merely at the tail instead
	template <typename T>
	T& LinkedList<T>::delTail()
	{
		assert(listLength != 0);

		if (listLength == 1)
		{
			Node<T>* tempTail = tail;
			head = NULL;
			tail = NULL;
			listLength--;
			return tempTail->getCard();
		}
		else
		{
			Node<T>* tempTail = tail;
			tail = tempTail->previous;
			listLength--;
			return tempTail->getCard();
		}
	}

	//Shuffles the data contained within the nodes using the rand int generated by the srand seed
	template <typename T>
	void LinkedList<T>::randNode(int j)
	{
		Node<T>* tempNode = head;
		T current;
		T tempTail;
		for (int i = 0; i < j; ++i)
		{
			tempNode = tempNode->next;
		}
		current = tempNode->getCard();
		tempTail = tail->getCard();
		tail->setCard(current);
		tempNode->setCard(tempTail);
		return;
	}

	//Returns the value stored in listLength
	template <typename T>
	std::size_t LinkedList<T>::length()
	{
		return listLength;
	}

	//Returns a concatenated string containing all the face values of the cards
	//With a special case to return the facedown dealer card
	template <typename T>
	std::string LinkedList<T>::face() const
	{
		std::string allCards;
		Node<T>* current = head;
		while (current != NULL)
		{
			if (current->card.getFaceup() == true)
			{
				allCards += current->card.getFace() + " ";
				current = current->next;
			}
			else
			{
				allCards += "?-? ";
				current = current->next;
			}
		}
		return allCards;
	}

	//Checks whether a card is face up or not
	//Then calculates the value of all faceup cards
	//Returning it as an int
	template <typename T>
	int LinkedList<T>::count()
	{
		Node<T>* temp = head;
		int cardValue = 0;
		assert(listLength != 0);
		while (temp != NULL)
		{
			if (temp->card.getFaceup() == true)
			{
				cardValue += temp->card.getValue();
				temp = temp->next;
			}
			else
			{
				temp = temp->next;
			}
		}
		return cardValue;
	}

	//Returns the value of all cards, whether faceup or not
	template <typename T>
	int LinkedList<T>::countAll()
	{
		Node<T>* temp = head;
		int cardValue = 0;
		assert(listLength != 0);

		while (temp != NULL)
		{
			cardValue += temp->card.getValue();
			temp = temp->next;
		}
		return cardValue;
	}

	//Sets boolean value of all cards to be face up (true)
	template <typename T>
	void LinkedList<T>::faceUp()
	{
		Node<T>* temp = head;
		while (temp != NULL)
		{
			temp->card.setFaceup(true);
			temp = temp->next;
		}
	}

	//Takes a card and a boolean to create a new card in HandofCards
	//This allows the cards to be either faceup or facedown on initialisation
	//Which is necessary to create the dealer hand
	template <typename T>
	void LinkedList<T>::copyCard(T& card, bool updown)
	{
		if (listLength == 0)
		{
			head = new Node<T>(card);
			head->card.setFaceup(updown);
			tail = head;
			listLength = 1;
		}
		else
		{
			Node<T>* tailInsert = new Node<T>(card);
			tailInsert->card.setFaceup(updown);
			tail->setNext(tailInsert);
			tailInsert->setPrevious(tail);
			tail = tailInsert;
			listLength++;
			tailInsert = NULL;
		}
	}
}