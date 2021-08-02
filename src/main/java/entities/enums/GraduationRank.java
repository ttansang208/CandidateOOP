package entities.enums;

public enum GraduationRank {

    EXCELLENT(1),

    GOOD(2),

    MEDIUM(3);

    GraduationRank(int Grank) {
        this.Grank = Grank;
    }

    private int Grank;

    public int getRank() {
        return this.Grank;
    }

    public static GraduationRank toGraduationRank(String value) {
        if (value.equals("EXCELLENT")) {
            return GraduationRank.EXCELLENT;
        } else if (value.equals("GOOD")) {
            return GraduationRank.GOOD;
        } else {
            return GraduationRank.MEDIUM;
        }
    }
}
