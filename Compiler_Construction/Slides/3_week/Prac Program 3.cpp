#include <iostream> 
#include <string> 
#include <regex> 
#include <iterator> 
using namespace std; 
   
int main() 
{  
    string mystr = "This is compiler construction class\n"; 
 
    cout<<"Input string: "<<mystr<<endl;
       
    // regex to match string beginning with 'p' 
    regex regexc("c[a-zA-z]+"); 
    cout<<"Replace the word 'class with word 'lab' : "; 
    // regex_replace() for replacing the match with the word 'lab'  
    cout << regex_replace(mystr, regexc, "lab"); 
     
    string result; 
       
    cout<<"Replace the word 'lab' back to 'class': ";
    // regex_replace( ) for replacing the match back with 'class' 
    regex_replace(back_inserter(result), mystr.begin(), mystr.end(), 
                  regexc,  "class"); 
    cout << result; 
}


