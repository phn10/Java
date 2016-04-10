import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class simulate the game of reversi with gui design and different functions
 * @author Phong Nguyen
 */
public class Reversi extends JFrame
{
  private final int size = 70;       // the size of each square
  private int rows, columns;         // number of rows and columns in the table
  private JButton[][] buttons;       // an array of buttons 
  private JPanel panel;              // panel to store the table
  private JPanel heading;            // panel to store the heading
  private JLabel [] headingLabel;    // label to store attibutes on the heading
  private JButton newGame;           // new game, reset, button
  private int[][] array;             // the numerical representations of each pieces: 1: dark, 2: light, 0: empty space
  private boolean lightTurn = false; // light player turn
  private boolean lightAvailable;    // check if light player can play the next move 
  private boolean darkAvailable;     // check if dark player can play the next move
  private JPanel optionPanel;        // panel which store the game options 
  private JRadioButton option1;      // option1 players vs players
  private JRadioButton option2;      // option2 players vs computers
  private ButtonGroup group;         // group button
  private boolean gameOption;        // choose game option: player vs player or player vs computer
  
  Icon grid;                          // icon display the table grid 
  Icon lightHover;                    // icon display when hover the light piece on the table
  Icon darkHover;                     // icon display when hover the dark piece on the table
  Icon lightClick;                    // icon display when light piece is placed on the table
  Icon darkClick;                     // icon display when dark piece is placed on the table

  /* constructor
   * @param height the height of the board
   * @param width the width of the board
   */
  public Reversi(int height, int width)
  {
    columns = width;
    rows = height;
    setTable();
  }
  
  /* constructor
   * @param boardSize the size of the board
   */
  public Reversi(int boardSize)
  {
    this(boardSize, boardSize);
  }
  
  /* constructor
   */
  public Reversi()
  {
    this(8);
  }

  /* main function of the program
   * @param args argument 
   */
  public static void main(String[] args)
  {
    Reversi reversi;
    if (args.length == 0)
    {
      reversi = new Reversi();
      reversi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    if (args.length == 1)
    {
      int width = Integer.parseInt(args[0]);
      reversi = new Reversi(width);
      reversi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    if (args.length == 2)
    {
      int height =  Integer.parseInt(args[0]);
      int width = Integer.parseInt(args[1]);
      reversi = new Reversi(width, height);
      reversi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  }
  
  /* get the array representaion of the board
   * @return the array representation of the board
   */
  public int[][] getArray()
  {
    return array;
  }
  
  /* set the array representation of the board
   * @param arr get the arr argument and set the value of the object array
   */
  public void setArray(int[][] arr)
  {
    int r = arr.length;
    int c = arr[0].length;
    for (int i = 0; i < r; i++)
    {
      for (int j = 0; j < c; j++)
      {
        this.array[i][j] = arr[i][j];
      }
    }
  }       
  
  /* set up the initial setting of the table
   */
  public void setTable()
  {
    Handler handler = new Handler();
    
    gameOption = true;
    optionPanel = new JPanel(new FlowLayout());
    optionPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, false));
    optionPanel.setBackground(new Color(9, 144, 14));
    option1 = new JRadioButton("Player vs Player", true);
    option1.setBackground(new Color(9, 144, 14));
    option2 = new JRadioButton("Player vs Computer", false);
    option2.setBackground(new Color(9, 144, 14));
    optionPanel.add(option1);
    optionPanel.add(option2);
      
    group = new ButtonGroup();
    group.add(option1);
    group.add(option2);
    
    // change choice to true if chossing option1 player vs player
    option1.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e)
      {
        gameOption = true;
      }
    });
    
    // change choice to false if choosing option2 player vs computer
    option2.addItemListener(new ItemListener() {
      @Override 
      public void itemStateChanged(ItemEvent e)
      {
        gameOption = false;
      }
    });
      
    this.add(optionPanel, "North");
    
    /* setup the image for the gui */
    grid = new ImageIcon(getClass().getResource("grid.png"));
    lightHover = new ImageIcon(getClass().getResource("lightHover.png"));
    darkHover = new ImageIcon(getClass().getResource("darkHover.png"));
    lightClick = new ImageIcon(getClass().getResource("lightClick.png"));
    darkClick = new ImageIcon(getClass().getResource("darkClick.png"));

    
    panel = new JPanel(new GridLayout(rows, columns));
    panel.setPreferredSize(new Dimension(size * columns, size * rows));
    panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, false));
    this.add(panel, "South");
    
    heading = new JPanel(new GridLayout(1, 5));
    heading.setPreferredSize(new Dimension(size* columns, 100));
    heading.setBackground(new Color(9, 144, 14));
    heading.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, false));
    
    /* setup the heading of the gui */
    headingLabel = new JLabel[4];
    headingLabel[0] = new JLabel(darkClick);
    headingLabel[1] = new JLabel("2");
    headingLabel[1].setSize(100, 100);
    headingLabel[2] = new JLabel("2", SwingConstants.RIGHT);
    headingLabel[2].setSize(100, 100);
    headingLabel[3] = new JLabel(lightClick);
    
    
    /* construct new game button */
    newGame = new JButton("New Game");
    newGame.setBackground(new Color(9, 144, 14));
    newGame.setFont(new Font("Serif", Font.BOLD, 12));
    newGame.setPreferredSize(new Dimension(200, 100));
    newGame.addMouseListener(handler);
    newGame.addMouseMotionListener(handler);
    
    /* construct the heading of the gui */
    heading.add(headingLabel[0]);
    heading.add(headingLabel[1]);
    heading.add(newGame);
    heading.add(headingLabel[2]);
    heading.add(headingLabel[3]);
    
    this.add(heading, "Center");
    
    buttons = new JButton[rows][columns];
    array = new int[rows][columns];
    
    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < columns; j++)
      {
        array[i][j] = 0;
        buttons[i][j] = new JButton("", grid);
        Color color = new Color(9, 144, 14);
        buttons[i][j].addMouseListener(handler);
        buttons[i][j].addMouseMotionListener(handler);
        panel.add(buttons[i][j]);
      }
    }
    
    init();          // setup 4 pieces in the center of the table
    
    try
    {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e)
    { }
    
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
  }
  
  /* setup the first 4 pieces in the center of the board
   */
  public void init()
  {
    int x1 = rows / 2 - 1;             // the x coordinate of the first center square
    int x2 = x1 + 1;                   // the x cooridante of the second center square
    int y1 = columns / 2 - 1;          // the y cooridnate of the first center square            
    int y2 = y1 + 1;                   // the y cooridnate of teh second center square
      
    buttons[x1][y1].setIcon(darkClick);
    buttons[x1][y2].setIcon(lightClick);
    buttons[x2][y1].setIcon(lightClick);
    buttons[x2][y2].setIcon(darkClick);
      
    array[x1][y1] = 1;
    array[x1][y2] = 2;
    array[x2][y1] = 2;
    array[x2][y2] = 1;
  }
   
  /* check the legal of the move, search in 8 directions
   * @param piece the color of the piece, 1: dark, 2: light
   * @param x the x coordinate of the square
   * @param y the y coordinate of the square
   * @param choice execute the flips only when choice is true, if choice is false, not flip anythings
   * @return true if this is a legal move, false if it's not
   */
  public boolean checkLegalMoves(int piece, int x, int y, boolean choice)
  {
    int deltaX;        // the change in x coordinate
    int deltaY;        // the change in y coordinate
    int numFlips1;     // the number of flips piece when searching up direction
    int numFlips2;     // the number of flips piece when searching up right direction
    int numFlips3;     // the number of flips piece when searching right direction
    int numFlips4;     // the number of flips piece when searching down right direction
    int numFlips5;     // the number of flips piece when searching down direction
    int numFlips6;     // the number of flips piece when searching down left direction
    int numFlips7;     // the number of flips piece when searching left direction
    int numFlips8;     // the number of flips piece when searching up left direction
    
    // search up direction
    deltaX = -1;
    deltaY = 0;
    numFlips1 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    if (numFlips1 > 0 && choice == true)
      flips(piece, x, y, deltaX, deltaY, numFlips1);
    
    // search up right direction
    deltaX = -1;
    deltaY = 1;
    numFlips2 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    if (numFlips2 > 0 && choice == true)
      flips(piece, x, y, deltaX, deltaY, numFlips2);
    
    // search right direction
    deltaX = 0;
    deltaY = 1;
    numFlips3 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    if (numFlips3 > 0 && choice == true)
      flips(piece, x, y, deltaX, deltaY, numFlips3);
    
    // search up down right direction
    deltaX = 1;
    deltaY = 1;
    numFlips4 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    if (numFlips4 > 0 && choice == true)
      flips(piece, x, y, deltaX, deltaY, numFlips4);
    
    // search down direction
    deltaX = 1;
    deltaY = 0;
    numFlips5 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    if (numFlips5 > 0 && choice == true)
      flips(piece, x, y, deltaX, deltaY, numFlips5);
    
    // search down left direction
    deltaX = 1;
    deltaY = -1;
    numFlips6 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    if (numFlips6 > 0 && choice == true)
      flips(piece, x, y, deltaX, deltaY, numFlips6);
    
    // search left direction
    deltaX = 0;
    deltaY = -1;
    numFlips7 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    if (numFlips7 > 0 && choice == true)
      flips(piece, x, y, deltaX, deltaY, numFlips7);
    
    // search up left direction
    deltaX = -1;
    deltaY = -1;
    numFlips8 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    if (numFlips8 > 0 && choice == true)
      flips(piece, x, y, deltaX, deltaY, numFlips8);
    
    if (numFlips1 != 0 || numFlips2 != 0 || numFlips3 != 0 || numFlips4 != 0 || 
        numFlips5 != 0 || numFlips6 != 0 || numFlips7 != 0 || numFlips8 != 0)
      return true;
    else
      return false;
  }
  
  /* find the toal of flips in 8 directions (use for AI methods)
   * @param piece the color of the selected piece, 1: dark, 2: light
   * @param x the x coordinate of the selected square
   * @param y the y cooridnate of the selected square
   * @return the total number of flips in 8 directions
   */
  public int maximumFlips(int piece, int x, int y)
  {
    int deltaX;        // the change in x coordinate
    int deltaY;        // the change in y coordinate
    int numFlips1 = 0;     // the number of flips piece when searching up direction
    int numFlips2 = 0;     // the number of flips piece when searching up right direction
    int numFlips3 = 0;     // the number of flips piece when searching right direction
    int numFlips4 = 0;     // the number of flips piece when searching down right direction
    int numFlips5 = 0;     // the number of flips piece when searching down direction
    int numFlips6 = 0;     // the number of flips piece when searching down left direction
    int numFlips7 = 0;     // the number of flips piece when searching left direction
    int numFlips8 = 0;     // the number of flips piece when searching up left direction
    
    // search up direction
    deltaX = -1;
    deltaY = 0;
    numFlips1 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search up right direction
    deltaX = -1;
    deltaY = 1;
    numFlips2 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search right direction
    deltaX = 0;
    deltaY = 1;
    numFlips3 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search up down right direction
    deltaX = 1;
    deltaY = 1;
    numFlips4 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search down direction
    deltaX = 1;
    deltaY = 0;
    numFlips5 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search down left direction
    deltaX = 1;
    deltaY = -1;
    numFlips6 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search left direction
    deltaX = 0;
    deltaY = -1;
    numFlips7 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search up left direction
    deltaX = -1;
    deltaY = -1;
    numFlips8 = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    return numFlips1 + numFlips2 + numFlips3 + numFlips4 +
      numFlips5 + numFlips6 + numFlips7 + numFlips8; 
  }
  
  /* check if the selected square is outside or inside the board
   * @param x the x coordinate of the piece
   * @param y the y coordinate of the piece
   * @return true if the piece is inside, false if the piece is outside
   */
  public boolean checkEdge(int x, int y)
  {
    return (x >= 0 && x < rows && y >= 0 && y < columns);
  }
    
  /* check if the selected square has any pieces inside
   * @param x the x cooridnate of the square
   * @param y the y cooridnate of the square
   * @return true if the square is empty, false if the square not empty
   */
  public boolean checkEmpty(int x, int y)
  {
    if (array[x][y] == 0)
      return false;
    else
      return true;
  }

  /* count number of flips when searching certain directions from the selected piece
   * @param piece the color of the selected piece, 1: dark, 2: light
   * @param x the x coordinate of the selected piece
   * @param y the y cooridnate of the selected piece
   * @param deltaX 
   * @param deltaY
   * @return the number of flips
   */
  public int checkNumberOfFlips(int piece, int x, int y, int deltaX, int deltaY)
  {
    int counter;       // the name of the opposite piece 
    int numFlips = 0;  // the number of flipped piece
    x = x + deltaX;
    y = y + deltaY;
    
    if (piece == 1)
      counter = 2;
    else
      counter = 1;
  
    while (checkEdge(x, y) && checkEmpty(x, y) && array[x][y] == counter)
    {
      x = x + deltaX;
      y = y + deltaY;
      numFlips++;
    }
    
    if (checkEdge(x, y)  == false || checkEmpty(x, y) == false)
      numFlips = 0;

    return numFlips;
  }
  
  /* flip the piece in between two piece in certain direction
   * @param piece the color of the selected piece 1: dark, 2: light 
   * @param x the x coordinate of the selected piece
   * @param y the y cooridnate of the selected piece
   * @param deltaX 
   * @param deltay
   * @param numFlips the number of desired fliped piece
   */
  public void flips(int piece, int x, int y, int deltaX, int deltaY, int numFlips)
  {
    int tempX;
    int tempY;
    for (int i = 1; i <= numFlips; i++)
    {
     tempX = x + i * deltaX;
     tempY = y + i * deltaY;
     array[tempX][tempY] = piece;
     if (piece == 2)
       buttons[tempX][tempY].setIcon(lightClick);
     if (piece == 1)
       buttons[tempX][tempY].setIcon(darkClick);
    }
  }
  
  /* test if dark or light player able to make the next move
   * @param piece the color of the piece  1: dark, 2: light
   * @return true if dark or light player still have move, false if he runs out of move
   */
  public boolean checkTurn(int piece)
  {
    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < columns; j++)
      {
        if (array[i][j] == 0)
        {
          if (checkLegalMoves(piece, i, j, false))
          {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  /* print out the celebration for the winner
   */
  public void celebrate()
  {
    int darkPlayer = countPiece(1);           // count the number of dark pieces on the table
    int lightPlayer = countPiece(2);          // count the number of white pieces on the table
    
    if (darkPlayer > lightPlayer)
      JOptionPane.showMessageDialog(null, String.format("Dark Player %d - %d Light Player\nDark Player Win!", darkPlayer, lightPlayer));
    if (darkPlayer < lightPlayer)
      JOptionPane.showMessageDialog(null, String.format("Dark Player %d - %d Light Player\nLight Player Win!", darkPlayer, lightPlayer));
    if (darkPlayer == lightPlayer)
      JOptionPane.showMessageDialog(null, String.format("Dark Player %d - %d Light Player\nGame Draws!", darkPlayer, lightPlayer));
  }
  
  /* count the number of dark or light pieces on the board
   * @param piece the color of the pieces want to count 1: dark 2: light
   * @return the number of selected pieces on the table
   */
  public int countPiece(int piece)
  {
    int count = 0;
    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < columns; j++)
      {
        if(array[i][j] == piece)
        {
          count++;
        }
      }
    }
    return count;
  } 
  
  /* reset the game
   */
  public void reset()
  {
    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < columns; j++)
      {
        array[i][j] = 0;
        buttons[i][j].setIcon(grid);
      }
    }
    lightTurn = false;
    init();
  }
  
  /* find the square which can flip the most pieces
   * then execute the move on selected square and flip
   */
  public void lightAIMoves()
  {
    int numFlips = 0;    // the number of flips
    int temp;            // count number of flips in each situation
    int aiX = 0;         // x coordinate of the selected square
    int aiY = 0;         // y coordinate of the selected square
    
    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < columns; j++)
      {
        if (array[i][j] == 0)
        {
          temp = maximumFlips(2, i, j);
          if (temp > numFlips)
          {
            numFlips = temp;
            aiX = i;
            aiY = j;
          }
        }
      }   
    }
    
    // execute the flip
    checkLegalMoves(2, aiX, aiY, true);
    array[aiX][aiY] = 2;
    buttons[aiX][aiY].setIcon(lightClick);
  } 
  
  /* execute the dark play
   */
  public void darkMoves(int x, int y)
  {
    if (checkLegalMoves(1, x, y, true))
    {
      array[x][y] = 1;
      buttons[x][y].setIcon(darkClick);
    }
  }
  
  /* execute the light play
   */
  public void lightMoves(int x, int y)
  {
    if (checkLegalMoves(2, x, y, true))
    {
      array[x][y] = 2;
      buttons[x][y].setIcon(lightClick);
    }
  }
  
  /* check the availability of dark and light player after
   * dark player executes his move
   * @return true if light player can play next
   */
  public boolean darkCheckNextTurn()
  {
    boolean lightAvailable;
    boolean darkAvailable;
    
    lightAvailable = checkTurn(2);
    if (lightAvailable)
      return true;
    else
    {
      darkAvailable = checkTurn(1);
      if (darkAvailable)
      {
        JOptionPane.showMessageDialog(null, "Light Player out of move.\nDark Player continue.");
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Both Players out of move.\nGame End.");
        celebrate();
      }
      return false;
    }
  }
  
  /* check the availability of light and dark player after
   * light player executes his move
   * @return false if dark player can play next
   */
  public boolean lightCheckNextTurn()
  {
    boolean lightAvailable;
    boolean darkAvailable;
    
    darkAvailable = checkTurn(1);
    if (darkAvailable)
      return false;
    else
    {
      lightAvailable = checkTurn(2);
      if (lightAvailable)
      {
        JOptionPane.showMessageDialog(null, "Dark Player out of move.\nLight Player continue.");
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Both Players out of move.\nGame End.");
        celebrate();
      }
      return true;
    }
  }
  
  /*
   * update the score of each players on the heading
   */
  public void displayScore()
  {
    headingLabel[1].setText(String.format("%d", countPiece(1)));
    headingLabel[2].setText(String.format("%d", countPiece(2)));
  }
  
  
  /**
   * modify the behavior of mouse when click or hover over the buttons
   * @author Phong Nguyen
   */
  public class Handler implements MouseMotionListener, MouseListener
  {
    /*  execute the body when click any buttons
     */
    @Override
    public void mouseClicked(MouseEvent e) {       
      
      // click "new game" button
      if (newGame == e.getSource())
      {
        reset();
        displayScore();
      }
        
      // click square on the table
      // go through the columns and rows to find the button which is clicked
      for (int i = 0; i < rows; i++)
      {
        for (int j = 0; j < columns; j++)
        {
          if (buttons[i][j] == e.getSource() && array[i][j] == 0)
          { 
            boolean played = false;
            
            // if the game option is player vs player
            if (gameOption)
            {
              //dark player turn
              if (lightTurn == false && checkLegalMoves(1, i, j, false) && played == false)
              {
                darkMoves(i, j);
                lightTurn = darkCheckNextTurn();
                displayScore();
                played = true;
              }
              // white player turn
              if (lightTurn == true && checkLegalMoves(2, i, j, false) && played == false)
              {
                lightMoves(i, j);
                lightTurn = lightCheckNextTurn();
                displayScore();
                played = true;
              }
            }
            
            // if the game option is player vs computer 
            else
            {
              // dark player turn
              if (lightTurn == false && checkLegalMoves(1, i, j, false) && played == false)
              {
                darkMoves(i, j);
                lightTurn = darkCheckNextTurn();
                displayScore();
              }
              // white player turn
              if (lightTurn == true && played == false)
              {
                lightAIMoves();
                lightTurn = lightCheckNextTurn();
                displayScore();
                played = true;
              }
            }
          }
        }         
      }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {}
    
    /*
     * execute the body if hover the mouse over buttons on board
     */
    @Override 
    public void mouseMoved(MouseEvent e) {
      for (int i = 0; i < rows; i++)
      {
        for (int j = 0; j < columns; j++)
        {
          if (lightTurn == false)
            buttons[i][j].setRolloverIcon(darkHover);
          else
            buttons[i][j].setRolloverIcon(lightHover);
        }
      }
    }
  }
}
