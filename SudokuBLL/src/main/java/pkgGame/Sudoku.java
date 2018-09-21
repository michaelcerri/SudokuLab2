package pkgGame;

import java.util.Arrays;
import pkgHelper.LatinSquare;

public class Sudoku extends LatinSquare {
	
	private int iSize;
	private int iSqrtSize;

	public int getiSize() {
		return iSize;
	}

	public void setiSize(int iSize) {
		this.iSize = iSize;
	}

	public int getiSqrtSize() {
		return iSqrtSize;
	}

	public void setiSqrtSize(int iSqrtSize) {
		this.iSqrtSize = iSqrtSize;
	}

	public Sudoku(int iSize) throws Exception {

		if (Math.sqrt(iSize) == (int) Math.sqrt(iSize)) {
			this.iSize = iSize;
			this.iSqrtSize = (int) Math.sqrt(iSize);
		} else
			throw new Exception("size must be a perfect square");

	}

	public Sudoku(int[][] puzzle) throws Exception {

		super(puzzle);

		if (puzzle.length == (int) Math.sqrt(puzzle.length)) {
			this.iSize = puzzle.length;
			this.iSqrtSize = (int) Math.sqrt(iSize);
		} else
			throw new Exception("size must be a perfect square");
	}
	
	protected int[][] getPuzzle()
	{
		return super.getLatinSquare();
	}
	
	protected int[] getRegion(int r)
	{
		if (r >= iSize)
			return null;
		int [][] myPuzzle = getPuzzle();
		int[] region = new int[iSize];
		int CRange = r % iSqrtSize;
		int RRange = r / iSqrtSize;
		for(int i = 0; i < iSize; i++)
		{
			for(int Col = iSqrtSize*CRange; Col < iSqrtSize*(CRange + 1); Col++)
			{
				for(int Row = iSqrtSize*RRange; Row < iSqrtSize*(RRange + 1); Row++)
				{
					region[i] = myPuzzle[Row][Col];
				}
			}
		}
		return region;
	}
	
	protected int[] getRegion(int iCol, int iRow)
	{
		if(iCol >= iSize || iRow >= iSize)
			return null;
		int [][] myPuzzle = getPuzzle();
		int[] region = new int[iSize];
		int RegionCol = iCol % iSqrtSize;
		int RegionRow = iRow % iSqrtSize;
		for(int i = 0; i < iSize; i++)
		{
			for(int Col = iSqrtSize*RegionCol;Col < iSqrtSize*(RegionCol + 1); Col++)
			{
				for(int Row = iSqrtSize*RegionRow; Row < iSqrtSize*(RegionRow + 1); Row++)
				{
					region[i] = myPuzzle[Row][Col];
				}
			}
		}
		return region;
	}

	protected boolean isSudoku() {
		boolean hasAllVals = false;
		
		for (int i = 1; i < this.iSize; i++) {
			if(!super.hasAllValues(getRegion(0), getRegion(i)))
				return false;
		}
		
		hasAllVals = true;
			
		return (hasAllVals && super.isLatinSquare() && !super.ContainsZero());
	}
	
	protected boolean isPartialSudoku() {
		//checks that it has zeros
		if (super.ContainsZero()!=true) 
			return false;
			
		//check that row, column, region doesn't have duplicates
		super.setbIgnoreZero(true);
		for (int i=0; i<iSize;i++) {	
				if(!super.hasDuplicates(super.getRow(0))||super.hasDuplicates(getRegion(i))==true)
					return false;
			}	
		return true;
	}
	
	protected boolean isValueValid(int iValue, int Col, int Row) {
		return false;
	}
}
