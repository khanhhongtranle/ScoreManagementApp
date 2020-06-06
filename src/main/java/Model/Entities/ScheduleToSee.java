package Model.Entities;

public class ScheduleToSee {
    private String MaMonHoc;
    private String TenMonHoc;
    private String PhongHoc;

    public ScheduleToSee(){

    }

    public ScheduleToSee(String m, String t, String p){
        this.MaMonHoc = m;
        this.TenMonHoc = t;
        this.PhongHoc = p;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public String getPhongHoc() {
        return PhongHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        MaMonHoc = maMonHoc;
    }

    public void setPhongHoc(String phongHoc) {
        PhongHoc = phongHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        TenMonHoc = tenMonHoc;
    }
}
