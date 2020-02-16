/*
Student.cpp
SENG1120 Assignment 3
Tobias Colson, C3184056
*/

#include <iostream>
#include <string>
#include "Student.h"

namespace assignment3
{
	//Constructors
	Student::Student()
	{
		name = "";
		grade = 0;
	}

	Student::Student(std::string nm, float grd)
	{
		name = nm;
		grade = grd;
	}

	// Required getters/setters for Students
	void Student::setName(std::string n)
	{
		name = n;
	}

	void Student::setGrade(float g)
	{
		grade = g;
	}

	std::string Student::getName() const
	{
		return name;
	}

	float Student::getGrade() const
	{
		return grade;
	}

	//Overloaded << operator for printing student name and grade with a space in between
	std::ostream& operator << (std::ostream& out, const Student& target)
	{
		out << target.getName() + " " + std::to_string(target.getGrade());

		return out;
	}

	//Overloaded < operator for comparaing student names
	bool operator < (const Student& student1, const Student& student2)
	{
		std::string a = student1.getName();
		std::string b = student2.getName();
		return (a.compare(b) < 0);

	}

	// Same but with > operator
	bool operator > (const Student& student1, const Student& student2)
	{
		std::string a = student1.getName();
		std::string b = student2.getName();
		return (a.compare(b) > 0);
	}

	// Again but ==
	bool operator == (const Student& student1, const Student& student2)
	{
		std::string a = student1.getName();
		std::string b = student2.getName();
		return (a.compare(b) == 0);
	}
}