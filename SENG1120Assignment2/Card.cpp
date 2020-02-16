/*
Card.cpp
SENG1120 Assignment 2
Tobias Colson, C3184056
*/
#include <iostream>
#include <string>
#include "Card.h"

namespace assignment2
{
	//Constructors
	Card::Card()
	{
		face = "";
		value = 0;
		faceup = false;
	}

	Card::Card(std::string fc, int val, bool down)
	{
		face = fc;
		value = val;
		faceup = down;
	}
	// Required getters/setters for cards
	void Card::setFace(std::string f)
	{
		face = f;
	}

	void Card::setValue(int v)
	{
		value = v;
	}

	void Card::setFaceup(bool up)
	{
		faceup = up;
	}

	std::string Card::getFace() const
	{
		return face;
	}

	int Card::getValue() const
	{
		return value;
	}

	bool Card::getFaceup() const
	{
		return faceup;
	}
}