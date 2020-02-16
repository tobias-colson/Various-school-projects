/*
Card.h
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#ifndef Card_h
#define Card_h

#include <iostream>
#include <string>

namespace assignment2
{
	class Card
	{
	private:
		std::string face;
		int value;
		bool faceup;

	public:

		//Constructors
		Card();

		Card(std::string fc, int val, bool down);

		// Required accessors/mutators for cards
		void setFace(std::string f);

		void setValue(int v);

		void setFaceup(bool up);

		std::string getFace() const;

		int getValue() const;

		bool getFaceup() const;

		//Overloaded << operator for printing value
		friend std::ostream& operator<<(std::ostream& output,
			const Card& C)
		{
			output << C.getFace();
			return output;
		}
	};
}
#endif