#include<iostream>
#include<regex>
using namespace std;

int main(){
	string str;
	while(true){
		cin>>str;
		
		//regex e("abc");
		//regex e("[a-z]");		//only single character from a to z
		//regex e("[A-za-z0-9]");		//Only single character from ranges A-Z or a-z or 0-9
		
		
		regex d("[a-z]+");	//one or multiple characters from a to z
		//regex e("[a-z]*");	//zero or multiple characters from a to z
		//regex e("\\.");		//for nay special character,, here . is accepted
		//regex e("abc*");		//start with ab followed by zero or multiple c
		//regex e("ab[cdef]*");	//starts with ab followed by zero or multiple characters insdie square brackets
		
		//regex e("[a-zA-Z_][a-zA-Z_0-9]*\\.[a-zA-Z0-9]+");   
		/*starting witch small or captial single alphabet, following _ then 0 or
		many alphanumeric then . then alphanumerics*/
		
		bool match=regex_match(str, e);
		cout<<(match? "Accepted":"Not Accepted")<<endl;
	}
	
}



