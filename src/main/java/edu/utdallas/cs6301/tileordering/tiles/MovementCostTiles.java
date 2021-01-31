package edu.utdallas.cs6301.tileordering.tiles;

public class MovementCostTiles extends Tiles {
    private String cost = "";

    public MovementCostTiles(String tiles, int indexMoved) {
        super(tiles, indexMoved);
    }

    public MovementCostTiles(String tiles) {
        super(tiles);
    }

    @Override
    public int getCost(Tiles previousState) {
        int xIdxPrevious = previousState.getTiles().indexOf(BLANK_MARKER);
        int xIdxThis = this.getTiles().indexOf(BLANK_MARKER);

        int cost = Math.abs(xIdxThis - xIdxPrevious);
        this.cost = String.format("(c=%d)", cost);

        return cost;
    }

    @Override
    protected Tiles getNewTiles(String state) {
        return new MovementCostTiles(state);
    }

    @Override
    protected Tiles getNewTiles(String state, int move) {
        return new MovementCostTiles(state, move);
    }

    @Override
    public String toString() {
        return super.toString() + " " + cost;
    }
}
