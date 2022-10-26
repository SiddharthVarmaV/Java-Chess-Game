import java.util.*;

public class Pieces {
    
    int xp;
    int yp;
    int x;
    int y;
    boolean isWhite;
    LinkedList<Pieces> ps;
    String name;

    public Pieces(int xp, int yp, boolean isWhite, String name, LinkedList<Pieces> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp* 90;
        y = yp*90;
        this.isWhite = isWhite;
        this.ps = ps;
        this.name = name;
        ps.add(this);
    }

    public void move(int xp, int yp) {
        
        if(Chess.getPiece(xp*90, yp*90) != null){
            if(Chess.getPiece(xp*90, yp*90).isWhite != this.isWhite){
                Chess.getPiece(xp*90, yp*90).kill();
            }else{
                x = this.xp* 90;
                y = this.yp*90;
                return;
            }
        }
                
        this.xp = xp;
        this.yp = yp;
        x = xp* 90;
        y = yp*90;
    }

    public void kill() {
        ps.remove(this);
    }
}