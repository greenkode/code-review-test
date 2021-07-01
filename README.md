# code-review-test
simple code with bugs for code review test.


### Requirements
Create a Money Transfer Service and implement a money transfer method
that will transfer money from one bank account to the other.

1. Check that the accounts exist, if not the method should fail
2. Check that neither of the accounts are blacklisted before transfering
3. The fee rules are as follows
* from 0 to 100,000 (no fee)
* from 100 to 500,000 (N 50)
* from 500,000 and above (N 100)