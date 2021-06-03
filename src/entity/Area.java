package entity;

/**
 * @description 地区表对应的实体类
 * @user summerHouAnNing
 * @creatTime 2021/4/26--9:06
 */
public class Area {
    private Integer id;
    private String areaId;
    private String fatherId;
    private String grandfatherId;
    private String areaName;

    public Area() {
    }

    public Area(Integer id, String areaId, String fatherId, String grandfatherId, String areaName) {
        this.id = id;
        this.areaId = areaId;
        this.fatherId = fatherId;
        this.grandfatherId = grandfatherId;
        this.areaName = areaName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getGrandfatherId() {
        return grandfatherId;
    }

    public void setGrandfatherId(String grandfatherId) {
        this.grandfatherId = grandfatherId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", areaId='" + areaId + '\'' +
                ", fatherId='" + fatherId + '\'' +
                ", grandfatherId='" + grandfatherId + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
