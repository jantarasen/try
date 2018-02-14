public class Battleship extends ConsoleProgram
{
    private Player a;
    private Player b;
    private String alphabetRow[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I" ,"J"};
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    public static final int UNSET = -1;
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    public void run()
    {
        // Start here! This class should interact with the user
        // to play the game of Battleship
       
        // You only need to allow for the user to set up each player's
        // ships and for each player to make a guess on the other player's grid
        // Don't worry about finishing the whole game yet.
        
        // You will probably need to make additions to the Player class to
        // allow for this setting up and guessing

        a = new Player();
        b = new Player();
        int mytotal = 0;
        int opptotal = 0;
        
        System.out.println("Battleship!");
        System.out.println("Enter to continue...");
        
        System.out.println("Set up your boats.");
        //selectShips(a);
        opponentShip(a);
        
        
        System.out.println("Set up the comnputer boats.");
        opponentShip(b);
        
        //setting OPPONENT for each grids
        a.setOpponentGrid(b.getPlayerGrid());
        
        
        b.setOpponentGrid(a.getPlayerGrid());
        
        
        System.out.println("Start!");
        
        
        while(true)
        {
            //record use's guess
            if(askForGuess(a) == true)
            {
                System.out.println("YOU HIT!");
                mytotal++;
            }
            else
            {
                System.out.println("YOU MISS!");
            }
            
            
            
            //print out computer's grid's status
            a.printMyGuesses();
            
            System.out.println("Player hit = " + mytotal + " Opponent hit = " + opptotal);
            
            if(mytotal == 17)
            {
                //print the result
                System.out.println("Player win");
                break;
            }
            
            
            //record computer's guess
            if(makeGuess(b) == true)
            {
                 System.out.println("Computer HIT!");
                 opptotal++;
            }
            else
            {
                 System.out.println("computer MISS!");
            }
            
            System.out.println("Player hit = " + mytotal + " Opponent hit = " + opptotal);
            
            //print out player's grid's status
            b.printMyGuesses();
            
            if(opptotal == 17)
            {
                //print the result
                System.out.println("Computer win");
                break;
            }
            
           
        }
        
    }

    public boolean askForGuess(Player player)
    {
        int col = 0;
        int row = 0;
        
        while(true)
        {
            String str = readLine("It is your turn to guess. HIT ENTER!");
            
            row = getRow();
            col = getCol();
            
            if(player.getPlayerGrid().getStatus(row,col) == 0)
            {
                break;
            }
            
            System.out.println("you already made that guess!");
        }
        
        return player.recordMyGuess(row, col);
        
    }
    
    
    
    public boolean makeGuess(Player player)
    {
            int row = Randomizer.nextInt(0,9);
            int col = Randomizer.nextInt(0,9);
            
            while(player.getOpponentGrid().getStatus(row,col) != 0)
            {
                row = Randomizer.nextInt(0,9);
                col = Randomizer.nextInt(0,9);
            }
            
            return player.recordMyGuess(row, col);
    }
    
    

    
    public void opponentShip(Player player)
    {
        System.out.println("Create opponent ship");
        for(int i = 0 ; i< 5 ;i++)
        {
            int rowShip1 = Randomizer.nextInt(0,9);
            int colShip1 = Randomizer.nextInt(0,9);
            int direcShip1 = Randomizer.nextInt(0,1);
            
            while(!player.chooseShipLocation(player.getShip(i), rowShip1, colShip1, direcShip1))
            {
                
                rowShip1 = Randomizer.nextInt(0,9);
                colShip1 = Randomizer.nextInt(0,9);
                direcShip1 = Randomizer.nextInt(0,1);
                
            }
        }
        player.printMyShips();
    }
    
    
    public void selectShips(Player player)
    {
        for(int i = 0 ; i< 5 ;i++)
        {
            while(true)
            {
                System.out.println("set your ship of length "+SHIP_LENGTHS[i]);
                
                int rowShip1 = getRow();
                int colShip1 = getCol();
                int direcShip1 = getDirec();

                
                if(player.chooseShipLocation(player.getShip(i), rowShip1, colShip1, direcShip1))
                {
                    break;
                }
                
                System.out.println("wrong");
            }
            
            player.printMyShips();
        }
        
        
    }
    
    private int getRow()
    {
        String inputRow = readLine("Input your rows(A-J): ");
        int row = (int)inputRow.charAt(0) - 65;

        while(row < 0 || row > 9 )
        {
            System.out.println("your input went wrong");
            inputRow = readLine("Input your rows(A-J): ");
            row = (int)inputRow.charAt(0) - 65;
        }

        return row;
    }
    
    private int getCol()
    {
        int col = readInt("Input your column(1-10): ");
        while(col < 1 || col > 10)
        {
            System.out.println("your input went wrong");
            col = readInt("Input your column(1-10): ");
        }
        
        return col-1;
    }
    
    private int getDirec()
    {
        int direction=UNSET;
        while(true)
        {
            String directionString = readLine("horizontal or vertical?(enter h or v)");
            if(directionString.equalsIgnoreCase("h"))
            {
                direction=HORIZONTAL;
                break;
            }
            if(directionString.equalsIgnoreCase("v"))
            {
                direction=VERTICAL;
                break;
            }
            System.out.println("Wrong direction");
        }
        
        return direction;
    }
    

    
    
}

