import java.util.ArrayList;
import java.util.Scanner;
   import java.io.*;

    public class Hangman
   {
       public static void main(String[] args) throws IOException
      {
         Scanner input = new Scanner(System.in);
         String choice;
         String[] leftOverBodyParts= {"Head","LeftEye","RightEye","Nose","Mouth","Body","LeftArm","RightArm","LeftLeg","RightLeg"};
         ArrayList<String> guessedLetters = new ArrayList<String>();
         char letter;
         char playAgain = 'n';
         String secretWord;
         StringBuffer underscore;
         String guessedsofar;
         final int TOTALBODYPARTS = 10;
         int partsofthebody;
         boolean finish;
		 int count=0;
     
      
          //engishWords.txt contains 50 words
         Scanner inputfile = new Scanner(new FileReader("englishWords.txt"));
      
         do {
            secretWord = inputfile.next();
            guessedsofar = "";
            finish = false;
            partsofthebody = TOTALBODYPARTS;
         
         // underscores for letters for secret word 
            underscore = makeUnderscore(secretWord);
            for(int i=0;i<leftOverBodyParts.length;)
            {
          
            while (! finish)
            {
               System.out.println("Secret Word you have to guess is : " + underscore);
               System.out.println("Guessed letters so far: " + guessedsofar);
               System.out.print("enter your guess (it can either letter or a word): \n");
               choice = input.next();
               letter = choice.charAt(0);
            // for word guess
            
               if (choice.length() > 1)  
               {
                  if (choice.equals(secretWord))
                     System.out.println("Congratulations! you won");
                  else
                     System.out.println("OOPS! you lost.. You Hanged the Man...Try Again");
                  finish=true;
               }
               else
                   // for a letter guess
               {
                   if(guessedsofar.indexOf(letter)!= -1)
                   {    
					   guessedsofar += letter;
					   count++;

					   if(count<=1){
                       System.out.print("Already Guessed.!!! Please guess a different letter \n ");
					   }
					   else
					   {
						   --partsofthebody;
                     System.out.print("your Guess is wrong and you lost " +leftOverBodyParts[i]);
                     i++;
					   }

                   }
                   else
                   {
                  
                  guessedsofar += letter;
                  if (secretWord.indexOf(letter) < 0)  
                  {    
                      
                     --partsofthebody;
                     System.out.print("your Guess is wrong and you lost " +leftOverBodyParts[i]);
                     i++;
                                                
                  }
                  else 
                      //If the letter is in the secret word put the letter in the underscore
                  {
                      
                     letterInSecretWord(underscore, letter, secretWord);                                   
                  }
               }
                  System.out.println(" \n Bodyparts left so far are :" +partsofthebody );
                  if (partsofthebody == 0)
                  {    
                System.out.println("OOPS! you lost.. You Hanged the Man...Try Again");
                  finish = true;
                  }
               }
                  if (secretWord.equals(underscore.toString()))
                  {    
                System.out.println("Congratulations! you won");
                  finish = true;
                  }
               } 
            
            } 
         
            if (inputfile.hasNext())
            {
               System.out.println("Do you want to play again (Y/N)?: ");
               playAgain = input.next().charAt(0);
            }
            else
               System.out.println("Thank you for playing Hangman..see you again");
         } while (inputfile.hasNext() && (playAgain == 'Y' || playAgain == 'y'));
      } 
      
       public static StringBuffer makeUnderscore(String s)
       {
          StringBuffer underscore = new StringBuffer(s.length());
          for (int count=0; count < s.length(); count++)
              underscore.append("-");
          return underscore;
       }
    public static void letterInSecretWord( StringBuffer underscore, char letter, String secretword)
      {
         for (int i = 0; i < secretword.length(); i++)
            if (secretword.charAt(i) == letter)
                underscore.setCharAt(i, letter);
         System.out.print("Your Guessed Letter is Correct \n");
      }
   
       
   }

