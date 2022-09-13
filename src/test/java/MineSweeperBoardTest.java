
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MineSweeperBoardTest {

    @Test
    public void OneXOneBlankBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {"."});
        assertArrayEquals(new String[] {"0"}, mineSweeperBoard.getBoard());
    }

    @Test
    public void OneXOneMineBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {"*"});
        assertArrayEquals(new String[] {"*"}, mineSweeperBoard.getBoard());
    }

    @Test
    public void ThreeXOneBlankBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {".",".",".",});
        assertArrayEquals(new String[] {"0","0","0",}, mineSweeperBoard.getBoard());
    }
    @Test
    public void OneXThreeBlankBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {"..."});
        assertArrayEquals(new String[] {"000"}, mineSweeperBoard.getBoard());
    }

    @Test
    public void ThreeXOneMineBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {".","*",".",});
        assertArrayEquals(new String[] {"1","*","1",}, mineSweeperBoard.getBoard());
    }

    @Test
    public void OneXThreeMineBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {".*."});
        assertArrayEquals(new String[] {"1*1"}, mineSweeperBoard.getBoard());
    }

    @Test
    public void ThreeXThreeBlankBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {"...","...","..."});
        assertArrayEquals(new String[] {"000","000","000"}, mineSweeperBoard.getBoard());
    }

    @Test
    public void ThreeXThreeMineBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {"..*",".*.","*.."});
        assertArrayEquals(new String[] {"12*","2*2","*21"}, mineSweeperBoard.getBoard());
    }

    @Test
    public void SixXSixMineBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[]
                {"..*",
                        ".*.",
                        "*..",
                        "..*",
                        ".*.",
                        "*.."});
        assertArrayEquals(new String[]
                {"12*",
                        "2*2",
                        "*32",
                        "23*",
                        "2*2",
                        "*21"}, mineSweeperBoard.getBoard());
    }

    @Test
    public void ThreeXOneMineWithNumbersBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {".","*",".",});
        assertArrayEquals(new String[] {"1","*","1",}, mineSweeperBoard.getBoard());
    }

    @Test
    public void OneXThreeMineWithNumbersBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {".*.",});
        assertArrayEquals(new String[] {"1*1"}, mineSweeperBoard.getBoard());
    }
    @Test
    public void ThreeXThreeMineWithNumbersBoardTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(new String[] {"***","*.*","***",});
        assertArrayEquals(new String[] {"***","*8*","***"}, mineSweeperBoard.getBoard());
    }
    @Test
    public void finalTest(){
        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(
                new String[] {
                        "..**.*...",
                        "..*......",
                        "....*....",
                        ".........",
                        "....*....",
                        ".........",
                        ".....**..",
                        "......*..",
                        ".*......."
                }); assertArrayEquals(new String[] {
                "02**2*100",
                "02*432100",
                "0112*1000",
                "000222000",
                "0001*1000",
                "000123210",
                "00001**20",
                "111013*20",
                "1*1001110", }, mineSweeperBoard.getBoard());
    }
}
