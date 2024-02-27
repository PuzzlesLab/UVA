#include <iostream>
#include <string>

using namespace std;

int main()
{
    string pairs[] = {
        "eu        ",
        "tu        ",
        "ele/ela   ",
        "nos       ",
        "vos       ",
        "eles/elas "};
    pairs[3][1] = (char)243;
    pairs[4][1] = (char)243;

    string s1;
    string s2;
    bool first = true;
    while (cin >> s1 >> s2)
    {
        if (first)
            first = false;
        else
            cout << endl;

        cout << s1 << " (to " << s2 << ")" << endl;
        string end = s1.length() >= 2 ? s1.substr(s1.length() - 2, s1.length()) : "";
        if (end == "ar" || end == "er")
        {
            string root = s1.substr(0, s1.length() - 2);
            char tv = s1[s1.length() - 2];
            cout << pairs[0] << root << "o" << endl;
            cout << pairs[1] << root << tv << "s" << endl;
            cout << pairs[2] << root << tv << endl;
            cout << pairs[3] << root << tv << "mos" << endl;
            cout << pairs[4] << root << tv << "is" << endl;
            cout << pairs[5] << root << tv << "m" << endl;
        }
        else if (end == "ir")
        {
            string root = s1.substr(0, s1.length() - 2);
            char tv = s1[s1.length() - 2];
            cout << pairs[0] << root << "o" << endl;
            cout << pairs[1] << root << "es" << endl;
            cout << pairs[2] << root << "e" << endl;
            cout << pairs[3] << root << tv << "mos" << endl;
            cout << pairs[4] << root << tv << "s" << endl;
            cout << pairs[5] << root << "em" << endl;
        }
        else
        {
            cout << "Unknown conjugation" << endl;
        }
    }
}