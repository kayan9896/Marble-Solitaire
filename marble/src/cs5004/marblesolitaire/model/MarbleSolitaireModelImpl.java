package cs5004.marblesolitaire.model;

/**
 * This class implements MarbleSolitaireModel and represents a marble solitaire model implement
 * with the board size n and a 2D-board
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
    private int n;
    private char[][] board;

    /**
     * Generate the board with a specific size x
     * @param x the size of the central square
     */
    private void genBoard(int x){
        n=2*x;
        int mid=n/2;
        board=new char[n+1][n+1];
        int i,j;
        for(i=0; i<n+1; i++){
            for(j=0; j<n+1; j++) {
                board[i][j]=' ';
            }
        }
        for(i=0; i<n+1; i++){
            for(j=mid-x/2; j<=mid+x/2; j++){
                board[i][j]='O';
                board[j][i]='O';
            }
        }
    }

    /**
     * Construct a MarbleSolitaireModelImpl with no argument
     */
    public MarbleSolitaireModelImpl(){
        genBoard(3);
        board[3][3]='_';
    }

    /**
     * Construct a MarbleSolitaireModelImpl with the row and column of the empty space
     * @param r the row of the empty space
     * @param c the column of the empty space
     * @throws IllegalArgumentException if the empty position is out of bound
     */
    public MarbleSolitaireModelImpl(int r, int c) throws IllegalArgumentException{
        n=2*3;
        if(r<0||c<0||r>n||c>n) throw new IllegalArgumentException("Invalid empty cell position ("+r+","+c+")");
        genBoard(3);
        if(board[r][c]==' ') throw new IllegalArgumentException("Invalid empty cell position ("+r+","+c+")");
        board[r][c]='_';
    }

    /**
     * Construct a MarbleSolitaireModelImpl with the size of the central square
     * @param x the size of the square
     * @throws IllegalArgumentException if the size is odd or less than 1
     */
    public MarbleSolitaireModelImpl(int x) throws IllegalArgumentException{
        if(x<=1||x%2==0) throw new IllegalArgumentException("Illegal size");
        genBoard(x);
        board[x][x]='_';
    }

    /**
     * Construct a MarbleSolitaireModelImpl with the position of the empty space and the size of the central square
     * @param x the size of the square
     * @param r the row of the empty space
     * @param c the column of the empty space
     * @throws IllegalArgumentException if the empty position is out of bound or the size is invalid
     */
    public MarbleSolitaireModelImpl(int x, int r, int c) throws IllegalArgumentException{
        if(x<=1||x%2==0) throw new IllegalArgumentException("Illegal size");
        n=2*x;
        if(r<0||c<0||r>n||c>n) throw new IllegalArgumentException("Invalid empty cell position ("+r+","+c+")");
        genBoard(x);
        if(board[r][c]==' ') throw new IllegalArgumentException("Invalid empty cell position ("+r+","+c+")");
        board[r][c]='_';
    }

    /**
     * Return true if the marble can move to a specific position
     * @param fromRow the original row of the marble
     * @param fromCol the original column of the marble
     * @param toRow the row of the targeted position
     * @param toCol the column of the targeted position
     * @return true if the marble can move to a specific position
     */
    private boolean canMove(int fromRow, int fromCol, int toRow, int toCol){
        if(fromRow<0||fromCol<0||toRow<0||toCol<0||fromRow>n||fromCol>n||toRow>n||toCol>n) return false;
        if(board[toRow][toCol]=='_'&board[fromRow][fromCol]=='O') {
            if((Math.abs(toRow-fromRow)==2&fromCol==toCol)||(Math.abs(toCol-fromCol)==2&fromRow==toRow)){
                if(board[(toRow+fromRow)/2][(toCol+fromCol)/2]=='O') return true;
            }
        }
        return false;
    }
    @Override
    public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
        if(fromRow<0||fromCol<0||toRow<0||toCol<0||fromRow>n||fromCol>n||toRow>n||toCol>n) throw new IllegalArgumentException("Move is not possible");
        if(canMove(fromRow,fromCol,toRow,toCol)==false) throw new IllegalArgumentException("Move is not possible");
        board[toRow][toCol]='O';
        board[fromRow][fromCol]='_';
        board[(fromRow+toRow)/2][(fromCol+toCol)/2]='_';
    }

    @Override
    public boolean isGameOver() {
        int i,j;
        for(i=0; i<n+1; i++){
            for(j=0; j<n+1; j++){
                if(board[i][j]=='O') {
                    if(canMove(i,j,i+2,j)) return false;
                    if(canMove(i,j,i-2,j)) return false;
                    if(canMove(i,j,i,j+2)) return false;
                    if(canMove(i,j,i,j-2)) return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getGameState() {
        int i,j;
        String str="";
        for(i=0; i<n+1; i++){
            for(j=0; j<n+1; j++){
                str+=board[i][j]+" ";
            }
            str=str.substring(0,str.length()-1)+"\n";
        }
        return str.substring(0,str.length()-1);
    }

    @Override
    public int getScore() {
        int i,j;
        int s=0;
        for(i=0; i<n+1; i++){
            for(j=0; j<n+1; j++){
                if(board[i][j]=='O') s+=1;
            }
        }
        return s;
    }
}
