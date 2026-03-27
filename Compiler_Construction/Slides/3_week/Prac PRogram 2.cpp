#include <iostream>
#include <string>
#include <regex>
using namespace std;
 
int main () {
 //matching a string literal
   if (regex_match ("CompilerConstruction", regex("[Comp].*") ))
      cout << "string:literal => matched\n";  
 
 	//matching a string object
   const char mystr[] = "CompilerConstructionCourse";
   string str ("thecomputer");
   regex str_expr ("(.*)(comp)(.*)");

   if (regex_match (str,str_expr))
      cout << "string:object => matched\n";
 
   if ( regex_match ( str.begin(), str.end(), str_expr ) )
      cout << "string:range(begin-end)=> matched\n";
 
   smatch sm;
   regex_match (str,sm,str_expr);
    
   regex_match ( str.cbegin(), str.cend(), sm, str_expr);
   cout << "String:range, size:" << sm.size() << " matches\n";
   
  //regex_match ( mystr, sm, str_expr, regex_constants::match_default );
   cout << "the matches are: ";
   for (unsigned i=0; i<sm.size(); ++i) {
      cout << "[" << sm[i] << "] ";
   }
}


