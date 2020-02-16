/*
BinaryTree.h
SENG1120 Assignment 3
Tobias Colson, C3184056
*/

#include <assert.h>
#include <string>
#include <iomanip>

namespace assignment3
{
	//Default constructor
	template <typename T>
	BinaryTree<T>::BinaryTree()
	{
		root = NULL;
		numNodes = 0;
	}

	//Parameterized constructor
	template <typename T>
	BinaryTree<T>::BinaryTree(T& student)
	{
		root = new Node<T>(student);
		numNodes = 1;
	}

	//Destructor to remove tree from memory
	template <typename T>
	BinaryTree<T>::~BinaryTree()
	{
		destroy(root);
	}

	//Used by deconstructor to remove tree from memory
	template <typename T>
	void BinaryTree<T>::destroy(Node<T>* current)
	{
		if (current != NULL)
		{
			if ((current->getLeft() == NULL) && (current->getRight() == NULL))
			{
				current = NULL;
				delete current;

			}
			else
			{
				destroy(current->getLeft());
				destroy(current->getRight());
			}
		}
		return;
	}

	//Returns size of the tree, by number of nodes in it
	template <typename T>
	std::size_t BinaryTree<T>::size()
	{
		return numNodes;
	}

	//Returns the student stored in the tree's root node
	template <typename T>
	T BinaryTree<T>::treeRoot() const
	{
		return root->getStudent();
	}

	//Starter function for the recursive insert function, checks for empty tree
	template <typename T>
	void BinaryTree<T>::insert(T& student)
	{
		if (root == NULL)
		{
			root = new Node<T>(student);
			numNodes++;
		}
		else
		{
			addNew(root, student);
		}
	}

	//Recursive add function for adding new student nodes to the tree
	//Checks whether the student to be inserted is on the right or left of the current node
	//Additionally checks whether leaf nodes are empty and inserts
	template <typename T>
	void BinaryTree<T>::addNew(Node<T>* current, T& student)
	{
		int relation = compare(student, current->getStudent());

		if (relation == 0 || relation > 0)
		{
			if (current->getRight() == NULL)
			{
				current->setRight(new Node<T>(student, current));
				numNodes++;
				return;
			}
			else
				addNew(current->getRight(), student);
		}
		else
		{
			if (current->getLeft() == NULL)
			{
				current->setLeft(new Node<T>(student, current));
				numNodes++;
				return;
			}
			else
				addNew(current->getLeft(), student);
		}
	}

	//Comparison function for comparing two objects
	template <typename T>
	int BinaryTree<T>::compare(T a, T b)
	{
		if (a < b) { return -1; }

		else if (a == b) { return 0; }

		else { return 1; }
	}

	//Recurvise starter function for printing tree
	template <typename T>
	void BinaryTree<T>::printInOrder()
	{
		inOrder(root);
	}

	//Recursively prints the tree starting from the root
	template <typename T>
	void BinaryTree<T>::inOrder(Node<T>* node)
	{
		if (node != NULL)
		{
			inOrder(node->getLeft());
			std::cout << "( " << node->getStudent().getName() << " ";
			std::cout << std::fixed << std::setprecision(2) << node->getStudent().getGrade() << " ) ";

			inOrder(node->getRight());
		}
	}

	//Starter function for recursive function to check for fail grades
	template <typename T>
	void BinaryTree<T>::fail(float grade)
	{
		if (numNodes == 0)
			return;
		else
		{
			return fail(root, grade);
		}
	}

	//Recursive function for checking for fail grades, incrementing a counter when one is found
	template <typename T>
	void BinaryTree<T>::fail(Node<T>* current, float grade)
	{
		if (current != NULL)
		{
			fail(current->getLeft(), grade);

			if (grade > current->getStudent().getGrade())
				ff++;

			fail(current->getRight(), grade);
		}

		return;
	}

	//Returns number of fails
	template <typename T>
	size_t BinaryTree<T>::ffs()
	{
		return ff;
	}

	//Starter function for recursively adding all grades together
	template <typename T>
	void BinaryTree<T>::sum()
	{

		sumAll = 0;

		if (numNodes == 0)
			return;
		else
		{
			return sum(root);
		}
	}

	//Recursive function that sums all grades in the tree together
	template <typename T>
	void BinaryTree<T>::sum(Node<T>* current)
	{


		if (current != NULL)
		{
			sum(current->getLeft());

			sumAll += current->getStudent().getGrade();

			sum(current->getRight());
		}

		return;
	}

	//Averages the grades in the tree using the sum and number of nodes
	template <typename T>
	float BinaryTree<T>::average()
	{
		if (numNodes == 0)
			return 0;
		else
			return (sumAll / numNodes);
	}

	//Complex removal function for culling fail grades from the tree and ensuring tree structure is kept intact
	//Steps through the tree, ensures tree exists, name exists in tree and grade is < 50
	template <typename T>
	void BinaryTree<T>::remove(std::string name)
	{
		Node<T>* current;
		Node<T>* trailCurrent;
		bool found = false;

		if (numNodes == 0)
			std::cout << "Tree empty";
		else
		{
			current = root;
			trailCurrent = root;

			while (current != NULL && !found)
			{
				if (current->getStudent().getName() == name)
					found = true;
				else
				{
					trailCurrent = current;

					if (current->getStudent().getName() > name)
						current = current->getLeft();
					else
						current = current->getRight();
				}
			}

			if (current == NULL)
				std::cout << "Item not found";
			else if (found && (current->getStudent().getGrade() < 50))
			{
				if (current == root)
					remove(root);
				else if (trailCurrent->getStudent().getName() > name)
					remove(trailCurrent->getLeft());
				else
					remove(trailCurrent->getRight());
			}
		}
	}

	//Contains all options for node to be deleted including:
	//Leaf, has left child, has right child, has two children
	//When correct option is found, node is nulled, deleted and it's connections updated
	template <typename T>
	void BinaryTree<T>::remove(Node<T>* &p)
	{

		Node<T>* current;
		Node<T>* trailCurrent;
		Node<T>* temp;

		if (p == NULL)
		{
			std::cout << "No node" << std::endl;
			return;
		}

		else if (p->isLeaf())
		{
			temp = p;
			p = NULL;
			temp = NULL;
			delete temp;
			numNodes--;
		}

		else if (p->getLeft() == NULL)
		{
			temp = p;
			p = temp->getRight();
			temp = NULL;
			delete temp;
			numNodes--;
		}

		else if (p->getRight() == NULL)
		{
			temp = p;
			p = temp->getLeft();
			temp = NULL;
			delete temp;
			numNodes--;
		}

		else
		{
			current = p->getLeft();
			trailCurrent = NULL;

			while (current->getRight() != NULL)
			{
				trailCurrent = current;
				current = current->getRight();
			}

			p->setStudent(current->getStudent());

			if (trailCurrent == NULL)
			{
				p->setLeft(current->getLeft());
			}
			else
			{
				trailCurrent->setRight(current->getLeft());
			}
			numNodes--;
			delete current;
			current = NULL;
		}
	}

	//Function to traverse tree to find student using inputted name
	//Returns student data or N/A
	template <typename T>
	void BinaryTree<T>::findStudent(std::string name)
	{
		Node<T>* current;
		Node<T>* trailCurrent;
		bool found = false;

		if (numNodes == 0)
			std::cout << "Tree empty";
		else
		{
			current = root;
			trailCurrent = root;

			while (current != NULL && !found)
			{
				if (current->getStudent().getName() == name)
					found = true;
				else
				{
					trailCurrent = current;

					if (current->getStudent().getName() > name)
						current = current->getLeft();
					else
						current = current->getRight();
				}
			}

			if (current == NULL)
				std::cout << "Name not found" << std::endl;
			else if (found)
			{
				std::cout << "( " << current->getStudent().getName() << " ";
				std::cout << std::fixed << std::setprecision(2) << current->getStudent().getGrade() << " ) " << std::endl;
			}
		}
	}
}