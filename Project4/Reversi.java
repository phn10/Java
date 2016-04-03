import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reversi extends JFrame
{
  private final int size = 70;
  private int rows, columns;
  private JButton[][] buttons;
  private JPanel panel;
  private int[][] array;
  boolean lightTurn = false;
  
  Icon grid;
  Icon lightHover;
  Icon darkHover;
  Icon lightClick;
  Icon darkClick;
  
  public Reversi()
  {
    rows = 8;
    columns = 8;
    setTable();
  }

  public Reversi(int boardSize)
  {
    rows = boardSize;
    columns = boardSize;
    setTable();
  }

  public Reversi(int height, int width)
  {
    columns = width;
    rows = height;
    setTable();
  }

  void setTable()
  {
    Handler handler = new Handler();
    
    panel = new JPanel(new GridLayout(rows, columns));
    panel.setPreferredSize(new Dimension(size * columns, size * rows));
    this.add(panel, "Center");
    
    grid = new ImageIcon(getClass().getResource("grid"));
    lightHover = new ImageIcon(getClass().getResource("lightHover"));
    darkHover = new ImageIcon(getClass().getResource("darkHover"));
    lightClick = new ImageIcon(getClass().getResource("lightClick"));
    darkClick = new ImageIcon(getClass().getResource("darkClick"));
    
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
    
    this.pack();
    this.setVisible(true);
  }
  
  public void init()
  {
    int x1 = rows / 2 - 1;
    int x2 = x1 + 1;
    int y1 = columns / 2 - 1;
    int y2 = y1 + 1;
      
    buttons[x1][y1].setIcon(darkClick);
    buttons[x1][y2].setIcon(lightClick);
    buttons[x2][y1].setIcon(lightClick);
    buttons[x2][y2].setIcon(darkClick);
      
    array[x1][y1] = 1;
    array[x1][y2] = 2;
    array[x2][y1] = 2;
    array[x2][y2] = 1;
  }
   
  
  public void checkLegalMoves(int piece, int x, int y)
  {
    int deltaX;
    int deltaY;
    int numFlips;
    // search up direction
    deltaX = -1;
    deltaY = 0;
    numFlips = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    
    // search up right direction
    deltaX = -1;
    deltaY = 1;
    numFlips = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search right direction
    deltaX = 0;
    deltaY = 1;
    numFlips = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search up down right direction
    deltaX = 1;
    deltaY = 1;
    numFlips = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search down direction
    deltaX = 1;
    deltaY = 0;
    numFlips = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search down left direction
    deltaX = 1;
    deltaY = -1;
    numFlips = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search left direction
    deltaX = 0;
    deltaY = -1;
    numFlips = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
    // search up left direction
    deltaX = -1;
    deltaY = -1;
    numFlips = checkNumberOfFlips(piece, x, y, deltaX, deltaY);
    
  }
  
  public boolean checkEdge(int x, int y)
  {
    return (x >= 0 && x < rows && y >= 0 && y < columns);
  }
    
  public boolean checkEmpty(int x, int y)
  {
    if (array[x][y] == 0)
      return false;
    else
      return true;
  }

  public int checkNumberOfFlips(int piece, int x, int y, int deltaX, int deltaY)
  {
    int counter;
    int numFlips = 0;
    x = x + deltaX;
    y = y + deltaY;
    
    if (piece == 1)
      counter = 2;
    else
      counter = 1;
    
    if (checkEdge(x, y))
    {
      if (checkEmpty(x, y))
      {
        if (array[x][y] == counter)
        {
          while (checkEdge(x, y) && checkEmpty(x, y))
          {
            if (array[x][y] == counter)
            {
              x = x + deltaX;
              y = y + deltaY;
              numFlips++;
            }
            else
            {
              break;
            }
          }
          
          if (!checkEdge(x, y))
          {
            System.out.println("Inside but out of check edge");
          }
          else if (!checkEmpty(x ,y))
          {
            System.out.println("Inside but out of check empty");
            numFlips = 0;
          }
          else
          {
            System.out.println(String.format("Flips: %d", numFlips));
          }
        }
        //else
          //System.out.println("Cannot find counter");
      }
      //else
        //System.out.println("Out at checkEmpty");
    }
    //else
      //System.out.println("Out at checkEdge");
    return numFlips;
  }
    
  
  public class Handler implements MouseMotionListener, MouseListener
  {
    @Override
    public void mouseClicked(MouseEvent e) {
      for (int i = 0; i < rows; i++)
      {
        for (int j = 0; j < columns; j++)
        {
          if (buttons[i][j] == e.getSource() && array[i][j] == 0)
          {
            if (lightTurn == true)
            {
              array[i][j] = 2;     // 2 for light piece
              buttons[i][j].setIcon(lightClick);
              checkLegalMoves(2, i, j);
            }
            else
            {
              array[i][j] = 1;      // 1 for dark piece
              buttons[i][j].setIcon(darkClick);
              checkLegalMoves(1, i, j);
            }
            lightTurn = !lightTurn; // change to other turn
          }
        }
      }
      
      for (int i = 0; i < rows; i++)
      {
        for (int j = 0; j < columns; j++)
        {
          System.out.print(array[i][j]);
        }
        System.out.println("");
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
