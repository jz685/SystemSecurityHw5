HOW TO RUN THIS PROGRAM:
./classify.sh



DOCUMENTATION

This program takes in one string as a password and determines whether it is strong or weak. We do not allow spaces in the password.
We used the free Openwall list as the dictionary in this program as it is used in the paper "Guess again (and again and again): Measuring password strength by 
simulating password-cracking algorithms" by Kelley et al. We also ignore case when comparing passwords with words provided in the dictionary. 

A password is considered strong if it meets any one of the following requirements:

1. Comprehensive 8+: The password is at least 8 characters long and must include at least an uppercase letter, a lowercase letter, a symbol, and a digit. 
And the password must not contains any common password sequence and dictionary words predefined in the free Openwall list that is at least 2 characters long.

2. Basic 16+: The password is at least 16 characters long and must include at least four different characters.

The reason why we choose Comprehensive 8 is that it is proven to be strong in the paper "Guess again (and again and again): Measuring password strength by 
simulating password-cracking algorithms" by Kelley et al. The only difference is that we allow password that contains dictionary words of length 2 characters or less. 
We decide to allow password that contains dictionary words of length 2 characters or less is because in the direction we are using, where are words like "1" and "a".
And it is clearly not correct to say that any password contains an "a" or "1" must be a weak password. 

The reason why we choose Basic 16 is that it is proven to be strong in the paper "Guess again (and again and again): Measuring password strength by 
simulating password-cracking algorithms" by Kelley et al. The only difference is that we require at least 4 different characters. We do this because assuming that 
a threat has read Kelley's paper, the first passwords that they would guess are the simplist ones.  Therefore, we say that passwords like "1111111111111111" are not strong passwords. 

Jared Exter and Jia Zhang