//Finite Automata program to find a string starts and ends with b
#include<iostream>
#include<time.h>
#include<stdlib.h>
using namespace std;

int main(){
	int x=0;
	int flag=0;
	
	srand(time(0));
	int maxi = 1+rand()%15;
	

	
	while(x<=maxi){
		char word='a'+ rand()%2;  //generate first word of string
		cout<<word<<" ";
	
		//if the first character is 'b'	
		if(x==0 && word=='b'){
			flag++;
		}
		if(x==maxi && word=='b'){
				flag++;
		}
		x++;
	}
	
	cout<<"\n Value of flag \n "<<flag;
	if(flag>1){
		cout<<" \nYes, It statisfies the condition";
	}
	else{
		cout<<" \nNo It doesn't satisfies the Condition";
	}
	
	return 0;
}
