import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class Chess {
    
    
    public static LinkedList<Pieces> ps = new LinkedList<>();
    public static Pieces selectedPiece = null;
    
    public static void main(String[] args) throws IOException {
        
        
        BufferedImage all = ImageIO.read(new File("piecesImg.png"));
        Image imgs[] = new Image[12];
        int ind = 0;
        // Croping the images from the file 
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(90, 90, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        
        // creating black objects 
        Pieces brook=new Pieces(0, 0, false, "rook", ps);
        Pieces bkinght=new Pieces(1, 0, false, "knight", ps);
        Pieces bbishop=new Pieces(2, 0, false, "bishop", ps);
        Pieces bqueen=new Pieces(3, 0, false, "queen", ps);
        Pieces bking=new Pieces(4, 0, false, "king", ps);
        Pieces bbishop2=new Pieces(5, 0, false, "bishop", ps);
        Pieces bkight2=new Pieces(6, 0, false, "knight", ps);
        Pieces brook2=new Pieces(7, 0, false, "rook", ps);
        Pieces bpawn1=new Pieces(0, 1, false, "pawn", ps);
        Pieces bpawn2=new Pieces(1, 1, false, "pawn", ps);
        Pieces bpawn3=new Pieces(2, 1, false, "pawn", ps);
        Pieces bpawn4=new Pieces(3, 1, false, "pawn", ps);
        Pieces bpawn5=new Pieces(4, 1, false, "pawn", ps);
        Pieces bpawn6=new Pieces(5, 1, false, "pawn", ps);
        Pieces bpawn7=new Pieces(6, 1, false, "pawn", ps);
        Pieces bpawn8=new Pieces(7, 1, false, "pawn", ps);
        
        // creating white objects 
        Pieces wrook=new Pieces(0, 7, true, "rook", ps);
        Pieces wkinght=new Pieces(1, 7, true, "knight", ps);
        Pieces wbishop=new Pieces(2, 7, true, "bishop", ps);
        Pieces wqueen=new Pieces(3, 7, true, "queen", ps);
        Pieces wking=new Pieces(4, 7, true, "king", ps);
        Pieces wbishop2=new Pieces(5, 7, true, "bishop", ps);
        Pieces wkight2=new Pieces(6, 7, true, "knight", ps);
        Pieces wrook2=new Pieces(7, 7, true, "rook", ps);
        Pieces wpawn1=new Pieces(0, 6, true, "pawn", ps);
        Pieces wpawn2=new Pieces(1, 6, true, "pawn", ps);
        Pieces wpawn3=new Pieces(2, 6, true, "pawn", ps);
        Pieces wpawn4=new Pieces(3, 6, true, "pawn", ps);
        Pieces wpawn5=new Pieces(4, 6, true, "pawn", ps);
        Pieces wpawn6=new Pieces(5, 6, true, "pawn", ps);
        Pieces wpawn7=new Pieces(6, 6, true, "pawn", ps);
        Pieces wpawn8=new Pieces(7, 6, true, "pawn", ps);
        
        
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setBounds(0, 0, 720, 720);
        
        JPanel board = new JPanel() {
            @Override
            public void paint(Graphics g) {
                // board preparation
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if ((x + y) % 2 == 0) {
                            g.setColor(new Color(255, 191, 112));
                        } else {
                            g.setColor(new Color(166, 95, 7));
                        }
                        g.fillRect(x * 90, y * 90, 90, 90);
                    }
                }
                // board preparation
                for (Pieces p : ps) {

                    int ind = 0;
                    if (p.name.equals("king")) {
                        ind = 0;
                    }
                    if (p.name.equals("queen")) {
                        ind = 1;
                    }
                    if (p.name.equals("bishop")) {
                        ind = 2;
                    }
                    if (p.name.equals("knight")) {
                        ind = 3;
                    }
                    if (p.name.equals("rook")) {
                        ind = 4;
                    }
                    if (p.name.equals("pawn")) {
                        ind = 5;
                    }
                    if (!p.isWhite) {
                        ind += 6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            }
        };

        frame.add(board);
        
        frame.addMouseMotionListener(new MouseMotionListener(){
        
            @Override
            public void mouseDragged(MouseEvent e){
                if(selectedPiece != null){
                    
                    selectedPiece.x = e.getX() - 45;
                    selectedPiece.y = e.getY() - 45;
                    frame.repaint();
                }
            }
            
            @Override
            public void mouseMoved(MouseEvent e){
                
            }
        
        });
        
        frame.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseClicked(MouseEvent e){
                
            }
            
            @Override
            public void mousePressed(MouseEvent e){
                //System.out.println(getPiece(e.getX(),e.getY()).name);
                selectedPiece = getPiece(e.getX(),e.getY());
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                if(selectedPiece!= null)
                selectedPiece.move(e.getX()/90, e.getY()/90);
                frame.repaint();
            }
            
            @Override
            public void mouseEntered(MouseEvent e){
            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
            
            }
            
        
        }); 
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static Pieces getPiece(int x, int y){
        
        int xInd = x/90;
        int yInd = y/90;
        
        for(Pieces p : ps){
            
            if(p.xp == xInd && p.yp == yInd){
                
                return p;
                
            }
            
        }
        
        return null;
    }
}