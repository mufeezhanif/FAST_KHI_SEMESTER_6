#include <iostream>
#include <iterator>
#include <regex>
#include <string>
using namespace std;
 
int main()
{
    string s = "Some people, when confronted with a problem, think "
        "\"I know, I'll use regular expressions.\" "
        "Now they have two problems.";
 
    regex self_regex("REGULAR EXPRESSIONS", regex_constants::ECMAScript | regex_constants::icase);
    if (regex_search(s, self_regex))
       
	   cout << "Text contains the phrase 'regular expressions'\n";
 
  	regex word_regex("(\\w+)");
    auto words_begin =  sregex_iterator(s.begin(), s.end(), word_regex);
    auto words_end = sregex_iterator();
 
    cout << "Found " <<distance(words_begin, words_end) << " words\n";
 
    const int N = 6;

}




