package JAVA;
/*Temurbek Sabirov
 Assignment_6
 Bank Accounts
 This program is build to serve as a basic
 ATM machine.
 */

import java.io.*;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) throws IOException {
            //Bank cannot have more than 100 accounts
            final int MAX_NUM_accounts = 100;

            PrintStream pS= new PrintStream(" ");



            File infile= new File(" ");
            Scanner sc= new Scanner(infile);



            //declare variables
            int [] acctnum_array =  new int [MAX_NUM_accounts];
            double [] balance_array= new double [MAX_NUM_accounts];
            int num_accts;
            int max_accts=MAX_NUM_accounts;

            pS.println("Temurbek Sabirov");

            char choice;

            menu(pS);


            num_accts=read_accts(acctnum_array, balance_array, max_accts, sc, pS);

            do{

                System.out.println("Enter a chioce:");
                choice=sc.next().charAt(0);// input characters


                switch(choice){

                    case('W'):
                        withdrawal( acctnum_array, balance_array, num_accts,pS,sc);
                        break;


                    case('D'):
                        deposit( acctnum_array, balance_array, num_accts,pS,sc);
                        break;


                    case('N'):
                        num_accts=new_acct( acctnum_array,  balance_array, num_accts,MAX_NUM_accounts ,pS,sc);

                        break;


                    case('B'):
                        balance( acctnum_array,  balance_array,  num_accts,sc,pS);
                        break;


                    case('X'):
                        num_accts= delete_acct( acctnum_array, balance_array, num_accts,pS,sc);
                        break;


                    case('Q'):

                        break;

                    default:
                        pS.print(choice+" is invalid\n\n\n");
                        break;

                }//closing switch


            }while(choice!='Q');


            print_accts( acctnum_array, balance_array, num_accts, pS);
            //Makes a file priority over console when prints
            pS.flush();
            pS.close();
            sc.close();


        }



        /* Method read_accts()
         input:
         acctnum_array[]- account numbers
         balance_array[]- amount of money
         max_accts-maximum of accounts
         out1-reference to output file
         in1- reference to input file

         process:
         taking in account numbers and balance
         output:
         sending account numbers and balance

         */
        public static int read_accts(int []acctnum_array, double []balance_array, int max_accts,Scanner sc, PrintStream pS)
        {
            //actual number of accts
            int num_accts=0;

            pS.println("account number\tbalance");

            acctnum_array[num_accts]=sc.nextInt();// input account number

            while( acctnum_array[num_accts]>=0  && num_accts<max_accts )

            {// opening while loop


                balance_array[num_accts]=sc.nextDouble();// input balance of account number

                pS.println(acctnum_array[num_accts]+"\t\t"+ balance_array[num_accts]);
                //print each account number and balance

                num_accts++;
                acctnum_array[num_accts]=sc.nextInt();// input account number

            }// closing of while loop

            pS.println("\n\n");
            return num_accts;
        }



        /* Method menu()
         * input:
         out1-a reference to the output file

         process:
         printing menu

         output:
         print menu
         */
        public static void menu(PrintStream pS){
            System.out.print("W - Withdrawal\n"
                    +"D - Deposit\n"
                    +"N - New account\n"
                    +"B - Balance\n"
                    +"Q – Quit\n"
                    +"X – Delete Account\n\n\n");



        }

        /* Method findacct()
         input:
         acctnum_array[]- account number
         num_accts- number of accounts
         account- input account number


         process:
         checking if account is valid or not

         output:
         print if account is valid or not

         */
        public static int findacct(int []acctnum_array, int num_accts, int account)
        {
            int found=-1; //assume not found

            for(int i=0; i<num_accts;i++)
                if( acctnum_array[i]==account) //search found
                    found= i;


            return found;
        }



        /* Method withdrawal()
         input:
         acctnum_array[]- account number
         balance_array[]- amount of money
         num_accts- number of accounts
         account- input account number
         in1- a reference to the input file
         out1-a reference to the output file

         process:
         check if account is valid or not
         check if there is any sufficient fund to withdraw
         check if the amount is valid or not
         withdraw money if account is valid, if there is any sufficient money, and if the amount is valid

         output:
         print money withdraw

         */
        public static void withdrawal(int []acctnum_array, double []balance_array, int num_accts, PrintStream pS, Scanner sc)
        {
            int account,found;
            double withdraw;

            System.out.println("Enter an ID:");
            account=sc.nextInt(); // input account number
            System.out.println("Enter a balance:");
            withdraw=sc.nextDouble(); // input amount to withdraw

            found=findacct(acctnum_array, num_accts, account);

            if(found==-1)// check if account is valid or not

            {
                pS.print(account+" is an invalid account\n\n\n");
            }
            else
            {
                pS.print(account+" is a valid account\n");


                if  (withdraw> balance_array[found]){
                    pS.print("error: insufficient funds"+"\n\n\n");
                }
                else
                {
                    if(withdraw>=0)
                    {
                        pS.print("You have withdrawn: "+withdraw+" \nOld balance: "+balance_array[found]+"\n");

                        balance_array[found] =balance_array[found] - withdraw;

                        pS.print("The new balance is " +balance_array[found] +"\n\n\n");
                    }
                    else
                    {

                        pS.println(withdraw+" is an invalid amount\n\n");
                    }

                }

            }

        }





        /* Method deposit()
         input:
         acctnum_array[]- account number
         balance_array[]- amount of money
         num_accts- number of accounts
         account- input account number
         in1- a reference to the input file
         out1-a reference to the output file

         process:
         check if account is valid or not
         check if the amount is valid or not
         deposit money if account is valid and the amount is valid

         output:
         print money deposit

         */
        public static void deposit(int []acctnum_array, double []balance_array, int num_accts, PrintStream pS, Scanner sc)
        {
            int account,found;
            double deposit;

            System.out.println("Enter an ID:");
            account=sc.nextInt(); // input account number
            System.out.println("Enter a balance:");
            deposit=sc.nextDouble(); // input amount to deposit

            found=findacct(acctnum_array, num_accts, account);

            if(found==-1)// check if account is valid or not

            {
                pS.print(account+" is an invalid account\n\n\n");
            }
            else
            {
                pS.println(account+" is a valid account");

                if(deposit>=0) {

                    pS.print("You have deposited : "+deposit
                            +"\nOld balance was: "+balance_array[found]+"\n");

                    balance_array[found]=balance_array[found] + deposit;

                    pS.print("Your new balance is " +balance_array[found] +"\n\n\n");
                }
                else
                {
                    pS.println(deposit+" is an invalid amount\n\n");

                }

            }

        }


        /* Method new_accts()
         input:
         acctnum_array[]- account number
         balance_array[]- amount of money
         num_accts- number of accounts
         account- input account number
         in1- a reference to the input file
         out1-a reference to the output file

         process:
         check if account exist or not
         check if bank can hold any more accounts
         if account exist and the bank can hold more accounts,then create a new account

         output:
         print new account

         */
        public static int new_acct(int []acctnum_array, double []balance_array, int num_accts,int MAX_NUM_accounts, PrintStream pS, Scanner sc)
        {
            int account,found;

            System.out.println("Enter an ID:");
            account=sc.nextInt(); // input account number


            found=findacct(acctnum_array, num_accts, account);

            if(found==-1)

            {


                if(num_accts<MAX_NUM_accounts)
                {

                    acctnum_array[num_accts]=account;
                    balance_array[num_accts]=0.0;
                    num_accts++;
                    pS.println(account + " is now a new account\n"
                            + "balance: "+balance_array[num_accts]+"\n\n");


                }
                else

                    pS.println("this account can't hold any more accounts\n\n");

            }
            else
            {
                pS.print(account+" is invalid because it exists\n\n\n");

            }

            return num_accts;
        }



        /* Method balance()
         input:
         acctnum_array[]- account number
         balance_array[]- amount of money
         num_accts- number of accounts
         account- input account number
         in1- a reference to the input file
         out1-a reference to the output file

         process:
         check if account is valid or not
         if account is valid, then show the balance

         output:
         print balance

         */
        public static void balance(int []acctnum_array, double []balance_array, int num_accts,Scanner sc, PrintStream pS)
        {
            int account,found;

            System.out.println("Enter an ID:");
            account=sc.nextInt(); // input account number

            found=findacct(acctnum_array, num_accts, account);

            if(found==-1)// check if account is valid or not

            {
                pS.print(account+" is an invalid account\n\n\n");
            }
            else
            {
                pS.print(account+" is a valid account\n");
                pS.print("The account number is "+acctnum_array[found]+
                        "\nthe balance is "+balance_array[found]+".\n\n\n");




            }

        }

        /* Method delete_acct()
         input:
         acctnum_array[]- account number
         balance_array[]- amount of money
         num_accts- number of accounts
         account- input account number
         in1- a reference to the input file
         out1-a reference to the output file

         process:
         check if account exist or not
         check if account's balance is non-zero
         delete old account if account exist and if account's balance is non-zero

         output:
         print deleted old account

         */
        public static int delete_acct(int []acctnum_array, double []balance_array,
                                      int num_accts, PrintStream pS, Scanner sc) {
            int account,found;

            System.out.println("Enter an ID:");
            account=sc.nextInt(); // input account number

            found=findacct(acctnum_array, num_accts, account);

            if(found==-1)// check if account exist ot not

            {
                pS.print(account+" can't be deleted because it doesn't exist.\n\n\n");


            }

            else

            {
                int i,a=0;
                for (i = 0; i < num_accts; i++)
                {
                    if(acctnum_array[i] == account )
                    {
                        a=i;
                    }
                }

                if( balance_array [a]==0.0) {
                    while(a<num_accts)
                    {
                        acctnum_array [a] = acctnum_array[a+1];
                        acctnum_array [num_accts] = acctnum_array[num_accts+1];

                        balance_array [a] = balance_array[a+1];
                        balance_array [num_accts] = balance_array[num_accts+1];

                        a++;

                    }
                    num_accts=num_accts-1;
                    pS.print(account+" is  deleted.\n\n\n");
                }
                else
                {
                    pS.print(account+" can't be deleted because the balance is a non-zero.\n\n\n");
                }

            }

            return num_accts;
        }


        /* Method print_accts()
         input:
         acctnum_array[]- account number
         balance_array[]- amount of money
         num_accts- number of accounts
         account- input account number
         out1-a reference to the output file

         process:
         show account and balance

         output:
         print account and balance

         */
        public static void print_accts(int []acctnum_array, double []balance_array, int num_accts, PrintStream pS)
        {
            int i;
            pS.println("account number\tbalance");
            for(i=0; i<num_accts;i++)
                pS.println(acctnum_array[i]+"\t\t"+balance_array[i]);


        }

    }