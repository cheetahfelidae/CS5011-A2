package search.constantVariable;

import java.util.logging.Logger;

public class Map {
    private static final char[][] MAP1 = new char[][]{
            {'I', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'O'},
            {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'B', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'G'},
    };

    private static final char[][] MAP2 = new char[][]{
            {'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
            {'O', 'X', 'O', 'X', 'O', 'O', 'B', 'O', 'O', 'X'},
            {'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'G', 'O', 'I', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    };

    private static final char[][] MAP3 = new char[][]{
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'I', 'O', 'O', 'O', 'X', 'X', 'O', 'B', 'O'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'G', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
    };

    private static final char[][] MAP4 = new char[][]{
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'I', 'O', 'O', 'O', 'X', 'X', 'O', 'B', 'O'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X'},
            {'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'G', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
    };

    private static final char[][] MAP5 = new char[][]{
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'B', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X'},
            {'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'X', 'O', 'O', 'G', 'O', 'X', 'O', 'I', 'O'},
            {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O'}
    };

    private static final char[][] MAP6 = new char[][]{
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
            {'O', 'O', 'B', 'O', 'O', 'X', 'X', 'O', 'O', 'O'},
            {'O', 'X', 'X', 'X', 'O', 'O', 'X', 'O', 'O', 'I'},
            {'O', 'X', 'X', 'X', 'O', 'O', 'X', 'X', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O'},
            {'X', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'},
            {'X', 'X', 'O', 'O', 'O', 'X', 'X', 'O', 'X', 'X'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'X', 'X', 'G', 'O', 'X', 'X', 'O', 'X'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O'}
    };

    public static char[][] get_map(int num) {

        switch (num) {
            case 1:
                return MAP1;
            case 2:
                return MAP2;
            case 3:
                return MAP3;
            case 4:
                return MAP4;
            case 5:
                return MAP5;
            case 6:
                return MAP6;
            default:
                Logger.getLogger(Map.class.getName()).severe("MAP NUMBER " + num + " IS UNRECOGNISED");
                System.exit(0);
                return null;
        }

    }
}
