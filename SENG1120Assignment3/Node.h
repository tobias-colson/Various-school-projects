/*
Node.h
SENG1120 Assignment 3
Tobias Colson, C3184056
*/

#ifndef Node_h
#define Node_h

#include "Student.h"
#include <cstdlib>

/*
Declaration of Node structure
To be used in the creation of the 
student binary tree
*/
namespace assignment3
{
	template <typename T>
	class Node
	{

	private:
		// Member variables
		Node* left;
		Node* right;
		Node* parent;
		T student; 

	public:
		//Default constructor for node class, 
		Node(const T& init_Student = T(), Node* init_parent = NULL, Node* init_left = NULL, Node* init_right = NULL)
		{
			student = init_Student;
			parent = init_parent;
			left = init_left;
			right = init_right;
		}

		~Node()
		{
			left = NULL;
			right = NULL;
			parent = NULL;
			delete left;
			delete right;
			delete parent;
		}

		//Accessors and mutators for handling data and pointers
		void setStudent(const T& new_Student)	{ student = new_Student; }
		void setLeft(Node* left_link)			{ left = left_link; }
		void setRight(Node* right_link)			{ right = right_link; }
		void setParent(Node* parent_link)		{ parent = parent_link; }

		T getStudent() const					{ return student; }
		T& getStudent()							{ return student; }

		const Node* &getLeft() const			{ return left; }
		Node* &getLeft()						{ return left; }

		const Node* &getRight() const			{ return right; }
		Node*  &getRight()						{ return right; }

		const Node* getParent() const			{ return parent; }
		Node*  getParent()						{ return parent; }

		//Returns true for leaf nodes
		bool isLeaf() const						{ return (left == NULL) && (right == NULL); }
		bool oneChild() const					{ return ((left == NULL) && (right != NULL)) || ((left != NULL) && (right == NULL)); }
		bool isRightChild() const				{ return student > parent->getStudent(); }
	};
}
#endif