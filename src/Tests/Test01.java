package Tests;

import java.util.HashMap;
import java.util.Map;

public class Test01 {
	
	static int MATRIX_WIDTH = 10;
	static int MATRIX_HEIGHT = 10;

	public static void main(String[] args) {
		//String s = "UDDLRL";
		String s = "RRY";
		
		// initialize moves
		new Up();
		new Down();
		new Right();
		new Left();
		new DoubleLeft();
		new DiagonalRight();
		
		try {
			int[] coords = moveRobot(s);
			System.out.print(coords[0] + ", " + coords[1]);
		} catch(MoveNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static int[] moveRobot(String s) throws MoveNotFoundException {
		int[] coords = new int[2];
		int row = 0, col = 0;
		
		for (int i = 0; i < s.length(); i++) {
			coords = MoveFactory.getMove(s.charAt(i)).move(col, row);
			row = coords[0];
			col = coords[1];
		}
		
		return coords;
	}
	
	interface Move {
		int[] move(int x, int y);
	}
	
	static class Up implements Move {

		static {
			MoveFactory.registerMove('U', new Up());
		}
		
		@Override
		public int[] move(int col, int row) {
			if (row > 0) {
				row--;
			}
			
			return new int[]{row, col};
		}
	}
	
	static class Down implements Move {

		static {
			MoveFactory.registerMove('D', new Down());
		}
		
		@Override
		public int[] move(int col, int row) {
			if (row < MATRIX_HEIGHT - 1) {
				row++;
			}
			
			return new int[]{row, col};
		}
	}
	
	static class Right implements Move {

		static {
			MoveFactory.registerMove('R', new Right());
		}
		
		@Override
		public int[] move(int col, int row) {
			if (col < MATRIX_WIDTH - 1) {
				col++;
			}
			
			return new int[]{row, col};
		}
	}
	
	static class Left implements Move {

		static {
			MoveFactory.registerMove('L', new Left());
		}
		
		@Override
		public int[] move(int col, int row) {
			if (col > 0) {
				col--;
			}
			
			return new int[]{row, col};
		}
	}
	
	static class DoubleLeft implements Move {

		static {
			MoveFactory.registerMove('X', new DoubleLeft());
		}
		
		@Override
		public int[] move(int col, int row) {
			col = col - 2;
			
			if (col < 0) {
				col = 0;
			}
			
			return new int[]{row, col};
		}
	}
	
	static class DiagonalRight implements Move {

		static {
			MoveFactory.registerMove('Y', new DiagonalRight());
		}
		
		@Override
		public int[] move(int col, int row) {
			if (row < MATRIX_HEIGHT - 1 && col < MATRIX_WIDTH - 1) {
				col++;
				row++;
			}
			
			return new int[]{row, col};
		}
	}
	
	static class MoveFactory {
		static MoveFactory instance = null;
		static Map<Character, Move> moves = new HashMap<>();
		
		private MoveFactory() {
			
		}
		
		static public MoveFactory getInstance() {
			if (instance == null) {
				instance = new MoveFactory();
			}
			
			return instance;
		}
		
		public static void registerMove(char c, Move move) {
			moves.put(c, move);
		}
		
		/*public static Move getMove(char c) throws MoveNotFoundException {
			if (c == 'U')
				return new Up();
			
			if (c == 'D')
				return new Down();
			
			if (c == 'R')
				return new Right();
			
			if (c == 'L')
				return new Left();
			
			if (c == 'X') 
				return new DoubleLeft();
			
			throw new MoveNotFoundException("Move not found: " + c);
		}*/
		
		public static Move getMove(char c) throws MoveNotFoundException {
			if (moves.containsKey(c)) {
				return moves.get(c);
			}
			
			throw new MoveNotFoundException("Move not found: " + c);
		}
	}	
}

@SuppressWarnings("serial")
class MoveNotFoundException extends Exception {
	public MoveNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}