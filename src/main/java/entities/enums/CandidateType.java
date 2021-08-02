package entities.enums;

public enum CandidateType {

    EXPERIENCE(0),

    FRESHER(1),

    INTERN(2);

    CandidateType(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return this.code;
    }

    public static CandidateType toCandidateType(String value) {
        if (value.equals("FRESHER")) {
            return CandidateType.FRESHER;
        } else if (value.equals("INTERN")) {
            return  CandidateType.INTERN;
        } else {
            return CandidateType.EXPERIENCE;
        }
    }

}
