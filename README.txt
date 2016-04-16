HOW TO RUN THIS PROGRAM:
Type in the following code and hit enter
$ ./classify.sh
Then enter your password followed enter



DOCUMENTATION

This program takes in one string as a password and determines whether it is strong or weak. We do not allow spaces in the password.
We used the free Openwall list as the dictionary in this program as it is used in the paper "Guess again (and again and again): Measuring password strength by 
simulating password-cracking algorithms" by Kelley et al. We also ignore case when comparing passwords with words provided in the dictionary. 

A password is considered strong if it meets any one of the following requirements:

1. Comprehensive 8: The password is at least 8 characters long and must include at least an uppercase letter, a lowercase letter, a symbol, and a digit. 
And the password must not contains any common password sequence and dictionary words predefined in the free Openwall list.

2. Basic 16+: The password is at least 16 characters long and must include at least four different characters.

The reason why we choose Comprehensive 8 is that it is proven to be strong in the paper "Guess again (and again and again): Measuring password strength by 
simulating password-cracking algorithms" by Kelley et al. 

The reason why we choose Basic 16 is that it is proven to be strong in the paper "Guess again (and again and again): Measuring password strength by 
simulating password-cracking algorithms" by Kelley et al. The only difference is that we require at least 4 different characters. We do this because assuming that 
a threat has read Kelley's paper, the first passwords that they would guess are the simplist ones.  Therefore, we say that passwords like "1111111111111111" are not strong passwords. 

Jared Exter and Jia Zhang