
public class MineSweeperBoard {
    //Example input:
        /*
            "...", "...", "..."
        */
    public String[] board;
    public int count;

    public MineSweeperBoard(String[] board){
        this.board = board;
    }

    public String[] getBoard(){

        for (int y = 0; y < board.length; y++){
            String newRow = "";

            for (int x = 0; x < board[y].length(); x++){
                if (board[y].charAt(x) == '.'){

                    //If this is true, board[y].charAt(x) should become a value between 1 - 8
                    if (hasMine(x, y)){
                        newRow += ""+count;
                    } else {
                        newRow += "0";
                    }
                } else {
                    newRow += "*";
                }
            } board[y] = newRow;
        }
        return board;
    }

    public Boolean hasMine(int xAxis, int yAxis){
        count = 0;

        //if the value above the node is not null
        //&&
        //if the value above equals *
        if (yAxis - 1 >= 0 && board[yAxis - 1].charAt(xAxis) == '*') {
            count++;
        }

        //if the value below the node is not null
        //&&
        //if the value below equals *
        if (yAxis + 1 < board.length && board[yAxis + 1].charAt(xAxis) == '*') {
            count++;
        }

        //if the value to the left of the node is not null
        //&&
        //if the value to the left equals *
        if (xAxis - 1 >= 0 && board[yAxis].charAt(xAxis - 1) == '*') {
            count++;
        }

        //if the value to the right of the node is not null
        //&&
        //if the value to the right equals *
        if (xAxis + 1 < board[yAxis].length() && board[yAxis].charAt(xAxis + 1) == '*') {
            count++;
        }

        //if the value in the top-left corner is not null
        //&&
        //if the value in the top-left corner equals *
        if (yAxis - 1 >= 0 && xAxis - 1 >= 0 && board[yAxis - 1].charAt(xAxis - 1) == '*') {
            count++;
        }

        //if the value in the top-right corner is not null
        //&&
        //if the value in the top-right corner equals *
        if (yAxis - 1 >= 0 && xAxis + 1 < board[yAxis].length() && board[yAxis - 1].charAt(xAxis + 1) == '*') {
            count++;
        }

        //if the value in the bottom-left corner is not null
        //&&
        //if the value in the bottom-left corner equals *
        if (yAxis + 1 < board.length && xAxis - 1 >= 0 && board[yAxis + 1].charAt(xAxis - 1) == '*') {
            count++;
        }

        //if the value in the bottom-right corner is not null
        //&&
        //if the value in the bottom right corner equals *
        if (yAxis + 1 < board.length && xAxis + 1 < board[yAxis].length() && board[yAxis + 1].charAt(xAxis + 1) == '*') {
            count++;
        }

        return count > 0;
    }
}

