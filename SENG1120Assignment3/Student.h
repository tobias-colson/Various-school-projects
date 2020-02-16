/*
Student.h
SENG1120 Assignment 3
Tobias Colson, C3184056
*/
#ifndef Student_h
#define Student_h

#include <iostream>
#include <string>

namespace assignment3
{
	class Student
	{
	private:
		std::string name;
		float grade;

	public:

		//Constructors
		Student();

		Student(std::string nm, float grd);

		// Required accessors/mutators for students
		void setName(std::string n);
		void setGrade(float g);

		std::string getName() const;
		float getGrade() const;

		//Overloaded operators to handle object comparison
		friend std::ostream& operator << (std::ostream& out, const Student& target);
		friend bool operator < (const Student& student1, const Student& student2);
		friend bool operator > (const Student& student1, const Student& student2);
		friend bool operator == (const Student& student1, const Student& student2);
	};
}
#endif