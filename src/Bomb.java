import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;

public class Bomb extends JFrame implements ActionListener, MouseListener{
        int nobombs = 20;
        int perm[][];
       String tmp;
       boolean found = false;
       int row;
       int column;
       int guesses[][];
       JButton b[][];
       int[][] bombs;
       boolean allbombs;
       int n = 20; // Left to Right 
       int m = 20; // Up to Down
       int rcx[] = {-1, 0, 1, -1, 1, -1, 0, 1}; // row coordinates
       int ccy[] = {-1, -1, -1, 0, 0, 1, 1, 1}; // column coordinates
       double starttime;
       double endtime;
       double totaltime;
       public Bomb(){
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        perm = new int[n][m];
               boolean allbombs = false;
               guesses = new int [n+2][m+2];
               bombs = new int[n+2][m+2];
               b = new JButton [n][m];
               setLayout(new GridLayout(n,m));
               for (int y = 0;y<m+2;y++){
                       bombs[0][y] = 3;
                       bombs[n+1][y] = 3;
                       guesses[0][y] = 3;
                       guesses[n+1][y] = 3;
               }
               for (int x = 0;x<n+2;x++){
                       bombs[x][0] = 3;
                       bombs[x][m+1] = 3;
                       guesses[x][0] = 3;
                       guesses[x][m+1] = 3;
               }
               do {
                       int check = 0;
                       for (int y = 1;y<m+1;y++){
                           for (int x = 1;x<n+1;x++){
                                   bombs[x][y] = 0;
                                   guesses[x][y] = 0;
                           }
                       }
               for (int x = 0;x<nobombs;x++){
                       bombs [(int) (Math.random()*(n)+1)][(int) (Math.random()*(m)+1)] = 1;
               }
               for (int x = 0;x<n;x++){
                       for (int y = 0;y<m;y++){
               if (bombs[x+1][y+1] == 1){
                       check++;
               }
               }}
               if (check == nobombs){
                       allbombs = true;
                       }}while (allbombs == false);
               for (int y = 0;y<m;y++){
                       for (int x = 0;x<n;x++){
                           if ((bombs[x+1][y+1] == 0) || (bombs[x+1][y+1] == 1)){
                                   perm[x][y] = perimcheck(x,y);
                           }
                               b[x][y] = new JButton(" ? ");
                               b[x][y].setBackground(Color.CYAN);
                               b[x][y].addActionListener(this);
                               b[x][y].addMouseListener(this);
                               add(b[x][y]);
                               b[x][y].setEnabled(true);
                       }//end inner for
               }//end for
               pack();
               setVisible(true);
               for (int y = 0;y<m+2;y++){
               for (int x = 0;x<n+2;x++){
             //  System.out.print(bombs[x][y]);
               }
             //  System.out.println("");
             }
               starttime = new Date().getTime();
       }//end constructor bomb()
 
       public void actionPerformed(ActionEvent e){
           found =  false;
               JButton current = (JButton)e.getSource();
               for (int y = 0;y<m;y++){
                       for (int x = 0;x<n;x++){
                               JButton t = b[x][y];
                               if(t == current){
                                       row=x;column=y; found =true;
                               }
                       }//end inner for
               }//end for
               if(!found) {
                       System.out.println("didn't find the button, there is a error "); System.exit(-1);
               }
               Component temporaryLostComponent = null;
               if (b[row][column].getBackground() == Color.orange){
                   return;
               }else if (bombs[row+1][column+1] == 1){
                        endtime = new Date().getTime();
                        totaltime = ((endtime-starttime)/1000);
                       JOptionPane.showMessageDialog(temporaryLostComponent, "Oh man, you blast a bomb! Game over \n You take "+(int)totaltime+" seconds ");
                       System.exit(0);
               } else {
                       tmp = Integer.toString(perm[row][column]);
                       if (perm[row][column] == 0){
                               tmp = " ";
                       }
                       b[row][column].setText(tmp);
                       b[row][column].setEnabled(false);
                       checkifend();
                       if (perm[row][column] == 0){
                           scan(row, column);
                           checkifend();
                       }}}
       
       public void checkifend(){
           int check= 0;
           for (int y = 0; y<m;y++){
                for (int x = 0;x<n;x++){
           if (b[x][y].isEnabled()){
                   check++;
           }
                }}
           if (check == nobombs){
                   endtime = new Date().getTime();
                   totaltime = ((endtime-starttime));
                   Component temporaryLostComponent = null;
                   JOptionPane.showMessageDialog(temporaryLostComponent, "Congrats you won! it take you "+(int)totaltime+" seconds!");
           }
       }
 
       public void scan(int x, int y){
               for (int a = 0;a<8;a++){
       if (bombs[x+1+rcx[a]][y+1+ccy[a]] == 3){
           
       } else if ((perm[x+rcx[a]][y+ccy[a]] == 0) && (bombs[x+1+rcx[a]][y+1+ccy[a]] == 0) && (guesses[x+rcx[a]+1][y+ccy[a]+1] == 0)){
           if (b[x+rcx[a]][y+ccy[a]].isEnabled()){
               b[x+rcx[a]][y+ccy[a]].setText("");
               b[x+rcx[a]][y+ccy[a]].setEnabled(false);
               scan(x+rcx[a], y+ccy[a]);
      }
       } else if ((perm[x+rcx[a]][y+ccy[a]] != 0) && (bombs[x+1+rcx[a]][y+1+ccy[a]] == 0)  && (guesses[x+rcx[a]+1][y+ccy[a]+1] == 0)){
               tmp = new Integer(perm[x+rcx[a]][y+ccy[a]]).toString();
               b[x+rcx[a]][y+ccy[a]].setText(Integer.toString(perm[x+rcx[a]][y+ccy[a]]));
               b[x+rcx[a]][y+ccy[a]].setEnabled(false);    
       }
               }}
       
       public int perimcheck(int a, int y){
               int bombcount = 0;
                       for (int x = 0;x<8;x++){
                               if (bombs[a+rcx[x]+1][y+ccy[x]+1] == 1){
                                       bombcount++;
                               }
               }
               return bombcount;
       }
 
       public void windowIconified(WindowEvent e){
 
       }
 
       public static void main(String[] args){
               new Bomb();
       }
 
 
                public void mouseClicked(MouseEvent e) {
               }                
                public void mouseEntered(MouseEvent arg0) {                    
                }                
                public void mouseExited(MouseEvent arg0) {
                }
                public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                                found =  false;
                    Object current = e.getSource();
                    for (int y = 0;y<m;y++){
                            for (int x = 0;x<n;x++){
                                    JButton t = b[x][y];
                                    if(t == current){
                                            row=x;column=y; found =true;
                                    }
                            }//end inner for loop
                    }//end for loop
                    if(!found) {
                            System.out.println("didn't find the button, there is a error "); System.exit(-1);
                    }
                    if ((guesses[row+1][column+1] == 0) && (b[row][column].isEnabled())){
                        b[row][column].setText("x");
                    guesses[row+1][column+1] = 1;
                    b[row][column].setBackground(Color.orange);
                    } else if (guesses[row+1][column+1] == 1){
                        b[row][column].setText("*");
                        guesses[row+1][column+1] = 0;
                        b[row][column].setBackground(null);
                    }}
                }
                public void mouseReleased(MouseEvent arg0) {    
                }}