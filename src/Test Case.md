# Test case for <font color = 'Orange'>_PasswordChecker feature_</font>

| №  | Check name                                                                 | Expected result         | Status                            | Comment                                                                                       |
|----|----------------------------------------------------------------------------|-------------------------|-----------------------------------|-----------------------------------------------------------------------------------------------|
| 1  | If password contains a least 8 characters 1 digit and 1 special symbol     | Password is complex     | <font color='Green'>Passed</font> |
| 2  | If password meets requirements and all letters are in upper case           | Password is complex     | <font color='Green'>Passed</font> |
| 3  | If password meets requirements and all letters are in lower and upper case | Password is complex     | <font color='Green'>Passed</font> |
| 4  | If password contains more than expected characters  and meets requirements | Password is complex     | <font color='Green'>Passed</font> |
| 5  | If password contains characters less than expected                         | Password is not complex | <font color='Green'>Passed</font> |
| 6  | If special symbol is missing from the password                             | Password is not complex | <font color='Green'>Passed</font> |
| 7  | If password contains only digits                                           | Password is not complex | <font color='Green'>Passed</font> |
| 8  | If password contains only lower case letters                               | Password is not complex | <font color='Green'>Passed</font> |
| 9  | If password contains only upper case letters                               | Password is not complex | <font color='Green'>Passed</font> |
| 10 | If password field is empty                                                 | Password is not complex | <font color='Green'>Passed</font> |
| 11 | If password meets requirements and contains cyrillic letters               | Password is not complex | <font color='Red'>Failed</font>   | 