import java.io.*;
class GameOfLife
{
    String world[][]=new String[40][40];String world_new[][]=new String[40][40];String dead="|_|",alive="|#|";int i,j,count=0,k=0,l=0;// fields/instance variables
    void delay(int time)//function to dealy the output
    {
        try{
            Thread.sleep(time);//function of class Thread to stop execution for inputted period of time
        }
        catch(InterruptedException ie)
        {
            System.out.println("Thread started a bit earlier");
        }
    }//method
    void display()//function to display the matrix
    {
        for(i=0;i<40;i++)
        {
            for(j=0;j<40;j++)
            {
                System.out.print(world_new[i][j]+" ");
            }
            System.out.println();
        }
    }//method
    void inputlife()throws IOException//function to take input from the user
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        System.out.println("Welcome to 'THE GAME OF LIFE', a cellular automaton originally invented by John Convay in 1970.\n\nRefernces:\n(1) en.wikipedia.org/wiki/Conway's_Game_of_Life  \n(2) 'The Grand Design' - Stephen Hawking and Leonard Mlodinow\n(3)http://www.dreamincode.net/forums/topic/33439-a-way-to-delay-output/\n\n ");
        System.out.println("What do you want to do?\n1. Check Examples, or\n2.Input live square positions and simulate results\n\nInput choice number ");
        int choice=Integer.parseInt(in.readLine());
        System.out.println("To make dead squares of the matrix visible input 'v' enter any other value to make it invisible");
            if(in.readLine().equals("v"))
            {
            dead="|_|"; alive="|#|";
            }
            else
            {
            dead=" ";alive="#";
            }
        for(i=0;i<40;i++)//initialising all matrix world squares to dead state
        {
            for(j=0;j<40;j++)
            {
                world[i][j]=dead;
            }
        }        
        for(i=0;i<40;i++)//initialising matrix world_new to all squares dead
        {
            for(j=0;j<40;j++)
            {
                world_new[i][j]=dead;
            }
        }
        
        switch(choice)
        {
            case 1:
            examples();break;
            case 2:
            {
                System.out.println("How many live squares do you want to initialise?");
                int obj_ini=Integer.parseInt(in.readLine());
                int a=1;
                while(obj_ini>0)
                {
                    System.out.println("Declare the position of live square "+(obj_ini-(obj_ini-a)));
                    i=Integer.parseInt(in.readLine());
                    j=Integer.parseInt(in.readLine());
                    world[i][j]=alive;a++;obj_ini--;
                }
            }break;
            default:
            System.out.println("Restart program and enter a valid value");break;
        }        
    }//method
    void compute()//function to determine the new state of the matrix/world
    {
        //survival for the inner array leaving boundary squares
        for(i=0;i<40;i++)
        {
            for(j=0;j<40;j++)
            {
                if(i>0&&i<39&&j>0&&j<39)
                {
                    for(k=(i-1);k<(i+2);k++)
                    {
                        for(l=(j-1);l<(j+2);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
                else if(i==0&&j==0)//survival logic for fist square of the first row
                {
                    for(k=i;k<(i+2);k++)
                    {
                        for(l=j;l<(j+2);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
                else if(i==0&&j==39)//survival logic for last square of the first row
                {
                    for(k=i;k<(i+2);k++)
                    {
                        for(l=(j-1);l<(j+1);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
                else if(i==39&&j==0)//survival logic for the last square of the first column
                {
                    for(k=(i-1);k<(i+1);k++)
                    {
                        for(l=j;l<(j+2);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
                else if(i==39&&j==39)//survival logic for the last square of the last column
                {
                    for(k=(i-1);k<(i+1);k++)
                    {
                        for(l=(j-1);l<(j+1);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
                else if(i==0&&j>0&&j<39)//survival logic for the elements of the first row
                {
                    for(k=i;k<(i+2);k++)
                    {
                        for(l=(j-1);l<(j+2);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
                else if(i>0&&i<39&&j==0)//survival logic for squares of the fist column
                {
                    for(k=(i-1);k<(i+2);k++)
                    {
                        for(l=j;l<(j+2);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
                else if(i>0&&i<39&&j==39)//survival logic for the elements of the last column
                {
                    for(k=(i-1);k<(i+2);k++)
                    {
                        for(l=(j-1);l<(j+1);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
                else//survival logic for squares of the last row
                {
                    for(k=(i-1);k<(i+1);k++)
                    {
                        for(l=(j-1);l<(j+2);l++)
                        {
                            if(world[k][l].equals(alive))
                            ++count;
                        }
                    }
                    if(world[i][j].equals(alive))
                    decide((count-1),i,j);
                    else
                    decide(count,i,j);
                    count=0;
                }
            }
        }
    }//method
    void examples()throws IOException//method containing predefined live squares for the simulation
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        System.out.println("Choose any one of the automata\n\n1) Gosper Glider Gun\n2) Simple Glider\n3) Crab Glider\n4) Arch automata\n5)Sign Board\n6)Line Butterfly");
        int choice=Integer.parseInt(in.readLine());
        switch(choice)
        {
            case 1:
            {
                //Gosper Glider Gun, currrently the smallest glider gun. 
                world[4][0]=alive;world[4][1]=alive;world[5][0]=alive;world[5][1]=alive;//left square(static)
                world[2][12]=alive;world[2][13]=alive;world[3][11]=alive;world[4][10]=alive;world[5][10]=alive;world[6][10]=alive;world[7][11]=alive;world[8][12]=alive;
                world[8][13]=alive;world[3][15]=alive;world[4][16]=alive;world[5][16]=alive;world[6][16]=alive;world[5][14]=alive;
                world[5][17]=alive;world[7][15]=alive;//left part of the gun (non-static)
                world[2][20]=alive;world[3][20]=alive;world[4][20]=alive;world[2][21]=alive;
                world[3][21]=alive;world[4][21]=alive;world[1][22]=alive;world[0][24]=alive;world[1][24]=alive;world[5][22]=alive;
                world[5][22]=alive;world[5][24]=alive;world[6][24]=alive;//right part of the gun(non-static)
                world[2][34]=alive;world[3][34]=alive;world[2][35]=alive;world[3][35]=alive;//right square(static)
            }break;
            case 2:
            {
                //Simple Glider
                world[1][2]=alive;world[2][3]=alive;world[3][3]=alive;world[3][2]=alive;world[3][1]=alive;
            }break;
            case 3:
            {
                //Crab
                world[22][28]=alive;world[22][29]=alive;world[21][29]=alive;world[21][30]=alive;world[23][30]=alive;world[25][31]=alive;
                world[24][32]=alive;world[24][33]=alive;world[27][33]=alive;world[27][30]=alive;world[28][30]=alive;world[28][29]=alive;
                world[30][30]=alive;world[29][28]=alive;world[30][28]=alive;world[31][29]=alive;world[31][25]=alive;world[31][26]=alive;
                world[32][25]=alive;world[32][26]=alive;world[30][23]=alive;world[29][21]=alive;world[29][22]=alive;world[28][22]=alive;
                world[28][23]=alive;
            }break;
            case 4:
            {
                //Arch automata
                world[20][22]=alive;world[21][21]=alive;world[21][22]=alive;world[21][23]=alive;world[22][20]=alive;world[22][21]=alive;
                world[22][23]=alive;world[22][24]=alive;world[23][21]=alive;world[23][23]=alive;
            }break;
            case 5:
            {
                //Sign Board 
                 world[15][15]=alive;world[15][16]=alive;world[15][17]=alive;world[15][18]=alive;world[15][19]=alive;world[15][20]=alive;world[15][21]=alive;world[15][22]=alive;world[15][23]=alive;world[15][24]=alive;
            }break;
            case 6:
            {
                //Line butterfly
                world[15][13]=alive;world[15][14]=alive;world[15][15]=alive;world[15][16]=alive;world[15][17]=alive;world[15][18]=alive;world[15][19]=alive;world[15][20]=alive;
            }break;
            default:
            System.out.println("Restart the program and input a valid choice");break;
        }
    }//method       
    void swap()//method to transfer values from world_new to world for validating next iteration
    {
        for(i=0;i<40;i++)
        {
            for(j=0;j<40;j++)
            {
                world[i][j]=world_new[i][j];
                world_new[i][j]=dead;//re-initialising all world_new squares to dead state for next iteration
            }
        }
    }//method
    void decide(int count,int i,int j)//method which decides whether given square is alive or dead
    {
        if(count==3)//birth of a sqaure
        {
            world_new[i][j]=alive;
        }
        else if(count<2)//death due to loneliness
        {
            world_new[i][j]=dead;
        }
        else if(count>3)//death due to over-crowding
        {
            world_new[i][j]=dead;
        }
        if(world[i][j].equals(alive))//survival of a square 
        {
            if(count==2)
            world_new[i][j]=alive;
        }
    }//method
    public static void main(String args[])throws IOException
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        GameOfLife x=new GameOfLife();String run="";
        do
        {
            x.inputlife();int a=1;
            System.out.println("Initial matrix as set up by you is as follows:-");
            for(x.i=0;x.i<40;x.i++)
            {
                for(x.j=0;x.j<40;x.j++)
                {
                    System.out.print(x.world[x.i][x.j]+" ");
                }
                System.out.println();
            }
            System.out.println("How many generations of the squares do you want to view?");
            int iterations=Integer.parseInt(in.readLine());
            System.out.println("Enter the time (in milliseconds) for which each generation should be simulated");
            int time=Integer.parseInt(in.readLine());
            while(iterations>0)
            {
                System.out.print('\u000C');//used to clear the screen
                x.compute();
                x.display();
                System.out.println("GENERATION "+(iterations-(iterations-a)));
                x.swap();
                iterations--;a++;
                x.delay(time);     
            }
            System.out.println("Do you want to run the program again? Input 'y' for yes and 'n' for no");
            run=in.readLine();
        }while(run.equals("y"));
        System.out.println("\tProgram stopped. \n\tThank you for trying the 'GAME OF LIFE' Cellular Automata. :)\n\n\tComputer Project-\n\n\tKaran Sapolia.");
    }//method
}//class
