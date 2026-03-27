#include<iostream>
using namespace std;

//Defining Class
class Room{
	private:   //Access specifier is now private
	//Data Members	
	double length;
	double width;
	double height;
	
	public:		// Members functions are public
	
	//Member function to initialize Data Members
	void inializeData(double ln, double wd, double ht){
		length=ln;
		width=wd;
		height=ht;
	}
	double area(){
		return length * width;	
	}
	
	double volume(){
		return length * width * height;
	}
};

int main(){
	Room myRoom;	//Intializing Room class Object as myRoom
	
	// pass the values of private variables as arguments
	myRoom.inializeData(47.3, 42.45,12.2);
	
	double roomarea=myRoom.area();
	double roomvolume=myRoom.volume();
	
	cout<<"Room Area "<<roomarea<<endl;
	cout<<"Room Volume"<<roomvolume<<endl;
}

