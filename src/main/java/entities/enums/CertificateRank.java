package entities.enums;

public enum CertificateRank {

    EXCELLENT(0),

    GOOD(1),

    MEDIUM(2);

    CertificateRank(int cRank) { this.cRank = cRank;}

    private int cRank;

    public int getRank() { return this.cRank; }

    public static CertificateRank toCertificateRank(String value) {
        if (value.equals("EXCELLENT")) {
            return CertificateRank.EXCELLENT;
        } else if (value.equals("GOOD")) {
            return  CertificateRank.GOOD;
        } else {
            return CertificateRank.MEDIUM;
        }
    }

}

