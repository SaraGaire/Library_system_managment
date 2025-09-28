#include <stdio.h>
#include <stdlib.h>
#include <string.h>


// MVP: this program just prints the API call you would make to return a loan.
// In a real deployment, you would integrate with the API using libcurl.


int main() {
char loanId[128];
printf("Scan/Enter Loan ID to return: ");
if (scanf("%127s", loanId) != 1) return 1;
printf("\nUse this command to return via API (example):\n");
printf("curl -X POST -H 'X-API-KEY: dev-demo-key-CHANGE_ME' http://localhost:8080/loans/%s/return\n", loanId);
return 0;
}
