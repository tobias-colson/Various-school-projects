/*
ClassList.cpp
SENG1120 Assignment 3
Tobias Colson, C3184056
*/
#include <iostream> // for cout and endl
#include <cstdlib>  // for srand, rand and atoi
#include <ctime>    // for time
#include "Student.h"
#include "BinaryTree.h"
#include <vector>
#include <algorithm>

using namespace assignment3;

int main(int argc, char* argv[]) {

	//Member variables
	float l;
	std::size_t k;
	
	//New binary tree object
	BinaryTree<Student> studentTree;
	//Vectors to store student names/grades
	static std::vector<std::string>  studentList;
	static std::vector<float> studentGrades;

	// Initialises vector with student names
	studentList = { "Hugo", "Jamey", "Lidong", "Karm", "Jianjun", "Edouard", "Tyson", "Aaron",
					"Kundayi", "Max", "Tobias", "Sarah", "Prajna", "Sebastian", "Benoit", "Binbin", "Trent",
					"Corey", "Darcy", "Fadzayi", "Blake", "Matthem", "Stephen", "Elijah", "Jonathan", "Kopara",
					"Bryce", "Lea", "Brandon", "Cliff", "Cameron", "Naneth", "Reid", "Hadia", "Andrew",
					"Jesse", "Robert", "Radhika", "Sean", "Jacob", "Timothy", "Liam", "James", "David",
					"Luke", "Lachlan", "Dylan", "Mitchell", "Ryan", "Geoffrey" };

	//Initialises vector with student grades
	studentGrades = { 87, 40, 37, 78, 79, 50, 42, 47, 96, 57, 99, 55, 85, 58, 69, 53, 75, 12, 32, 54, 20, 34, 30, 87, 40,
					  88, 61, 25, 88, 17, 49, 56, 87, 19, 46, 31, 72, 7, 28, 6, 8, 26, 40, 21, 57, 97, 73, 36, 27, 72 };

	//Creates new binary tree with random assigned grade for each student
	for (int j = 0; j < 50; j++)
	{

		studentTree.insert(*new Student(studentList[j], studentGrades[j]));
	}

	//Prints tree pre-failure removal
	studentTree.printInOrder();
	//Calculates number of high distinctions in tree
	studentTree.fail(50);
	k = studentTree.ffs();
	std::cout << std::endl << std::endl << "FF: " << k << std::endl << std::endl;
	//Calculates sum of all scores in tree
	studentTree.sum();
	//Calculates average of all scores in tree using sum
	l = studentTree.average();
	std::cout << "Average: " << l << std::endl << std::endl;
	//Removes all students with scores below 50
	for (int j = 0; j < 50; j++)
	{
		studentTree.remove(studentList[j]);
	}
	//Recalcs average
	l = studentTree.average();
	//Reprints tree HDs and average after deletion
	studentTree.printInOrder();	
	studentTree.sum();
	std::cout << std::endl << "Average: " << studentTree.average() << std::endl;
	std::cout << std::endl << "Input name to be searched:" << std::endl;
	std::string name;
	std::cin >> name;
	studentTree.findStudent(name);
	//Deconstructs tree
	studentTree.~BinaryTree();
	return 0;
}
