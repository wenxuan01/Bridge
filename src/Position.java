public enum Position {
    NORTH ("North", "N"),
    EAST ("East", "E"),
    SOUTH ("South", "S"),
    WEST ("West", "W");
    
    private final String longStr;
    private final String str;

    Position(String longStr, String str) {
        this.longStr = longStr;
        this.str = str;
    }
    
    Position next() {
        return switch(this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalStateException("Invalid Position: " 
                    + this);
        };
    }

    Position partner() {
        return switch(this) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            default -> throw new IllegalStateException("Invalid Position: " 
                    + this);
        };
    }

    String getLongStr() {
        return longStr;
    }

    String getStr() {
        return str;
    }
}
